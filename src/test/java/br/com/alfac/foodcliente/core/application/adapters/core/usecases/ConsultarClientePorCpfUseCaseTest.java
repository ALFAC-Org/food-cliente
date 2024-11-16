package br.com.alfac.foodcliente.core.application.adapters.core.usecases;

import br.com.alfac.foodcliente.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.foodcliente.core.application.usecases.ConsultarClientePorCpfUseCase;
import br.com.alfac.foodcliente.core.domain.cliente.Cliente;
import br.com.alfac.foodcliente.core.domain.cliente.vo.CPF;
import br.com.alfac.foodcliente.core.exception.FoodException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarClientePorCpfUseCaseTest {

    @Mock
    private RepositorioClienteGateway clienteRepository;

    @InjectMocks
    private ConsultarClientePorCpfUseCase consultarClientePorCpfUseCase;

    @Test
    void deveDevolverUmClienteQuandoEncontrado() throws FoodException {
        // Arrange
        Cliente cliente = new Cliente();
        when(clienteRepository.consultarClientePorCPF(anyString())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClientePorCpfUseCase.execute("12345678900");

        // Assert
        assertThat(result).isEqualTo(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        // Arrange
        when(clienteRepository.consultarClientePorCPF(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FoodException.class, () -> consultarClientePorCpfUseCase.execute("12345678900"));
    }


    @Test
    void shouldSetCPFValueCorrectly() throws FoodException{
        // Arrange
        String cpf = "12345678900";
        Cliente cliente = new Cliente();
        cliente.setCpf(new CPF(cpf));
        when(clienteRepository.consultarClientePorCPF(anyString())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClientePorCpfUseCase.execute(cpf);

        // Assert
        assertThat(result.getCpf()).isEqualTo(new CPF(cpf));
    }
}
