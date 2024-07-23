package br.com.alfac.food.core.application.cliente.usecases.consultarclienteporcpf;

import java.util.Optional;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.gateways.ClienteRepositoryInterface;
import br.com.alfac.food.core.application.cliente.mappers.ClienteMapper;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;

public class ConsultarClientePorCpfUseCase implements ConsultarClientePorCpfInterfaceUseCase {
    private final ClienteRepositoryInterface clienteRepository;

    public ConsultarClientePorCpfUseCase(final ClienteRepositoryInterface clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO execute(String cpf) throws FoodException {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorCPF(cpf);

        return ClienteMapper.mapearParaClienteDTO(clienteOpt);
    }
}
