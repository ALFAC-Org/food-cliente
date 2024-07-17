package br.com.alfac.food.database.pagamento.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoEntityRepository extends JpaRepository<PagamentoEntity, Long> {


}