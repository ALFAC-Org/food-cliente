package br.com.alfac.food.core.application.pedido.adapters.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.pedido.dto.LancheDTO;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.domain.pedido.Lanche;
import br.com.alfac.food.core.utils.CollectionsUtils;

public final class ItemPedidoMapper {

    public static ItemDTO mapearParaItemDTO(Item item) {
        
        if(item != null){
            ItemDTO itemDTO = new ItemDTO();

            if(item instanceof Lanche){
                Lanche lanche = (Lanche) item;

                LancheDTO lancheDTO = new LancheDTO();
                lancheDTO.setObservacoes(lanche.getObservacoes());

                List<ItemDTO> complementosDTO = new ArrayList<>();
                if(CollectionsUtils.naoVazio(lanche.getComplementos())){
                    for(Item complemento : lanche.getComplementos()){
                        complementosDTO.add(mapearParaItemDTO(complemento));
                    }
                }
                lancheDTO.setComplementos(complementosDTO);
                itemDTO = lancheDTO;
            } 

            itemDTO.setId(item.getId());
            itemDTO.setPreco(item.getPreco());
            itemDTO.setCategoria(item.getCategoria());
            itemDTO.setNome(item.getNome());

            return itemDTO;
        }

        return null;
    }

}