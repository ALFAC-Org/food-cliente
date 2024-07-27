package br.com.alfac.food.infra.pagamento.webhook.dto;

import br.com.alfac.food.core.domain.pagamento.StatusConfirmacaoPagamento;

public record RetornoPagamentoRequest(Long pagamentoId, StatusConfirmacaoPagamento statusConfirmacaoPagamento) {
}
