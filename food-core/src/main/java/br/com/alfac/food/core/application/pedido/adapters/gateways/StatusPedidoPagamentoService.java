package br.com.alfac.food.core.application.pedido.adapters.gateways;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.exception.FoodException;

public interface StatusPedidoPagamentoService {

    PedidoDTO atualizarStatus(PedidoDTO pedidoDTO) throws FoodException;

}
