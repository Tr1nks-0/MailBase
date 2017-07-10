package com.tr1nks.model.services;

import com.tr1nks.model.entities.CathedraEntity;

import java.util.List;

/**
 * Service для {@link CathedraEntity CathedraEntity}
 */
public interface CathedraService {
    /**
     * добавить кафедру
     *
     * @param cathedraEntity кафедра
     * @return добавленная кафедра
     */
    CathedraEntity addCathedra(CathedraEntity cathedraEntity);

    /**
     * удалить кафедру
     *
     * @param id id кафедры
     */
    void delete(int id);

    /**
     * получить кафедру по названию
     *
     * @param name название кафедры
     * @return кафедра
     */
    CathedraEntity getByName(String name);

    /**
     * получить кафедру по аббревиатуре
     *
     * @param abbr аббревиатура кафедры
     * @return кафедра
     */
    CathedraEntity getByAbbr(String abbr);

    /**
     * редактировать кафедру
     *
     * @param cathedraEntity кафедра с отредактированными данными
     * @return отредактированная кафедра
     */
    CathedraEntity editCathedra(CathedraEntity cathedraEntity);

    /**
     * получить все кафедры
     *
     * @return все кафедры
     */
    List<CathedraEntity> getAll();

    /**
     * получить кафедру по id
     *
     * @param id id кафедры
     * @return кафедра
     */
    CathedraEntity getById(int id);
}
