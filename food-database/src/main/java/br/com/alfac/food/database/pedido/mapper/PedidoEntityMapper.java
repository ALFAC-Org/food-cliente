package br.com.alfac.food.database.pedido.mapper;

import br.com.alfac.food.database.cliente.mapper.ClienteEntityMapper;
import br.com.alfac.food.database.pedido.persistence.ComboEntity;
import br.com.alfac.food.database.pedido.persistence.ItemComboEntity;
import br.com.alfac.food.database.pedido.persistence.PedidoEntity;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.domain.pedido.Combo;
import br.com.alfac.food.core.domain.pedido.Lanche;
import br.com.alfac.food.core.domain.pedido.Pedido;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ClienteEntityMapper.class})
public interface PedidoEntityMapper {

    ItemComboEntityMapper itemComboEntityMapper = Mappers.getMapper(ItemComboEntityMapper.class);

    @Mapping(target = "combos", source = "combos", qualifiedByName = "combosToEntityParser")
    PedidoEntity toEntity(Pedido pedido);

    @Mapping(target = "combos", source = "combos", qualifiedByName = "combosToDomainParser")
    Pedido toDomain(PedidoEntity pedido);

    List<Pedido> toDomain(List<PedidoEntity> pedido);

    @Named("combosToEntityParser")
    default List<ComboEntity> combosToEntity(List<Combo> combos) {
        List<ComboEntity> combosEntities = new ArrayList<>();

        for (Combo combo : combos) {
            List<ItemComboEntity> itensComboEntities = new ArrayList<>();
            ComboEntity comboEntity = new ComboEntity();

            for(Item item : combo.getItens()){
                ItemComboEntity itemComboEntity = null;

                if(item instanceof Lanche){
                    itemComboEntity = itemComboEntityMapper.lancheToEntity((Lanche) item);
                } else {
                    itemComboEntity = itemComboEntityMapper.itemToEntity(item);
                }

                itensComboEntities.add(itemComboEntity);
            }
        
            comboEntity.setItens(itensComboEntities);
            combosEntities.add(comboEntity);
        }
        return combosEntities;
    }

    @Named("combosToDomainParser")
    default List<Combo> combosToDomain(List<ComboEntity> combosEntities) {
        List<Combo> combos = new ArrayList<>();

        for(ComboEntity comboEntity : combosEntities){
            Combo combo = new Combo();

            for(ItemComboEntity itemEntity : comboEntity.getItens()){
                if(CategoriaItem.LANCHE.equals(itemEntity.getItem().getCategoria())){
                    combo.setLanche(itemComboEntityMapper.toLancheDomain(itemEntity));
                }
                if(CategoriaItem.ACOMPANHAMENTO.equals(itemEntity.getItem().getCategoria())){
                    combo.setAcompanhamento(itemComboEntityMapper.toItemDomain(itemEntity));
                }
                if(CategoriaItem.BEBIDA.equals(itemEntity.getItem().getCategoria())){
                    combo.setBebida(itemComboEntityMapper.toItemDomain(itemEntity));
                }
                if(CategoriaItem.SOBREMESA.equals(itemEntity.getItem().getCategoria())){
                    combo.setSobremesa(itemComboEntityMapper.toItemDomain(itemEntity));
                }
            }
            combos.add(combo);
        }

        return combos;
    }

}
