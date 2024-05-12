package br.com.alfac.food.core.application.item.services;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.item.ports.ItemRepository;
import br.com.alfac.food.core.application.item.ports.ItemService;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.item.ItemErros;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> consultarItens() throws Exception {
        List<Item> itemList = itemRepository.consultarItens();

        if (itemList == null || itemList.isEmpty()) {
            throw new FoodException(ItemErros.ITEM_NAO_ENCONTRADO);
        }

        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : itemList) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setNome(item.getNome());
            itemDTO.setPreco(item.getPreco());
            itemDTO.setCategoria(item.getCategoria().toString());
            itemDTOList.add(itemDTO);
        }

        return itemDTOList;
    }

    @Override
    public List<ItemDTO> consultarItensPorCategoria(CategoriaItem categoria) throws Exception {
        List<Item> itemList = itemRepository.consultarItensPorCategoria(categoria);

        if (itemList == null || itemList.isEmpty()) {
            throw new FoodException(ItemErros.ITEM_NAO_ENCONTRADO);
        }

        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : itemList) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setNome(item.getNome());
            itemDTO.setPreco(item.getPreco());
            itemDTO.setCategoria(item.getCategoria().toString());
            itemDTOList.add(itemDTO);
        }

        return itemDTOList;
    }

    public void cadastrarItem(Item item) {

    }

    public void atualizarItem(Integer idItem, Item item) {

    }

    public void excluirItem(Integer idItem) {

    }

}
