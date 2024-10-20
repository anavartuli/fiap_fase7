package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import model.ErrorMessageModel;
import org.junit.Assert;
import services.CadastroSemaforoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroSemaforoSteps {

    CadastroSemaforoService cadastroSemaforoService = new CadastroSemaforoService();

    @Dado("que eu tenha os seguintes dados do semaforo:")
    public void queEuTenhaOsSeguintesDadosDoSemaforo(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            cadastroSemaforoService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));
        }
    }

//    @Dado("que eu tenha os seguintes dados do semaforo:")
//    public void queEuTenhaOsSeguintesDadosDoSemaforo(List<Map<String, String>> rows) {
//
//        for(Map<String, String> columns : rows) {
//            cadastroSemaforoService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));
//        }
//
//    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de semaforos")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeSemaforos(String endPoint) {

        cadastroSemaforoService.createDelivery(endPoint);

    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {

        Assert.assertEquals(statusCode, cadastroSemaforoService.response.statusCode());

    }

    @E("o corpo de resposta de erro da api deve retornar a o JSON: {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAOJSON(String jsonResposta) {
        ErrorMessageModel errorMesageModel = cadastroSemaforoService.gson.fromJson(
                cadastroSemaforoService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(jsonResposta, cadastroSemaforoService.response.asString());
    }

    @Dado("que eu recupere o ID do semaforo no contexto")
    public void queEuRecupereOIDDoSemaforoNoContexto() {
        cadastroSemaforoService.retrieveIdSemaforo();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de semaforo")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeSemaforo(String endPoint) {
        cadastroSemaforoService.deleteSemaforo(endPoint);
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        cadastroSemaforoService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroSemaforoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

}
