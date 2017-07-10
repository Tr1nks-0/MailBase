package com.tr1nks.model.entities;

import javax.persistence.*;
import java.util.List;

/**
 * кафедра
 */
@Entity
@Table(name = "cathedra")
public class CathedraEntity {
    /**
     * id сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    /**
     * факультет к которому предлежит кафедра
     */
    @Basic
    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id", nullable = false)
    private FacultyEntity faculty;
    /**
     * название кафедры
     */
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    /**
     * аббревиатура названия кафедры
     */
    @Basic
    @Column(name = "abbr", nullable = false, length = 5)
    private String abbr;
    /**
     * преподаватели кафедры
     */
    @OneToMany(mappedBy = "cathedra")
    private List<TeacherEntity> teacherEntities;

    /**
     * конструктор
     * @param faculty факультет к которому предлежит кафедра
     * @param name название кафедры
     * @param abbr аббревиатура названия кафедры
     */
    public CathedraEntity(FacultyEntity faculty, String name, String abbr) {
        this.faculty = faculty;
        this.name = name;
        this.abbr = abbr;
    }

    /**
     * конструктор
     */
    public CathedraEntity() {
    }

    /**
     * получить id сущности
     *
     * @return id сущности
     */
    public int getId() {
        return id;
    }

    /**
     * установить id сущности
     *
     * @param id id сущности
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * получить факультет к которому предлежит кафедра
     *
     * @return факультет к которому предлежит кафедра
     */
    public FacultyEntity getFaculty() {
        return faculty;
    }

    /**
     * установить факультет к которому предлежит кафедра
     *
     * @param faculty факультет к которому предлежит кафедра
     */
    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    /**
     * получить название кафедры
     *
     * @return название кафедры
     */
    public String getName() {
        return name;
    }

    /**
     * установить название кафедры
     *
     * @param name название кафедры
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * получить аббревиатуру названия кафедры
     *
     * @return аббревиатура названия кафедры
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * установить аббревиатуру названия кафедры
     *
     * @param abbr аббревиатура названия кафедры
     */
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    /**
     * получить преподавателей кафедры
     *
     * @return преподаватели кафедры
     */
    public List<TeacherEntity> getTeacherEntities() {
        return teacherEntities;
    }

    /**
     * установить преподавателей кафедры
     *
     * @param teacherEntities преподаватели кафедры
     */
    public void setTeacherEntities(List<TeacherEntity> teacherEntities) {
        this.teacherEntities = teacherEntities;
    }
}
