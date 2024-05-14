package br.com.alfac.food.api.config;

import br.com.alfac.food.api.config.exception.ApiError;
import br.com.alfac.food.api.config.exception.ApiErrorItem;
import br.com.alfac.food.core.exception.FoodException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@ControllerAdvice
public class FoodExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ops, ocorreu um erro interno.",
                "INTERNAL_SERVER_ERROR"
        );
        return buildResponseEntity(error);
    }

    @ExceptionHandler(FoodException.class)
    public ResponseEntity<ApiError> handleFoodException(FoodException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
        ApiError error =ApiError.createError(ex);
        return buildResponseEntity(error);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
        ApiError apiError = ApiError.createDefaultApiValidationError();
        Iterator var4 = ex.getConstraintViolations().iterator();

        while (var4.hasNext()) {
            ConstraintViolation error = (ConstraintViolation) var4.next();
            apiError.getArguments().add(new ApiErrorItem("", error.getMessage(), error.getPropertyPath().toString()));
        }


        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
        ApiError apiError = ApiError.createDefaultApiValidationError();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                apiError.addArguments(new ApiErrorItem("", error.getDefaultMessage(), error.getField()))
        );

        return buildResponseEntity(apiError);
    }



    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}