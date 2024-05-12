package br.com.alfac.food.database.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alfac.food.database.item.entity.ItemEntity;

@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity, String> {

    List<ItemEntity> findByCategoria(String categoria);
}
