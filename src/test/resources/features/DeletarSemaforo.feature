# language: pt
@regressivo
Funcionalidade: Deletar um semaforo
  Como usuário da API
  Quero conseguir deletar um semaforo
  Para que o registro seja apagado corretamente no sistema
  Contexto: Cadastro bem-sucedido de semaforo
    Dado que eu tenha os seguintes dados do semaforo:
      | campo            | valor                   |
      | lgLogradouro     | Estrada da Prosperidade |
      | lgNumero         | 4444                    |
      | lgCidade         | Sao Paulo               |
      | lgEstado         | Sao Paulo               |
      | tmTempoVerde     | 11                      |
      | tmTempoAmarelo   | 22                      |
      | tmTempoVeremelho | 44                      |
    Quando eu enviar a requisição para o endpoint "/api/semaforos" de cadastro de semaforos
    Então o status code da resposta deve ser 201

  Cenário: Deve ser possível deletar uma entrega
    Dado que eu recupere o ID do semaforo no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/semaforos" de deleção de semaforo
    Então o status code da resposta deve ser 204