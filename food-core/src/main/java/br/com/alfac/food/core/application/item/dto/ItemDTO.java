package br.com.alfac.food.core.application.item.dto;

import java.util.UUID;

import br.com.alfac.food.core.domain.item.CategoriaItem;


public class ItemDTO {
    private UUID id;
    private String nome;
    private Double preco;
    private CategoriaItem categoria;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public CategoriaItem getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaItem categoria) {
        this.categoria = categoria;
    }
}