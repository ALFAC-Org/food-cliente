package br.com.alfac.food.core.application.pedido.dto;

import java.util.List;
// import java.util.UUID;

public class PedidoDTO {
    private List<ComboDTO> combos;
    private Long clienteId;
    // private UUID clienteUuId;

    public List<ComboDTO> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboDTO> combos) {
        this.combos = combos;
    }

    // public UUID getClienteUuId() {
    //     return clienteUuId;
    // }
    
    // public void setClienteUuid(UUID clienteUuId) {
    //     this.clienteUuId = clienteUuId;
    // }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}