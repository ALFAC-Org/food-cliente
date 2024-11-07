package br.com.alfac.food.core.application.cliente.usecases;

import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.ClienteHelper;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarClientePorCpfUseCaseTest {

    @Mock
    private RepositorioClienteGateway cadastroClienteGateway;

    @Test
    void deveDevolverUmClientePorCpf() throws FoodException {
//        Arrange
        Cliente cliente = ClienteHelper.criarCliente();
        var cpf = cliente.getCpf().getNumero();
        var uuid = UUID.randomUUID();
        cliente.setUuid(uuid);
        when(cadastroClienteGateway.consultarClientePorCPF(cpf)).thenReturn(java.util.Optional.of(cliente));

//        Act
        Cliente execute = new ConsultarClientePorCpfUseCase(cadastroClienteGateway).execute(cpf);

//        Assert
        verify(cadastroClienteGateway, times(1)).consultarClientePorCPF(cpf);

        var clienteRetornado = assertThat(execute)
                .isInstanceOf(Cliente.class);

        clienteRetornado
                .extracting(Cliente::getUuid)
                .isEqualTo(uuid);
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
    void deveDevolverUmClientePorCpfException() throws FoodException {
//        Arrange
        var cpf = "00000000000";
        when(cadastroClienteGateway.consultarClientePorCPF(cpf)).thenReturn(java.util.Optional.empty());

//        Act/Assert
        assertThatThrownBy(() -> new ConsultarClientePorCpfUseCase(cadastroClienteGateway).execute(cpf))
                .isInstanceOf(FoodException.class)
                .hasMessage("Cliente não encontrado");
    }


    @Test
    void execute() throws FoodException {
//        Arrange
        Cliente cliente = ClienteHelper.criarCliente();
        var cpf = cliente.getCpf().getNumero();

        when(cadastroClienteGateway.consultarClientePorCPF(cpf)).thenReturn(java.util.Optional.of(cliente));

//        Act
        Cliente execute = new ConsultarClientePorCpfUseCase(cadastroClienteGateway).execute(cpf);

//        Assert
        assertNotNull(execute);
    }
}