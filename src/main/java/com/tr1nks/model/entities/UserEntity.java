package com.tr1nks.model.entities;

import com.tr1nks.model.entities.entityenums.SiteRoles;

import javax.persistence.*;

/**
 * пользователь сайта (администратор)
 */
@Entity
@Table(name = "user")
public class UserEntity {
    /**
     * id сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    /**
     * логин пользователя
     */
    @Basic
    @Column(name = "login", length = 20, nullable = false)
    private String login;
    /**
     * пароль пользователя
     */
    @Basic
    @Column(name = "password", length = 30, nullable = false)
    private String password;
    /**
     * роль пользователя {@link SiteRoles SiteRoles}
     */
    @Basic
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private SiteRoles role;

    public UserEntity(String login, String passw) {
        this.login = login;
        this.password = passw;
        this.role = SiteRoles.ROLE_USER;
    }

    public UserEntity() {
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
     * получить логин пользователя
     *
     * @return логин пользователя
     */
    public String getLogin() {
        return login;
    }

    /**
     * установить логин пользователя
     *
     * @param login логин пользователя
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * получить пароль пользователя
     *
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * установить пароль пользователя
     *
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * получить роль пользователя
     *
     * @return роль пользователя
     */
    public SiteRoles getRole() {
        return role;
    }

    /**
     * установить роль пользователя
     *
     * @param role роль пользователя
     */
    public void setRole(SiteRoles role) {
        this.role = role;
    }
}
