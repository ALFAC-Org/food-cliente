package br.com.alfac.food.core.domain.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.domain.item.Item;

public class Lanche extends Item {
    private List<Complemento> complementos;
    private String observacao;

    public List<Complemento> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<Complemento> complementos) {
        this.complementos = complementos;
    }

    public String getObservacao() {
        return observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void adicionaComplemento(Complemento complemento) {
        if(complementos == null){
            complementos = new ArrayList<>();
        }
        this.complementos.add(complemento);
    }
    
}