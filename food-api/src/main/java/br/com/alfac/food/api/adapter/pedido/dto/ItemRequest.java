package br.com.alfac.food.api.adapter.pedido.dto;

import br.com.alfac.food.core.application.pedido.dto.ItemMenuDTO;

public class ItemRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemMenuDTO toDTO() {
        ItemMenuDTO itemMenuDTO = new ItemMenuDTO();
        itemMenuDTO.setId(id);
        return itemMenuDTO;
    }

}