# Fluxo do usuário

Uma vez a aplicação rodando, é necessário acessar o `Swagger` da aplicação pelo navegador:

- [http://localhost:30001/api-docs](http://localhost:30001/api-docs)
- ou [http://localhost:30001/swagger-ui/index.html](http://localhost:30001/swagger-ui/index.html)

O fluxo deve ser feito na sequência:

1. Se identificando (opcional)
2. Montando o `payload` do seu pedido, contendo ao menos um dos itens a seguir:
    - Lanche
    - Acompanhamento
    - Bebida
    - Sobremesa
3. Registrando o seu pedido
4. Realizando o pagamento
5. Avançando o status do pedido (fila)
6. Pedido sendo finalizado
7. Acompanhamento do pedido (simulação de totem)

## 1. Se identificando (opcional)

<details>
  <summary>Passo a passo</summary>

Seguindo o cenário feliz, faça o cadastro do seu cliente. E com o id que irá retornar da `response`, você irá utilizá-lo nas etapas seguintes.

### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Cliente/cadastrarCliente](http://localhost:30001/swagger-ui/index.html#/Cliente/cadastrarCliente)

### Via Terminal

`POST http://localhost:30001/api/v1/clientes`

```bash
curl -X 'POST' \
'http://localhost:30001/api/v1/clientes' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
    "nome": "Nome do cliente",
    "email": "email@provedor.com",
    "cpf": "12121212121"
}'
```

### Resposta

```json
{
  "nome": "Nome do cliente",
  "email": "email@provedor.com",
  "id": "1",
  "uuid": "00000000-0000-0000-0000-000000000000"
}
```

Com isso, você terá seu cliente cadastrado.
</details>

### 2. Montando o `payload` com os itens do seu pedido

<details>
  <summary>Passo a passo</summary>

Você precisa escolher os itens que deseja.

Onde `CATEGORIA` deve ser um dos itens:

- `LANCHE`;
- `COMPLEMENTO`;
- `ACOMPANHAMENTO`;
- `BEBIDA`;
- `SOBREMESA`;

Para consultar os itens disponíveis:

### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Item/consultarItensPorCategoria](http://localhost:30001/swagger-ui/index.html#/Item/consultarItensPorCategoria)

### Via Terminal

Exemplo (pega todos os produtos (itens) disponíveis na categoria de LANCHE):

```bash
curl -X 'GET' \
  'http://localhost:30001/api/v1/itens/por-categoria/LANCHE/itens' \
  -H 'accept: application/json'
```

### Resposta

```json
[
  {
    "id": "d649a7fd-16f5-11ef-b59f-0242ac120002",
    "nome": "Hamburguer",
    "preco": 15,
    "categoria": "LANCHE"
  },
  {
    "id": "d649aad0-16f5-11ef-b59f-0242ac120002",
    "nome": "Hot Dog",
    "preco": 10,
    "categoria": "LANCHE"
  },
  ...
]
```

No fim, após escolher todos os itens, monte um objeto com a seguinte estrutura:

```json
{
  "clienteId": 1,
  "combos": [
    {
      "lanche": {
        "id": 15,
        "complementos": [
          {
             "id": 6
          }
        ],
        "observacoes": "Capricha no queijo!"
      },
      "acompanhamento": {
        "id": 8
      },
      "bebida": {
        "id": 11
      },
      "sobremesa": {
        "id": 14
      }
    }
  ]
}
```

O `payload` anterior contempla:

```markdown
Cliente
id: 1
Nome: Joaquim Da Silva

Lanche
id: 15
Nome: Hamburguer

Complemento
id: 6
Nome: Queijo extra

Acompanhamento
id: 8
Nome: Batata Frita

Bebida
id: 11
Nome: Refrigerante

Sobremesa
id: 14
Nome: Sorvete
```

Basta então registrar o pedido, como na próxima etapa.

</details>

## 3. Registrando o seu pedido

<details>
  <summary>Passo a passo</summary>

Envie o `payload` para o pedido ser registrado:

### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Pedido/criarPedido](http://localhost:30001/swagger-ui/index.html#/Pedido/criarPedido)

### Via Terminal

```bash
curl -X 'POST' \
  'http://localhost:30001/api/v1/pedidos' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "clienteId": 1,
  "combos": [
    {
      "lanche": {
        "id": 15,
        "complementos": [
          {
            "id": 6
          }
        ],
        "observacoes": "Capricha no queijo!"
      },
      "acompanhamento": {
        "id": 8
      },
      "bebida": {
        "id": 11
      },
      "sobremesa": {
        "id": 14
      }
    }
  ]
}'
```

### Resposta

```json
{
  "id": 2
}
```
</details>

### 4. Realizando o pagamento

<details>
  <summary>Passo a passo</summary>

Todo pedido realizado começa com status de `Aguardando Pagamento`.

Sendo assim, precisamos realizar o `pagamento` deste pedido.

Podemos tanto `APROVAR` como `REPROVAR` o pagamento.

### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Retorno%20Pagamento/consultarPedidoPorStatus](http://localhost:30001/swagger-ui/index.html#/Retorno%20Pagamento/consultarPedidoPorStatus)

### Via Terminal

```bash
curl -X 'POST' \
  'http://localhost:30001/wh/v1/pagamentos' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "pagamentoId": 1,
  "statusConfirmacaoPagamento": "APROVADO"
}'
```

### Resposta

```json
200 OK
```

Após o pagamento, é necessário avançar o status do pedido na fila. Veja o tópico a seguir.

</details>

### 5. Avançando o status do pedido (fila)

<details>
  <summary>Passo a passo</summary>

Havendo a confirmação do pagamento, precisamos executar a API que irá atualizar o status e mover o pedido no fluxo.

Fluxo da alteração dos status:

- Uma vez que o pedido é realizado: `Aguardando Pagamento`;
  - Executa a API para pagar: `Recebido`;
    - Executa a API para atualizar status: `Em preparação`;
      - Executa a API para atualizar status: `Pronto`;
        - Executa a API para atualizar status: `Finalizado`;
          - Executa a API para atualizar status: recebe a mensagem `Status do pedido já finalizado não permite alteração.`;

### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Pedido/atualizarStatusPedido](http://localhost:30001/swagger-ui/index.html#/Pedido/atualizarStatusPedido)

### Via Terminal

```bash
curl -X 'PUT' \
  'http://localhost:30001/api/v1/pedidos/1/atualizar-status' \
  -H 'accept: application/json'
```

### Resposta

```json
{
  "pedidos": [
    {
      "combos": [
        {
          "lanche": {
            "id": 1,
            "nome": "Hamburguer",
            "preco": 15,
            "categoria": "LANCHE",
            "complementos": [
              {
                "id": 6,
                "nome": "Queijo Extra",
                "preco": 2,
                "categoria": "COMPLEMENTO"
              }
            ],
            "observacoes": "Capricha no queijo!"
          },
          "acompanhamento": {
            "id": 8,
            "nome": "Batata Frita",
            "preco": 5,
            "categoria": "ACOMPANHAMENTO"
          },
          "bebida": {
            "id": 11,
            "nome": "Refrigerante",
            "preco": 4,
            "categoria": "BEBIDA"
          },
          "sobremesa": {
            "id": 14,
            "nome": "Sorvete",
            "preco": 5,
            "categoria": "SOBREMESA"
          }
        }
      ],
      "clienteId": 1,
      "id": 1,
      "statusPedido": "FINALIZADO"
    },
    {
      "combos": [
        {
          "lanche": {
            "id": 1,
            "nome": "Hamburguer",
            "preco": 15,
            "categoria": "LANCHE",
            "complementos": [
              {
                "id": 6,
                "nome": "Queijo Extra",
                "preco": 2,
                "categoria": "COMPLEMENTO"
              }
            ],
            "observacoes": "Capricha no queijo!"
          },
          "acompanhamento": {
            "id": 8,
            "nome": "Batata Frita",
            "preco": 5,
            "categoria": "ACOMPANHAMENTO"
          },
          "bebida": {
            "id": 11,
            "nome": "Refrigerante",
            "preco": 4,
            "categoria": "BEBIDA"
          },
          "sobremesa": {
            "id": 14,
            "nome": "Sorvete",
            "preco": 5,
            "categoria": "SOBREMESA"
          }
        }
      ],
      "clienteId": 14,
      "id": 14,
      "statusPedido": "FINALIZADO"
    }
  ]
}
```

A atualização deve ser feita até que se chegue ao status de `FINALIZADO`.

</details>

## 6. Pedido sendo finalizado

<details>
  <summary>Passo a passo</summary>

Uma vez que o pedido chegou ao status de `FINALIZADO`, consideramos que o cliente recebeu o mesmo e que assim, podemos verificar todos os itens finalizados.

Para isso, podemos listar os pedidos finalizados:

### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Pedido/consultarPedidoPorStatus_1](http://localhost:30001/swagger-ui/index.html#/Pedido/consultarPedidoPorStatus_1)

### Via Terminal

```bash
curl -X 'GET' \
  'http://localhost:30001/api/v1/pedidos/status/FINALIZADO' \
  -H 'accept: application/json'
```

Com isso, podemos considerar o fluxo encerrado e que o nosso cliente está feliz com seu lance :) .

</details>

## 7. Acompanhamento do pedido (simulação de totem)

<details>
  <summary>Passo a passo</summary>
Uma vez que o pedido é realizado e pago, ele irá automaticamente ser atualizado para `RECEBIDO`. Com isso, podemos ver o seu progresso, simulando o totem.

É possível acompanhar o pedido entre os status: `RECEBIDO`, `EM_PREPARACAO` e `PRONTO`.

Para isso, é necessário seguir a ordem: [registrando o seu pedido](#3-registrando-o-seu-pedido), [realizando o pagamento](#4-realizando-o-pagamento)
e por fim [avançando o status do pedido (fila)](#5-avançando-o-status-do-pedido-fila).

[Veja este vídeo de demonstração para melhor entendimento](https://drive.google.com/file/d/1ZpjbJ1gIHJ5RgNASV-KS6is5Nw9I788H/view?usp=sharing)
  </summary>
</details>
