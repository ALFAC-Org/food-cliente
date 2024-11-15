package br.com.alfac.food.core.application.adapters.presenter;

import br.com.alfac.food.core.application.dto.ClienteDTO;
import br.com.alfac.food.core.domain.cliente.Cliente;

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
