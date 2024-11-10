package br.com.alfac.food.core.exception;


public interface FoodError {

    String getErrorCode();

    String getErrorMessage();

    int getStatusCode();
}
