package br.com.alfac.food.database.pedido.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "item_combo")
public class ItemComboEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

}