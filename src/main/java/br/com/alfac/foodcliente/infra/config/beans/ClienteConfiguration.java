package br.com.alfac.foodcliente.infra.config.beans;

import br.com.alfac.foodcliente.core.application.adapters.controller.ControladorCliente;
import br.com.alfac.foodcliente.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.infra.gateways.RepositorioClienteMySQLGatewayImpl;
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
