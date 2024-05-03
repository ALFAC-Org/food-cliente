package br.com.alfac.food.api.adapter.cliente.driver.controller;

import br.com.alfac.food.api.dto.ClienteRequest;
import br.com.alfac.food.core.domain.pedido.entities.Pedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.ports.ClienteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Consultar Cliente pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Examples not found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pedido.class))
            })})
    @GetMapping(value = "/por-cpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO consultarCliente(@PathVariable String cpf) throws Exception {
        return clienteService.consultarClientePorCpf(cpf);
    }

    @Operation(summary = "Cadastrar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado"),
            @ApiResponse(responseCode = "404", description = "Erro ao cadastrar cliente", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pedido.class))
            })})
    @PostMapping
    public void cadastrarCliente(@RequestBody ClienteRequest clienteRequest) {
        ClienteDTO clienteDTO = clienteRequest.toDTO();

        clienteService.cadastrarCliente(clienteDTO);
    }

}
