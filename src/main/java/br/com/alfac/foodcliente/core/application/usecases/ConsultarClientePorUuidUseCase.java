package br.com.alfac.foodcliente.core.application.usecases;

import java.util.Optional;
import java.util.UUID;

import br.com.alfac.foodcliente.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.domain.cliente.Cliente;
import br.com.alfac.foodcliente.core.exception.FoodException;
import br.com.alfac.foodcliente.core.exception.cliente.ClienteError;

public class ConsultarClientePorUuidUseCase {

    private final RepositorioClienteGateway clienteRepository;

    public ConsultarClientePorUuidUseCase(final RepositorioClienteGateway clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente execute(UUID uuid) throws FoodException {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorUuId(uuid);

        return clienteOpt.orElseThrow(() -> new FoodException(ClienteError.CLIENTE_NAO_ENCONTRADO));
    }
}
