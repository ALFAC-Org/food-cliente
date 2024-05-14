package br.com.alfac.food.core.application.cliente.ports;

import br.com.alfac.food.core.domain.cliente.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {

    /**
     * 
     */
    Optional<Cliente> consultarClientePorCPF(String cpf);
    Optional<Cliente> consultarClientePorId(UUID id);

    Cliente cadastrarCliente(Cliente cliente);

}
