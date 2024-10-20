# language: pt
@regressivo
Funcionalidade: Cadastro de novo semaforo
  Como usuário da API
  Quero cadastrar um novo semaforo
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de semaforo
    Dado que eu tenha os seguintes dados do semaforo:
      | campo            | valor                  |
      | lgLogradouro     | Avenida Rio das Pedras |
      | lgNumero         | 2342                   |
      | lgCidade         | Sao Paulo              |
      | lgEstado         | Sao Paulo              |
      | tmTempoVerde     | 180                    |
      | tmTempoAmarelo   | 10                     |
      | tmTempoVeremelho | 90                     |
    Quando eu enviar a requisição para o endpoint "/api/semaforos" de cadastro de semaforos
    Então o status code da resposta deve ser 201

  Cenário: Cadastro de semaforo sem sucesso ao passar o campo logradouro vazio
    Dado que eu tenha os seguintes dados do semaforo:
      | campo            | valor                  |
      | lgLogradouro     |                        |
      | lgNumero         | 9999                   |
      | lgCidade         | Sao Paulo              |
      | lgEstado         | Sao Paulo              |
      | tmTempoVerde     | 180                    |
      | tmTempoAmarelo   | 10                     |
      | tmTempoVeremelho | 90                     |
    Quando eu enviar a requisição para o endpoint "/api/semaforos" de cadastro de semaforos
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a o JSON: "{\"lgLogradouro\":\"Logradouro do semaforo é obrigatório!\"}"