package com.tr1nks.model.repositories;

import com.tr1nks.model.entities.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * {@link JpaRepository JpaRepository} для {@link FacultyEntity FacultyEntity}
 */
@Repository
public interface FacultyRepository extends JpaRepository<FacultyEntity, Integer> {
}
