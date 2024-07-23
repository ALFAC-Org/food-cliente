package br.com.alfac.food.core.application.cliente.usecases.consultarclienteporuuidu;

import java.util.Optional;
import java.util.UUID;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.cliente.gateways.ClienteRepositoryInterface;
import br.com.alfac.food.core.application.cliente.mappers.ClienteMapper;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;

public class ConsultarClientePorUuidUseCase implements ConsultarClientePorUuidInterfaceUseCase {

    private final ClienteRepositoryInterface clienteRepository;

    public ConsultarClientePorUuidUseCase(final ClienteRepositoryInterface clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO execute(UUID uuid) throws FoodException {
        Optional<Cliente> clienteOpt = clienteRepository.consultarClientePorUuId(uuid);

        return ClienteMapper.mapearParaClienteDTO(clienteOpt);
    }
}
