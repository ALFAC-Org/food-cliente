package br.com.alfac.foodcliente.unit.infra.config.exception;

import br.com.alfac.foodcliente.infra.config.exception.ApiError;
import br.com.alfac.foodcliente.infra.config.exception.FoodExceptionHandler;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodExceptionHandlerTest {

    @InjectMocks
    private FoodExceptionHandler foodExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @Test
    void testHandleException() {
        Exception ex = new Exception("Test Exception");
        ResponseEntity<ApiError> response = foodExceptionHandler.handleException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ops, ocorreu um erro interno.", response.getBody().getMessage());
        assertEquals("INTERNAL_SERVER_ERROR", response.getBody().getCode());
    }

    @Test
    void testHttpMessageNotReadableException() {
        InvalidFormatException cause = new InvalidFormatException(null, "Invalid format", null, String.class);
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Test Exception", cause);
        ResponseEntity<ApiError> response = foodExceptionHandler.httpMessageNotReadableException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid format", response.getBody().getMessage());
        assertEquals("BAD_REQUEST", response.getBody().getCode());
    }

    @Test
    void testHandleConstraintViolationException() {
        ConstraintViolationException ex = mock(ConstraintViolationException.class);
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        Path propertyPath = mock(Path.class);
        when(violation.getMessage()).thenReturn("Violation message");
        when(violation.getPropertyPath()).thenReturn(propertyPath);
        when(propertyPath.toString()).thenReturn("propertyPath");
        when(ex.getConstraintViolations()).thenReturn(Set.of(violation));

        ResponseEntity<ApiError> response = foodExceptionHandler.handleConstraintViolationException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Requisição inválida", response.getBody().getMessage());
        assertEquals("Bad Request", response.getBody().getCode());
        assertEquals(1, response.getBody().getArguments().size());
    }

    @Test
    void testHandleMethodArgumentNotValid() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ApiError> response = foodExceptionHandler.handleMethodArgumentNotValid(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Requisição inválida", response.getBody().getMessage());
        assertEquals("Bad Request", response.getBody().getCode());
        assertEquals(1, response.getBody().getArguments().size());
    }

    @Test
    void testHandleDataIntegrityViolationException() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Test Exception");
        ResponseEntity<ApiError> response = foodExceptionHandler.handleDataIntegrityViolationException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Requisição inválida", response.getBody().getMessage());
        assertEquals("Bad Request", response.getBody().getCode());
        assertEquals(1, response.getBody().getArguments().size());
    }
}
