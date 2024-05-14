package br.com.alfac.food.core.application.item.ports;

import java.util.List;

import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;

public interface ItemService {
    
    public List<ItemDTO> consultarItens() throws Exception;

    public List<ItemDTO> consultarItensPorCategoria(CategoriaItem categoria) throws Exception;

    public ItemDTO consultarItemPorId(String id) throws Exception;

    public void cadastrarItem(Item item);
    
    public ItemDTO atualizarItem(Integer idItem, ItemDTO item) throws Exception;
    
    public ItemDTO excluirItem(Integer idItem) throws Exception;
    
}
