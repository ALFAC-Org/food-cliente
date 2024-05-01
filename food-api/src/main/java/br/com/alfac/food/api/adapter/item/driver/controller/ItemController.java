package br.com.alfac.food.api.adapter.item.driver.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/itens")
public class ItemController {

    @PostMapping
    public void cadastrarItem(){

    }

    @PutMapping("/{id}")
    public void atualizarItem(@PathVariable Integer id, @RequestBody String entity) {
    }

    @DeleteMapping("/{id}")
    public void excluirItem(@PathVariable Integer id) {
    }

}
