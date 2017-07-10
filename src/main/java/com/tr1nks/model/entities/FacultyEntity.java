package com.tr1nks.model.entities;

import javax.persistence.*;
import java.util.List;

/**
 * факультет
 */
@Entity
@Table(name = "faculty")
public class FacultyEntity {
    /**
     * id сущности
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    /**
     * название факультета
     */
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    /**
     * аббревиатура названия факультета
     */
    @Basic
    @Column(name = "abbr", nullable = false, length = 5)
    private String abbr;
    /**
     * кафедры факультета
     */
    @OneToMany(mappedBy = "faculty")
    private List<CathedraEntity> cathedras;
    /**
     * группы факультета
     */
    @OneToMany(mappedBy = "faculty")
    private List<GroupEntity> groups;

    public FacultyEntity(String name, String abbr) {
        this.name = name;
        this.abbr = abbr;
    }

    public FacultyEntity(int id, String name, String abbr) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
    }

    public FacultyEntity() {
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
     * получить название факультета
     * @return название факультета
     */
    public String getName() {
        return name;
    }

    /**
     * установить название факультета
     * @param name название факультета
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * получить аббревиатуру названия факультета
     * @return аббревиатура названия факультета
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * установить аббревиатуру названия факультета
     * @param abbr аббревиатура названия факультета
     */
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    /**
     * получить кафедры факультета
     * @return кафедры факультета
     */
    public List<CathedraEntity> getCathedras() {
        return cathedras;
    }

    /**
     * установить кафедры факультета
     * @param cathedras кафедры факультета
     */
    public void setCathedras(List<CathedraEntity> cathedras) {
        this.cathedras = cathedras;
    }

    /**
     * получить группы факультета
     * @return группы факультета
     */
    public List<GroupEntity> getGroups() {
        return groups;
    }

    /**
     * установить группы факультета
     * @param groups группы факультета
     */
    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }

    /**
     * {@inheritDoc}
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyEntity that = (FacultyEntity) o;
        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!abbr.equals(that.abbr)) return false;
        if (cathedras != null ? !cathedras.equals(that.cathedras) : that.cathedras != null) return false;
        return groups != null ? groups.equals(that.groups) : that.groups == null;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + abbr.hashCode();
        result = 31 * result + (cathedras != null ? cathedras.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

//    @Override
//    public String toString() {
//        return "FacultyEntity{" +
//                "name='" + name + '\'' +
//                '}';
//    }
}
