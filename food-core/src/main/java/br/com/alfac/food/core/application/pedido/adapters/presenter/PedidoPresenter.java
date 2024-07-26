package br.com.alfac.food.core.application.pedido.adapters.presenter;

import br.com.alfac.food.core.application.pedido.adapters.mappers.PedidoMapper;
import br.com.alfac.food.core.application.pedido.dto.PedidoCriadoDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.utils.CollectionsUtils;

import java.util.List;

public class PedidoPresenter {

    private PedidoPresenter() {
    }

    public static PedidoCriadoDTO mapearParaPedidoCriadoDTO(Pedido pedido, String qrCodeParaPagamento) {
        return new PedidoCriadoDTO(PedidoMapper.mapearParaPedidoDTO(pedido), qrCodeParaPagamento);
    }

    public static List<PedidoDTO> mapearParaPedidoDTO(final List<Pedido> pedidos) {

        if (CollectionsUtils.vazio(pedidos)) {
            return List.of();
        }
        return pedidos.stream().map(PedidoPresenter::mapearParaPedidoDTO).toList();
    }


    public static PedidoDTO mapearParaPedidoDTO(final Pedido pedido) {
        return PedidoMapper.mapearParaPedidoDTO(pedido);
    }
}
