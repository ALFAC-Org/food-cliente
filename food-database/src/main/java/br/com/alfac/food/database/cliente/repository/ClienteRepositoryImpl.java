package br.com.alfac.food.database.cliente.repository;

import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.database.cliente.entity.ClienteEntity;
import br.com.alfac.food.database.cliente.mapper.ClienteEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        //Recupera o ClienteEntity do SpringClienteRepository

        Optional<Cliente> clienteOpt = Optional.empty();

        Optional<ClienteEntity> clienteEntityOpt = clienteEntityRepository.findByCpf(cpf);

        if (clienteEntityOpt.isPresent()) {
            ClienteEntity clienteEntity = clienteEntityOpt.get();

            Cliente cliente = clienteEntityMapper.toDomain(clienteEntity);

            clienteOpt = Optional.of(cliente);
        }

        return clienteOpt;
    }

    @Override
    public void cadastrarCliente(Cliente cliente){
        ClienteEntity clienteEntity = clienteEntityMapper.toEntity(cliente);

        //Chama o SpringClienteRepository passando o ClienteEntity para persistencia
        clienteEntityRepository.save(clienteEntity);
    }

}
