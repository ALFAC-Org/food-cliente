package br.com.alfac.food.core.domain.pedido;

import java.util.List;

import br.com.alfac.food.core.domain.item.Item;

public class Lanche extends Item {
    private List<Complemento> complementos;
    private String observacao;
    
}