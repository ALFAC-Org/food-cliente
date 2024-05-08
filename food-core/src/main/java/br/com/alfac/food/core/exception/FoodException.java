package br.com.alfac.food.core.exception;

public class FoodException extends Exception {

    private final FoodErros foodErros;

    public FoodException(FoodErros foodErros) {
        super(foodErros.getErrorMessage());
        this.foodErros = foodErros;
    }

    public FoodException(FoodErros foodErros, Throwable e) {
        super(foodErros.getErrorMessage(), e);
        this.foodErros = foodErros;
    }

    public FoodException(FoodErros foodErros, Object... args) {
        super(String.format(foodErros.getErrorMessage(), args));
        this.foodErros = foodErros;
    }

    public FoodException(FoodErros foodErros, Throwable e,  Object... args) {
        super(String.format(foodErros.getErrorMessage(), args), e);
        this.foodErros = foodErros;
    }

    public FoodErros getFoodErros() {
        return foodErros;
    }

}
