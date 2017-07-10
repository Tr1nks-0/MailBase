package com.tr1nks.model.services;

import com.tr1nks.model.entities.UserEntity;

import java.util.List;

/**
 * Service для {@link UserEntity UserEntity}
 */
public interface UserService {
    /**
     * Добавить пользователя
     *
     * @param userEntity пользователь
     * @return добавленный пользователь
     */
    UserEntity addUser(UserEntity userEntity);

    /**
     * удалить пользователя
     *
     * @param id id удаляемого пользователя
     */
    void delete(int id);

    /**
     * получить пользователя по его логину
     *
     * @param login логин
     * @return пользователь
     */
    UserEntity getByLogin(String login);

    /**
     * обновить пользователя
     *
     * @param userEntity пользователь с обновленными данными
     * @return обновленный пользователь
     */
    UserEntity editUser(UserEntity userEntity);

    /**
     * получить всех пользователей
     *
     * @return все пользователи
     */
    List<UserEntity> getAll();

    /**
     * получить пользователя по его id
     *
     * @param id id пользователя
     * @return пользователь
     */
    UserEntity getById(int id);
}
