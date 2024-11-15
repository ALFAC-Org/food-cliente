package br.com.alfac.food.infra.gateways;

import br.com.alfac.food.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.infra.mapper.ClienteEntityMapper;
import br.com.alfac.food.infra.persistence.ClienteEntity;
import br.com.alfac.food.infra.persistence.ClienteEntityRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RepositorioClienteMySQLGatewayImpl implements RepositorioClienteGateway {
    
    private final ClienteEntityRepository clienteEntityRepository;

    public RepositorioClienteMySQLGatewayImpl(final ClienteEntityRepository clienteEntityRepository) {
        this.clienteEntityRepository = clienteEntityRepository;
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
        ClienteEntity clienteEntity = ClienteEntityMapper.INSTANCE.toEntity(cliente);
        clienteEntity.setUuid(UUID.randomUUID());

        ClienteEntity clienteCriado = clienteEntityRepository.save(clienteEntity);

        return ClienteEntityMapper.INSTANCE.toDomain(clienteCriado);
    }

    private Optional<Cliente> montarCliente(Optional<ClienteEntity> clienteEntityOpt) {
        Optional<Cliente> clienteOpt = Optional.empty();

        if (clienteEntityOpt.isPresent()) {
            ClienteEntity clienteEntity = clienteEntityOpt.get();

            Cliente cliente = ClienteEntityMapper.INSTANCE.toDomain(clienteEntity);

            clienteOpt = Optional.of(cliente);
        }

        return clienteOpt;
    }
}