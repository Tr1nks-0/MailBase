package com.tr1nks.model.entities.entityenums;

/**
 * роли сайта
 */
public enum SiteRoles {
    /**
     * администратор
     */
    ROLE_ADMIN("ROLE_ADMIN"),
    /**
     * пользователь
     */
    ROLE_USER("ROLE_USER");

    private final String role;

    SiteRoles(String role) {
        this.role = role;
    }

    /**
     * получить строку роли
     * @return строка роли
     */
    public String getRole() {
        return this.role;
    }

    /**
     * получить строку роли в виде "hasRole('ИМЯ_РОЛИ')
     * @param role роль
     * @return строка роли
     */
    public static String hasRole(SiteRoles role) {
        return "hasRole('" + role.role + "')";
    }
}
