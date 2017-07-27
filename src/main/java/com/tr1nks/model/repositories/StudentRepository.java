package com.tr1nks.model.repositories;

import com.tr1nks.model.entities.GroupEntity;
import com.tr1nks.model.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link JpaRepository JpaRepository} для {@link StudentEntity StudentEntity}
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    @Query("SELECT s.id FROM StudentEntity s WHERE s.login = :login")
    Integer testEmail(@Param("login") String login);

    @Query("SELECT s.id FROM StudentEntity s WHERE s.code = :code")
    Integer testCode(@Param("code") String code);

//    Integer getIdFirstByCode(String code);


//    @Query(value = "SELECT * FROM student s WHERE s.group_id = (SELECT g.id FROM group_ g WHERE concat(g.level,'.',g.faculty_id,'.',g.speciality,'.',g.year,'.',g.num) LIKE :grp)", nativeQuery = true)
//    List<StudentEntity> getAllByGroup(@Param("grp") String group);

    @Query(value = "SELECT *  FROM student s WHERE s.group_id = :grp", nativeQuery = true)
    List<StudentEntity> getAllByGroupId(@Param("grp") Integer group);
//    List<StudentEntity> getAllByGroup

    @Query(value = "SELECT * FROM student s WHERE s.group_id in (SELECT g.id FROM group_ g WHERE g.faculty_id= :facultyId)", nativeQuery = true)
    List<StudentEntity> getAllByFacultyId(@Param("facultyId") Integer facultyId);

    @Query(value = "SELECT * FROM student s WHERE s.group_id in (SELECT g.id FROM group_ g WHERE g.year= :yr)", nativeQuery = true)
    List<StudentEntity> getAllByYear(@Param("yr") Integer year);

    //    List<StudentEntity> findAllById(List<Integer> id);
    List<StudentEntity> findAllByIdIn(List<Integer> id);

    StudentEntity getBySurnameAndNameAndGroup(String surname, String name, GroupEntity group);
}
