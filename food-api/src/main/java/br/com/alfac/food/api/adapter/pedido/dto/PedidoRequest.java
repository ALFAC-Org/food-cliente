package br.com.alfac.food.api.adapter.pedido.dto;

import java.util.List;
// import java.util.UUID;

public class PedidoRequest {
    private Long clienteId;
    private List<ComboRequest> combos;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ComboRequest> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboRequest> combos) {
        this.combos = combos;
    }

}