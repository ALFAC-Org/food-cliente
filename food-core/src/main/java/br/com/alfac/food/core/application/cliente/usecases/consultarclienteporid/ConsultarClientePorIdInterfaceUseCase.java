package br.com.alfac.food.core.application.cliente.usecases.consultarclienteporid;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.exception.FoodException;

public interface ConsultarClientePorIdInterfaceUseCase {
    ClienteDTO execute(Long id) throws FoodException;
}