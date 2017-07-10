package com.tr1nks.model.services;

import com.tr1nks.model.entities.GroupEntity;

import java.util.List;

/**
 * Service для {@link GroupEntity GroupEntity}
 */
public interface GroupService {
    /**
     * добавить группу
     *
     * @param groupEntity группа
     * @return добавленная группа
     */
    GroupEntity addGroup(GroupEntity groupEntity);

    /**
     * удалить группу
     *
     * @param id id группы
     */
    void delete(int id);

    /**
     * получить группу по шифру
     *
     * @param cipher шифр
     * @return группа
     */
    GroupEntity getByCipher(String cipher);

    /**
     * редактировать группу
     *
     * @param groupEntity группа с отредактированными данными
     * @return группу с отредактированными данными
     */
    GroupEntity editGroup(GroupEntity groupEntity);

    /**
     * получить все группы
     *
     * @return все группы
     */
    List<GroupEntity> getAll();

    /**
     * получить годы без повторений
     *
     * @return годы без повторений
     */
    List<Integer> getYears();

    /**
     * получить группу по id
     *
     * @param id id группы
     * @return группа
     */
    GroupEntity getById(int id);
}
