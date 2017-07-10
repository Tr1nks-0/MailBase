package com.tr1nks.model.repositories;

import com.tr1nks.model.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link JpaRepository JpaRepository} для {@link TeacherEntity TeacherEntity}
 */
@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
}
