package br.com.alfac.food.api.adapter.cliente.driver.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.ports.ClienteService;
import br.com.alfac.food.core.domain.cliente.Cliente;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/por-cpf/{cpf}")
    public ClienteDTO consultarCliente(@PathVariable String cpf){
        return clienteService.consultarClientePorCpf(cpf);
    }

    @PostMapping
    public void cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        clienteService.cadastrarCliente(clienteDTO);
    }    

}
