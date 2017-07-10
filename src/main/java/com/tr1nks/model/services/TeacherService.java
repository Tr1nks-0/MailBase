package com.tr1nks.model.services;

import com.tr1nks.model.entities.TeacherEntity;

import java.util.List;

/**
 * Service для {@link TeacherEntity TeacherEntity}
 */
public interface TeacherService {
    /**
     * добавить преподавателя
     *
     * @param teacherEntity преподаватель
     * @return добавленный преподаватель
     */
    TeacherEntity addTeacher(TeacherEntity teacherEntity);

    /**
     * удалить преподавателя
     *
     * @param id id преподавателя
     */
    void delete(int id);

    /**
     * получить преподавателя по ФИО
     *
     * @param fio ФИО
     * @return преподаватель
     */
    TeacherEntity getByFio(String fio);

    /**
     * получить преподавателя по коду
     *
     * @param code код
     * @return преподаватель
     */
    TeacherEntity getByCode(String code);

    /**
     * получить преподавателя по логину почты
     *
     * @param login логин почты
     * @return преподаватель
     */
    TeacherEntity getByLogin(String login);

    /**
     * редактировать преподавателя
     *
     * @param teacherEntity преподаватель с отредактированными данными
     * @return отредактированный преподаватель
     */
    TeacherEntity editTeacher(TeacherEntity teacherEntity);

    /**
     * получить всех преподавателей
     *
     * @return всех преподавателей
     */
    List<TeacherEntity> getAll();

    /**
     * получить преподавателя по id
     *
     * @param id id преподавателя
     * @return преподаватель
     */
    TeacherEntity getById(int id);
}
