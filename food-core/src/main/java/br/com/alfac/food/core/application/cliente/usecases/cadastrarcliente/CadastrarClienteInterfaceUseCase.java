package br.com.alfac.food.core.application.cliente.usecases.cadastrarcliente;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;

public interface CadastrarClienteInterfaceUseCase {
    ClienteDTO execute(ClienteDTO cliente);
}
