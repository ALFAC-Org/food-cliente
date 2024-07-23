package br.com.alfac.food.api.webhook;

import br.com.alfac.food.api.webhook.dto.RetornoPagamentoRequest;
import br.com.alfac.food.core.application.pagamento.controller.ControladoRecebimentoPagamento;
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


    private final ControladoRecebimentoPagamento controladoRecebimentoPagamento;

    public RetornoPagamentoController(final ControladoRecebimentoPagamento controladoRecebimentoPagamento) {
        this.controladoRecebimentoPagamento = controladoRecebimentoPagamento;
    }


    @Operation(summary = "Responsável por receber estimulo requisição quando pagamento realizado na processadora de pagamento")
    @PostMapping
    public ResponseEntity<Void> consultarPedidoPorStatus(@RequestBody RetornoPagamentoRequest retornoPagamento) throws FoodException {
        controladoRecebimentoPagamento.executar(retornoPagamento.pagamentoId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
