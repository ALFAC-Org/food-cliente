package br.com.alfac.food.api.webhook;

import br.com.alfac.food.api.webhook.dto.RetornoPagamentoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wh/v1/pagamentos")
@Tag(name = "Retorno Pagamento", description = "Métodos manipulação do retorno de pagamento")
public class RetornoPagamentoController  {



    @Operation(summary = "Consultar pedidos por status")
    @PostMapping
    public ResponseEntity<Void> consultarPedidoPorStatus(@RequestBody RetornoPagamentoRequest  retornoPagamento) {
//        List<PedidoDTO> pedidoDTOS = pedidoService.listarPedidosPorStatus(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
