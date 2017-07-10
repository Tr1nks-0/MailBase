package com.tr1nks.model.repositories;

import com.tr1nks.model.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * {@link JpaRepository JpaRepository} для {@link GroupEntity GroupEntity}
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
    @Query("select distinct g.year from GroupEntity g")
    List<Integer> getYears();

    @Query("select g from GroupEntity g where concat(g.level,'.',g.faculty,'.',g.speciality,'.',g.year,'.',g.num) like :cipher")
    GroupEntity findByChiper(@Param("cipher") String cipher);

}
