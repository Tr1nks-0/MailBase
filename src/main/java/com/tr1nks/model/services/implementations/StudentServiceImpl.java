package com.tr1nks.model.services.implementations;

import com.tr1nks.model.entities.StudentEntity;
import com.tr1nks.model.repositories.StudentRepository;
import com.tr1nks.model.services.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * реализация для {@link StudentService StudentService}
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentRepository studentRepository;

    /**
     * {@inheritDoc}
     *
     * @param studentEntity студент
     * @return
     */
    @Override
    public StudentEntity addStudent(StudentEntity studentEntity) {
        return studentRepository.saveAndFlush(studentEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id id студента
     */
    @Override
    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param fio ФИО
     * @return
     */
    @Override
    public StudentEntity getByFio(String fio) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param code код
     * @return
     */
    @Override
    public StudentEntity getByCode(String code) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param login логин почты
     * @return
     */
    @Override
    public StudentEntity getByLogin(String login) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param studentEntity
     * @return
     */
    @Override
    public StudentEntity editStudent(StudentEntity studentEntity) {
        return studentRepository.saveAndFlush(studentEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<StudentEntity> getAll() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param id id студента
     * @return
     */
    @Override
    public StudentEntity getById(int id) {
        return studentRepository.getOne(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param login логин
     * @return
     */
    @Override
    public boolean testEmail(String login) {
        return null == studentRepository.testEmail(login);
    }

    @Override
    public boolean testCode(String code) {
//        System.out.println(null == studentRepository.getIdFirstByCode(code));
        return null == studentRepository.testCode(code);
    }

    /**
     * {@inheritDoc}
     *
     * @param integer id группы
     * @return
     */
    @Override
    public List<StudentEntity> getStudentsByGroupId(Integer integer) {
        return studentRepository.getAllByGroupId(integer);
    }

    /**
     * {@inheritDoc}
     *
     * @param facultyId
     * @return
     */
    @Override
    public List<StudentEntity> getStudentsByFacultyId(Integer facultyId) {
        return studentRepository.getAllByFacultyId(facultyId);
    }

    /**
     * {@inheritDoc}
     *
     * @param integer факультет
     * @return
     */
    @Override
    public List<StudentEntity> getStudentsByYear(Integer integer) {
        return studentRepository.getAllByYear(integer);
    }
}
