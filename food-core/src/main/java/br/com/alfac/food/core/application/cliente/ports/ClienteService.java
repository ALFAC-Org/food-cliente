package br.com.alfac.food.core.application.cliente.ports;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.exception.FoodException;

import java.util.UUID;

public interface ClienteService {
    
    ClienteDTO consultarClientePorCpf(String cpf) throws FoodException;
    ClienteDTO consultarClientePorId(UUID id) throws FoodException;

    UUID cadastrarCliente(ClienteDTO cliente);
    
}
