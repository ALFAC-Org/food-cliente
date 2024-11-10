package br.com.alfac.food.core.exception;

import java.util.List;

public class FoodException extends Exception {

    private final FoodError foodError;
    private final List<FoodError> foodErrors;

    public FoodException(final List<FoodError> foodErrors) {
        super(getMessages(foodErrors));
        this.foodErrors = foodErrors;
        this.foodError = null;
    }

    public FoodException(FoodError foodError) {
        super(foodError.getErrorMessage());
        this.foodError = foodError;
        this.foodErrors = null;
    }

    public FoodException(final List<FoodError> foodErrors, Throwable e) {
        super(getMessages(foodErrors), e);
        this.foodErrors = foodErrors;
        this.foodError = null;
    }
    public FoodException(FoodError foodError, Throwable e) {
        super(foodError.getErrorMessage(), e);
        this.foodError = foodError;
        this.foodErrors = null;
    }


    public FoodException(FoodError foodError, Object... args) {
        super(String.format(foodError.getErrorMessage(), args));
        this.foodError = foodError;
        this.foodErrors = null;
    }

    public FoodException(FoodError foodError, Throwable e, Object... args) {
        super(String.format(foodError.getErrorMessage(), args), e);
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
