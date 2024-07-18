package br.com.alfac.food.core.exception.pagamento;

import br.com.alfac.food.core.exception.FoodError;
import br.com.alfac.food.core.exception.FoodErrosImpl;
import br.com.alfac.food.core.exception.StatusCode;

import java.util.List;

public class PagamentoErro extends FoodErrosImpl {

    public static final FoodError PAGAMENTO_JA_REALIZADO = new PagamentoErro("001", "Pagamento já realizado");
    public static final FoodError STATUS_INVALIDO_APROVACAO = new PagamentoErro("002", "Status atual do pagamento não permite aprovação");
    public static final FoodError PEDIDO_ID_OBRIGATORIO = new PagamentoErro("003", "Id do pedido é obrigatório para registrar o pagamento");
    public static final FoodError STATUS_OBRIGATORIO = new PagamentoErro("004", "Status do pagamento é obrigatório");
    public static final FoodError ID_OBRIGATORIO = new PagamentoErro("005", "Id do pagamento é obrigatório");
    public static final FoodError DATA_PAGAMENTO_OBRIGATORIO = new PagamentoErro("006", "Data do pagamento é obrigatório");
    public static final FoodError PAGAMENTO_NAO_ENCONTRADO = new PagamentoErro("007", "Pagamento não encontrado");

    private static final String APP_PREFIX = "PGTO";

    public PagamentoErro(final String errorCode, final String errorMessage) {
      super(APP_PREFIX, errorCode, errorMessage);
    }

    public PagamentoErro(final String errorCode, final String errorMessage, final StatusCode statusCode) {
      super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }
}
