package br.com.alfac.food.core.exception.cliente;

import br.com.alfac.food.core.exception.FoodError;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;

public class ClienteError extends FoodErrosImpl {

    public static final FoodError CLIENTE_NAO_ENCONTRADO = new ClienteError("001", "Cliente não encontrado", StatusCode.NOT_FOUND);
    public static final FoodError CLIENTE_NAO_EXISTENTE = new ClienteError("002", "Cliente não existente");


    private static final String APP_PREFIX = "CLI";


    public ClienteError(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public ClienteError(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }


}
