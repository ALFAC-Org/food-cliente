package br.com.alfac.food.core.application.pagamento.controller;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.core.application.pagamento.usecases.AlterarStatusPagamentoRealizado;
import br.com.alfac.food.core.application.pedido.usecases.AtualizarStatusPedidoPagamentoRecebido;
import br.com.alfac.food.core.exception.FoodException;

public class ControladoRecebimentoPagamento {



    private final AlterarStatusPagamentoRealizado alterarStatusPagamentoRealizado;
    private final AtualizarStatusPedidoPagamentoRecebido atualizarStatusPedidoPagamentoRecebido;

    public ControladoRecebimentoPagamento(final AlterarStatusPagamentoRealizado alterarStatusPagamentoRealizado,
                                          final AtualizarStatusPedidoPagamentoRecebido atualizarStatusPedidoPagamentoRecebido) {
        this.alterarStatusPagamentoRealizado = alterarStatusPagamentoRealizado;
        this.atualizarStatusPedidoPagamentoRecebido = atualizarStatusPedidoPagamentoRecebido;
    }


    public void executar(final Long idPagamento) throws FoodException {

        PagamentoEntityDTO pagamentoEntityDTO = this.alterarStatusPagamentoRealizado.executar(idPagamento);

        atualizarStatusPedidoPagamentoRecebido.executar(pagamentoEntityDTO.pedidoId());

    }
}
