package br.com.alfac.food.database.pedido.repository;

import br.com.alfac.food.database.pedido.entity.PedidoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoEntityRepository extends JpaRepository<PedidoEntity, Long> {


}
