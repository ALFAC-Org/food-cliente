package br.com.alfac.food.core.application.item.usecases;

import java.util.List;

import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodException;

public class ConsultarItensUseCase {

    private final RepositorioItemGateway itemRepository;

    public ConsultarItensUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> execute() throws FoodException {
        List<Item> itemList = itemRepository.consultarItens();
        return itemList;
    }

}
