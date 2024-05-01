package br.com.alfac.food.api.adapter.pedido.driver.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @GetMapping
    public String listarPedidos() {
        return new String();
    }

    @PostMapping
    public void enviarPedido(@RequestBody String entity) {
    }

}
