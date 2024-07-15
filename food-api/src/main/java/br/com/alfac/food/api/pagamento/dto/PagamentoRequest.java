package br.com.alfac.food.api.pagamento.dto;

import jakarta.validation.constraints.NotNull;

public class PagamentoRequest {

    @NotNull(message = "Id do pedido é obrigatório")
    private Long idPedido;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(final Long idPedido) {
        this.idPedido = idPedido;
    }
}
