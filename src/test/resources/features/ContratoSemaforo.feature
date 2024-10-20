# language: pt
@regressivo
Funcionalidade: Cadastro de novo semaforo
  Como usuário da API
  Quero cadastrar um novo semaforo
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de semaforo
    Dado que eu tenha os seguintes dados do semaforo:
      | campo            | valor                  |
      | lgLogradouro     | Rua do Limoeiro        |
      | lgNumero         | 2342                   |
      | lgCidade         | Sao Paulo              |
      | lgEstado         | Sao Paulo              |
      | tmTempoVerde     | 180                    |
      | tmTempoAmarelo   | 10                     |
      | tmTempoVeremelho | 90                     |
    Quando eu enviar a requisição para o endpoint "/api/semaforos" de cadastro de semaforos
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido de semaforo"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado