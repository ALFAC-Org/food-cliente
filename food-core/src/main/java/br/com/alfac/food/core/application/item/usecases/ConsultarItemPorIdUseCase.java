package br.com.alfac.food.core.application.item.usecases;

import br.com.alfac.food.core.application.item.gateways.ItemRepository;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.item.ItemError;

import java.util.Optional;

public class ConsultarItemPorIdUseCase {

    private final ItemRepository itemRepository;

    public ConsultarItemPorIdUseCase(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Item executar(Long id) throws FoodException {
        Optional<Item> itemOpt = itemRepository.consultarItemPorId(id);

        return itemOpt.orElseThrow(() -> new FoodException(ItemError.ITEM_NAO_ENCONTRADO));
    }


}
