package br.com.alfac.food.database.pedido.repository;

import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.application.pedido.ports.PedidoRepository;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.cliente.vo.CPF;
import br.com.alfac.food.core.domain.pedido.Combo;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.database.cliente.entity.ClienteEntity;
import br.com.alfac.food.database.pedido.entity.ComboEntity;
import br.com.alfac.food.database.pedido.entity.ItemComboEntity;
import br.com.alfac.food.database.pedido.entity.PedidoEntity;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {


    private final PedidoEntityRepository pedidoEntityRepository;

    public PedidoRepositoryImpl(final PedidoEntityRepository pedidoEntityRepository) {
        this.pedidoEntityRepository = pedidoEntityRepository;
    }


    @Override
    public void registrarPedido(Pedido pedido){

        PedidoEntity pedidoEntity = new PedidoEntity();

        for (Combo combo: pedido.getCombos())
        {

        }

        pedidoEntityRepository.save(pedidoEntity);
    }

}
