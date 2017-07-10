package com.tr1nks.model.pagedatas.pdenums;

/**
 * кодировки для {@link com.tr1nks.model.pagedatas.PageData PageData}
 */
public enum CodePagesEnum {
    /**
     * UTF-8 кодировка
     */
    UTF8("UTF-8"),
    /**
     * Windows 1251 кодировка
     */
    cp1251("Cp1251"),
    /**
     * OEM Dos кодировка
     */
    cp866("Cp866");
    private String value;

    /**
     * все значения enum
     *
     * @return все значения enum
     */
    public static CodePagesEnum[] valuesW() {
        return values();
    }

    /**
     * конструктор
     *
     * @param value значение кодировки
     */
    CodePagesEnum(String value) {
        this.value = value;
    }

    /**
     * получить значение кодировки
     *
     * @return значение кодировки
     */
    public String getValue() {
        return value;
    }
}
