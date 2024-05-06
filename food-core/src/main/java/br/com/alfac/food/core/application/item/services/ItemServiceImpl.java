package br.com.alfac.food.core.application.item.services;

import br.com.alfac.food.core.application.item.ports.ItemRepository;
import br.com.alfac.food.core.application.item.ports.ItemService;
import br.com.alfac.food.core.domain.item.Item;

public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
    
    public void cadastrarItem(Item item){

    }
    
    public void atualizarItem(Integer idItem, Item item){
        
    }
    
    public void excluirItem(Integer idItem){
        
    }

}
