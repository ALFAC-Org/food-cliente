package br.com.alfac.foodcliente.core.adapters.gateways;

import br.com.alfac.foodcliente.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.domain.cliente.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositorioClienteGatewayTest {

    @Mock
    private RepositorioClienteGateway repositorioClienteGateway;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setUuid(UUID.randomUUID());
        cliente.setNome("Nome Teste");
        cliente.setEmail("email@teste.com");
    }

    @Test
    void deveConsultarClientePorCPF() {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorCPF(anyString())).thenReturn(Optional.of(cliente));

        // Act
        Optional<Cliente> result = repositorioClienteGateway.consultarClientePorCPF("12345678900");

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(cliente);
    }

    @Test
    void deveConsultarClientePorUuId() {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorUuId(any(UUID.class))).thenReturn(Optional.of(cliente));

        // Act
        Optional<Cliente> result = repositorioClienteGateway.consultarClientePorUuId(UUID.randomUUID());

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(cliente);
    }

    @Test
    void deveConsultarClientePorId() {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorId(anyLong())).thenReturn(Optional.of(cliente));

        // Act
        Optional<Cliente> result = repositorioClienteGateway.consultarClientePorId(1L);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(cliente);
    }

    @Test
    void deveCadastrarCliente() {
        // Arrange
        when(repositorioClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(cliente);

        // Act
        Cliente result = repositorioClienteGateway.cadastrarCliente(cliente);

        // Assert
        assertThat(result).isEqualTo(cliente);
    }
}
