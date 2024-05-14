# Aplicação Fast Food - ALFAC

Esta é uma aplicação que tem por objetivo, fornecer uma plataforma de pedidos de fast food. A plataforma permite aos clientes seguir o fluxo comum de um pedido: escolher o lanche com seu complemento, acompanhamento, bebida e sobremesa. 

Ao final, o cliente irá realizar o pagamento deste pedido, através de um QR Code e por fim, receber seu pedido.

Para isso, o consumidor desta plataforma deve seguir o fluxo estabelecido na imagem a seguir:

![Fluxo básico da aplicação](docs/basic-flow.jpg)

Fluxo completo no MIRO:

[https://miro.com/app/board/uXjVKZNCxxM=/](https://miro.com/app/board/uXjVKZNCxxM=/)

---

## Tecnologia

- Java 17 - _backend_
- MySQL 8 - _banco de dados_
- Swagger - _documentação e uso de API's_
- Docker - _orquestração da aplicação_
- Maven - _gerenciar dependências do backend_

## Requisitos

- Docker

## Executar a aplicação

    docker-compose up

# Realização do pedido

Uma vez a aplicação rodando, é necessário acessar a página no navegador: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

O fluxo deve ser feito na sequência:

1. Identificação do cliente
2. Escolha do lanche
3. Escolha de acompanhamento
4. Escolha de bebida
5. Escolha de  sobremesa
6. Fecha pedido
7. Faz pagamento
8. Recebe ticket de pedido
9. Recebe pedido
10. Pedido finalizado

## 1. Identificação do cliente

Faça o cadastro do seu cliente. E com o id que irá retornar da response, você irá utilizá-lo nas etapas seguintes.

### Requisição

`POST /api/v1/clientes`

```bash
curl -X 'POST' \
'http://localhost:8080/api/v1/clientes' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
    "nome": "Nome do cliente",
    "email": "email@provedor.com",
    "cpf": "12121212121"
}'
```

### Resposta

    connection: keep-alive 
    content-length: 0 
    date: Tue,14 May 2024 01:50:27 GMT 
    keep-alive: timeout=60 

