package br.com.alfac.food.core.application.item.usecases;

import java.util.List;

import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.item.ItemError;

public class ConsultarItensPorCategoriaUseCase {

    private final RepositorioItemGateway itemRepository;

    public ConsultarItensPorCategoriaUseCase(final RepositorioItemGateway itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> execute(CategoriaItem categoria) throws FoodException {
        List<Item> itemList = itemRepository.consultarItensPorCategoria(categoria);

        if (itemList == null || itemList.isEmpty()) {
            throw new FoodException(ItemError.CATEGORIA_SEM_ITENS);
        }

        return itemList;
    }

}