package br.com.alfac.food.infra.item.mapper;

import org.mapstruct.Mapper;

import br.com.alfac.food.infra.item.dto.ItemRequest;
import br.com.alfac.food.core.application.item.dto.ItemDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDTO toDTO(ItemRequest itemRequest);
}
