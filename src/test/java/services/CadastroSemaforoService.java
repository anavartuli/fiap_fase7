package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.SemaforoModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroSemaforoService {

    final SemaforoModel semaforoModel = new SemaforoModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";
    // o end point fica na feature

    String idSemaforo;
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de semaforo" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-semaforo.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException
    {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }

    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "lgLogradouro" -> semaforoModel.setLgLogradouro(value);
            case "lgNumero" -> semaforoModel.setLgNumero(Integer.parseInt(value));
            case "lgCidade" -> semaforoModel.setLgCidade(value);
            case "lgEstado" -> semaforoModel.setLgEstado(value);
            case "tmTempoVerde" -> semaforoModel.setTmTempoVerde(Integer.parseInt(value));
            case "tmTempoAmarelo" -> semaforoModel.setTmTempoAmarelo(Integer.parseInt(value));
            case "tmTempoVeremelho" -> semaforoModel.setTmTempoVeremelho(Integer.parseInt(value));
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void createDelivery(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(semaforoModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveIdSemaforo() {
        idSemaforo = String.valueOf(gson.fromJson(response.jsonPath().prettify(), SemaforoModel.class).getCdSemaforo());
    }

    public void deleteSemaforo(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idSemaforo);
        response = given()
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }
}
