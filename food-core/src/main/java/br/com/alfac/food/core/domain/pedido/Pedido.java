package br.com.alfac.food.core.domain.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.domain.base.AggregateRoot;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pedido.PedidoErros;

public class Pedido implements AggregateRoot {
    private Long id;
    private Cliente cliente;
    private StatusPedido status;
    private List<Combo> combos;

    public void atualizarStatus() throws FoodException {

        if (StatusPedido.FINALIZADO.equals(this.status)) {
            throw new FoodException(PedidoErros.STATUS_PEDIDO_JA_FINALIZADO);
        }

        this.status = this.status.getProximoStatus();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public void setCombos(List<Combo> combos) {
        this.combos = combos;
    }

    public void adicionaCombo(Combo combo) {
        if(combos == null){
            combos = new ArrayList<>();
        }
        this.combos.add(combo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido statusPedido) {
        this.status = statusPedido;
    }
    
}