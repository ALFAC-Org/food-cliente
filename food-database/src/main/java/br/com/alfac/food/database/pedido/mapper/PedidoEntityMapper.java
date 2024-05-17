package br.com.alfac.food.database.pedido.mapper;

import br.com.alfac.food.database.cliente.mapper.ClienteEntityMapper;
import br.com.alfac.food.database.item.entity.ItemEntity;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.domain.pedido.Combo;
import br.com.alfac.food.core.domain.pedido.Lanche;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.database.pedido.entity.ComboEntity;
import br.com.alfac.food.database.pedido.entity.ItemComboEntity;
import br.com.alfac.food.database.pedido.entity.PedidoEntity;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {ClienteEntityMapper.class})
public interface PedidoEntityMapper {

    @Mapping(target = "combos", source = "combos", qualifiedByName = "combosToEntityParser")
    PedidoEntity toEntity(Pedido pedido);

    @Mapping(target = "combos", source = "combos", qualifiedByName = "combosToDomainParser")
    Pedido toDomain(PedidoEntity pedido);

    @Named("combosToEntityParser")
    default List<ComboEntity> combosToEntity(List<Combo> combos) {
        List<ComboEntity> combosEntities = new ArrayList<>();

        for (Combo combo : combos) {
            List<ItemComboEntity> itensComboEntities = new ArrayList<>();
            ComboEntity comboEntity = new ComboEntity();

            for(Item item : combo.getItens()){
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setId(item.getId());

                ItemComboEntity itemComboEntity = new ItemComboEntity();
                itemComboEntity.setItem(itemEntity);
                itemComboEntity.setPreco(item.getPreco());

                if(item instanceof Lanche){
                    itemComboEntity.setObservacoes(((Lanche) item).getObservacoes());
                }

                itensComboEntities.add(itemComboEntity);
            }
        
            comboEntity.setItens(itensComboEntities);
            combosEntities.add(comboEntity);
        }
        return combosEntities;
    }
    

    @Named("combosToDomainParser")
    default List<Combo> combosToDomain(List<ComboEntity> combos) {
        return new ArrayList<>();
    }

}
