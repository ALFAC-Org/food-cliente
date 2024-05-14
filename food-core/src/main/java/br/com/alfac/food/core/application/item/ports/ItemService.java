package br.com.alfac.food.core.application.item.ports;

import java.util.List;

import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodException;

public interface ItemService {
    
    public List<ItemDTO> consultarItens() throws FoodException;

    public List<ItemDTO> consultarItensPorCategoria(CategoriaItem categoria) throws FoodException;

    public ItemDTO consultarItemPorId(String id) throws FoodException;

    public void cadastrarItem(Item item);
    
    public ItemDTO atualizarItem(Integer idItem, ItemDTO item) throws FoodException;
    
    public ItemDTO excluirItem(Integer idItem) throws FoodException;
    
}
