package com.tr1nks.model.services;

import com.tr1nks.model.entities.PersonEntity;

import java.util.List;

/**
 * Service для {@link PersonEntity PersonEntity}
 */
public interface PersonService {
    /**
     * добавить персону
     *
     * @param personEntity персона
     * @return добавленная персона
     */
    public PersonEntity addPerson(PersonEntity personEntity);

    /**
     * удалить персону
     *
     * @param id id персоны
     */
    public void delete(int id);

    /**
     * получить персону по ФИО
     *
     * @param fio ФИО
     * @return персона
     */
    public PersonEntity getByFio(String fio);

    /**
     * получить персону по коду
     *
     * @param code код
     * @return персона
     */
    public PersonEntity getByCode(String code);

    /**
     * получить персону по логину почты
     *
     * @param login логин почты
     * @return персону
     */
    public PersonEntity getByLogin(String login);

    /**
     * редактировать персону
     *
     * @param personEntity порсона с редактированными данными
     * @return редактированная персона
     */
    public PersonEntity editPerson(PersonEntity personEntity);

    /**
     * получить всех персон
     *
     * @return всех персон
     */
    public List<PersonEntity> getAll();
}
