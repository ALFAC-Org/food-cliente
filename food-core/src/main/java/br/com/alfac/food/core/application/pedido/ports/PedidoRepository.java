package br.com.alfac.food.core.application.pedido.ports;

import java.util.List;
import java.util.Optional;

import br.com.alfac.food.core.domain.pedido.Pedido;

public interface PedidoRepository {

    List<Pedido> listarPedidos();

    Optional<Pedido> consultarPedidoPorId(Long id);

    Pedido registrarPedido(Pedido pedido);

}
