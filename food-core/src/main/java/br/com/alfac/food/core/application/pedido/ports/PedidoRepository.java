package br.com.alfac.food.core.application.pedido.ports;

import java.util.List;

import br.com.alfac.food.core.domain.pedido.Pedido;

public interface PedidoRepository {

    List<Pedido> listarPedidos();

    Pedido registrarPedido(Pedido pedido);

}
