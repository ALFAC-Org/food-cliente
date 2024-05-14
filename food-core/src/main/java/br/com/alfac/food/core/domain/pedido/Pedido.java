package br.com.alfac.food.core.domain.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.domain.base.AggregateRoot;
import br.com.alfac.food.core.domain.cliente.Cliente;

public class Pedido implements AggregateRoot {
    private Cliente cliente;
    private List<Combo> combos;


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
    
}