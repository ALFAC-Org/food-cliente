package br.com.alfac.foodcliente.bdd;

import br.com.alfac.foodcliente.core.domain.Cliente;
import br.com.alfac.foodcliente.core.domain.vo.CPF;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import br.com.alfac.foodcliente.utils.ClienteHelper;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class PassosClienteTest {

    private Response response;

    private Cliente clienteResponse;

    private String FULL_ENDPOINT_CLIENTES;

    @Value("${server.port}")
    private String applicationPort;

    @PostConstruct
    public void init() {
        FULL_ENDPOINT_CLIENTES = "http://localhost:" + applicationPort + "/api/v1/clientes";
    }

    @Quando("criar um novo cliente")
    public void criarNovoCliente() {
        var clienteRequest = ClienteHelper.criarClienteRequest();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteRequest)
                .when().post(FULL_ENDPOINT_CLIENTES);

        if (response.getStatusCode() == HttpStatus.CREATED.value()) {
            response.then().body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
            clienteResponse = response.then().extract().as(Cliente.class);
        } else {
            throw new RuntimeException("Failed to create client: " + response.getBody().asString());
        }
    }

    @Então("o cliente é registrado com sucesso")
    public void clienteRegistradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
    }

    @Dado("que um cliente já foi cadastrado")
    public void clienteJaCadastrado() {
        var clienteRequest = ClienteHelper.criarClienteRequest();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteRequest)
                .when().post(FULL_ENDPOINT_CLIENTES);

        clienteResponse = response.then().extract().as(Cliente.class);
        clienteResponse.setCpf(new CPF(clienteRequest.getCpf()));
    }

    @Quando("requisitar a busca do cliente pelo CPF")
    public void requisitarBuscarClientePorCPF() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(FULL_ENDPOINT_CLIENTES + "/por-cpf/{cpf}", clienteResponse.getCpf().getNumero());
    }

    @Quando("requisitar a busca do cliente pelo ID")
    public void requisitarBuscarClientePorID() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(FULL_ENDPOINT_CLIENTES + "/{id}", clienteResponse.getId());
    }

    @Quando("requisitar a busca do cliente pelo UUID")
    public void requisitarBuscarClientePorUUID() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(FULL_ENDPOINT_CLIENTES + "/por-uuid/{uuid}", clienteResponse.getUuid());
    }

    @Então("o cliente é exibido com sucesso")
    public void clienteExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
    }

    @Quando("requisitar a busca de um cliente inexistente pelo ID")
    public void requisitarBuscarClienteInexistentePorID() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(FULL_ENDPOINT_CLIENTES + "/{id}", 9999); // ID inexistente
    }

    @Então("uma mensagem de cliente não encontrado é exibida")
    public void clienteNaoEncontrado() {
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Cliente não encontrado"));
    }
}