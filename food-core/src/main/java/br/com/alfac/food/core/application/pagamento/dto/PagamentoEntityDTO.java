package br.com.alfac.food.core.application.pagamento.dto;

import br.com.alfac.food.core.domain.pagamento.StatusPagamento;

import java.time.LocalDateTime;

public record PagamentoEntityDTO(Long idPedido, Long id, StatusPagamento status, Long pedidoId, LocalDateTime dataPagamento) {

}
