package br.com.alfac.food.core.application.cliente.adapters.presenter;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteError;

import java.util.Optional;

public final class ClientePresenter {

    private ClientePresenter() {
    }

    public static ClienteDTO mapearParaClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setId(cliente.getId());
        clienteDTO.setUuId(cliente.getUuid());
        return clienteDTO;
    }


}
