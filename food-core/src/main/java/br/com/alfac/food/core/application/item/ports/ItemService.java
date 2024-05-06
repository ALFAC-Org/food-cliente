package br.com.alfac.food.core.application.item.ports;

import br.com.alfac.food.core.domain.item.Item;

public interface ItemService {
    
    public void cadastrarItem(Item item);
    
    public void atualizarItem(Integer idItem, Item item);
    
    public void excluirItem(Integer idItem);
    
}
