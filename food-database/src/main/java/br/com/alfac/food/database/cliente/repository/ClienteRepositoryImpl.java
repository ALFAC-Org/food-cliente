package br.com.alfac.food.database.cliente.repository;

import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.cliente.vo.CPF;
import br.com.alfac.food.database.cliente.entity.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepositoryImpl implements ClienteRepository {


    private final ClienteEntityRepository clienteEntityRepository;

    public ClienteRepositoryImpl(final ClienteEntityRepository clienteEntityRepository) {
        this.clienteEntityRepository = clienteEntityRepository;
    }


    @Override
    public Optional<Cliente> consultarClientePorCPF(String cpf){
        //Recupera o ClienteEntity do SpringClienteRepository

        Optional<Cliente> clienteOpt = Optional.empty();

        Optional<ClienteEntity> clienteEntityOpt = clienteEntityRepository.findByCpf(cpf);

        if (clienteEntityOpt.isPresent()) {
            ClienteEntity clienteEntity = clienteEntityOpt.get();
            Cliente cliente = new Cliente();

            cliente.setCpf(new CPF(clienteEntity.getCpf()));
            cliente.setNome(clienteEntity.getNome());
            cliente.setEmail(clienteEntity.getEmail());

            clienteOpt = Optional.of(cliente);
        }

        return clienteOpt;
    }

    @Override
    public void cadastrarCliente(Cliente cliente){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setCpf(cliente.getCpf().getNumero());
        clienteEntity.setEmail(cliente.getEmail());

        //Chama o SpringClienteRepository passando o ClienteEntity para persistencia
        clienteEntityRepository.save(clienteEntity);
    }

}
