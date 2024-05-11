package br.com.alfac.food.api.adapter.item.driver.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alfac.food.api.adapter.item.mapper.ItemMapper;
import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.item.ports.ItemService;
import br.com.alfac.food.core.domain.item.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/itens")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(final ItemService itemService, final ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @Operation(summary = "consultar todos os Itens")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação bem sucessida"),
        @ApiResponse(responseCode = "404", description = "Nenhum item cadastrado", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Item.class))
        })})
    @GetMapping(value = "consultar-itens", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> consultarItens() throws Exception {
        return itemService.consultarItens();
    }

    @PostMapping
    public void cadastrarItem() {

    }

    @PutMapping("/{id}")
    public void atualizarItem(@PathVariable Integer id, @RequestBody String entity) {
    }

    @DeleteMapping("/{id}")
    public void excluirItem(@PathVariable Integer id) {
    }
}
