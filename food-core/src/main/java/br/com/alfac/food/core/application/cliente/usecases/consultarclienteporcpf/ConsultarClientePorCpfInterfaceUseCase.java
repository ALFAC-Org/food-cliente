package br.com.alfac.food.core.application.cliente.usecases.consultarclienteporcpf;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.exception.FoodException;

public interface ConsultarClientePorCpfInterfaceUseCase {
    ClienteDTO execute(String cpf) throws FoodException;
}
