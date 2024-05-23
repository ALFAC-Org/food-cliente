package br.com.alfac.food.core.application.pedido.dto;

import java.util.List;

import br.com.alfac.food.core.domain.pedido.StatusPedido;

public class PedidoDTO {
    private List<ComboDTO> combos;
    private Long clienteId;
    private Long id;
    private StatusPedido statusPedido;
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

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}