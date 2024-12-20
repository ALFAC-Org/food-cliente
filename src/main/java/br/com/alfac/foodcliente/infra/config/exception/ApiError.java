package br.com.alfac.foodcliente.infra.config.exception;

import br.com.alfac.foodcliente.core.exception.FoodException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class ApiError {

    public static final String REQUISICAO_INVALIDA = "Requisição inválida";
    private String code;
    private String message;
    private Long date;
    private List<ApiErrorItem> arguments = new ArrayList<>();
    private Integer status;

    public ApiError(int status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.date = new Date().getTime();
    }

    public static ApiError createError(FoodException ex) {

        if (Objects.nonNull(ex.getFoodErros())) {
            return new ApiError(
                    HttpStatus.valueOf(ex.getFoodErros().getStatusCode()).value(),
                    ex.getMessage(),
                    ex.getFoodErros().getErrorCode()
            );
        } else {
            ApiError apiError = ApiError.createDefaultApiValidationError();
            ex.getFoodErrors().forEach(foodError -> apiError.getArguments().add(new ApiErrorItem( foodError.getErrorCode(), foodError.getErrorMessage())));
            return apiError;
        }


    }

    public static ApiError createDefaultApiValidationError() {
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                REQUISICAO_INVALIDA,
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDate() {
        return this.date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<ApiErrorItem> getArguments() {
        return this.arguments;
    }

    public void setArguments(List<ApiErrorItem> arguments) {
        this.arguments = arguments;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void addArguments(ApiErrorItem apiErrorItem) {
        this.arguments.add(apiErrorItem);

    }
}