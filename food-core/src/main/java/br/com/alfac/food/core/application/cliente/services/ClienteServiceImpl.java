package br.com.alfac.food.core.application.cliente.services;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.application.cliente.ports.ClienteService;
import br.com.alfac.food.core.domain.cliente.Cliente;

import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(final ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO consultarClientePorCpf(String cpf) throws Exception {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorCPF(cpf);

        Cliente cliente = clienteOpt.orElseThrow(() -> new Exception("TODO Tratar Exception"));

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        return clienteDTO;
    }

    public void cadastrarCliente(ClienteDTO clienteDTO){
        //Converte ClienteDTO para Cliente
        Cliente cliente = null;

        clienteRepository.cadastrarCliente(cliente);
    }

}
