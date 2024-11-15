package br.com.alfac.food.core.application.adapters.gateways;

import java.util.Optional;
import java.util.UUID;

import br.com.alfac.food.core.domain.cliente.Cliente;

public interface RepositorioClienteGateway {
    Optional<Cliente> consultarClientePorCPF(String cpf);
    Optional<Cliente> consultarClientePorUuId(UUID id);
    Optional<Cliente> consultarClientePorId(Long id);
    Cliente cadastrarCliente(Cliente cliente);
}
