package br.com.alfac.food.core.application.pedido.adapters.gateways;

import java.util.List;
import java.util.Optional;

import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;

public interface RepositorioPedidoGateway {

    List<Pedido> listarPedidos();

    Optional<Pedido> consultarPedidoPorId(Long id) throws FoodException;

    Pedido registrarPedido(Pedido pedido) throws FoodException;

    Pedido atualizarStatusPedido(Long id, StatusPedido statusPedido) throws FoodException;

    List<Pedido> listarPedidosPorStatus(StatusPedido status);
}
