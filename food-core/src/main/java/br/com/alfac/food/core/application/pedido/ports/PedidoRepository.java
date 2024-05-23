package br.com.alfac.food.core.application.pedido.ports;

import java.util.List;
import java.util.Optional;

import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;

public interface PedidoRepository {

    List<Pedido> listarPedidos();

    Optional<Pedido> consultarPedidoPorId(Long id);

    Pedido registrarPedido(Pedido pedido);

    Pedido atualizarStatusPedido(Long id, StatusPedido statusPedido);

}
