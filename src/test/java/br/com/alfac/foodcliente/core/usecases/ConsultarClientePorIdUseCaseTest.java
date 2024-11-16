package br.com.alfac.foodcliente.core.usecases;

import br.com.alfac.foodcliente.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.application.usecases.ConsultarClientePorIdUseCase;
import br.com.alfac.foodcliente.core.domain.cliente.Cliente;
import br.com.alfac.foodcliente.core.exception.FoodException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarClientePorIdUseCaseTest {

    @Mock
    private RepositorioClienteGateway clienteRepository;

    @InjectMocks
    private ConsultarClientePorIdUseCase consultarClientePorIdUseCase;

    @Test
    void deveDevolverUmClienteQuandoEncontrado() throws FoodException {
        // Arrange
        Cliente cliente = new Cliente();
        when(clienteRepository.consultarClientePorId(anyLong())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClientePorIdUseCase.execute(1L);

        // Assert
        assertThat(result).isEqualTo(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        // Arrange
        when(clienteRepository.consultarClientePorId(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FoodException.class, () -> consultarClientePorIdUseCase.execute(1L));
    }

    @Test
    void shouldSetIdValueCorrectly() throws FoodException {
        // Arrange
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        when(clienteRepository.consultarClientePorId(anyLong())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClientePorIdUseCase.execute(id);

        // Assert
        assertThat(result.getId()).isEqualTo(id);
    }
}
