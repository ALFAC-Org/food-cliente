package br.com.alfac.food.database.pedido.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "combo")
public class ComboEntity implements Serializable {

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemComboEntity> itens;

}