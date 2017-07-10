package com.tr1nks.model.pagedatas.pdenums;

/**
 * типы персон для {@link com.tr1nks.model.pagedatas.PageData PageData}
 */
public enum PersonEnum {
    /**
     * Студент
     */
    STUDENT("Студент"),
    /**
     * Преподаватель
     */
    TEACHER("Преподаватель");
    private String value;

    /**
     * все значения enum
     *
     * @return все значения enum
     */
    public static PersonEnum[] valuesW() {
        return values();
    }

    /**
     * конструктор
     *
     * @param value значение персоны
     */
    PersonEnum(String value) {
        this.value = value;
    }

    /**
     * получить
     *
     * @return значение персоны
     */
    public String getValue() {
        return value;
    }
}
