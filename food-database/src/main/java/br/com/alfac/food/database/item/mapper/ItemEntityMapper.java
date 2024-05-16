package br.com.alfac.food.database.item.mapper;

import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.database.item.entity.ItemEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemEntityMapper {

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "uuid", source = "id")
    ItemEntity toEntity(Item item);

    @Mapping(target = "id", source = "uuid")
    Item toDomain(ItemEntity Item);

}
