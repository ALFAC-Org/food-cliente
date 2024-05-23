package br.com.alfac.food.core.exception.pedido;

import br.com.alfac.food.core.exception.FoodErros;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;

public class PedidoErros extends FoodErrosImpl {
    public static final FoodErros PEDIDO_NAO_ENCONTRADO = new PedidoErros("001", "Pedido não encontrado",
        StatusCode.NOT_FOUND);

    private static final String APP_PREFIX = "PEDIDO";

    public PedidoErros(final String errorCode, final String errorMessage) {
      super(APP_PREFIX, errorCode, errorMessage);
    }

    public PedidoErros(final String errorCode, final String errorMessage, final StatusCode statusCode) {
      super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }
}
