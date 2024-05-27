package br.com.alfac.food.api.adapter.pedido.driver.controller;

import br.com.alfac.food.api.adapter.pedido.dto.PedidoRequest;
import br.com.alfac.food.api.adapter.pedido.dto.PedidosResponse;
import br.com.alfac.food.api.adapter.pedido.mapper.PedidoMapper;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.ports.PedidoService;
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

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoController(final PedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
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
    public ResponseEntity<PedidoDTO> registrarPedido(@RequestBody PedidoRequest pedidoRequest) throws FoodException {
        PedidoDTO pedido = pedidoService.registrarPedido(pedidoMapper.toDTO(pedidoRequest));

        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
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
