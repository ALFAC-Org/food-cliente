package br.com.alfac.food.core.application.cliente.controller;

import java.util.UUID;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.usecases.cadastrarcliente.CadastrarClienteInterfaceUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporcpf.ConsultarClientePorCpfInterfaceUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporid.ConsultarClientePorIdInterfaceUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporuuidu.ConsultarClientePorUuidInterfaceUseCase;
import br.com.alfac.food.core.exception.FoodException;

public class ControladorCliente {

    private final ConsultarClientePorCpfInterfaceUseCase consultarClientePorCpfUseCase;
    private final ConsultarClientePorIdInterfaceUseCase consultarClientePorId;
    private final ConsultarClientePorUuidInterfaceUseCase consultarClientePorUuid;
    private final CadastrarClienteInterfaceUseCase cadastrarClienteUseCase;

    public ControladorCliente(final ConsultarClientePorCpfInterfaceUseCase consultarClientePorCpfUseCase,
            final ConsultarClientePorIdInterfaceUseCase consultarClientePorId,
            final ConsultarClientePorUuidInterfaceUseCase consultarClientePorUuid,
            final CadastrarClienteInterfaceUseCase cadastrarClienteUseCase
    ) {
        this.consultarClientePorCpfUseCase = consultarClientePorCpfUseCase;
        this.consultarClientePorId = consultarClientePorId;
        this.consultarClientePorUuid = consultarClientePorUuid;
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
    }

    public ClienteDTO consultarClientePorCpf(String cpf) throws FoodException {
        return consultarClientePorCpfUseCase.execute(cpf);
    }

    public ClienteDTO consultarClientePorId(Long id) throws FoodException {
        return consultarClientePorId.execute(id);
    }

    public ClienteDTO consultarClientePorUuid(UUID id) throws FoodException {
        return consultarClientePorUuid.execute(id);
    }

    public ClienteDTO cadastrarCliente(ClienteDTO cliente) throws FoodException {
        return cadastrarClienteUseCase.execute(cliente);
    }
}
