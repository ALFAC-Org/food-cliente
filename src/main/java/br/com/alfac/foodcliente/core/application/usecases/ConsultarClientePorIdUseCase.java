package br.com.alfac.foodcliente.core.application.usecases;

import java.util.Optional;

import br.com.alfac.foodcliente.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.domain.Cliente;
import br.com.alfac.foodcliente.core.exception.FoodException;
import br.com.alfac.foodcliente.core.exception.cliente.ClienteError;

public class ConsultarClientePorIdUseCase {
    private final RepositorioClienteGateway clienteRepository;

    public ConsultarClientePorIdUseCase(final RepositorioClienteGateway clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente execute(Long id) throws FoodException {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorId(id);

        return clienteOpt.orElseThrow(() -> new FoodException(ClienteError.CLIENTE_NAO_ENCONTRADO));
    }
}