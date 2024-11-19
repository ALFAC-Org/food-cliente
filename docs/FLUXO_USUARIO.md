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