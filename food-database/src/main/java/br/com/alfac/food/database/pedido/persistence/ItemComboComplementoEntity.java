package br.com.alfac.food.database.pedido.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

import br.com.alfac.food.database.item.persistence.ItemEntity;

@Entity
@Table(name = "item_combo_complemento")
public class ItemComboComplementoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "id_item")
    @NotNull(message = "Item é obrigatório")
    private ItemEntity item;

    @NotNull(message = "Preço do item é obrigatório")
    private Double preco;

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
    
}