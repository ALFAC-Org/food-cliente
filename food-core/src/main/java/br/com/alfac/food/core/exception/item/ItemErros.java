package br.com.alfac.food.core.exception.item;

import br.com.alfac.food.core.exception.FoodErros;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;

public class ItemErros extends FoodErrosImpl {
    public static final FoodErros ITEM_NAO_ENCONTRADO = new ItemErros("001", "Item não encontrado", StatusCode.NOT_FOUND);
    public static final FoodErros CATEGORIA_SEM_ITENS = new ItemErros("002", "Categoria sem itens cadastrados", StatusCode.NOT_FOUND);

    private static final String APP_PREFIX = "ITEM";

    public ItemErros(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public ItemErros(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }
}
