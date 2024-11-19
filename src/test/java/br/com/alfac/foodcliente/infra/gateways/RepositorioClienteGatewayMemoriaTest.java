package br.com.alfac.foodcliente.infra.gateways;

import br.com.alfac.foodcliente.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.domain.Cliente;
import br.com.alfac.foodcliente.core.domain.vo.CPF;
import br.com.alfac.foodcliente.infra.mapper.ClienteEntityMapper;
import br.com.alfac.foodcliente.infra.persistence.ClienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepositorioClienteGatewayMemoriaTest {

    @InjectMocks
    private RepositorioClienteGatewayMemoria repositorioClienteGatewayMemoria;

    @Mock
    private ClienteEntityMapper clienteEntityMapper;

    @Test
    void consultarClientePorCPF_clienteExistente() {
        // Arrange
        String cpf = "12345678900";
        Cliente cliente = new Cliente();
        cliente.setCpf(new CPF(cpf));
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(cpf);
        lenient().when(clienteEntityMapper.toEntity(any(Cliente.class))).thenReturn(clienteEntity);
        lenient().when(clienteEntityMapper.toDomain(any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        repositorioClienteGatewayMemoria.cadastrarCliente(cliente);
        Optional<Cliente> result = repositorioClienteGatewayMemoria.consultarClientePorCPF(cpf);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void consultarClientePorCPF_clienteInexistente() {
        // Arrange
        String cpf = "12345678900";

        // Act
        Optional<Cliente> result = repositorioClienteGatewayMemoria.consultarClientePorCPF(cpf);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void consultarClientePorUuId_clienteExistente() {
        // Arrange
        Cliente cliente = new Cliente();
        ClienteEntity clienteEntity = new ClienteEntity();
        lenient().when(clienteEntityMapper.toEntity(any(Cliente.class))).thenReturn(clienteEntity);
        lenient().when(clienteEntityMapper.toDomain(any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        Cliente clienteCadastrado = repositorioClienteGatewayMemoria.cadastrarCliente(cliente);
        UUID uuid = clienteCadastrado.getUuid();
        Optional<Cliente> result = repositorioClienteGatewayMemoria.consultarClientePorUuId(uuid);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void consultarClientePorUuId_clienteInexistente() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        Optional<Cliente> result = repositorioClienteGatewayMemoria.consultarClientePorUuId(uuid);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void consultarClientePorId_clienteExistente() {
        // Arrange
        Long id = 1L;
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(id);
        repositorioClienteGatewayMemoria.cadastrarCliente(new Cliente());

        // Act
        Optional<Cliente> result = repositorioClienteGatewayMemoria.consultarClientePorId(id);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void consultarClientePorId_clienteInexistente() {
        // Arrange
        Long id = 1L;

        // Act
        Optional<Cliente> result = repositorioClienteGatewayMemoria.consultarClientePorId(id);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void cadastrarCliente() {
        // Arrange
        Cliente cliente = new Cliente();
        ClienteEntity clienteEntity = new ClienteEntity();
        lenient().when(clienteEntityMapper.toEntity(any(Cliente.class))).thenReturn(clienteEntity);
        lenient().when(clienteEntityMapper.toDomain(any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        Cliente result = repositorioClienteGatewayMemoria.cadastrarCliente(cliente);

        // Assert
        assertNotNull(result);
    }
}
