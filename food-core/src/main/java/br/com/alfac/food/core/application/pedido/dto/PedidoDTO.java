package br.com.alfac.food.core.application.pedido.dto;

import java.util.List;
import java.util.UUID;

public class PedidoDTO {
    private List<ComboDTO> combos;
    private UUID clienteId;

    public List<ComboDTO> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboDTO> combos) {
        this.combos = combos;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }
    
}