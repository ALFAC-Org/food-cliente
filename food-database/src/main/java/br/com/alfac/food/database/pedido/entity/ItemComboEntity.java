package br.com.alfac.food.database.pedido.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

import br.com.alfac.food.database.item.entity.ItemEntity;

@Entity
@Table(name = "item_combo")
public class ItemComboEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "id_item")
    @NotNull(message = "Item é obrigatório")
    private ItemEntity item;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_item_combo")
    private List<ItemComboComplementoEntity> complementos;

    @NotNull(message = "Preço do item é obrigatório")
    private Double preco;

    private String observacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<ItemComboComplementoEntity> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<ItemComboComplementoEntity> complementos) {
        this.complementos = complementos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }    
    
}