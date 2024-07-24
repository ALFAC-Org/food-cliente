# Aplicação Fast Food - ALFAC

Esta é uma aplicação que tem por objetivo, fornecer uma plataforma de pedidos de fast food. A plataforma permite aos clientes seguir o fluxo comum de um pedido: escolher o lanche com seu complemento, acompanhamento, bebida e sobremesa.

Ao final, o cliente irá realizar o pagamento deste pedido, através de um QR Code e por fim, receber seu pedido.

Para isso, o consumidor desta plataforma deve seguir o fluxo estabelecido na imagem a seguir:

![Fluxo básico da aplicação](docs/flow.png)

## Fluxo completo no MIRO

### Fase 1

- Brain Storming
- Event Storming
- Fluxo Vertical
- Linguagem Ubíqua

### Fase 2

- <span style="color:red">Desenho da arquitetura</span>
- <span style="color:red">Requisitos da infraestrutura</span>

Veja em: [https://miro.com/app/board/uXjVKZNCxxM](https://miro.com/app/board/uXjVKZNCxxM=/?share_link_id=127959473892)

> [!WARNING]  
> **Essa documentação foca na 2° FASE do Tech Challenge - usando Kubernetes como base para a aplicação. Se precisar, consulte o README.md da 1° FASE no link: https://github.com/ALFAC-Org/food/tree/hexagonal**


## Tabela de conteúdos
- [Tecnologia](#tecnologia)
- [Requisitos](#requisitos)
- [Executando a aplicação](#executando-a-aplicação)
- [Realizando o pedido](#realizando-o-pedido)
  - [1. Se identificando (opcional)](#1-se-identificando-opcional)
  - [2. Montando o `payload` com os itens do seu pedido](#2-montando-o-payload-com-os-itens-do-seu-pedido)
  - [3. Registrando o seu pedido](#3-registrando-o-seu-pedido)
- Pagamento do pedido
  - [4. Realizando o pagamento](#4-realizando-o-pagamento)
- Movendo pedido na fila
  - [5. Avançando o status do pedido (fila)](#5-avançando-o-status-do-pedido-fila)
- Encerramento do fluxo
  - [6. Pedido sendo finalizado](#6-pedido-sendo-finalizado)
- **Tutoriais (vídeos)**
  - [1. Executando a aplicação - Com Kubernetes - localmente](https://drive.google.com/file/d/1CloOrEDDemPQSZ8cqH2SL5Oh6xoJIAXb/view?usp=sharing)
  - [2. Executando a aplicação - Com Kubernetes - na nuvem (AWS)](https://drive.google.com/file/d/1njxcGlQfmKcCtbqMI9Qf19vxJwZ-MD3D/view?usp=sharing)
  
  **Entenda tudo de uma única vez**

  - [2. Fluxo inteiro](https://drive.google.com/file/d/1VTyIiK2U3QD6bJR_qfqcXjnM9rrR28b-/view?usp=sharing)

  **Entenda o fluxo por partes**

  - [2. Se identificando (opcional)](https://drive.google.com/file/d/1bju8UWoqZsbBnEKla8-jTX7MMUT_7z_1/view?usp=sharing)
  - [3. Montando o `payload` com os itens do seu pedido](https://drive.google.com/file/d/1U2TRn4kerONNgG21dugjLrfBcWzrxdKT/view?usp=sharing)
  - [4. Pagando o pedido](https://drive.google.com/file/d/1vV3wZzFVNcnvOvxM0MaJ-2rET_7tlazL/view?usp=sharing)
  - [5. Atualizando status do pedido](https://drive.google.com/file/d/1RdVzS6jiC0mbnTH7vW-2H640A-kYRBht/view?usp=sharing)
  - [6. Consultando pedidos por status (simulando um totem)](https://drive.google.com/file/d/1IFsE6sMsJIG6ymnFMLJtY-f1CoxVwTOF/view?usp=sharing)

## Tecnologia

- Java 17 - _backend_
- Maven - _gerenciar dependências do backend_
- MySQL 8 - _banco de dados_
- Swagger - _documentação e uso de API's_
- Docker - _containerização da aplicação_
- Kubernetes - _orquestração da aplicação_

## Requisitos

- Docker _(versão 27.0.3)_ - para rodar localmente
- Kubernetes _(versão 1.30)_ - para rodar localmente e na nuvem (AWS)

## Executando a aplicação

Adicione _configmaps_ e _secrets_:

```bash
kubectl apply -f food/k8s/dev/shared
```

Adicione _banco de dados_:

```bash
kubectl apply -f food/k8s/dev/db
```

Adicionando _backend_:

```bash
kubectl apply -f food/k8s/dev/backend
```

1.4 - (opcional) Adicione _autoscaling_:

```bash
kubectl apply -f food/k8s/dev/autoscaling
```

# Realizando o pedido

Uma vez a aplicação rodando, é necessário acessar o `Swagger` da aplicação pelo navegador:

* [http://localhost:30001/api-docs](http://localhost:30001/api-docs) 
* ou [http://localhost:30001/swagger-ui/index.html](http://localhost:30001/swagger-ui/index.html)


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

### 1. Se identificando (opcional)

<details>
  <summary>Passo a passo</summary>

Seguindo o cenário feliz, faça o cadastro do seu cliente. E com o id que irá retornar da `response`, você irá utilizá-lo nas etapas seguintes.

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Cliente/cadastrarCliente](http://localhost:30001/swagger-ui/index.html#/Cliente/cadastrarCliente)

#### Via Terminal

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

```bash
{
  "nome": "Nome do cliente",
  "cpf": null,
  "email": "email@provedor.com",
  "id": "92190798-aa89-4d7d-91f2-1e155688cbcd"
  "uuid": null
}
```

Com isso, você terá seu cliente cadastrado.
</details>

### 2. Montando o `payload` com os itens do seu pedido

<details>
  <summary>Passo a passo</summary>

Você precisa escolher os itens que deseja.

Onde `CATEGORIA`:

- `LANCHE`;
- `COMPLEMENTO`;
- `ACOMPANHAMENTO`;
- `BEBIDA`;
- `SOBREMESA`;

Para consultar os itens disponíveis:

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Item/consultarItensPorCategoria](http://localhost:30001/swagger-ui/index.html#/Item/consultarItensPorCategoria)

#### Via Terminal

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

```
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

### 3. Registrando o seu pedido

<details>
  <summary>Passo a passo</summary>

Envie o `payload` para o pedido ser registrado:

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Pedido/criarPedido](http://localhost:30001/swagger-ui/index.html#/Pedido/criarPedido)

#### Via Terminal

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

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Pagamento/pagar](http://localhost:30001/swagger-ui/index.html#/Pagamento/pagar)

#### Via Terminal

```bash
curl -X 'POST' \
  'http://localhost:8081/api/v1/pagamento' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "idPedido": 1
}'
```

### Resposta

```json
{
  "idPedido": 1,
  "realizado": true,
  "statusPedido": "RECEBIDO"
}
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

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Pedido/atualizarStatusPedido](http://localhost:30001/swagger-ui/index.html#/Pedido/atualizarStatusPedido)

#### Via Terminal

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

### 6. Pedido sendo finalizado

<details>
  <summary>Passo a passo</summary>

Uma vez que o pedido chegou ao status de `FINALIZADO`, consideramos que o cliente recebeu o mesmo e que assim, podemos verificar todos os itens finalizados.

Para isso, podemos listar os pedidos finalizados:

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Pedido/listarPedidos](http://localhost:30001/swagger-ui/index.html#/Pedido/listarPedidos)

#### Via Terminal

```bash
curl -X 'GET' \
  'http://localhost:30001/api/v1/pedidos/status/FINALIZADO' \
  -H 'accept: application/json'
```

Com isso, podemos considerar o fluxo encerrado e que o nosso cliente está feliz com seu lance :) .

</details>

## Roadmap

<details>
  <summary>FASE 1</summary>

Veja em https://github.com/ALFAC-Org/food/tree/hexagonal#roadmap

</details>

<details>
  <summary>FASE 2</summary>

#### Alterar/criar as APIs

- [x] Checkout do pedido: deverá receber os produtos solicitados e retornar a identificação do pedido;
- [x] Consultar status do pagamento do pedido: informando se o pagamento foi aprovado ou não (incluindo cancelado).
- [x] Webhook: para receber confirmação de pagamento aprovado ou recusado;
- [x] A lista de pedidos deverá retorná-los com suas descrições, ordenados com a seguinte regra:
  - [x] 1. Pronto > Em Preparação > Recebido;
  - [x] 2. Pedidos mais antigos primeiro e mais novos depois;
  - [x] 3. Pedidos com status Finalizado não devem aparecer na lista.
- [x] Atualizar o status do pedido;
- ~~[ ] Como desafio extra (opcionalmente), você pode implementar a integração com Mercado Pago para gerar o QRCode para pagamento e integrar com o WebHook para capturar os pagamentos.~~
- [x] Caso contrário, será necessário realizar o mock da parte de pagamentos. Como referência, acesse: https://www.mercadopago.com.br/developers/pt/docs/qr-code/integration-configuration/qr-dynamic/integration.

#### Documentação

- [x] Atualização de README;
- [ ] Desenho da arquitetura pensado por você, pessoa arquiteta de software, contemplando:
  - [x] - i. Os requisitos do negócio (problema). <span style="color:red">(Colocar o link do diagrama que o @Leonardo FIAP) (Podemos usar o Miro que fizemos da primeira vez)</span>
  - [x] - ii. Os requisitos de infraestrutura:
    - Você pode utilizar o MiniKube, Docker Kubernetes, AKS, EKS, GKE ou qualquer nuvem que você desenha.
- [x] Collection com todas as APIs desenvolvidas:
  - [x] i. Link do Swagger no projeto ou link para download da collection do Postman (JSON).
- [ ] Guia completo com todas as instruções para execução do projeto e a ordem de execução das APIs, caso seja necessário;
- [ ] Link para vídeo demonstrando a arquitetura desenvolvida na nuvem ou localmente.

#### Melhorias

- [ ] Quando tentar deletar um item que está sendo usando em um pedido, devemos tratar melhor a mensagem de erro;
- [ ] Quando criar um novo item, podemos ter uma uk no nome + categoria para não deixar criar um item com nome e categoria igual a um item que já existe;
- [x] Como padrão:
  - [x] em todas as tabelas termos: `data_cadastro`,  `data_atualizacao`;
- [x]   Atualizar a aplicação desenvolvida na FASE 1 refatorando o código para seguir os padrões clean code e clean architecture.

</details>

## Entregas

- FASE 1 - **28/05/2024** - **<span style="color:green">FEITO</span>**
- FASE 2 - **30/07/2024** - **<span style="color:red">AGUARDANDO</span>**

## Membros

|Membro| Informações |
|--|--|
| Leonardo Fraga | - *RM354771* <br />- *[rm354771@fiap.com.br](mailto:rm354771@fiap.com.br)* <br />- [@LeonardoFraga](https://github.com/LeonardoFraga) |
| Carlos Henrique Carvalho de Santana | - *RM355339* <br />-  *[rm355339@fiap.com.br](mailto:rm355339@fiap.com.br)* <br />- [@carlohcs](https://github.com/carlohcs) |
| Leonardo Alves Campos | - *RM355568* <br />- [rm355568@fiap.com.br](mailto:rm355568@fiap.com.br) <br />- [@lcalves](https://github.com/lcalves) |
| Andre Musolino | -  *RM355582* <br />- *[rm355582@fiap.com.br](mailto:rm355582@fiap.com.br)* <br />- [@amusolino](https://github.com/amusolino) |
