package br.com.alfac.food.infra.pedido.dto;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;

import java.util.List;

public record PedidosResponse (List<PedidoDTO> pedidos) {

}
