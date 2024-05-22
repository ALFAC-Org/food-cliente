package br.com.alfac.food.api.adapter.pedido.driver.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.alfac.food.api.adapter.pedido.dto.PedidoRequest;
import br.com.alfac.food.api.adapter.pedido.mapper.PedidoMapper;
import br.com.alfac.food.api.config.exception.ApiError;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
// import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.ports.PedidoService;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.exception.FoodException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedido", description = "Métodos para manipulação de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoController(final PedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }

    @GetMapping
    public List<PedidoDTO> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @Operation(summary = "Registrar Pedidos", description = "Lembre-se de adicionar os itens que deseja dentro da estrutura exemplificada", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(value = """
                    {
                            "clienteId": 1,
                            "combos": [
                                {
                                    "lanche": {
                                        "id": 1,
                                        "complementos": [
                                            {
                                                "id": 6
                                            }
                                        ],
                                        "observacoes": "Capricha no queijo!"
                                    },
                                    "acompanhamento": {
                                        "id": 8
                                    },
                                    "bebida": {
                                        "id": 11
                                    },
                                    "sobremesa": {
                                        "id": 14
                                    }
                                }
                            ]
                        }

                                            """)
    })))

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido registrado"),
            @ApiResponse(responseCode = "422", description = "Erro ao registrar pedido", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            }) })
    @PostMapping
    public ResponseEntity<PedidoDTO> registrarPedido(@RequestBody PedidoRequest pedidoRequest) throws FoodException {
        PedidoDTO pedido = pedidoService.registrarPedido(pedidoMapper.toDTO(pedidoRequest));

        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

}
