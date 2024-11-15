package br.com.alfac.food.core.application.adapters.infra.gateways;

import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.infra.gateways.RepositorioClienteMySQLGatewayImpl;
import br.com.alfac.food.infra.mapper.ClienteEntityMapper;
import br.com.alfac.food.infra.persistence.ClienteEntity;
import br.com.alfac.food.infra.persistence.ClienteEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.ClienteHelper;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepositorioClienteGatewayImplTest {

    @Mock
    private ClienteEntityRepository clienteEntityRepository;

    @Mock
    private ClienteEntityMapper clienteEntityMapper;

    @InjectMocks
    private RepositorioClienteMySQLGatewayImpl repositorioClienteMySQLGateway;

    @Test
    void shouldThrowExceptionWhenClienteIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> repositorioClienteMySQLGateway.cadastrarCliente(null));
    }
}