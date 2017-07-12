package com.tr1nks.model.utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;

import java.io.IOException;
import java.io.InputStream;

/**
 * поиск или декодирование изображений в html для pdf
 */
public class Base64ImageProviderForPDF extends AbstractImageProvider {
    /**
     * {@inheritDoc}
     * Извлечь изображение
     *
     * @param src строка - путь к изображению или само изображение закодированное в Base64
     * @return изображение
     */
    @Override
    public Image retrieve(String src) {
        int pos = src.indexOf("base64,");
        try {
            if (src.startsWith("data") && pos > 0) {
                byte[] img = Base64.decode(src.substring(pos + 7));
                return Image.getInstance(img);
            } else {
                InputStream is = this.getClass().getResourceAsStream(FileGenerator.PDF_RESOURCE_LOCATION + src);
                if (null != is) {
                    byte[] arr = new byte[is.available()];
                    is.read(arr);
                    return Image.getInstance(arr);
                }
            }
        } catch (BadElementException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getImageRootPath() {
        return null;
    }
}