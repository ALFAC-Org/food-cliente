package br.com.alfac.food.core.application.pedido.dto;

import java.util.List;

public class PedidoDTO {
    private List<ComboDTO> combos;
    private Long clienteId;
    private Long id;
    // private UUID clienteUuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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