package br.com.alfac.food.core.application.item.usecases;

import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodException;

public class ExcluirItemUseCase {

    private final RepositorioItemGateway itemRepository;

    public ExcluirItemUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item execute(Long id) throws FoodException {
        Item item = itemRepository.excluirItem(id);
        return item;
    }

}
