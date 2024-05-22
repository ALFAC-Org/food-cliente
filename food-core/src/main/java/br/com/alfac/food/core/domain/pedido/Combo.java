package br.com.alfac.food.core.domain.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.utils.CollectionsUtils;

public class Combo {
    private Lanche lanche;
    private Item acompanhamento;
    private Item bebida;
    private Item sobremesa;

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }

    public Item getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Item acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Item getBebida() {
        return bebida;
    }

    public void setBebida(Item bebida) {
        this.bebida = bebida;
    }

    public Item getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(Item sobremesa) {
        this.sobremesa = sobremesa;
    }

    public List<Item> getItens() {
        List<Item> itens = new ArrayList<>();
        if (lanche != null) {
            itens.add(lanche);
        }
        if (acompanhamento != null) {
            itens.add(acompanhamento);
        }
        if (bebida != null) {
            itens.add(bebida);
        }
        if (sobremesa != null) {
            itens.add(sobremesa);
        }
        return itens;
    }

    public void validarItens() {

        if(CollectionsUtils.vazio(getItens())){
            //erro combo sem itens
        }

        if (lanche != null) {
            if(CategoriaItem.LANCHE.equals(lanche.getCategoria()) == false){
                //erro
            }
            if(lanche.getComplementos() != null){
                for(Item complemento : lanche.getComplementos()){
                    if(CategoriaItem.COMPLEMENTO.equals(complemento.getCategoria()) == false){
                        //erro
                    }
                }
            }
        }
        if (acompanhamento != null) {
            if(CategoriaItem.ACOMPANHAMENTO.equals(acompanhamento.getCategoria()) == false){
                //erro
            }
        }
        if (bebida != null) {
            if(CategoriaItem.BEBIDA.equals(bebida.getCategoria()) == false){
                //erro
            }
        }
        if (sobremesa != null) {
            if(CategoriaItem.SOBREMESA.equals(sobremesa.getCategoria()) == false){
                //erro
            }
        }
    }

}