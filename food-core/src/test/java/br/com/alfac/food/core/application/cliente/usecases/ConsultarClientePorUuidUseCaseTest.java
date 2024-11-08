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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarClientePorUuidUseCaseTest {

    @Mock
    private RepositorioClienteGateway cadastroClienteGateway;

    @Test
    void deveDevolverUmClientePorId() throws FoodException {
//        Arrange
        Cliente cliente = ClienteHelper.criarCliente();
        UUID uuid = cliente.getUuid();
        when(cadastroClienteGateway.consultarClientePorUuId(uuid)).thenReturn(java.util.Optional.of(cliente));

//        Act
        Cliente execute = new ConsultarClientePorUuidUseCase(cadastroClienteGateway).execute(uuid);

//        Assert
        var clienteRetornado = assertThat(execute)
                .isInstanceOf(Cliente.class);

        clienteRetornado
                .extracting(Cliente::getId)
                .isEqualTo(cliente.getId());
        clienteRetornado
                .extracting(Cliente::getUuid)
                .isEqualTo(cliente.getUuid());
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
    void deveDevolverUmClientePorIdException() throws FoodException {
//        Arrange
        Cliente cliente = ClienteHelper.criarCliente();
        UUID uuid = cliente.getUuid();
        when(cadastroClienteGateway.consultarClientePorUuId(uuid)).thenReturn(java.util.Optional.empty());

//        Act/Assert
        assertThatThrownBy(() -> new ConsultarClientePorUuidUseCase(cadastroClienteGateway).execute(uuid))
                .isInstanceOf(FoodException.class)
                .hasMessage("Cliente não encontrado");
    }

    @Test
    void execute() throws FoodException {
//        Arrange
        Cliente cliente = ClienteHelper.criarCliente();
        UUID uuid = cliente.getUuid();
        when(cadastroClienteGateway.consultarClientePorUuId(uuid)).thenReturn(java.util.Optional.of(cliente));

//        Act
        Cliente execute = new ConsultarClientePorUuidUseCase(cadastroClienteGateway).execute(uuid);

//        Assert
        assertNotNull(execute);
    }
}