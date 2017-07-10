package com.tr1nks.model.entities;


import javax.persistence.*;

/**
 * студент
 */
@Entity
@Table(name = "student")
public class StudentEntity extends PersonEntity {
    /**
     * группа к которой принадлежит студент
     */
    @Basic
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private GroupEntity group;
    /**
     * флаг о наличии бюджета
     */
    @Basic
    @Column(name = "budget", nullable = false, columnDefinition = "bit(1) default false")
    private boolean budget;

    /**
     * конструктор
     *
     * @param surname    фамилия
     * @param name       имя
     * @param patronymic отчество
     * @param code       код
     * @param group      группа к которой принадлежит студент
     */
    public StudentEntity(String surname, String name, String patronymic, String code, GroupEntity group) {
        super(surname, name, patronymic, code);
        this.group = group;
    }

    /**
     * конструктор
     *
     * @param surname    фамилия
     * @param name       имя
     * @param patronymic отчество
     * @param code       код
     * @param group      группа к которой принадлежит студент
     * @param login      логин почты
     * @param passw      начальный пароль почты
     * @param budget     флаг о наличии бюджета
     */
    public StudentEntity(String surname, String name, String patronymic, String code, GroupEntity group, String login, String passw, boolean budget) {
        this(surname, name, patronymic, code, group);
        this.login = login;
        this.initPassw = passw;
        this.budget = budget;
    }

    /**
     * @param strings массив строк - {фамилия,имя,отчество,код}
     * @param group   группа к которой принадлежит студент
     * @param budget  флаг о наличии бюджета
     */
    public StudentEntity(String[] strings, GroupEntity group, boolean budget) {
        this(strings[0], strings[1], strings[2], strings[3], group);
        this.budget = budget;
    }

    /**
     * конструктор
     */
    public StudentEntity() {
    }

    /**
     * получить группу к которой принадлежит студент
     *
     * @return группа к которой принадлежит студент
     */
    public GroupEntity getGroup() {
        return group;
    }

    /**
     * установить группу к которой принадлежит студент
     *
     * @param group группа к которой принадлежит студент
     */
    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    /**
     * получить флаг о наличии бюджета
     *
     * @return флаг о наличии бюджета
     */
    public boolean getBudget() {
        return budget;
    }

    /**
     * установить флаг о наличии бюджета
     *
     * @param budget флаг о наличии бюджета
     */
    public void setBudget(boolean budget) {
        this.budget = budget;
    }
}
