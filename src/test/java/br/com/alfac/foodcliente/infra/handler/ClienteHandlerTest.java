package br.com.alfac.foodcliente.infra.handler;

import br.com.alfac.foodcliente.core.application.adapters.controller.ControladorCliente;
import br.com.alfac.foodcliente.core.application.dto.ClienteDTO;
import br.com.alfac.foodcliente.infra.config.exception.FoodExceptionHandler;
import br.com.alfac.foodcliente.infra.dto.ClienteRequest;
import br.com.alfac.foodcliente.infra.mapper.ClienteMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import utils.ClienteHelper;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClienteHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private ControladorCliente controladorClienteMySQL;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteHandler clienteHandler;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteHandler)
                .setControllerAdvice(new FoodExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void consultarClientePorCpf_ShouldReturnCliente() throws Exception {
        // Arrange
        String cpf = "12345678901";
        ClienteDTO clienteDTO = new ClienteDTO();
        when(controladorClienteMySQL.consultarClientePorCpf(anyString())).thenReturn(clienteDTO);

        // Act & Assert
        mockMvc.perform(get("/api/v1/clientes/por-cpf/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value(clienteDTO.getCpf()));
    }

    @Test
    void consultarClientePorUuid_ShouldReturnCliente() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ClienteDTO clienteDTO = ClienteHelper.criarClienteDTO();
        clienteDTO.setUuId(uuid);
        when(controladorClienteMySQL.consultarClientePorUuid(any(UUID.class))).thenReturn(clienteDTO);

        // Act & Assert
        mockMvc.perform(get("/api/v1/clientes/por-uuid/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(clienteDTO.getUuid().toString()));
    }

    @Test
    void cadastrarCliente_ShouldReturnCreatedCliente() throws Exception {
        // Arrange
        ClienteRequest clienteRequest = new ClienteRequest();
        ClienteDTO clienteDTO = new ClienteDTO();
        when(clienteMapper.toDTO(any(ClienteRequest.class))).thenReturn(clienteDTO);
        when(controladorClienteMySQL.cadastrarCliente(any(ClienteDTO.class))).thenReturn(clienteDTO);

        // Act & Assert
        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clienteRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value(clienteDTO.getCpf()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
