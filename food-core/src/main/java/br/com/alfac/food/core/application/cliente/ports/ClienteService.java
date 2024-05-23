package br.com.alfac.food.core.application.cliente.ports;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.exception.FoodException;

import java.util.UUID;

public interface ClienteService {
    
    ClienteDTO consultarClientePorCpf(String cpf) throws FoodException;

    ClienteDTO consultarClientePorUuid(UUID id) throws FoodException;
    
    ClienteDTO consultarClientePorId(Long id) throws FoodException;

    ClienteDTO cadastrarCliente(ClienteDTO cliente);
    
}
