package br.com.alfac.food.api.config;

import br.com.alfac.food.api.config.exception.ApiError;
import br.com.alfac.food.api.config.exception.ApiErrorItem;
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

    private static final Logger LOGGER_EXCEPTION = LoggerFactory.getLogger(FoodExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        LOGGER_EXCEPTION.error(ex.getLocalizedMessage(), ex);
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                "INTERNAL_SERVER_ERROR",
                "System"
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex) {
        LOGGER_EXCEPTION.error(ex.getLocalizedMessage(), ex);
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                "INTERNAL_SERVER_ERROR",
                "System"
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
        LOGGER_EXCEPTION.error(ex.getLocalizedMessage(), ex);
        ApiError apiError = createDefaultApiValidationError();
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
        LOGGER_EXCEPTION.error(ex.getLocalizedMessage(), ex);
        ApiError apiError = createDefaultApiValidationError();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                apiError.addArguments(new ApiErrorItem("", error.getDefaultMessage(), error.getField()))
        );

        return buildResponseEntity(apiError);
    }

    private ApiError createDefaultApiValidationError() {
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida",
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "System"
        );
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
