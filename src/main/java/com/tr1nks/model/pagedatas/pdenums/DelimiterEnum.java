package com.tr1nks.model.pagedatas.pdenums;

/**
 * символы разделители для {@link com.tr1nks.model.pagedatas.PageData PageData}
 */
public enum DelimiterEnum {
    /**
     * <h1>;<h1/>
     */
    SEMICOLON(";"),
    /**
     * <h1>,<h1/>
     */
    COMMA(","),
    /**
     * <h1>.<h1/>
     */
    DOT("."),
    /**
     * <h1>-<h1/>
     */
    DASH("-");
    private String value;

    /**
     * все значения enum
     *
     * @return все значения enum
     */
    public static DelimiterEnum[] valuesW() {
        return values();
    }

    /**
     * конструктор
     *
     * @param value символ-разделитель
     */
    DelimiterEnum(String value) {
        this.value = value;
    }

    /**
     * получить  символ-разделитель
     *
     * @return символ-разделитель
     */
    public String getValue() {
        return value;
    }
}
