package br.com.alfac.foodcliente.infra.gateways;

import br.com.alfac.foodcliente.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.domain.Cliente;
import br.com.alfac.foodcliente.core.domain.vo.CPF;
import br.com.alfac.foodcliente.infra.gateways.RepositorioClienteMySQLGatewayImpl;
import br.com.alfac.foodcliente.infra.mapper.ClienteEntityMapper;
import br.com.alfac.foodcliente.infra.persistence.ClienteEntity;
import br.com.alfac.foodcliente.infra.persistence.ClienteEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RepositorioClienteMySQLGatewayImplTest {

    @Mock
    private ClienteEntityRepository clienteEntityRepository;

    @Mock
    private ClienteEntityMapper clienteEntityMapper;

    @InjectMocks
    private RepositorioClienteMySQLGatewayImpl repositorioClienteMySQLGateway;

    private ClienteEntity clienteEntity;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setUuid(UUID.randomUUID());
        clienteEntity.setNome("Nome Teste");
        clienteEntity.setEmail("email@teste.com");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setUuid(UUID.randomUUID());
        cliente.setNome("Nome Teste");
        cliente.setEmail("email@teste.com");
    }

    @Test
    void deveConsultarClientePorCPF() {
        // Arrange
        cliente.setCpf(new CPF("12345678900"));
        UUID uuid = UUID.randomUUID();
        cliente.setUuid(uuid);
        clienteEntity.setUuid(uuid);
        clienteEntity.setCpf("12345678900");

        when(clienteEntityRepository.findByCpf(anyString())).thenReturn(Optional.of(clienteEntity));
        when(clienteEntityMapper.toDomain(clienteEntity)).thenReturn(cliente);

        // Act
        Optional<Cliente> result = repositorioClienteMySQLGateway.consultarClientePorCPF("12345678900");

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(cliente);
    }

    @Test
    void deveConsultarClientePorUuId() {
        // Arrange
        cliente.setCpf(new CPF("12345678900"));
        UUID uuid = UUID.randomUUID();
        cliente.setUuid(uuid);
        clienteEntity.setUuid(uuid);
        clienteEntity.setCpf("12345678900");

        when(clienteEntityRepository.findByUuid(any(UUID.class))).thenReturn(Optional.of(clienteEntity));
        when(clienteEntityMapper.toDomain(clienteEntity)).thenReturn(cliente);

        // Act
        Optional<Cliente> result = repositorioClienteMySQLGateway.consultarClientePorUuId(uuid);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(cliente);
    }

    @Test
    void deveConsultarClientePorId() {
        // Arrange
        cliente.setCpf(new CPF("12345678900"));
        UUID uuid = UUID.randomUUID();
        cliente.setUuid(uuid);
        clienteEntity.setUuid(uuid);
        clienteEntity.setCpf("12345678900");

        when(clienteEntityRepository.findById(anyLong())).thenReturn(Optional.of(clienteEntity));
        when(clienteEntityMapper.toDomain(clienteEntity)).thenReturn(cliente);

        // Act
        Optional<Cliente> result = repositorioClienteMySQLGateway.consultarClientePorId(1L);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(cliente);
    }

    @Test
    void deveCadastrarCliente() {
        // Arrange
        cliente.setCpf(new CPF("12345678900"));
        UUID uuid = UUID.randomUUID();
        cliente.setUuid(uuid);
        clienteEntity.setUuid(uuid);
        clienteEntity.setCpf("12345678900");

        when(clienteEntityMapper.toEntity(cliente)).thenReturn(clienteEntity);
        when(clienteEntityRepository.save(any(ClienteEntity.class))).thenReturn(clienteEntity);
        when(clienteEntityMapper.toDomain(clienteEntity)).thenReturn(cliente);

        assertThat(clienteEntityMapper.toEntity(cliente)).isEqualTo(clienteEntity);
        assertThat(clienteEntityRepository.save(clienteEntity)).isEqualTo(clienteEntity);
        assertThat(clienteEntityMapper.toDomain(clienteEntity)).isEqualTo(cliente);

        // Act
        Cliente result = repositorioClienteMySQLGateway.cadastrarCliente(cliente);

        assertThat(result).isNotNull();

        // Assert
        assertThat(result).usingRecursiveComparison().isEqualTo(cliente);
    }

    @Test
    void shouldThrowExceptionWhenClienteIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> repositorioClienteMySQLGateway.cadastrarCliente(null));
    }
}