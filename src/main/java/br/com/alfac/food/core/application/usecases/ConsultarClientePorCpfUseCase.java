package br.com.alfac.food.core.application.usecases;

import br.com.alfac.food.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteError;

import java.util.Optional;

public class ConsultarClientePorCpfUseCase {
    private final RepositorioClienteGateway clienteRepository;

    public ConsultarClientePorCpfUseCase(final RepositorioClienteGateway clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public Cliente execute(String cpf) throws FoodException {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorCPF(cpf);

        return clienteOpt.orElseThrow(() -> new FoodException(ClienteError.CLIENTE_NAO_ENCONTRADO));

    }
}
