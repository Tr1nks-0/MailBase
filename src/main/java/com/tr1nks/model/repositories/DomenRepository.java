package com.tr1nks.model.repositories;


import com.tr1nks.model.entities.DomensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link JpaRepository JpaRepository} для {@link DomensEntity DomensEntity}
 */
@Repository
public interface DomenRepository extends JpaRepository<DomensEntity, Integer> {
//    @Query(value = "SELECT * FROM domens LIMIT 1", nativeQuery = true)
//    DomensEntity getFirst();
}
