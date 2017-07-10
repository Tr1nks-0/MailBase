package com.tr1nks.model.entities;

import javax.persistence.*;
import java.util.List;

/**
 * группа
 */
@Entity
@Table(name = "group_")
//@SequenceGenerator(name = "group_seq", sequenceName = "group_entity_id_seq", initialValue = 1, allocationSize = 1)
public class GroupEntity {
    /**
     * id сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    /**
     * степень обучения
     */
    @Basic
    @Column(name = "level", nullable = false, length = 3)
    private int level;
    /**
     * факультет к которому предлежит группа
     */
    @Basic
    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id", nullable = false)
    private FacultyEntity faculty;
    /**
     * номер специальности
     */
    @Basic
    @Column(name = "speciality", nullable = false, length = 3)
    private int speciality;
    /**
     * год поступления
     */
    @Basic
    @Column(name = "year", nullable = false, length = 3)
    private int year;
    /**
     * номер группы
     */
    @Basic
    @Column(name = "num", nullable = false, length = 3)
    private int num;
    /**
     * студенты группы
     */
    @OneToMany(mappedBy = "group")
    private List<StudentEntity> students;

    /**
     * конструктор
     *
     * @param level      степень обучения
     * @param faculty    факультет к которому предлежит группа
     * @param speciality номер специальности
     * @param year       год поступления
     * @param num        номер группы
     */
    public GroupEntity(int level, FacultyEntity faculty, int speciality, int year, int num) {
        this.level = level;
        this.faculty = faculty;
        this.speciality = speciality;
        this.year = year;
        this.num = num;
    }

    /**
     * конструктор
     */
    public GroupEntity() {
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
     * получить степень обучения
     *
     * @return степень обучения
     */
    public int getLevel() {
        return level;
    }

    /**
     * установить степень обучения
     *
     * @param level степень обучения
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * получить факультет к которому предлежит группа
     *
     * @return факультет к которому предлежит группа
     */
    public FacultyEntity getFaculty() {
        return faculty;
    }

    /**
     * установить факультет к которому предлежит группа
     *
     * @param faculty факультет к которому предлежит группа
     */
    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    /**
     * получить номер специальности
     *
     * @return номер специальности
     */
    public int getSpeciality() {
        return speciality;
    }

    /**
     * установить номер специальности
     *
     * @param speciality номер специальности
     */
    public void setSpeciality(int speciality) {
        this.speciality = speciality;
    }

    /**
     * получить год поступления
     *
     * @return год поступления
     */
    public int getYear() {
        return year;
    }

    /**
     * установить год поступления
     *
     * @param year год поступления
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * получить номер группы
     *
     * @return номер группы
     */
    public int getNum() {
        return num;
    }

    /**
     * установить номер группы
     *
     * @param num номер группы
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * получить студентов группы
     *
     * @return студенты группы
     */
    public List<StudentEntity> getStudents() {
        return students;
    }

    /**
     * установить студентов группы
     *
     * @param students студенты группы
     */
    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    /**
     * получить шифр группы в виде "степень_обучения.факультет.номер_специальности.год_поступления.номер_группы"
     *
     * @return шифр группы
     */
    public String getChiper() {
        return level + "." + faculty.getId() + "." + speciality + "." + year + "." + num;
    }
}
