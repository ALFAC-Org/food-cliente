package br.com.alfac.food.core.application.pagamento.dto;

import br.com.alfac.food.core.domain.pedido.StatusPedido;

public record PagamentoDTO(Long idPedido, boolean realizado, StatusPedido statusPedido) {

}
