package br.com.alfac.food.database.pedido.mapper;

import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.database.pedido.persistence.ItemComboComplementoEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemComboComplementoEntityMapper {

    @Mapping(target = "item.id", source = "id")
    @Mapping(target = "id", source = "id", ignore = true)
    ItemComboComplementoEntity toEntity(Item item);

    @Mapping(target = "id", source = "item.id")
    @Mapping(target = "nome", source = "item.nome")
    @Mapping(target = "categoria", source = "item.categoria")
    Item toDomain(ItemComboComplementoEntity item);

}
    