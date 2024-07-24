package br.com.alfac.food.infra.config.beans;

import br.com.alfac.food.core.application.cliente.adapters.controller.ControladorCliente;
import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfiguration {



    @Bean(name = "controladorClientePostgres")
    public ControladorCliente controladorClientePostgres(final RepositorioClienteGateway repositorioClienteGatewayMySQL ) {
        return new ControladorCliente(repositorioClienteGatewayMySQL);
    }

    @Bean(name = "controladorClienteMemoria")
    public ControladorCliente controladorClienteMemoria(final RepositorioClienteGateway repositorioClienteGatewayMemoria ) {
        return new ControladorCliente(repositorioClienteGatewayMemoria);
    }

}
