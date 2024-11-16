package br.com.alfac.foodcliente.controller;

import br.com.alfac.foodcliente.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.presenter.ClientePresenter;
import br.com.alfac.foodcliente.core.application.dto.ClienteDTO;
import br.com.alfac.foodcliente.core.application.usecases.CadastrarClienteUseCase;
import br.com.alfac.foodcliente.core.application.usecases.ConsultarClientePorCpfUseCase;
import br.com.alfac.foodcliente.core.application.usecases.ConsultarClientePorIdUseCase;
import br.com.alfac.foodcliente.core.application.usecases.ConsultarClientePorUuidUseCase;
import br.com.alfac.foodcliente.core.domain.cliente.Cliente;
import br.com.alfac.foodcliente.core.exception.FoodException;

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
