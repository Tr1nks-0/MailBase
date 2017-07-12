package com.tr1nks.model.utils;

import com.tr1nks.model.entities.PersonEntity;
import com.tr1nks.model.entities.StudentEntity;
import com.tr1nks.model.entities.TeacherEntity;
import com.tr1nks.model.services.DomensService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private static final String SLH = "/";
    @Resource
    private DomensService domensService;

    public byte[] createPDFArchiveBytes(List<PersonEntity> persons) {
        byte[] arr = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            StringBuilder builder = new StringBuilder();
            for (PersonEntity person : persons) {
                if (person instanceof StudentEntity) {
                    builder.append(((StudentEntity) person).getGroup().getFaculty().getAbbr())
                            .append(SLH).append(((StudentEntity) person).getGroup().getChiper())
                            .append(SLH);
                } else if (person instanceof TeacherEntity) {
                    builder.append(((TeacherEntity) person).getCathedra().getFaculty())
                            .append(SLH).append(((TeacherEntity) person).getCathedra().getAbbr())
                            .append(SLH);
                }
                builder.append(person.getSurname()).append("_").append(person.getName()).append(".pdf");
                zipOutputStream.putNextEntry(new ZipEntry(builder.toString()));
                HashMap<Pattern, String> replaceMap = new HashMap<>();
                zipOutputStream.write(create(person));
                zipOutputStream.closeEntry();
                builder.replace(0, builder.length(), "");
            }
            arr = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }


    public static final String PDF_RESOURCE_LOCATION = "/static/pdf/";
    private static final Pattern PDF_EMAIL_ADRESS_PATTERN = Pattern.compile("@@EMAIL-ADDRESS");
    private static final Pattern PDF_EMAIL_PASSWORD_PATTERN = Pattern.compile("@@EMAIL-PASSWORD");
    private static final Pattern PDF_SECTION_CONNECTOR = Pattern.compile("@@SECTION_CONNECTOR");
    private PdfFromHtmlCreator creator = new PdfFromHtmlCreator();
//    private static final String

    /**
     * создать PDF
     * подготавливает данные для работы {@link PdfFromHtmlCreator PdfFromHtmlCreator}
     *
     * @param person персона для которой создается pdf
     * @return pdf в виде массива байт
     */
    public byte[] create(PersonEntity person) {
        HashMap<Pattern, String> replaceMap = new HashMap<>();
        replaceMap.put(PDF_EMAIL_ADRESS_PATTERN, person.getLogin() + domensService.getEmailDomen());
        replaceMap.put(PDF_EMAIL_PASSWORD_PATTERN, person.getInitPassw());
        return creator.create(creator.loadHtmlCssData("pdfSample.html"), replaceMap);
    }
}
