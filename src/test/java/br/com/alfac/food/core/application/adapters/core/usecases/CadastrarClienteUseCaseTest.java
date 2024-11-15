package br.com.alfac.food.core.application.adapters.core.usecases;

import br.com.alfac.food.core.application.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.application.dto.ClienteDTO;
import br.com.alfac.food.core.application.usecases.CadastrarClienteUseCase;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.cliente.vo.CPF;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.ClienteHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarClienteUseCaseTest {

    @Mock
    private RepositorioClienteGateway cadastroClienteGateway;

    @InjectMocks
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @Test
    void deveDevolverUmCliente() {
        // Arrange
        ClienteDTO clienteDTO = ClienteHelper.criarClienteDTO();
        Cliente cliente = ClienteHelper.criarCliente();
        when(cadastroClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(cliente);

        // Act
        Cliente execute = cadastrarClienteUseCase.execute(clienteDTO);

        // Assert
        var clienteRetornado = assertThat(execute)
                .isInstanceOf(Cliente.class);

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
    void execute() {
        // Arrange
        when(cadastroClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(new Cliente());

        // Act
        Cliente execute = cadastrarClienteUseCase.execute(new ClienteDTO());

        // Assert
        assertNotNull(execute);
    }

    @Test
    void shouldThrowExceptionWhenClienteDTOIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> cadastrarClienteUseCase.execute(null));
    }

    @Test
    void shouldSetCPFValueCorrectly() {
        // Arrange
        ClienteDTO clienteDTO = ClienteHelper.criarClienteDTO();
        Cliente cliente = new Cliente();
        cliente.setCpf(new CPF(clienteDTO.getCpf()));
        when(cadastroClienteGateway.cadastrarCliente(any(Cliente.class))).thenReturn(cliente);

        // Act
        Cliente execute = cadastrarClienteUseCase.execute(clienteDTO);

        // Assert
        assertThat(execute.getCpf()).isEqualTo(new CPF(clienteDTO.getCpf()));
    }

}
