package br.com.alfac.food.infra.pedido.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ItemRequest {
    @Schema(example = "15")
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}