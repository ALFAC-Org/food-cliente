package br.com.alfac.food.database.cliente.repository;

import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.database.cliente.entity.ClienteEntity;

public class ClienteRepositoryImpl implements ClienteRepository {

    public Cliente consultarCliente(){
        //Recupera o ClienteEntity do SpringClienteRepository
        ClienteEntity clienteEntity = null;

        //Converte o ClienteEntity para Cliente
        return new Cliente();
    }

    public void cadastrarCliente(Cliente cliente){
        //Converte o Cliente para ClienteEntity
        ClienteEntity clienteEntity = null;

        //Chama o SpringClienteRepository passando o ClienteEntity para persistencia
    }

}
