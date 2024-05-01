package br.com.alfac.food.api.adapter.pedido.driver.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

    @GetMapping("/categorias/{idCategoria}/itens")
    public void consultarItensPorCategoria(@PathVariable Integer idCategoria){

    }

}
