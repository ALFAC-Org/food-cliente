package br.com.alfac.food.core.application.pagamento.gateways;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoDTO;
import br.com.alfac.food.core.exception.FoodException;

public interface PagamentoService {

    PagamentoDTO efetuarPagamento(Long idPedido) throws FoodException;
}
