package br.com.alfac.food.core.application.usecases;

import br.com.alfac.food.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.application.dto.ClienteDTO;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.cliente.vo.CPF;

public class CadastrarClienteUseCase {

    private final RepositorioClienteGateway clienteRepository;

    public CadastrarClienteUseCase(final RepositorioClienteGateway clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente execute(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        CPF cpf = new CPF(clienteDTO.getCpf());

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cpf);
        cliente.setEmail(clienteDTO.getEmail());

        return clienteRepository.cadastrarCliente(cliente);
    }
}
