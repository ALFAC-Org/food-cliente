package br.com.alfac.foodcliente.unit.core.usecases;

import br.com.alfac.foodcliente.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.application.usecases.ConsultarClientePorUuidUseCase;
import br.com.alfac.foodcliente.core.domain.Cliente;
import br.com.alfac.foodcliente.core.exception.FoodException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarClientePorUuidUseCaseTest {

    @Mock
    private RepositorioClienteGateway clienteRepository;

    @InjectMocks
    private ConsultarClientePorUuidUseCase consultarClientePorUuidUseCase;

    @Test
    void deveDevolverUmClienteQuandoEncontrado() throws FoodException {
        // Arrange
        Cliente cliente = new Cliente();
        when(clienteRepository.consultarClientePorUuId(any(UUID.class))).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClientePorUuidUseCase.execute(UUID.randomUUID());

        // Assert
        assertThat(result).isEqualTo(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        // Arrange
        when(clienteRepository.consultarClientePorUuId(any(UUID.class))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FoodException.class, () -> consultarClientePorUuidUseCase.execute(UUID.randomUUID()));
    }

    @Test
    void shouldSetUuidValueCorrectly() throws FoodException {
        // Arrange
        UUID uuid = UUID.randomUUID();
        Cliente cliente = new Cliente();
        cliente.setUuid(uuid);
        when(clienteRepository.consultarClientePorUuId(any(UUID.class))).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClientePorUuidUseCase.execute(uuid);

        // Assert
        assertThat(result.getUuid()).isEqualTo(uuid);
    }
}
