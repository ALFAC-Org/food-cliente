package br.com.alfac.food.database.pedido.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "combo")
public class ComboEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_combo")
    @NotEmpty(message = "Obrigatório 1 ou mais itens")
    private List<ItemComboEntity> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemComboEntity> getItens() {
        return itens;
    }

    public void setItens(List<ItemComboEntity> itens) {
        this.itens = itens;
    }

}