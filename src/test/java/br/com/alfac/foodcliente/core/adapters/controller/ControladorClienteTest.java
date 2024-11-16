package br.com.alfac.foodcliente.core.adapters.controller;

import br.com.alfac.foodcliente.controller.ControladorCliente;
import br.com.alfac.foodcliente.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.application.dto.ClienteDTO;
import br.com.alfac.foodcliente.core.domain.cliente.Cliente;
import br.com.alfac.foodcliente.core.exception.FoodException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControladorClienteTest {

    @Mock
    private RepositorioClienteGateway repositorioClienteGateway;

    @InjectMocks
    private ControladorCliente controladorCliente;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setUuid(UUID.randomUUID());
        cliente.setNome("Nome Mockado");
        cliente.setEmail("email@unittest.com");

        clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setUuId(cliente.getUuid());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
    }

    @Test
    void deveConsultarClientePorCpf() throws FoodException {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorCPF(anyString())).thenReturn(java.util.Optional.of(cliente));

        // Act
        ClienteDTO result = controladorCliente.consultarClientePorCpf("12345678900");

        // Assert
        assertThat(result).usingRecursiveComparison().isEqualTo(clienteDTO);
    }

    @Test
    void deveConsultarClientePorId() throws FoodException {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorId(anyLong())).thenReturn(java.util.Optional.of(cliente));

        // Act
        ClienteDTO result = controladorCliente.consultarClientePorId(1L);

        // Assert
        assertThat(result).usingRecursiveComparison().isEqualTo(clienteDTO);
    }

    @Test
    void deveConsultarClientePorUuid() throws FoodException {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorUuId(any(UUID.class))).thenReturn(java.util.Optional.of(cliente));

        // Act
        ClienteDTO result = controladorCliente.consultarClientePorUuid(UUID.randomUUID());

        // Assert
        assertThat(result).usingRecursiveComparison().isEqualTo(clienteDTO);
    }

    @Test
    void deveCadastrarCliente() {
        // Arrange
        when(repositorioClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(cliente);

        // Act
        ClienteDTO result = controladorCliente.cadastrarCliente(clienteDTO);

        // Assert
        assertThat(result).usingRecursiveComparison().isEqualTo(clienteDTO);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontradoPorCpf() {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorCPF(anyString())).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(FoodException.class, () -> controladorCliente.consultarClientePorCpf("12345678900"));
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontradoPorId() {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorId(anyLong())).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(FoodException.class, () -> controladorCliente.consultarClientePorId(1L));
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontradoPorUuid() {
        // Arrange
        when(repositorioClienteGateway.consultarClientePorUuId(any(UUID.class))).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(FoodException.class, () -> controladorCliente.consultarClientePorUuid(UUID.randomUUID()));
    }
}
