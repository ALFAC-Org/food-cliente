package br.com.alfac.food.database.pedido.repository;

import br.com.alfac.food.core.application.pedido.ports.PedidoRepository;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.database.item.entity.ItemEntity;
import br.com.alfac.food.database.pedido.entity.PedidoEntity;
import br.com.alfac.food.database.pedido.mapper.PedidoEntityMapper;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {


    private final PedidoEntityRepository pedidoEntityRepository;
    private final PedidoEntityMapper pedidoEntityMapper;

    public PedidoRepositoryImpl(final PedidoEntityRepository pedidoEntityRepository, final PedidoEntityMapper pedidoMapper) {
        this.pedidoEntityRepository = pedidoEntityRepository;
        this.pedidoEntityMapper = pedidoMapper;
    }

    @Override
    public List<Pedido> listarPedidos(){
        List<PedidoEntity> pedidoEntities = pedidoEntityRepository.findAll();

        List<Pedido> pedidos = new ArrayList<>();

        for (PedidoEntity pedidoEntity : pedidoEntities) {
            Pedido pedido = pedidoEntityMapper.toDomain(pedidoEntity);
            pedidos.add(pedido);
        }

        return pedidos;
    }

    @Override
    //@Transactional
    public void registrarPedido(Pedido pedido){

        PedidoEntity pedidoEntity = pedidoEntityMapper.toEntity(pedido);

        pedidoEntityRepository.save(pedidoEntity);
    }

}
