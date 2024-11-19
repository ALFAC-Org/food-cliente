package br.com.alfac.foodcliente.core.exception;


public interface FoodError {

    String getErrorCode();

    String getErrorMessage();

    int getStatusCode();
}
