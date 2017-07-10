package com.tr1nks.model.entities;


import javax.persistence.*;

/**
 * Generic для преподавателя и студента
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "person_seq", sequenceName = "person_entity_id_seq", initialValue = 1, allocationSize = 1)
public abstract class PersonEntity {
    /**
     * id сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @Column(name = "id", nullable = false)
    int id;
    /**
     * фамилия
     */
    @Basic
    @Column(name = "surname", nullable = false, length = 50)
    String surname;
    /**
     * имя
     */
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    String name;
    /**
     * отчество
     */
    @Basic
    @Column(name = "patronymic", nullable = true, length = 50)
    String patronymic;
    /**
     * код
     */
    @Basic
    @Column(name = "code", unique = true, nullable = false, length = 100)
    String code;
    /**
     * логин почты
     */
    @Basic
    @Column(name = "login", unique = true, nullable = true, length = 100)
    String login;
    /**
     * начальный пароль почты
     */
    @Basic
    @Column(name = "init_passw", nullable = true, length = 15)
    String initPassw;
    /**
     * флаг о наличии аккаунта imagine
     */
    @Basic
    @Column(name = "imagine", nullable = false, columnDefinition = "bit(1) default false")
    boolean imagine;
    /**
     * флаг о наличии аккаунта office
     */
    @Basic
    @Column(name = "office", nullable = false, columnDefinition = "bit(1) default false")
    boolean office;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Transient
    String errorMessage;

    /**
     * конструктор
     *
     * @param surname    фамилия
     * @param name       имя
     * @param patronymic отчество
     * @param code       код
     */
    public PersonEntity(String surname, String name, String patronymic, String code) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.code = code;
    }

    /**
     * конструктор
     */
    public PersonEntity() {
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
     * получить фамилию
     *
     * @return фамилия
     */
    public String getSurname() {
        return surname;
    }

    /**
     * установить фамилию
     *
     * @param surname фамилия
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * получить имя
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * установить имя
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * получить отчество
     *
     * @return отчество
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * установить отчество
     *
     * @param patronymic отчество
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * получить код
     *
     * @return код
     */
    public String getCode() {
        return code;
    }

    /**
     * установить код
     *
     * @param code код
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * получить логин почты
     *
     * @return логин почты
     */
    public String getLogin() {
        return login;
    }

    /**
     * установить логин почты
     *
     * @param login логин почты
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * получить начальный пароль почты
     *
     * @return начальный пароль почты
     */
    public String getInitPassw() {
        return initPassw;
    }

    /**
     * установить начальный пароль почты
     *
     * @param initPassw начальный пароль почты
     */
    public void setInitPassw(String initPassw) {
        this.initPassw = initPassw;
    }

    /**
     * получить флаг о наличии аккаунта imagine
     *
     * @return флаг о наличии аккаунта imagine
     */
    public boolean getImagine() {
        return imagine;
    }

    /**
     * установить флаг о наличии аккаунта imagine
     *
     * @param imagine флаг о наличии аккаунта imagine
     */
    public void setImagine(boolean imagine) {
        this.imagine = imagine;
    }

    /**
     * получить флаг о наличии аккаунта office
     *
     * @return флаг о наличии аккаунта office
     */
    public boolean getOffice() {
        return office;
    }

    /**
     * установить флаг о наличии аккаунта office
     *
     * @param office флаг о наличии аккаунта office
     */
    public void setOffice(boolean office) {
        this.office = office;
    }
}