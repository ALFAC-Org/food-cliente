package br.com.alfac.food.api.pedido.controller;

import br.com.alfac.food.core.application.pedido.dto.PedidoCriadoDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.alfac.food.api.config.exception.ApiError;
import br.com.alfac.food.api.pedido.dto.PedidoRequest;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.exception.FoodException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface PedidoControllerExamples {

  @Operation(summary = "Registrar Pedidos", description = "Lembre-se de adicionar os itens que deseja dentro da estrutura exemplificada", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
      @ExampleObject(name = "Pedido completo", value = """
          {
                  "clienteId": 1,
                  "combos": [
                      {
                          "lanche": {
                              "id": 1,
                              "complementos": [
                                  {
                                      "id": 6
                                  }
                              ],
                              "observacoes": "Capricha no queijo!"
                          },
                          "acompanhamento": {
                              "id": 8
                          },
                          "bebida": {
                              "id": 11
                          },
                          "sobremesa": {
                              "id": 14
                          }
                      }
                  ]
              }

                                  """),
      @ExampleObject(name = "Apenas lanche e complemento", value = """
          {
                  "clienteId": 1,
                  "combos": [
                      {
                          "lanche": {
                              "id": 1,
                              "complementos": [
                                  {
                                      "id": 6
                                  }
                              ],
                              "observacoes": "Capricha no queijo!"
                          }
                      }
                  ]
              }

                                  """),
      @ExampleObject(name = "Apenas acompanhamento", value = """
          {
                  "clienteId": 1,
                  "combos": [
                      {
                          "acompanhamento": {
                              "id": 8
                          }
                      }
                  ]
              }

                                  """),
      @ExampleObject(name = "Apenas bebida", value = """
          {
                  "clienteId": 1,
                  "combos": [
                      {
                          "bebida": {
                              "id": 11
                          }
                      }
                  ]
              }

                                  """),
      @ExampleObject(name = "Apenas sobremesa", value = """
          {
                  "clienteId": 1,
                  "combos": [
                      {
                          "sobremesa": {
                              "id": 14
                          }
                      }
                  ]
              }

                                  """)
  })))

  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Pedido registrado"),
      @ApiResponse(responseCode = "422", description = "Erro ao registrar pedido", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
      }) })

  ResponseEntity<PedidoCriadoDTO> registrarPedido(@RequestBody PedidoRequest pedidoRequest) throws FoodException;
}
