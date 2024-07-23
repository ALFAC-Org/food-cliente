package br.com.alfac.food.core.application.pagamento.mapper;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;

public class PagamentoMapper {


    public static PagamentoEntityDTO toPagamentoEntityDTO(Pagamento pagamento) {
        return new PagamentoEntityDTO(pagamento.getPedidoId(), pagamento.getId(), pagamento.getStatusPagamento(), pagamento.getPedidoId(),
                pagamento.getDataPagamento());
    }

    public static Pagamento toPagamento(PagamentoEntityDTO pagamentoEntity) throws FoodException {
        return new Pagamento(pagamentoEntity.id(), pagamentoEntity.status(), pagamentoEntity.pedidoId(), pagamentoEntity.dataPagamento());
    }
}
