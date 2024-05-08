package br.com.alfac.food.core.application.cliente.services;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.application.cliente.ports.ClienteService;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.cliente.vo.CPF;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteErros;

import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(final ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO consultarClientePorCpf(String cpf) throws Exception {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorCPF(cpf);

        Cliente cliente = clienteOpt.orElseThrow(() -> new FoodException(ClienteErros.CLIENTE_NAO_ENCONTRADO));

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());

        return clienteDTO;
    }

    public void cadastrarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        CPF cpf = new CPF(clienteDTO.getCpf());

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cpf);
        cliente.setEmail(clienteDTO.getEmail());

        clienteRepository.cadastrarCliente(cliente);
    }

}
