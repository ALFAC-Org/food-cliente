package br.com.alfac.food.database.pedido.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.pedido.StatusPedido;

@Entity
@Table(name = "pedido")
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Cliente cliente;
    
    @NotEmpty(message = "Status do pedido é obrigatório")
    private StatusPedido status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ComboEntity> combos;

}