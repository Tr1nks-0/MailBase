package com.tr1nks.model.services;

import com.tr1nks.model.entities.StudentEntity;

import java.util.List;

/**
 * Service для {@link StudentEntity StudentEntity}
 */
public interface StudentService {
    /**
     * добавить студента
     *
     * @param studentEntity студент
     * @return добавленный студент
     */
    StudentEntity addStudent(StudentEntity studentEntity);

    /**
     * удалить студента
     *
     * @param id id студента
     */
    void delete(int id);

    /**
     * получить студента по ФИО
     *
     * @param fio ФИО
     * @return студент
     */
    StudentEntity getByFio(String fio);

    /**
     * получить студента по коду
     *
     * @param code код
     * @return студент
     */
    StudentEntity getByCode(String code);

    /**
     * получить студента по логину почты
     *
     * @param login логин почты
     * @return студент
     */
    StudentEntity getByLogin(String login);

    StudentEntity editStudent(StudentEntity studentEntity);

    /**
     * получить всех студентов
     *
     * @return всех студентов
     */
    List<StudentEntity> getAll();

    /**
     * получить студента по id
     *
     * @param id id студента
     * @return студента
     */
    StudentEntity getById(int id);

    /**
     * проверить логин почты на доступность
     *
     * @param login логин
     * @return true если логин доступен
     */
    boolean testEmail(String login);

    /**
     * проверить код на наличие в базе
     *
     * @param s код
     * @return true если код отсутствует
     */
    boolean testCode(String s);

    /**
     * получить студентов по id группы
     *
     * @param integer id группы
     * @return студентов группы
     */
    List<StudentEntity> getStudentsByGroupId(Integer integer);

    /**
     * получить студентов по году поступления
     *
     * @param integer год поступления
     * @return студенты
     */
    List<StudentEntity> getStudentsByFacultyId(Integer integer);

    /**
     * получить студентов по факультету
     *
     * @param integer факультет
     * @return студенты факультета
     */
    List<StudentEntity> getStudentsByYear(Integer integer);
}
