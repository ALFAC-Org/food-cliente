package br.com.alfac.food.infra.pagamento.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoEntityRepository extends JpaRepository<PagamentoEntity, Long> {

    Optional<PagamentoEntity> findByPedidoId(Long pedidoId);
}
