package com.tr1nks.model.pagedatas;

import com.tr1nks.model.pagedatas.pdenums.CodePagesEnum;
import com.tr1nks.model.pagedatas.pdenums.DelimiterEnum;
import com.tr1nks.model.pagedatas.pdenums.PersonEnum;
import com.tr1nks.model.pagedatas.pdenums.UploadFileMaskEnum;

/**
 * данные для страницы upload
 */
public class UploadPageData extends PageData {
    /**
     * кодировка
     */
    private CodePagesEnum codepage = CodePagesEnum.UTF8;
    /**
     * символ-разделитель
     */
    private DelimiterEnum delimiter = DelimiterEnum.SEMICOLON;
    /**
     * тип персоны
     */
    private PersonEnum person = PersonEnum.STUDENT;
    /**
     * имя файла на сервере
     */
    private String filename;
    /**
     * строки предпросмотра
     */
    private String[][] fileStrings = new String[2][6];
    /**
     * маска столбцов файла
     */
    private UploadFileMaskEnum[] mask = new UploadFileMaskEnum[]{UploadFileMaskEnum.SURNAME, UploadFileMaskEnum.NAME, UploadFileMaskEnum.PATRONYMIC, UploadFileMaskEnum.CODE, UploadFileMaskEnum.GROUP, UploadFileMaskEnum.BUDGET};

    /**
     * получить символ-разделитель
     *
     * @return символ-разделитель
     */
    public DelimiterEnum getDelimiter() {
        return delimiter;
    }

    /**
     * установить символ-разделитель
     *
     * @param delimiter символ-разделитель
     */
    public void setDelimiter(DelimiterEnum delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * получить строки предпросмотра
     *
     * @return строки предпросмотра
     */
    public String[][] getFileStrings() {
        return fileStrings;
    }

    /**
     * установить строки предпросмотра
     *
     * @param fileStrings строки предпросмотра
     */
    public void setFileStrings(String[][] fileStrings) {
        this.fileStrings = fileStrings;
    }

    /**
     * получить кодировку
     *
     * @return кодировка
     */
    public CodePagesEnum getCodepage() {
        return codepage;
    }

    /**
     * установить кодировку
     *
     * @param codepage кодировка
     */
    public void setCodepage(CodePagesEnum codepage) {
        this.codepage = codepage;
    }

    /**
     * получить тип персоны
     *
     * @return тип персоны
     */
    public PersonEnum getPerson() {
        return person;
    }

    /**
     * установить тип персоны
     *
     * @param person тип персоны
     */
    public void setPerson(PersonEnum person) {
        this.person = person;
    }

    /**
     * получить маску столбцов файла
     *
     * @return маска столбцов файла
     */
    public UploadFileMaskEnum[] getMask() {
        return mask;
    }

    /**
     * установить маску столбцов файла
     *
     * @param mask маска столбцов файла
     */
    public void setMask(UploadFileMaskEnum[] mask) {
        this.mask = mask;
    }

    /**
     * получить имя файла на сервере
     *
     * @return имя файла на сервере
     */
    public String getFilename() {
        return filename;
    }

    /**
     * установить имя файла на сервере
     *
     * @param filename имя файла на сервере
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
