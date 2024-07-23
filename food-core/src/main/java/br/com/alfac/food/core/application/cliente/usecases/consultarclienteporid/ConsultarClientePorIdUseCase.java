package br.com.alfac.food.core.application.cliente.usecases.consultarclienteporid;

import java.util.Optional;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.gateways.ClienteRepositoryInterface;
import br.com.alfac.food.core.application.cliente.mappers.ClienteMapper;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;

public class ConsultarClientePorIdUseCase implements ConsultarClientePorIdInterfaceUseCase {
    private final ClienteRepositoryInterface clienteRepository;

    public ConsultarClientePorIdUseCase(final ClienteRepositoryInterface clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO execute(Long id) throws FoodException {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorId(id);

        return ClienteMapper.mapearParaClienteDTO(clienteOpt);
    }
}