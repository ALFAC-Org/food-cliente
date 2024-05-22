package br.com.alfac.food.database.cliente.repository;

import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.database.cliente.entity.ClienteEntity;
import br.com.alfac.food.database.cliente.mapper.ClienteEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClienteRepositoryImpl implements ClienteRepository {


    private final ClienteEntityRepository clienteEntityRepository;
    private final ClienteEntityMapper clienteEntityMapper;
    public ClienteRepositoryImpl(final ClienteEntityRepository clienteEntityRepository, final ClienteEntityMapper clienteMapper) {
        this.clienteEntityRepository = clienteEntityRepository;
        this.clienteEntityMapper = clienteMapper;
    }


    @Override
    public Optional<Cliente> consultarClientePorCPF(String cpf){

        Optional<ClienteEntity> clienteEntityOpt = clienteEntityRepository.findByCpf(cpf);

        return montarCliente(clienteEntityOpt);
    }

    @Override
    public Optional<Cliente> consultarClientePorUuId(final UUID id) {
        Optional<ClienteEntity> clienteEntityOpt = clienteEntityRepository.findByUuid(id);
        return montarCliente(clienteEntityOpt);
    }

    @Override
    public Optional<Cliente> consultarClientePorId(final Long id) {
        Optional<ClienteEntity> clienteEntityOpt = clienteEntityRepository.findById(id);
        return montarCliente(clienteEntityOpt);
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente){
        ClienteEntity clienteEntity = clienteEntityMapper.toEntity(cliente);
        clienteEntity.setUuid(UUID.randomUUID());

        ClienteEntity clienteCriado = clienteEntityRepository.save(clienteEntity);

        return clienteEntityMapper.toDomain(clienteCriado);
    }

    private Optional<Cliente> montarCliente(Optional<ClienteEntity> clienteEntityOpt) {
        Optional<Cliente> clienteOpt = Optional.empty();

        if (clienteEntityOpt.isPresent()) {
            ClienteEntity clienteEntity = clienteEntityOpt.get();

            Cliente cliente = clienteEntityMapper.toDomain(clienteEntity);

            clienteOpt = Optional.of(cliente);
        }

        return clienteOpt;
    }

}
