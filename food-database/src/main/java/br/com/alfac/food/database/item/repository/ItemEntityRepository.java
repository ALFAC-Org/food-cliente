package br.com.alfac.food.database.item.repository;

import br.com.alfac.food.database.item.entity.ItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {


}
