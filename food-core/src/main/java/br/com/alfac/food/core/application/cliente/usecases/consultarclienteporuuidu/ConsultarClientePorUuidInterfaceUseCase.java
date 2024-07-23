package br.com.alfac.food.core.application.cliente.usecases.consultarclienteporuuidu;

import java.util.UUID;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.exception.FoodException;

public interface ConsultarClientePorUuidInterfaceUseCase {
    ClienteDTO execute(UUID id) throws FoodException;
}