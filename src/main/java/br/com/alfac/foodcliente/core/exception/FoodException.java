package br.com.alfac.foodcliente.core.exception;

import java.util.List;

public class FoodException extends Exception {

    private final FoodError foodError;
    private final List<FoodError> foodErrors;

    public FoodException(FoodError foodError) {
        super(foodError.getErrorMessage());
        this.foodError = foodError;
        this.foodErrors = null;
    }


    public FoodError getFoodErros() {
        return foodError;
    }

    public List<FoodError> getFoodErrors() {
        return foodErrors;
    }

    private static String getMessages(final List<FoodError> foodErrors) {
        List<String> mensagensDeErro = foodErrors.stream().map(FoodError::getErrorMessage).toList();
        return String.join(",", mensagensDeErro);
    }
}
