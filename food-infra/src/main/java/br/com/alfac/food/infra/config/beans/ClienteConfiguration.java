package br.com.alfac.food.infra.config.beans;

import br.com.alfac.food.core.application.cliente.adapters.controller.ControladorCliente;
import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.infra.cliente.gateways.RepositorioClienteMySQLGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfiguration {



    @Bean(name = "controladorClienteMySQL")
    public ControladorCliente controladorClienteMySQL(final RepositorioClienteMySQLGatewayImpl repositorioClienteMySQLGateway ) {
        return new ControladorCliente(repositorioClienteMySQLGateway);
    }

    @Bean(name = "controladorClienteMemoria")
    public ControladorCliente controladorClienteMemoria(final RepositorioClienteGateway repositorioClienteGatewayMemoria ) {
        return new ControladorCliente(repositorioClienteGatewayMemoria);
    }

}
