package br.com.alfac.foodcliente.infra.config.exception;

import br.com.alfac.foodcliente.core.exception.FoodError;
import br.com.alfac.foodcliente.core.exception.FoodException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApiErrorTest {

    @Test
    void testApiErrorConstructor() {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", "400");
        assertEquals(HttpStatus.BAD_REQUEST.value(), apiError.getStatus());
        assertEquals("Bad Request", apiError.getMessage());
        assertEquals("400", apiError.getCode());
        assertNotNull(apiError.getDate());
        assertTrue(apiError.getArguments().isEmpty());
    }

    @Test
    void testCreateDefaultApiValidationError() {
        ApiError apiError = ApiError.createDefaultApiValidationError();
        assertEquals(HttpStatus.BAD_REQUEST.value(), apiError.getStatus());
        assertEquals(ApiError.REQUISICAO_INVALIDA, apiError.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), apiError.getCode());
    }

    @Test
    void testSettersAndGetters() {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", "400");
        apiError.setCode("401");
        apiError.setMessage("Unauthorized");
        apiError.setDate(new Date().getTime());
        apiError.setStatus(HttpStatus.UNAUTHORIZED.value());
        apiError.setArguments(Collections.singletonList(new ApiErrorItem("401", "Unauthorized")));

        assertEquals("401", apiError.getCode());
        assertEquals("Unauthorized", apiError.getMessage());
        assertNotNull(apiError.getDate());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), apiError.getStatus());
        assertEquals(1, apiError.getArguments().size());
    }

    @Test
    void testAddArguments() {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", "400");
        ApiErrorItem apiErrorItem = new ApiErrorItem("401", "Unauthorized");
        apiError.addArguments(apiErrorItem);

        assertEquals(1, apiError.getArguments().size());
        assertEquals(apiErrorItem, apiError.getArguments().get(0));
    }

    @Test
    void testCreateErrorWithFoodErros() {
        FoodException ex = mock(FoodException.class);
        FoodError foodError = mock(FoodError.class);
        when(foodError.getStatusCode()).thenReturn(400);
        when(foodError.getErrorCode()).thenReturn("400");
        when(foodError.getErrorMessage()).thenReturn("Error message");
        when(ex.getFoodErros()).thenReturn(foodError);
        when(ex.getMessage()).thenReturn("Error message");

        ApiError apiError = ApiError.createError(ex);

        assertEquals(HttpStatus.BAD_REQUEST.value(), apiError.getStatus());
        assertEquals("Error message", apiError.getMessage());
        assertEquals("400", apiError.getCode());
        assertNotNull(apiError.getDate());
    }

    @Test
    void testCreateErrorWithoutFoodErros() {
        FoodException ex = mock(FoodException.class);
        when(ex.getFoodErros()).thenReturn(null);
        FoodError foodError = mock(FoodError.class);
        when(foodError.getErrorCode()).thenReturn("400");
        when(foodError.getErrorMessage()).thenReturn("Error message");
        when(ex.getFoodErrors()).thenReturn(Collections.singletonList(foodError));

        ApiError apiError = ApiError.createError(ex);

        assertEquals(HttpStatus.BAD_REQUEST.value(), apiError.getStatus());
        assertEquals(ApiError.REQUISICAO_INVALIDA, apiError.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), apiError.getCode());
        assertNotNull(apiError.getDate());
        assertEquals(1, apiError.getArguments().size());
        assertEquals("400", apiError.getArguments().get(0).getCode());
        assertEquals("Error message", apiError.getArguments().get(0).getMessage());
    }
}
