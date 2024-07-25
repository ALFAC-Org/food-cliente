package br.com.alfac.food.core.application.pedido.adapters.presenter;

import br.com.alfac.food.core.application.pedido.adapters.mappers.PedidoMapper;
import br.com.alfac.food.core.application.pedido.dto.PedidoCriadoDTO;
import br.com.alfac.food.core.domain.pedido.Pedido;

public class PedidoPresenter {

    private PedidoPresenter() {
    }

    public static PedidoCriadoDTO mapearParaPedidoCriadoDTO(Pedido pedido, String qrCodeParaPagamento) {
        return new PedidoCriadoDTO(PedidoMapper.mapearParaPedidoDTO(pedido), qrCodeParaPagamento);
    }
}
