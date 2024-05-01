package br.com.alfac.food.core.application.cliente.ports;

import br.com.alfac.food.core.domain.cliente.Cliente;

public interface ClienteRepository {

    /**
     * 
     */
    public Cliente consultarCliente();

    public void cadastrarCliente(Cliente cliente);

}
