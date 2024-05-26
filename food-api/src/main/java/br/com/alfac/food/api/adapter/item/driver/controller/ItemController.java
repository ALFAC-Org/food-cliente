package br.com.alfac.food.api.adapter.item.driver.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alfac.food.api.adapter.item.dto.ItemRequest;
import br.com.alfac.food.api.adapter.item.mapper.ItemMapper;
import br.com.alfac.food.api.config.exception.ApiError;
import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.item.ports.ItemService;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.exception.FoodException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/itens")
@Tag(name = "Item", description = "Métodos para manipulação de itens (LANCHE, COMPLEMENTO, ACOMPANHAMENTO, BEBIDA, SOBREMESA...)")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(final ItemService itemService, final ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @Operation(summary = "Consultar todos os itens")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
        @ApiResponse(responseCode = "404", description = "Nenhum item cadastrado", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
        })})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> consultarItens() throws FoodException {
        return new ResponseEntity<>(itemService.consultarItens(), HttpStatus.OK);
    }

    @Operation(summary = "Consultar itens por categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
        @ApiResponse(responseCode = "404", description = "Nenhum item cadastrado", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
        })})
    @GetMapping(value = "por-categoria/{categoria}/itens", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> consultarItensPorCategoria(@PathVariable CategoriaItem categoria) throws FoodException {
        return new ResponseEntity<>(itemService.consultarItensPorCategoria(categoria), HttpStatus.OK);
    }

    @Operation(summary = "Consultar item por Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
        @ApiResponse(responseCode = "404", description = "Nenhum item cadastrado", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
        })})
    @GetMapping(value = "por-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> consultarPorId(@PathVariable Long id) throws FoodException {
        return new ResponseEntity<>(itemService.consultarItemPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Criar novo item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Operação bem sucedida"),
        @ApiResponse(responseCode = "400", description = "Erro ao cadastrar item", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
        })})
    @PostMapping
    public ResponseEntity<ItemDTO> cadastrarItem(@RequestBody ItemRequest itemRequest) throws FoodException {
        ItemDTO itemDTO = itemService.cadastrarItem(itemMapper.toDTO(itemRequest));

        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar item por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
        @ApiResponse(responseCode = "404", description = "Nenhum item cadastrado", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
        })})
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> atualizarItem(@PathVariable Long id, @RequestBody ItemRequest itemRequest) throws FoodException {
        ItemDTO itemDTO = itemService.atualizarItem(id, itemMapper.toDTO(itemRequest));
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }

    @Operation(summary = "Deletar item por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
        @ApiResponse(responseCode = "404", description = "Nenhum item cadastrado", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
        })})
    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDTO> excluirItem(@PathVariable Long id) throws FoodException {
        return new ResponseEntity<>(itemService.excluirItem(id), HttpStatus.OK);
    }
}
