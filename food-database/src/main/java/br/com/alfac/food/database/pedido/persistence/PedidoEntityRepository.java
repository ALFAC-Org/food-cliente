package br.com.alfac.food.database.pedido.persistence;

import br.com.alfac.food.core.domain.pedido.StatusPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoEntityRepository extends JpaRepository<PedidoEntity, Long> {


    List<PedidoEntity> findAllByStatus(StatusPedido status);
}
