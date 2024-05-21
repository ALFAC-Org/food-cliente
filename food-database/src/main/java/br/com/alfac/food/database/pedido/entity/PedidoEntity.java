package br.com.alfac.food.database.pedido.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.database.cliente.entity.ClienteEntity;

@Entity
@Table(name = "pedido")
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    @NotNull(message = "Status do pedido é obrigatório")
    private StatusPedido status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido")
    @NotEmpty(message = "Obrigatório 1 ou mais combos")
    private List<ComboEntity> combos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ComboEntity> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboEntity> combos) {
        this.combos = combos;
    }

}