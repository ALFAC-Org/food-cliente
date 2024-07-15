package br.com.alfac.food.database.item.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alfac.food.core.domain.item.CategoriaItem;

@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByCategoria(CategoriaItem categoria);

}