package br.com.alfac.food.core.application.pagamento.adapters.gateways;

import java.util.Optional;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoResponse;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;

public interface RepositorioPagamentoGateway {

    Pagamento criar(PagamentoResponse pagamentoResponse) throws FoodException;
    Pagamento atualizar(PagamentoResponse pagamentoResponse) throws FoodException;
    Optional<Pagamento> buscarPorId(Long id) throws FoodException;
    Optional<Pagamento> buscarPorPedidoId(Long pedidoId) throws FoodException;
}
