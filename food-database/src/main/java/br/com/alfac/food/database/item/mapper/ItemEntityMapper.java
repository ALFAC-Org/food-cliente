package br.com.alfac.food.database.item.mapper;

import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.database.item.entity.ItemEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemEntityMapper {

    ItemEntity toEntity(Item item);

    Item toDomain(ItemEntity Item);

}
