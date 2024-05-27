package br.com.alfac.food.core.exception.pedido;

import br.com.alfac.food.core.exception.FoodError;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;

public class PedidoErros extends FoodErrosImpl {
    public static final FoodError PEDIDO_NAO_ENCONTRADO = new PedidoErros("001", "Pedido não encontrado", StatusCode.NOT_FOUND);
    public static final FoodError STATUS_PEDIDO_JA_FINALIZADO = new PedidoErros("002", "Status do pedido já finalizado não permite alteração.");
    public static final FoodError PEDIDO_NAO_PAGO = new PedidoErros("003", "Pedido não pago.");

    private static final String APP_PREFIX = "PEDIDO";

    public PedidoErros(final String errorCode, final String errorMessage) {
      super(APP_PREFIX, errorCode, errorMessage);
    }

    public PedidoErros(final String errorCode, final String errorMessage, final StatusCode statusCode) {
      super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }
}
