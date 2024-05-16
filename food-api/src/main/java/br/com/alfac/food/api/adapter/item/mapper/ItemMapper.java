package br.com.alfac.food.api.adapter.item.mapper;

import org.mapstruct.Mapper;

import br.com.alfac.food.api.adapter.item.dto.ItemRequest;
import br.com.alfac.food.core.application.item.dto.ItemDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDTO toDTO(ItemRequest itemRequest);
}
