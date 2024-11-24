package br.com.alfac.foodcliente.bdd;

import br.com.alfac.foodcliente.core.domain.Cliente;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import br.com.alfac.foodcliente.utils.ClienteHelper;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class PassosClienteTest {

    private Response response;
    private Cliente clienteResponse;
    private String ENDPOINT_CLIENTES = "http://localhost:8080/api/v1/clientes";

    @Quando("criar um novo cliente")
    public void criarNovoCliente() {
        var clienteRequest = ClienteHelper.criarClienteDTO();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteRequest)
                .when().post(ENDPOINT_CLIENTES);
        //clienteResponse = response.then().extract().as(Cliente.class);
        if (response.getStatusCode() == HttpStatus.CREATED.value()) {
            response.then().body(matchesJsonSchemaInClasspath("schemas/ClienteSchema.json"));
            clienteResponse = response.then().extract().as(Cliente.class);
        } else {
            throw new RuntimeException("Failed to create client: " + response.getBody().asString());
        }
    }

    @Então("o cliente é registrado com sucesso")
    public void clienteRegistradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
                //.body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
    }

    @Dado("que um cliente já foi cadastrado")
    public void clienteJaCadastrado() {
        var clienteRequest = ClienteHelper.criarClienteDTO();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteRequest)
                .when().post(ENDPOINT_CLIENTES);
        clienteResponse = response.then().extract().as(Cliente.class);
    }

    @Quando("requisitar a busca do cliente pelo CPF")
    public void requisitarBuscarClientePorCPF() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTES + "/por-cpf/{cpf}", clienteResponse.getCpf());
    }

    @Quando("requisitar a busca do cliente pelo ID")
    public void requisitarBuscarClientePorID() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTES + "/{id}", clienteResponse.getId());
    }

    @Quando("requisitar a busca do cliente pelo UUID")
    public void requisitarBuscarClientePorUUID() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTES + "/por-uuid/{uuid}", clienteResponse.getUuid());
    }

    @Então("o cliente é exibido com sucesso")
    public void clienteExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
    }

    @Quando("requisitar a exclusão do cliente")
    public void requisitarExclusaoDoCliente() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(ENDPOINT_CLIENTES + "/{id}", clienteResponse.getId());
    }

    @Então("o cliente é removido com sucesso")
    public void clienteRemovidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo("cliente removido"));
    }


    @Quando("requisitar a busca de um cliente inexistente pelo ID")
    public void requisitarBuscarClienteInexistentePorID() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTES + "/{id}", 9999); // ID inexistente
    }

    @Então("uma mensagem de cliente não encontrado é exibida")
    public void clienteNaoEncontrado() {
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("cliente não encontrado"));
    }
}