package br.com.alfac.food.core.exception.pedido;

import br.com.alfac.food.core.exception.FoodError;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;

import java.util.List;

public class PedidoErros extends FoodErrosImpl {
    public static final FoodError PEDIDO_NAO_ENCONTRADO = new PedidoErros("001", "Pedido não encontrado", StatusCode.NOT_FOUND);
    public static final FoodError STATUS_PEDIDO_JA_FINALIZADO = new PedidoErros("002", "Status do pedido já finalizado não permite alteração.");
    public static final FoodError PEDIDO_NAO_PAGO = new PedidoErros("003", "Pedido não pago.");
    public static final FoodError PEDIDO_COMBO_NULL = new PedidoErros("004", "Não é possível adicionar combo nulo.");
    public static final FoodError DATA_CADASTRO_OBRIGATORIO = new PedidoErros("005", "Data de cadastro é obrigatória.");
    public static final FoodError ID_OBRIGATORIO = new PedidoErros("006", "Id é obrigatório.");
    public static final FoodError STATUS_OBRIGATORIO = new PedidoErros("007", "Status é obrigatório.");

    private static final String APP_PREFIX = "PEDIDO";

    public PedidoErros(final String errorCode, final String errorMessage) {
      super(APP_PREFIX, errorCode, errorMessage);
    }

    public PedidoErros(final String errorCode, final String errorMessage, final StatusCode statusCode) {
      super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }

    // criar mensagens de erro

}
