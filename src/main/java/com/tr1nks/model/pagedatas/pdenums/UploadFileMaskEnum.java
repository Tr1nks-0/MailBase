package com.tr1nks.model.pagedatas.pdenums;

/**
 * маски файла для {@link com.tr1nks.model.pagedatas.PageData PageData}
 */
public enum UploadFileMaskEnum {
    /**
     * Фамилия
     */
    SURNAME("Фамилия"),
    /**
     * Имя
     */
    NAME("Имя"),
    /**
     * Отчество
     */
    PATRONYMIC("Отчество"),
    /**
     * Код
     */
    CODE("Код"),
    /**
     * Группа
     */
    GROUP("Группа"),
    /**
     * Бюджет
     */
    BUDGET("Бюджет");
    private String value;

    /**
     * конструктор
     *
     * @param value значение
     */
    UploadFileMaskEnum(String value) {
        this.value = value;
    }

    /**
     * все значения enum
     *
     * @return все значения enum
     */
    public static UploadFileMaskEnum[] valuesW() {
        return values();
    }

    /**
     * получить значение маски
     *
     * @return значение маски
     */
    public String getValue() {
        return value;
    }
}
