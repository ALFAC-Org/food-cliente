package br.com.alfac.food.core.application.pedido.usecases;

import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.domain.pedido.Pedido;

import java.util.List;

public class ListarPedidosUseCase {

    private final RepositorioPedidoGateway pedidoRepository;

    public ListarPedidosUseCase(final RepositorioPedidoGateway pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> executar() {

        return pedidoRepository.listarPedidos();
    }

}
