package br.com.alfac.food.core.application.cliente.ports;

import br.com.alfac.food.core.domain.cliente.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {

    /**
     * 
     */
    Optional<Cliente> consultarClientePorCPF(String cpf);
    Optional<Cliente> consultarClientePorUuId(UUID id);
    Optional<Cliente> consultarClientePorId(Long id);

    Cliente cadastrarCliente(Cliente cliente);

}
