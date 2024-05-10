package br.com.alfac.food.core.exception.cliente;

import br.com.alfac.food.core.exception.FoodErros;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;

public class ClienteErros extends FoodErrosImpl {

    public static final FoodErros CLIENTE_NAO_ENCONTRADO = new ClienteErros("001", "Cliente não encontrado", StatusCode.NOT_FOUND);


    private static final String APP_PREFIX = "CLI";


    public ClienteErros(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public ClienteErros(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }


}
