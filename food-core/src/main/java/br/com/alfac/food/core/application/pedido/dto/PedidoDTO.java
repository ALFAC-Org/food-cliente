package br.com.alfac.food.core.application.pedido.dto;

import java.util.List;

public class PedidoDTO {
    private List<ComboDTO> combos;
    private Long clienteId;

    public List<ComboDTO> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboDTO> combos) {
        this.combos = combos;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    
}