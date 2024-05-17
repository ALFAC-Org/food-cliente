package br.com.alfac.food.core.domain.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.domain.item.Item;

public class Lanche extends Item {
    private List<Item> complementos;
    private String observacoes;

    public List<Item> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<Item> complementos) {
        this.complementos = complementos;
    }

    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacao) {
        this.observacoes = observacao;
    }

    public void adicionaComplemento(Item complemento) {
        if(complementos == null){
            complementos = new ArrayList<>();
        }
        this.complementos.add(complemento);
    }
    
}