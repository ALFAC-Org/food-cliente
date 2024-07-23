package br.com.alfac.food.config;

import br.com.alfac.food.database.cliente.gateways.ClienteRepository;
import br.com.alfac.food.database.cliente.mapper.ClienteEntityMapper;
import br.com.alfac.food.database.cliente.persistence.ClienteEntityRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public ClienteRepository clienteRepository(ClienteEntityRepository clienteEntityRepository, ClienteEntityMapper clienteMapper) {
        return new ClienteRepository(clienteEntityRepository, clienteMapper);
    }

}
