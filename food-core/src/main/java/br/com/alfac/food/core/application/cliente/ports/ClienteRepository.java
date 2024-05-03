package br.com.alfac.food.core.application.cliente.ports;

import br.com.alfac.food.core.domain.cliente.Cliente;

import java.util.Optional;

public interface ClienteRepository {

    /**
     * 
     */
    Optional<Cliente> consultarClientePorCPF(String cpf);

    void cadastrarCliente(Cliente cliente);

}
