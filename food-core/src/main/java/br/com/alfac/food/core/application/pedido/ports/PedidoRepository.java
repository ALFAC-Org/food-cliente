package br.com.alfac.food.core.application.pedido.ports;

import br.com.alfac.food.core.domain.pedido.Pedido;

public interface PedidoRepository {

    void registrarPedido(Pedido pedido);

}
