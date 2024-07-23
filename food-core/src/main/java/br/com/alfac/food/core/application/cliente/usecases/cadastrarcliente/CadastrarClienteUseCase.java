package br.com.alfac.food.core.application.cliente.usecases.cadastrarcliente;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.gateways.ClienteRepositoryInterface;
import br.com.alfac.food.core.application.cliente.mappers.ClienteMapper;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.cliente.vo.CPF;

public class CadastrarClienteUseCase implements CadastrarClienteInterfaceUseCase {

    private final ClienteRepositoryInterface clienteRepository;

    public CadastrarClienteUseCase(final ClienteRepositoryInterface clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO execute(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        CPF cpf = new CPF(clienteDTO.getCpf());

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cpf);
        cliente.setEmail(clienteDTO.getEmail());

        Cliente clienteSalvo = clienteRepository.cadastrarCliente(cliente);
        return ClienteMapper.mapearParaClienteDTO(clienteSalvo);
    }
}
