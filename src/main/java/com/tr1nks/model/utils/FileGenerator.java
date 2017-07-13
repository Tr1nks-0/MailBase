package com.tr1nks.model.utils;

import com.tr1nks.model.entities.PersonEntity;
import com.tr1nks.model.entities.StudentEntity;
import com.tr1nks.model.entities.TeacherEntity;
import com.tr1nks.model.services.DomensService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * генератор файлов
 */
@Component
public class FileGenerator {
    public static final String PDF_RESOURCE_LOCATION = "/static/pdf/";
    private static final Pattern PDF_EMAIL_ADRESS_PATTERN = Pattern.compile("@@EMAIL-ADDRESS");
    private static final Pattern PDF_EMAIL_PASSWORD_PATTERN = Pattern.compile("@@EMAIL-PASSWORD");
    private static final String SLH = "/";
    private static final String EMAIL_CSV_TAIL = ",,,,,,,,,,,,";
    private static final String OFFICE_CSV_TAIL = ",,,,,,,,,,";


    @Resource
    private DomensService domensService;
    private PdfFromHtmlCreator creator = new PdfFromHtmlCreator();
    private HtmlCssForPdfData pdfDataFull = creator.loadHtmlCssData("pdfSample_Full.html");
    private HtmlCssForPdfData pdfDataImagine = creator.loadHtmlCssData("pdfSample_Imagine.html");
    private HtmlCssForPdfData pdfDataOffice = creator.loadHtmlCssData("pdfSample_Office.html");
    private HtmlCssForPdfData pdfDataEmailOnly = creator.loadHtmlCssData("pdfSample_EmailOnly.html");

    /**
     * Создать архив с pdf и csv в со структурой директорий
     *
     * @param persons персоны для которых необходимо сгенерировать данные
     * @return массив байт архива с пдф
     */
    public byte[] createPDFArchiveBytes(List<PersonEntity> persons) {
        byte[] arr = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            StringBuilder builder = new StringBuilder();
            for (PersonEntity person : persons) {
                if (person instanceof StudentEntity) {
                    builder.append(((StudentEntity) person).getGroup().getFaculty().getAbbr())
                            .append(SLH).append(((StudentEntity) person).getGroup().getChiper().replace(".", "_"))
                            .append(SLH);
                } else if (person instanceof TeacherEntity) {
                    builder.append(((TeacherEntity) person).getCathedra().getFaculty().getAbbr())
                            .append(SLH).append(((TeacherEntity) person).getCathedra().getAbbr())
                            .append(SLH);
                }
                builder.append(person.getSurname()).append("_").append(person.getName()).append(".pdf");
                System.out.println(builder.toString());
                zipOutputStream.putNextEntry(new ZipEntry(builder.toString()));
                HashMap<Pattern, String> replaceMap = new HashMap<>();
                zipOutputStream.write(createPdfBytes(person));
                zipOutputStream.closeEntry();
                builder.replace(0, builder.length(), "");
            }
            writeCsvToArchive(persons, zipOutputStream);
            zipOutputStream.flush();
            arr = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * Дописать csv в архив с pdf
     *
     * @param persons         персоны архива
     * @param zipOutputStream выходной поток архива
     */
    private void writeCsvToArchive(List<PersonEntity> persons, ZipOutputStream zipOutputStream) {
        HashMap<String, StringBuilder> map = new HashMap<>();
        for (PersonEntity person : persons) {
            String name = null;
            if (person instanceof StudentEntity) {
                StudentEntity s = (StudentEntity) person;
                name = s.getGroup().getFaculty().getAbbr() + SLH + s.getGroup().getChiper().replace(".", "_") + ".csv";
            } else if (person instanceof TeacherEntity) {
                TeacherEntity t = (TeacherEntity) person;
                name = t.getCathedra().getFaculty().getAbbr() + SLH + t.getCathedra().getAbbr() + ".csv";
            }
            if (null != name && map.containsKey(name)) {
                map.get(name).append(createEmailCsvString(person));
            } else {
                map.put(name, new StringBuilder(createEmailCsvString(person)));
            }
        }
        for (String name : map.keySet()) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(zipOutputStream, "cp1251"))) {
                zipOutputStream.putNextEntry(new ZipEntry(name));
                zipOutputStream.write(map.get(name).toString().getBytes("cp1251"));
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    /**
     * создать строку для файла csv email
     *
     * @param person персона
     * @return строка файла csv
     */
    private String createEmailCsvString(PersonEntity person) {
        return person.getName() + ',' + person.getSurname() + ',' + person.getLogin() + domensService.getEmailDomen() + ',' + person.getInitPassw() + EMAIL_CSV_TAIL + "\n";
    }


    /**
     * создать строку для файла csv imagine
     *
     * @param person персона
     * @return строка файла csv
     */
    private String createImagineCsvString(PersonEntity person) {
        return person.getLogin() + domensService.getImagineDomen() + "\n";
    }

    /**
     * создать строку для файла csv office
     *
     * @param person персона
     * @return строка файла csv
     */
    private String createOfficeCsvString(PersonEntity person) {
        return person.getLogin() + domensService.getImagineDomen() + ',' + person.getName() + ',' + person.getSurname() + ',' + person.getName() + ',' + person.getSurname() + OFFICE_CSV_TAIL + "\n";
    }

    /**
     * Получить массив байт csv файлов
     * arr[0][] - массив байт файла csv email
     * arr[1][] - массив байт файла csv Imagine
     * arr[2][] - массив байт файла csv Office
     *
     * @param persons персоны
     * @return массив байт csv файлов
     */
    public byte[][] createFullPersonsCsvs(List<PersonEntity> persons) {
        byte[][] arr = new byte[3][];
        StringBuilder builderEmail = new StringBuilder();
        StringBuilder builderImagine = new StringBuilder();
        StringBuilder builderOffice = new StringBuilder();
        for (PersonEntity person : persons) {
            builderEmail.append(createEmailCsvString(person));
            if (person.getImagine()) {
                builderImagine.append(createImagineCsvString(person));
            }
            if (person.getOffice()) {
                builderOffice.append(createOfficeCsvString(person));
            }
        }
        try {
            arr[0] = builderEmail.toString().getBytes("cp1251");
            if (builderImagine.length() > 0) {
                arr[1] = builderEmail.toString().getBytes();
            }
            if (builderOffice.length() > 0) {
                arr[2] = builderOffice.toString().getBytes();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return arr;
    }


    /**
     * создать PDF
     * подготавливает данные для работы {@link PdfFromHtmlCreator PdfFromHtmlCreator}
     *
     * @param person персона для которой создается pdf
     * @return pdf в виде массива байт
     */
    public byte[] createPdfBytes(PersonEntity person) {
        HashMap<Pattern, String> replaceMap = new HashMap<>();
        replaceMap.put(PDF_EMAIL_ADRESS_PATTERN, person.getLogin() + domensService.getEmailDomen());
        replaceMap.put(PDF_EMAIL_PASSWORD_PATTERN, person.getInitPassw());
        if (person.getImagine() && person.getOffice()) {
            return creator.create(pdfDataFull, replaceMap);
        } else {
            if (person.getImagine()) {
                return creator.create(pdfDataImagine, replaceMap);
            } else if (person.getOffice()) {
                return creator.create(pdfDataOffice, replaceMap);
            } else {
                return creator.create(pdfDataEmailOnly, replaceMap);
            }
        }
    }
}
