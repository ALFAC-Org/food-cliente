package br.com.alfac.food.api.adapter.item.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public class ItemRequest {

    @Schema(example = "Hamburguer")
    @NotEmpty(message = "Nome é obrigatório.")
    private String nome;

    @Schema(example = "11.50")
    @NotEmpty(message = "Preço é obrigatorio.")
    private double preco;

    @Schema(example = "LANCHE, ACOMPANHAMENTO, BEBIDA")
    @NotEmpty(message = "Categoria é obrigatória.")
    // TODO: Adicionar validação para aceitar apenas os valores da enum CategoriaItem
    private String categoria;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}