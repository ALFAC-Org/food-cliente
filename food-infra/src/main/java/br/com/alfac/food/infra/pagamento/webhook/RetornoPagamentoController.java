package br.com.alfac.food.infra.pagamento.webhook;

import br.com.alfac.food.infra.pagamento.webhook.dto.RetornoPagamentoRequest;
import br.com.alfac.food.core.application.pagamento.adapters.controller.ControladorRecebimentoPagamento;
import br.com.alfac.food.core.exception.FoodException;
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
public class RetornoPagamentoController {


    private final ControladorRecebimentoPagamento controladorRecebimentoPagamento;

    public RetornoPagamentoController(final ControladorRecebimentoPagamento controladorRecebimentoPagamento) {
        this.controladorRecebimentoPagamento = controladorRecebimentoPagamento;
    }


    @Operation(summary = "Responsável por receber estimulo requisição quando pagamento realizado na processadora de pagamento")
    @PostMapping
    public ResponseEntity<Void> consultarPedidoPorStatus(@RequestBody RetornoPagamentoRequest retornoPagamento) throws FoodException {
        controladorRecebimentoPagamento.executarRetornoPagamento(retornoPagamento.pagamentoId(), retornoPagamento.statusConfirmacaoPagamento());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
