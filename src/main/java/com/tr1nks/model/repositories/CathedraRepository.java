package com.tr1nks.model.repositories;

import com.tr1nks.model.entities.CathedraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link JpaRepository JpaRepository} для {@link CathedraEntity CathedraEntity}
 */
@Repository
public interface CathedraRepository extends JpaRepository<CathedraEntity, Integer> {
}
