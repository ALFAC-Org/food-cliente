package br.com.alfac.food.core.application.cliente.adapters.controller;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.application.cliente.adapters.presenter.ClientePresenter;
import br.com.alfac.food.core.application.cliente.usecases.CadastrarClienteUseCase;
import br.com.alfac.food.core.application.cliente.usecases.ConsultarClientePorCpfUseCase;
import br.com.alfac.food.core.application.cliente.usecases.ConsultarClientePorIdUseCase;
import br.com.alfac.food.core.application.cliente.usecases.ConsultarClientePorUuidUseCase;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;

import java.util.UUID;

public class ControladorCliente {

    private final RepositorioClienteGateway repositorioClienteGateway;

    public ControladorCliente(final RepositorioClienteGateway repositorioClienteGateway) {
        this.repositorioClienteGateway = repositorioClienteGateway;
    }

    public ClienteDTO consultarClientePorCpf(String cpf) throws FoodException {
        ConsultarClientePorCpfUseCase consultarClientePorCpfUseCase = new ConsultarClientePorCpfUseCase(this.repositorioClienteGateway) {
        };
        Cliente cliente = consultarClientePorCpfUseCase.execute(cpf);
        return ClientePresenter.mapearParaClienteDTO(cliente);
    }

    public ClienteDTO consultarClientePorId(Long id) throws FoodException {
        ConsultarClientePorIdUseCase consultarClientePorId = new ConsultarClientePorIdUseCase(this.repositorioClienteGateway);
        Cliente cliente = consultarClientePorId.execute(id);

        return ClientePresenter.mapearParaClienteDTO(cliente);
    }

    public ClienteDTO consultarClientePorUuid(UUID id) throws FoodException {
        ConsultarClientePorUuidUseCase consultarClientePorUuid = new ConsultarClientePorUuidUseCase(this.repositorioClienteGateway);
        Cliente cliente = consultarClientePorUuid.execute(id);

        return ClientePresenter.mapearParaClienteDTO(cliente);
    }

    public ClienteDTO cadastrarCliente(ClienteDTO cliente) {
        CadastrarClienteUseCase cadastrarClienteUseCase = new CadastrarClienteUseCase(this.repositorioClienteGateway);
        Cliente clienteCadastrado = cadastrarClienteUseCase.execute(cliente);
        return ClientePresenter.mapearParaClienteDTO(clienteCadastrado);
    }
}
