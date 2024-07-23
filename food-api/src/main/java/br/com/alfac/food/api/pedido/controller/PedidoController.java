package br.com.alfac.food.api.pedido.controller;

import br.com.alfac.food.api.pedido.dto.PedidoRequest;
import br.com.alfac.food.api.pedido.dto.PedidosResponse;
import br.com.alfac.food.api.pedido.mapper.PedidoMapper;
import br.com.alfac.food.core.application.pedido.controller.ControladorPedido;
import br.com.alfac.food.core.application.pedido.dto.PedidoCriadoDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.gateways.PedidoService;
import br.com.alfac.food.core.application.pedido.usecases.PedidoUseCase;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedido", description = "Métodos para manipulação de pedidos")
public class PedidoController implements PedidoControllerExamples {

    private final PedidoUseCase pedidoService;
    private final PedidoMapper pedidoMapper;
    private final ControladorPedido controladorPedido;

    public PedidoController(final PedidoUseCase pedidoService, PedidoMapper pedidoMapper, final ControladorPedido controladorPedido) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
        this.controladorPedido = controladorPedido;
    }

    @GetMapping
    @Operation(summary = "Listar todos os pedidos")
    public List<PedidoDTO> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @Operation(summary = "Consultar pedido por pedido id")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoDTO> consultarPedidoPorId(@PathVariable Long id) throws FoodException {
        return new ResponseEntity<>(pedidoService.consultarPedidoPorId(id), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<PedidoCriadoDTO> registrarPedido(@RequestBody PedidoRequest pedidoRequest) throws FoodException {
        PedidoCriadoDTO pedidoCriado = controladorPedido.executar(pedidoMapper.toDTO(pedidoRequest));

        return new ResponseEntity<>(pedidoCriado, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar status do pedido (de modo sequencial)")
    @PutMapping(value = "{id}/atualizar-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoDTO> atualizarStatusPedido(@PathVariable Long id) throws FoodException {

        PedidoDTO pedido = pedidoService.atualizarStatusPedido(id);

        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @Operation(summary = "Consultar pedidos por status")
    @GetMapping(value = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidosResponse> consultarPedidoPorStatus(@PathVariable StatusPedido status) {
        List<PedidoDTO> pedidoDTOS = pedidoService.listarPedidosPorStatus(status);
        return new ResponseEntity<>(new PedidosResponse(pedidoDTOS), HttpStatus.OK);
    }
}
