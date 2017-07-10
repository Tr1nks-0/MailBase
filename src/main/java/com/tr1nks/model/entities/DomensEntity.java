package com.tr1nks.model.entities;

import javax.persistence.*;

/**
 * домены для учетных записей
 * таблица должна содержать одну запись со всеми доменами
 */
@Entity
@Table(name = "domens")
public class DomensEntity {
    /**
     * id сущности
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    /**
     * домен для почты
     */
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String emailDomen;
    /**
     * домен для imagine
     */
    @Basic
    @Column(name = "imagine", nullable = false, length = 50)
    private String imagineDomen;
    /**
     * домен для office
     */
    @Basic
    @Column(name = "office", nullable = false, length = 50)
    private String officeDomen;

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
     * получить домен для почты
     *
     * @return домен для почты
     */
    public String getEmailDomen() {
        return emailDomen;
    }

    /**
     * установить домен для почты
     *
     * @param emailDomen домен для почты
     */
    public void setEmailDomen(String emailDomen) {
        this.emailDomen = emailDomen;
    }

    /**
     * получить домен для imagine
     *
     * @return домен для imagine
     */
    public String getImagineDomen() {
        return imagineDomen;
    }

    /**
     * установить домен для imagine
     *
     * @param imagineDomen домен для imagine
     */
    public void setImagineDomen(String imagineDomen) {
        this.imagineDomen = imagineDomen;
    }

    /**
     * получить домен для office
     *
     * @return домен для office
     */
    public String getOfficeDomen() {
        return officeDomen;
    }

    /**
     * установить домен для office
     *
     * @param officeDomen домен для office
     */
    public void setOfficeDomen(String officeDomen) {
        this.officeDomen = officeDomen;
    }
}
