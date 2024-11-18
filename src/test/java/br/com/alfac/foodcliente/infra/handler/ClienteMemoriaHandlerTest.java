package br.com.alfac.foodcliente.infra.handler;

import br.com.alfac.foodcliente.core.application.adapters.controller.ControladorCliente;
import br.com.alfac.foodcliente.core.application.dto.ClienteDTO;
import br.com.alfac.foodcliente.core.exception.FoodException;
import br.com.alfac.foodcliente.infra.dto.ClienteRequest;
import br.com.alfac.foodcliente.infra.mapper.ClienteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClienteMemoriaHandlerTest {

    @Mock
    private ControladorCliente controladorClienteMemoria;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteMemoriaHandler clienteMemoriaHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void consultarClientePorCpf() throws FoodException {
        // Arrange
        String cpf = "12345678900";
        ClienteDTO clienteDTO = new ClienteDTO();
        when(controladorClienteMemoria.consultarClientePorCpf(cpf)).thenReturn(clienteDTO);

        // Act
        ResponseEntity<ClienteDTO> response = clienteMemoriaHandler.consultarCliente(cpf);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());
    }

    @Test
    void consultarClientePorId() throws FoodException {
        // Arrange
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        when(controladorClienteMemoria.consultarClientePorId(id)).thenReturn(clienteDTO);

        // Act
        ResponseEntity<ClienteDTO> response = clienteMemoriaHandler.consultarClientePorId(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());
    }

    @Test
    void consultarClientePorUuid() throws FoodException {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ClienteDTO clienteDTO = new ClienteDTO();
        when(controladorClienteMemoria.consultarClientePorUuid(uuid)).thenReturn(clienteDTO);

        // Act
        ResponseEntity<ClienteDTO> response = clienteMemoriaHandler.consultarClientePorUuid(uuid);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());
    }

    @Test
    void cadastrarCliente() throws FoodException {
        // Arrange
        ClienteRequest clienteRequest = new ClienteRequest();
        ClienteDTO clienteDTO = new ClienteDTO();
        when(clienteMapper.toDTO(any(ClienteRequest.class))).thenReturn(clienteDTO);
        when(controladorClienteMemoria.cadastrarCliente(any(ClienteDTO.class))).thenReturn(clienteDTO);

        // Act
        ResponseEntity<ClienteDTO> response = clienteMemoriaHandler.cadastrarCliente(clienteRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());
    }
}
