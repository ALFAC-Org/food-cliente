package br.com.alfac.food.core.application.pagamento.adapters.presenter;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoResponse;
import br.com.alfac.food.core.domain.pagamento.Pagamento;

public class PagamentoPresenter {

    private PagamentoPresenter() {
    }

    public static PagamentoResponse toPagamentoResponse(Pagamento pagamento) {
        return new PagamentoResponse(pagamento.getId(), pagamento.getStatusPagamento(), pagamento.getPedidoId(),
                pagamento.getDataPagamento());
    }
}
