package br.com.alfac.food.core.application.pedido.usecases;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.application.pedido.mappers.PedidoMapper;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;

public class ListarPedidosOrdenadosUseCase {

    private final PedidoRepository pedidoRepository;

    public ListarPedidosOrdenadosUseCase(
            final PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoDTO> listarPedidosOrdenados() {
        List<Pedido> pedidos = pedidoRepository.listarPedidos();
        pedidos = ordenarPedidos(filtrarPedidos(pedidos));
        return PedidoMapper.mapearParaListaPedidoDTO(pedidos);
    }

    private List<Pedido> ordenarPedidos(List<Pedido> pedidos) {
        List<StatusPedido> ordem = Arrays.asList(StatusPedido.PRONTO, StatusPedido.EM_PREPARACAO, StatusPedido.RECEBIDO);

        Collections.sort(pedidos, Comparator.comparing(Pedido::getStatus, (status1, status2) -> ordem.indexOf(status1) - ordem.indexOf(status2))
                .thenComparing(Pedido::getDataCadastro));
        return pedidos;
    }

    private List<Pedido> filtrarPedidos(List<Pedido> pedidos) {
        List<Pedido> pedidosFiltrados = pedidos.stream()
                .filter(pedido -> pedido.getStatus() == StatusPedido.PRONTO
                || pedido.getStatus() == StatusPedido.EM_PREPARACAO
                || pedido.getStatus() == StatusPedido.RECEBIDO)
                .collect(Collectors.toList());

        return pedidosFiltrados;
    }
}
