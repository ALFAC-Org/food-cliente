package br.com.alfac.food.database.pedido.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.domain.pedido.Lanche;
import br.com.alfac.food.database.pedido.entity.ItemComboComplementoEntity;
import br.com.alfac.food.database.pedido.entity.ItemComboEntity;

@Mapper(componentModel = "spring")
public interface ItemComboEntityMapper {

    ItemComboComplementoEntityMapper itemComboComplementoEntityMapper = 
        Mappers.getMapper(ItemComboComplementoEntityMapper.class);

    @Mapping(target = "item.id", source = "id")
    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "complementos", ignore = true)
    @Mapping(target = "observacoes", ignore = true)
    ItemComboEntity itemToEntity(Item item);

    @Mapping(target = "item.id", source = "id")
    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "complementos", source = "complementos", qualifiedByName = "itemComboToEntityParser")
    ItemComboEntity lancheToEntity(Lanche lanche);

    @Mapping(target = "id", source = "item.id")
    @Mapping(target = "nome", source = "item.nome")
    @Mapping(target = "categoria", source = "item.categoria")
    Item toItemDomain(ItemComboEntity item);

    @Mapping(target = "id", source = "item.id")
    @Mapping(target = "nome", source = "item.nome")
    @Mapping(target = "categoria", source = "item.categoria")
    @Mapping(target = "complementos", source = "complementos", qualifiedByName = "itemComboToDomainParser")
    Lanche toLancheDomain(ItemComboEntity item); 

    @Named("itemComboToEntityParser")
    default List<ItemComboComplementoEntity> itemComboToEntityParser(List<Item> complementos) {
        List<ItemComboComplementoEntity> complementosEntities = null;
        if(complementos != null){
            complementosEntities = new ArrayList<>(); 
            for(Item complemento : complementos){
                complementosEntities.add(itemComboComplementoEntityMapper.toEntity(complemento));
            }
        }
        return complementosEntities;
    }

    @Named("itemComboToDomainParser")
    default List<Item> itemComboToDomainParser(List<ItemComboComplementoEntity> complementosEntities) {
        List<Item> complementos = null;
        if(complementosEntities != null){
            complementos = new ArrayList<>(); 
            for(ItemComboComplementoEntity complementoEntity : complementosEntities){
                complementos.add(itemComboComplementoEntityMapper.toDomain(complementoEntity));
            }
        }
        return complementos;
    }

}
