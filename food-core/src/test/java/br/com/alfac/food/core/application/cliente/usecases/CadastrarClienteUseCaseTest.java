package br.com.alfac.food.core.application.cliente.usecases;

import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.domain.cliente.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.ClienteHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarClienteUseCaseTest {

    @Mock
    private RepositorioClienteGateway cadastroClienteGateway;

    @Test
    void deveDevolverUmCliente() {
//        Arrange
        ClienteDTO clienteDTO = ClienteHelper.criarClienteDTO();
        Cliente cliente = ClienteHelper.criarCliente();
        when(cadastroClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(cliente);

//        Act
        Cliente execute = new CadastrarClienteUseCase(cadastroClienteGateway).execute(clienteDTO);

//        Assert
        var clienteRetornado = assertThat(execute)
                .isInstanceOf(Cliente.class);

        clienteRetornado
                .extracting(Cliente::getNome)
                .isEqualTo(cliente.getNome());
        clienteRetornado
                .extracting(Cliente::getEmail)
                .isEqualTo(cliente.getEmail());
        clienteRetornado
                .extracting(Cliente::getCpf)
                .isEqualTo(cliente.getCpf());
    }


    @Test
    void execute() {
//        Arrange
        when(cadastroClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(new Cliente());

//        Act
        Cliente execute = new CadastrarClienteUseCase(cadastroClienteGateway).execute(new ClienteDTO());

//        Assert
        assertNotNull(execute);
    }
}