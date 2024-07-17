package br.com.alfac.food.api.pagamento.controller;

import br.com.alfac.food.api.config.exception.ApiError;
import br.com.alfac.food.api.pagamento.dto.PagamentoRequest;
import br.com.alfac.food.core.application.pagamento.dto.PagamentoDTO;
import br.com.alfac.food.core.exception.FoodException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pagamento")
@Tag(name = "Pagamento", description = "Métodos para realização do faker checkout do pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(final PagamentoService pagamentoService) {
        this.pagamentoService =pagamentoService;
    }


    @Operation(summary = "Efetivar pagamento do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento realizado"),
            @ApiResponse(responseCode = "404", description = "Erro pedido não encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @PostMapping
    public ResponseEntity<PagamentoDTO> pagar(@Valid @RequestBody PagamentoRequest pagamentoRequest) throws FoodException {
        PagamentoDTO pagamentoDTO = pagamentoService.efetuarPagamento(pagamentoRequest.getIdPedido());

        return new ResponseEntity<>(pagamentoDTO, HttpStatus.OK);
    }

}
