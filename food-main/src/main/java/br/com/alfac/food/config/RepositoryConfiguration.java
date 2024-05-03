package br.com.alfac.food.config;

import br.com.alfac.food.database.cliente.repository.ClienteEntityRepository;
import br.com.alfac.food.database.cliente.repository.ClienteRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public ClienteRepositoryImpl clienteRepository(ClienteEntityRepository clienteEntityRepository) {
        return new ClienteRepositoryImpl(clienteEntityRepository);
    }

}
