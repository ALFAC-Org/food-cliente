package br.com.alfac.food.core.application.pedido.usecases;

import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListarPedidosOrdenadosUseCase {

    private final RepositorioPedidoGateway pedidoRepository;

    public ListarPedidosOrdenadosUseCase(
            final RepositorioPedidoGateway pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> executar() {
        List<Pedido> pedidos = pedidoRepository.listarPedidos();
        return ordenarPedidos(filtrarPedidos(pedidos));
    }

    private List<Pedido> ordenarPedidos(List<Pedido> pedidos) {
        List<StatusPedido> ordem = Arrays.asList(StatusPedido.PRONTO, StatusPedido.EM_PREPARACAO, StatusPedido.RECEBIDO);

        pedidos.sort(Comparator.comparing(Pedido::getStatus, Comparator.comparingInt(ordem::indexOf))
                .thenComparing(Pedido::getDataCadastro));

        return pedidos;
    }

    private List<Pedido> filtrarPedidos(List<Pedido> pedidos) {

        return pedidos.stream()
                .filter(pedido -> pedido.getStatus() == StatusPedido.PRONTO
                        || pedido.getStatus() == StatusPedido.EM_PREPARACAO
                        || pedido.getStatus() == StatusPedido.RECEBIDO)
                .collect(Collectors.toList());
    }
}
