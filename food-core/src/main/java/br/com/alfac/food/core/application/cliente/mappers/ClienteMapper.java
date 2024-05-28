package br.com.alfac.food.core.application.cliente.mappers;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteError;

import java.util.Optional;

public final class ClienteMapper {

    private ClienteMapper() {
    }

    public static ClienteDTO mapearParaClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setId(cliente.getId());
        clienteDTO.setUuId(cliente.getUuid());
        return clienteDTO;
    }


    public static ClienteDTO mapearParaClienteDTO(Optional<Cliente> clienteOpt) throws FoodException {

        Cliente cliente = clienteOpt.orElseThrow(() -> new FoodException(ClienteError.CLIENTE_NAO_ENCONTRADO));

        return mapearParaClienteDTO(cliente);
    }
}
