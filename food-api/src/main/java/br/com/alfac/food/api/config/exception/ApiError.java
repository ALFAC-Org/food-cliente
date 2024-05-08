package br.com.alfac.food.api.config.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public final class ApiError {
    private String id;
    private String code;
    private String message;
    private String owner;
    private Long date;
    private List<ApiErrorItem> arguments = new ArrayList<>();
    private Integer status;

    public ApiError(int status, String message, String code, String owner) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.owner = owner;
        this.date = new Date().getTime();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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