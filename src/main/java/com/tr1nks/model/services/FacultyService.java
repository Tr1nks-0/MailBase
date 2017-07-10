package com.tr1nks.model.services;

import com.tr1nks.model.entities.FacultyEntity;

import java.util.List;

/**
 * Service для {@link FacultyEntity FacultyEntity}
 */
public interface FacultyService {
    /**
     * добавить факультет
     *
     * @param facultyEntity факультет
     * @return сохраненный факультет
     */
    public FacultyEntity addFaculty(FacultyEntity facultyEntity);

    /**
     * удалить факультет
     *
     * @param id id факультета
     */
    public void delete(int id);

    /**
     * получить факульткт по id
     * @param id id факультета
     * @return факультет
     */
    public FacultyEntity getById(int id);

    /**
     * получить факультет по имени
     * @param name имя факультета
     * @return факультет
     */
    public FacultyEntity getByName(String name);

    /**
     * получить факультет по аббревиатуре
     * @param abbr аббревиатура
     * @return факультет
     */
    public FacultyEntity getByAbbr(String abbr);

    /**
     * редактировать
     * @param facultyEntity факультет с редактированными данными
     * @return редактированный факультет
     */
    public FacultyEntity editFaculty(FacultyEntity facultyEntity);

    /**
     * получить все факультеты
     * @return все факультеты
     */
    public List<FacultyEntity> getAll();

}
