package br.com.alfac.food.core.application.pagamento.adapters.controller;

import br.com.alfac.food.core.application.pagamento.adapters.gateways.RepositorioPagamentoGateway;
import br.com.alfac.food.core.application.pagamento.adapters.presenter.PagamentoPresenter;
import br.com.alfac.food.core.application.pagamento.dto.PagamentoResponse;
import br.com.alfac.food.core.application.pagamento.usecases.ConsultarPagementoPorPedidoIdUseCase;
import br.com.alfac.food.core.exception.FoodException;

public class ControladorPagamento {

    private final ConsultarPagementoPorPedidoIdUseCase consultarPagementoPorPedidoIdUseCase;

    public ControladorPagamento(final RepositorioPagamentoGateway pagamentoRepository) {
        this.consultarPagementoPorPedidoIdUseCase = new ConsultarPagementoPorPedidoIdUseCase(pagamentoRepository);
    }

    public PagamentoResponse consultarPagamentoPorPedidoId(Long pedidoId) throws FoodException {
        return PagamentoPresenter.toPagamentoResponse(consultarPagementoPorPedidoIdUseCase.executar(pedidoId));
    }
}
