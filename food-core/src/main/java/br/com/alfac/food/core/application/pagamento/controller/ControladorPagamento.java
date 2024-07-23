package br.com.alfac.food.core.application.pagamento.controller;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.core.application.pagamento.usecases.ConsultarPagementoPorPedidoIdUseCase;
import br.com.alfac.food.core.exception.FoodException;

public class ControladorPagamento {

    private final ConsultarPagementoPorPedidoIdUseCase consultarPagementoPorPedidoIdUseCase;

    public ControladorPagamento(ConsultarPagementoPorPedidoIdUseCase consultarPagementoPorPedidoIdUseCase) {
        this.consultarPagementoPorPedidoIdUseCase = consultarPagementoPorPedidoIdUseCase;
    }
    
     public PagamentoEntityDTO consultarPagamentoPorPedidoId(Long pedidoId) throws FoodException {
        return consultarPagementoPorPedidoIdUseCase.executar(pedidoId);
    }
}
