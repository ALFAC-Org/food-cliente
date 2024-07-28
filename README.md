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

- [Desenho da arquitetura](https://miro.com/app/board/uXjVKZNCxxM=/?moveToWidget=3458764595480615411&cot=10)
- [Requisitos da infraestrutura](https://drive.google.com/file/d/1SdsSAvb8gIy9qvau1m_bTNp2WUR5uzds/view?usp=sharing)

Veja em: [https://miro.com/app/board/uXjVKZNCxxM](https://miro.com/app/board/uXjVKZNCxxM=/?share_link_id=127959473892)

> [!WARNING]  
> **Essa documentação foca na 2° FASE do Tech Challenge - usando Kubernetes como base para a aplicação. Se precisar, consulte o README.md da 1° FASE no link: https://github.com/ALFAC-Org/food/tree/hexagonal**

## Tabela de conteúdos

- [Tecnologia](#tecnologia)
- [Requisitos](#requisitos)
- [Arquitetura](#arquitetura)
  - [Visão Geral](#visão-geral)
    - [Vídeo - Arquitetura da aplicação: Kubernetes + AWS](https://drive.google.com/file/d/1wuyAu3_Hne0w3iy7KY5_TZ-NDytB4kTw/view?usp=sharing)
  - [Microsserviços](#microsserviços)
  - [Visão Geral](#visão-geral)
- [Executando a aplicação](#executando-a-aplicação)
  - [Localmente](#localmente)
  - [Na nuvem (AWS)](#na-nuvem-aws)
- [Fluxo do usuário](#fluxo-do-usuário)
  - [1. Se identificando (opcional)](#1-se-identificando-opcional)
  - [2. Montando o `payload` com os itens do seu pedido](#2-montando-o-payload-com-os-itens-do-seu-pedido)
  - [3. Registrando o seu pedido](#3-registrando-o-seu-pedido)
  - [4. Realizando o pagamento](#4-realizando-o-pagamento)
  - [5. Avançando o status do pedido (fila)](#5-avançando-o-status-do-pedido-fila)
  - [6. Pedido sendo finalizado](#6-pedido-sendo-finalizado)
  - [7. Acompanhamento do pedido(simulação de totem)](#7-acompanhamento-do-pedido-simulação-de-totem)
- **Tutoriais (vídeos)**
  - [1. Executando a aplicação - Com Kubernetes - localmente](https://drive.google.com/file/d/1CloOrEDDemPQSZ8cqH2SL5Oh6xoJIAXb/view?usp=sharing)
  - [2. Executando a aplicação - Com Kubernetes - na nuvem (AWS)](https://drive.google.com/file/d/1njxcGlQfmKcCtbqMI9Qf19vxJwZ-MD3D/view?usp=sharing)
  
  **Entenda o fluxo do usuário de uma vez só**

  - [Fluxo inteiro](https://drive.google.com/file/d/1Rr19LOmZgfViqO1NN9MvOKxz2yemDmL_/view?usp=sharing)

  **Entenda o fluxo do usuário por partes**

  - [1. Se identificando (opcional)](https://drive.google.com/file/d/1bju8UWoqZsbBnEKla8-jTX7MMUT_7z_1/view?usp=sharing)
  - [2. Montando o `payload` com os itens do seu pedido](https://drive.google.com/file/d/1U2TRn4kerONNgG21dugjLrfBcWzrxdKT/view?usp=sharing)
  - [3. Pagando o pedido](https://drive.google.com/file/d/1vV3wZzFVNcnvOvxM0MaJ-2rET_7tlazL/view?usp=sharing)
  - [4. Avançando o status do pedido (fila)](https://drive.google.com/file/d/1RdVzS6jiC0mbnTH7vW-2H640A-kYRBht/view?usp=sharing)
  - [5. Consultando pedidos por status (simulando um totem)](https://drive.google.com/file/d/1IFsE6sMsJIG6ymnFMLJtY-f1CoxVwTOF/view?usp=sharing)

## Tecnologia

- **Linguagem de Programação:** Java 17
- **Framework:** Spring Boot
- **Gerenciador de dependências:** Maven
- **Banco de dados:** MySQL 8
- **Documentação e uso de API's:** Swagger
- **Containerização:** Docker
- **Orquestração:** Kubernetes

## Requisitos

- Docker _(versão 27.0.3)_ - para rodar localmente
- Kubernetes _(versão 1.30)_ - para rodar localmente e na nuvem (AWS)

## Arquitetura

### Visão Geral

A aplicação está estruturada no padrão de _Clean Architecture_. A execução pode ser executada tanto via _Docker_ como _Kubernetes_. Podendo ser hospedada tanto localmente ou na nuvem, usando serviços como _AWS_. A interação da aplicação se dá através de _APIs_ com o _Swagger_ disponibilizado.

[Vídeo - Arquitetura da aplicação: Kubernetes + AWS](https://drive.google.com/file/d/1wuyAu3_Hne0w3iy7KY5_TZ-NDytB4kTw/view?usp=sharing)

### Microsserviços

2 microsserviços atendem nessa estrutura:

- backend (`svc-food`)
- banco de dados (`svc-db-food`)

## Executando a aplicação

### Localmente

<details>
  <summary>Passo a passo</summary>

No terminal, execute:

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

</details>

### Na nuvem (AWS)

<details>
  <summary>Passo a passo</summary>

No terminal, execute:

Adicione _configmaps_ e _secrets_:

```bash
kubectl apply -f food/k8s/prod/shared
```

Adicione o driver _aws-ebs-csi-driver_:

```bash
kubectl apply -k "github.com/kubernetes-sigs/aws-ebs-csi-driver/deploy/kubernetes/overlays/stable/?ref=release-1.32"
```

Adicione _banco de dados_:

```bash
kubectl apply -f food/k8s/prod/db
```

Adicionando _backend_:

```bash
kubectl apply -f food/k8s/prod/backend
```

1.4 - (opcional) Adicione _autoscaling_:

```bash
kubectl apply -f food/k8s/prod/autoscaling
```

</details>

## Fluxo do usuário

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

#### Resposta

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

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Item/consultarItensPorCategoria](http://localhost:30001/swagger-ui/index.html#/Item/consultarItensPorCategoria)

#### Via Terminal

Exemplo (pega todos os produtos (itens) disponíveis na categoria de LANCHE):

```bash
curl -X 'GET' \
  'http://localhost:30001/api/v1/itens/por-categoria/LANCHE/itens' \
  -H 'accept: application/json'
```

#### Resposta

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

#### Resposta

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

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Retorno%20Pagamento/consultarPedidoPorStatus](http://localhost:30001/swagger-ui/index.html#/Retorno%20Pagamento/consultarPedidoPorStatus)

#### Via Terminal

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

#### Resposta

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

#### Via Swagger

[http://localhost:30001/swagger-ui/index.html#/Pedido/atualizarStatusPedido](http://localhost:30001/swagger-ui/index.html#/Pedido/atualizarStatusPedido)

#### Via Terminal

```bash
curl -X 'PUT' \
  'http://localhost:30001/api/v1/pedidos/1/atualizar-status' \
  -H 'accept: application/json'
```

#### Resposta

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

[http://localhost:30001/swagger-ui/index.html#/Pedido/consultarPedidoPorStatus_1](http://localhost:30001/swagger-ui/index.html#/Pedido/consultarPedidoPorStatus_1)

#### Via Terminal

```bash
curl -X 'GET' \
  'http://localhost:30001/api/v1/pedidos/status/FINALIZADO' \
  -H 'accept: application/json'
```

Com isso, podemos considerar o fluxo encerrado e que o nosso cliente está feliz com seu lance :) .

</details>

### 7. Acompanhamento do pedido (simulação de totem)

<details>
  <summary>Passo a passo</summary>
Uma vez que o pedido é realizado e pago, ele irá automaticamente ser atualizado para `RECEBIDO`. Com isso, podemos ver o seu progresso, simulando o totem.

É possível acompanhar o pedido entre os status: `RECEBIDO`, `EM_PREPARACAO` e `PRONTO`.

Para isso, é necessário seguir a ordem: [registrando o seu pedido](#3-registrando-o-seu-pedido), [realizando o pagamento](#4-realizando-o-pagamento)
e por fim [avançando o status do pedido (fila)](#5-avançando-o-status-do-pedido-fila).

[Veja este vídeo de demonstração para melhor entendimento](https://drive.google.com/file/d/1ZpjbJ1gIHJ5RgNASV-KS6is5Nw9I788H/view?usp=sharing)
  </summary>
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
  - [x] - i. Os requisitos do negócio (problema).
  - [x] - ii. Os requisitos de infraestrutura:
    - Você pode utilizar o MiniKube, Docker Kubernetes, AKS, EKS, GKE ou qualquer nuvem que você desenha.
- [x] Collection com todas as APIs desenvolvidas:
  - [x] i. Link do Swagger no projeto ou link para download da collection do Postman (JSON).
- [x] Guia completo com todas as instruções para execução do projeto e a ordem de execução das APIs, caso seja necessário;
- [x] Link para vídeo demonstrando a arquitetura desenvolvida na nuvem ou localmente.

#### Melhorias (identificadas pelo time)

- [ ] Quando tentar deletar um item que está sendo usando em um pedido, devemos tratar melhor a mensagem de erro;
- [ ] Quando criar um novo item, podemos ter uma uk no nome + categoria para não deixar criar um item com nome e categoria igual a um item que já existe;
- [x] Em todas as tabelas termos: `data_cadastro`, `data_atualizacao`;
- [x] Atualizar a aplicação desenvolvida na FASE 1 refatorando o código para seguir os padrões clean code e clean architecture.

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
