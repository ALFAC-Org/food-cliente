package br.com.alfac.foodcliente.core.application.usecases;

import br.com.alfac.foodcliente.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.domain.cliente.Cliente;
import br.com.alfac.foodcliente.core.exception.FoodException;
import br.com.alfac.foodcliente.core.exception.cliente.ClienteError;

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
