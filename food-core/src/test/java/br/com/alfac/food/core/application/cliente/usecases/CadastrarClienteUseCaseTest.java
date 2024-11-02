package br.com.alfac.food.core.application.cliente.usecases;

import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.domain.cliente.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarClienteUseCaseTest {

    @Mock
    private RepositorioClienteGateway cadastroClienteGateway;

    @Test
    void deveDevolverUmCliente() {
        //when(cadastroClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(new Cliente());
        Cliente execute = new CadastrarClienteUseCase(cadastroClienteGateway).execute(new ClienteDTO());
        assertNotNull(execute);

    }


    @Test
    void execute() {
        when(cadastroClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(new Cliente());
        Cliente execute = new CadastrarClienteUseCase(cadastroClienteGateway).execute(new ClienteDTO());
        assertNotNull(execute);

    }
}