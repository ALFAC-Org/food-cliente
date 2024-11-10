package br.com.alfac.food.core.application.cliente.usecases;

import java.util.Optional;
import java.util.UUID;

import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteError;

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
