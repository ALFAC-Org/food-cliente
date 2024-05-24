package br.com.alfac.food.core.exception.pagamento;

import br.com.alfac.food.core.exception.FoodError;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;

public class PagamentoErro extends FoodErrosImpl {

    public static final FoodError PAGAMENTO_JA_REALIZADO = new PagamentoErro("001", "Pagamento já realizado");

    private static final String APP_PREFIX = "PGTO";

    public PagamentoErro(final String errorCode, final String errorMessage) {
      super(APP_PREFIX, errorCode, errorMessage);
    }

    public PagamentoErro(final String errorCode, final String errorMessage, final StatusCode statusCode) {
      super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }
}
