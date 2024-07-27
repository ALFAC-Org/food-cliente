package br.com.alfac.food.core.application.pagamento.adapters.mapper;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoResponse;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;

public class PagamentoMapper {

    public static PagamentoResponse toPagamentoEntityDTO(Pagamento pagamento) {
        return new PagamentoResponse(pagamento.getId(), pagamento.getStatusPagamento(), pagamento.getPedidoId(),
                pagamento.getDataPagamento());
    }

    public static Pagamento toPagamento(PagamentoResponse pagamentoEntity) throws FoodException {
        return new Pagamento(pagamentoEntity.id(), pagamentoEntity.status(), pagamentoEntity.pedidoId(), pagamentoEntity.dataPagamento());
    }
}
