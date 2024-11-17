package br.com.alfac.foodcliente.core.application.adapters.gateways;

import java.util.Optional;
import java.util.UUID;

import br.com.alfac.foodcliente.core.domain.Cliente;

public interface RepositorioClienteGateway {
    Optional<Cliente> consultarClientePorCPF(String cpf);
    Optional<Cliente> consultarClientePorUuId(UUID id);
    Optional<Cliente> consultarClientePorId(Long id);
    Cliente cadastrarCliente(Cliente cliente);
}
