package com.tr1nks.model.entities.entityenums;

public enum SiteRoles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String role;

    SiteRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }


    public static String hasRole(SiteRoles role) {
        return "hasRole('" + role.role + "')";
    }
}
