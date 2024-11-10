package br.com.alfac.food.core.application.cliente.adapters.presenter;

import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.domain.cliente.Cliente;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
