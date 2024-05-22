package br.com.alfac.food.database.pedido.mapper;

import br.com.alfac.food.database.cliente.mapper.ClienteEntityMapper;
import br.com.alfac.food.database.item.entity.ItemEntity;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.domain.pedido.Combo;
import br.com.alfac.food.core.domain.pedido.Lanche;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.database.pedido.entity.ComboEntity;
import br.com.alfac.food.database.pedido.entity.ItemComboComplementoEntity;
import br.com.alfac.food.database.pedido.entity.ItemComboEntity;
import br.com.alfac.food.database.pedido.entity.PedidoEntity;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

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

}
