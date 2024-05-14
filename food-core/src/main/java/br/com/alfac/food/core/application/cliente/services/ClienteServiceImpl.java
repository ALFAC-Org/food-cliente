package br.com.alfac.food.core.application.cliente.services;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.mappers.ClienteMapper;
import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.application.cliente.ports.ClienteService;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.cliente.vo.CPF;
import br.com.alfac.food.core.exception.FoodException;

import java.util.Optional;
import java.util.UUID;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(final ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO consultarClientePorCpf(String cpf) throws FoodException {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorCPF(cpf);

        return ClienteMapper.mapearParaClienteDTO(clienteOpt);
    }

    public ClienteDTO consultarClientePorId(UUID id) throws FoodException {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorId(id);

        return ClienteMapper.mapearParaClienteDTO(clienteOpt);
    }

    public UUID cadastrarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        CPF cpf = new CPF(clienteDTO.getCpf());

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cpf);
        cliente.setEmail(clienteDTO.getEmail());

        return clienteRepository.cadastrarCliente(cliente).getId();
    }

}
