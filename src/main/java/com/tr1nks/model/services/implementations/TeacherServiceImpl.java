package com.tr1nks.model.services.implementations;

import com.tr1nks.model.entities.TeacherEntity;
import com.tr1nks.model.repositories.TeacherRepository;
import com.tr1nks.model.services.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * реализация для {@link TeacherService TeacherService}
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherRepository teacherRepository;

    /**
     * {@inheritDoc}
     *
     * @param teacherEntity преподаватель
     * @return
     */
    @Override
    public TeacherEntity addTeacher(TeacherEntity teacherEntity) {
        return teacherRepository.saveAndFlush(teacherEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id id преподавателя
     */
    @Override
    public void delete(int id) {
        teacherRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param fio ФИО
     * @return
     */
    @Override
    public TeacherEntity getByFio(String fio) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param code код
     * @return
     */
    @Override
    public TeacherEntity getByCode(String code) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param login логин почты
     * @return
     */
    @Override
    public TeacherEntity getByLogin(String login) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param teacherEntity преподаватель с отредактированными данными
     * @return
     */
    @Override
    public TeacherEntity editTeacher(TeacherEntity teacherEntity) {
        return teacherRepository.saveAndFlush(teacherEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<TeacherEntity> getAll() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TeacherEntity getById(int id) {
        return teacherRepository.getOne(id);
    }
}
