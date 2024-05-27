package br.com.alfac.food.core.exception.combo;

import br.com.alfac.food.core.exception.FoodError;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;
import br.com.alfac.food.core.exception.cliente.ClienteError;

public class ComboError extends FoodErrosImpl {

    public static final FoodError COMBO_VAZIO = new ClienteError("001", "Combo não pode ser vazio");


    private static final String APP_PREFIX = "CB";


    public ComboError(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public ComboError(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }

}
