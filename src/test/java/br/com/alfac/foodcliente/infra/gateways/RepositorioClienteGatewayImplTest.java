package br.com.alfac.foodcliente.infra.gateways;

import br.com.alfac.foodcliente.infra.gateways.RepositorioClienteMySQLGatewayImpl;
import br.com.alfac.foodcliente.infra.mapper.ClienteEntityMapper;
import br.com.alfac.foodcliente.infra.persistence.ClienteEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

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