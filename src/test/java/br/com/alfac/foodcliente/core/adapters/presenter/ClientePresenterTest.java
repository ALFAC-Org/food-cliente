package br.com.alfac.foodcliente.core.adapters.presenter;

import br.com.alfac.foodcliente.core.application.adapters.presenter.ClientePresenter;
import br.com.alfac.foodcliente.core.application.dto.ClienteDTO;
import br.com.alfac.foodcliente.core.domain.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ClientePresenterTest {

    @Test
    void deveMapearParaClienteDTOCorretamente() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setUuid(UUID.randomUUID());
        cliente.setNome("Nome Teste");
        cliente.setEmail("email@teste.com");

        // Act
        ClienteDTO clienteDTO = ClientePresenter.mapearParaClienteDTO(cliente);

        // Assert
        assertThat(clienteDTO.getId()).isEqualTo(cliente.getId());
        assertThat(clienteDTO.getUuid()).isEqualTo(cliente.getUuid());
        assertThat(clienteDTO.getNome()).isEqualTo(cliente.getNome());
        assertThat(clienteDTO.getEmail()).isEqualTo(cliente.getEmail());
    }
}
