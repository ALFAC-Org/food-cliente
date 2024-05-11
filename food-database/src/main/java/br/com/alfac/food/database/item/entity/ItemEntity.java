package br.com.alfac.food.database.item.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "item")
public class ItemEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

}