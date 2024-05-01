package br.com.alfac.food.core.application.cliente.services;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.application.cliente.ports.ClienteService;
import br.com.alfac.food.core.domain.cliente.Cliente;

public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO consultarClientePorCpf(String cpf){
        Cliente cliente = clienteRepository.consultarCliente();
        return new ClienteDTO();
    }

    public void cadastrarCliente(ClienteDTO clienteDTO){
        //Converte ClienteDTO para Cliente
        Cliente cliente = null;

        clienteRepository.cadastrarCliente(cliente);
    }

}
