package br.com.alfac.food.core.application.cliente.ports;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;

public interface ClienteService {
    
    public ClienteDTO consultarClientePorCpf(String cpf) throws Exception;
    
    public void cadastrarCliente(ClienteDTO cliente);
    
}
