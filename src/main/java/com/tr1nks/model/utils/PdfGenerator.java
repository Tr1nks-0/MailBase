package com.tr1nks.model.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tr1nks.model.entities.PersonEntity;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * генератор PDF
 */
@Component
@PropertySource("classpath:static/application.properties")
public class PdfGenerator {
    @Resource
    private Environment environment;

    private static final String PDF_RESOURCE_LOCATION = "/static/pdf/";
    private static final String PDF_FONT_REGULAR = "pdf.font.regular";
    private static final String PDF_FONT_BOLD = "pdf.font.bold";
    private static final String PDF_GMAIL_LOGO_FILENAME = "pdf.gmail.logo";
    private static final String PDF_GMAIL_TEXT_FILENAME = "pdf.gmail.text";
    private static final String PDF_OFFICE_LOGO_FILENAME = "pdf.office.logo";
    private static final String PDF_OFFICE_TEXT_FILENAME = "pdf.office.text";
    private static final String PDF_IMAGINE_LOGO_FILENAME = "pdf.imagine.logo";
    private static final String PDF_IMAGINE_TEXT_FILENAME = "pdf.imagine.text";

    private static final Pattern PDF_EMAIL_ADRESS_PATTERN = Pattern.compile("@EMAIL-ADDRESS");
    private static final Pattern PDF_EMAIL_PASSWORD_PATTERN = Pattern.compile("@EMAIL-PASSWORD");


    private static Font fontRegular12;
    private static Font fontBold12;

    /**
     * создать PDF
     *
     * @param person персона для которой создается pdf
     * @return pdf в вмде массива байт
     */
    public byte[] create(PersonEntity person) {
        testLoadFonts(this.environment);
        Document document = null;
        byte numberColumn = 2;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            if (person.getImagine() && person.getOffice()) {
                document = new Document(PageSize.A4, 15F, 15F, 15F, 15F);
            } else if (person.getImagine() || person.getOffice()) {
                document = new Document(PageSize.A5.rotate(), 15F, 15F, 15F, 15F);
            } else {
                document = new Document(PageSize.A6, 15F, 15F, 15F, 15F);
                numberColumn = 1;
            }
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            PdfPTable table = new PdfPTable(numberColumn);
            table.getDefaultCell().setBorder(0);
            table.addCell(createGmailSection(person));
            if (person.getOffice()) {
                table.addCell(createOfficeSection(person));
            }
            if (person.getImagine()) {
                table.addCell(createImagineSection(person));
            }
            if (person.getImagine() && person.getOffice()) {
                table.addCell(createEmptySection());
            }
            table.setWidthPercentage(100F);
            document.add(table);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            assert document != null;
            document.close();
        }
        return null; //stub
    }

    /**
     * создать секцию почты
     *
     * @param person персона для которой создается pdf
     * @return секцию почты
     */
    private PdfPTable createGmailSection(PersonEntity person) {
        PdfPTable tableMail = new PdfPTable(1);
        tableMail.setWidthPercentage(100F);
        tableMail.getDefaultCell().setBorder(0);
        tableMail.addCell(loadPicture(environment.getProperty(PDF_GMAIL_LOGO_FILENAME)));
        HashMap<Pattern, String> replaceMap = new HashMap<>();
//        replaceMap.put(PDF_EMAIL_ADRESS_PATTERN,)
//        List<String> text = split(replace(loadText(environment.getProperty(PDF_GMAIL_TEXT_FILENAME), )));
//        ArrayList<String> textDoc = loadText("/pdf/" + LoadingConstants.getPDF_TEXT_GMAIL_FILENAME());
//        PdfPCell cells[] = new PdfPCell[]{
//                new PdfPCell(new Paragraph(textDoc.get(6), fontRegular12)),
//                new PdfPCell(new Paragraph((person.getSurname() + " " + person.getName() + " " + person.getPatronymic()), fontBold12)),
//                new PdfPCell(new Paragraph(textDoc.get(5), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(4), fontRegular12)),
//                new PdfPCell(new Paragraph((person.getLogin() + dbWorker.getDomenById(LoadingConstants.getGMAIL_DOMEN_ID())), fontBold12)),//stub
//                new PdfPCell(new Paragraph(textDoc.get(3), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(2), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(1), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(0), fontRegular12)),
//                new PdfPCell(new Paragraph(person.getInitPassw(), fontBold12))};
//        for (PdfPCell c : cells) {
//            c.setHorizontalAlignment(0);
//            c.setBorder(0);
//            tableMail.addCell(c);
//        }
//        return tableMail;
        return null; //stub
    }

    /**
     * создать секцию imagine
     *
     * @param person персона для которой создается pdf
     * @return секция imagine
     */
    private PdfPTable createImagineSection(PersonEntity person) {
//        PdfPTable tableMSDN = new PdfPTable(1);
//        tableMSDN.setWidthPercentage(100F);
//        tableMSDN.getDefaultCell().setBorder(0);
//        tableMSDN.addCell(loadPicture(PdfGenerator.class.getResource("/pdf/" + LoadingConstants.getPDF_IMAGINE_LOGO_NAME()).toURI()));
//        ArrayList<String> textDoc = loadText("/pdf/" + LoadingConstants.getPDF_TEXT_IMAGINE_FILENAME());
//        PdfPCell cells[] = new PdfPCell[]{
//                new PdfPCell(new Paragraph(textDoc.get(4), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(3), fontRegular12)),
//                new PdfPCell(new Paragraph((person.getLogin() + dbWorker.getDomenById(1)), fontBold12)),
//                new PdfPCell(new Paragraph(textDoc.get(2), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(1), fontBold12)),
//                new PdfPCell(new Paragraph(textDoc.get(0), fontBold12))
//        };
//        for (PdfPCell c : cells) {
//            c.setHorizontalAlignment(0);
//            c.setBorder(0);
//            tableMSDN.addCell(c);
//        }
//        return tableMSDN;
        return null;//stub
    }

    /**
     * создать секцию office
     *
     * @param person персона для которой создается pdf
     * @return секция office
     */
    private PdfPTable createOfficeSection(PersonEntity person) {
//        PdfPTable tableOffice = new PdfPTable(1);
//        tableOffice.setWidthPercentage(100F);
//        tableOffice.getDefaultCell().setBorder(0);
//        tableOffice.addCell(loadPicture(PdfGenerator.class.getResource("/pdf/" + LoadingConstants.getPDF_FONT_BOLD_NAME()).toURI()));
//        ArrayList<String> textDoc = loadText("/pdf/" + LoadingConstants.getPDF_TEXT_OFFICE_FILENAME());
//        PdfPCell cells[] = new PdfPCell[]{
//                new PdfPCell(new Paragraph(textDoc.get(5), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(4), fontRegular12)),
//                new PdfPCell(new Paragraph((person.getLogin() + dbWorker.getDomenById(2)), fontBold12)),
////                new PdfPCell(new Paragraph(person.getMail(), fontBold12)),
//                new PdfPCell(new Paragraph(textDoc.get(2), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(1), fontRegular12)),
//                new PdfPCell(new Paragraph(textDoc.get(0), fontRegular12))
//        };
//        for (PdfPCell c : cells) {
//            c.setHorizontalAlignment(0);
//            c.setBorder(0);
//            tableOffice.addCell(c);
//        }
//        return tableOffice;
        return null;//stub
    }

    /**
     * загрузить изображение
     *
     * @param pictureName имя файла изображения
     * @return изображение
     */
    private Jpeg loadPicture(String pictureName) {
        byte[] buffer = new byte[0x186a0];
        Jpeg picture = null;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(PdfGenerator.class.getResource(PDF_RESOURCE_LOCATION + pictureName).toURI())))) {
            if (in.read(buffer) != -1) {
                picture = new Jpeg(buffer);
            }
        } catch (IOException | BadElementException | URISyntaxException ex) {
            ex.printStackTrace();
        }
        return picture;
    }

    /**
     * загрузить текст
     *
     * @param textFileName имя файла текста
     * @return текст одной строкой с символами переноса строки
     */
    private String loadText(String textFileName) {
        StringBuilder builder = new StringBuilder();
        String temp;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(PDF_RESOURCE_LOCATION + textFileName), "utf-8"))) {//fixme кодировка
            while ((temp = reader.readLine()) != null) {
                builder.append(temp).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * создать пустую секцию
     *
     * @return пустая секция
     */
    private PdfPTable createEmptySection() {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100F);
        table.getDefaultCell().setBorder(0);
        return table;
    }

    /**
     * Проверить шрифты на существование и загрузить если необходимо
     *
     * @param environment окружения для получения строк из application.properties
     */
    private static void testLoadFonts(Environment environment) {
        if (null == fontRegular12 || null == fontBold12) {
            try {
                BaseFont fontNormal = BaseFont.createFont(String.valueOf(PdfGenerator.class.getResource(PDF_RESOURCE_LOCATION + environment.getProperty(PDF_FONT_REGULAR))), "Identity-H", false);
                BaseFont fontBold = BaseFont.createFont(String.valueOf(PdfGenerator.class.getResource(PDF_RESOURCE_LOCATION + environment.getProperty(PDF_FONT_BOLD))), "Identity-H", false);
                fontRegular12 = new Font(fontNormal, 12F, 0);
                fontBold12 = new Font(fontBold, 12F, 0);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}