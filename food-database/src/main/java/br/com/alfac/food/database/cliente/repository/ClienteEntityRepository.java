package br.com.alfac.food.database.cliente.repository;

import br.com.alfac.food.database.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByCpf(String cpf);
//    Optional<ClienteEntity> findByNomeAndCpf(String nome, String cpf);

//    @Query("from ClienteEntity ce where ce.cpf = :cpf")
//    Optional<ClienteEntity> buscarPorCPF(String cpf);

}
