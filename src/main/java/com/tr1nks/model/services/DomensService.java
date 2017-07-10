package com.tr1nks.model.services;

/**
 * Service для {@link com.tr1nks.model.entities.DomensEntity DomensEntity}
 */
public interface DomensService {
    /**
     * получить домен для почты
     *
     * @return домен для почты
     */
    String getEmailDomen();

    /**
     * получить домен для Imagine
     *
     * @return домен для Imagine
     */
    String getImagineDomen();

    /**
     * получить домен для office
     *
     * @return домен для office
     */
    String getOfficeDomen();
}
