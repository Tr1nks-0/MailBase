package com.tr1nks.model.formatters;

import com.tr1nks.model.entities.CathedraEntity;
import com.tr1nks.model.services.CathedraService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter для {@link CathedraEntity CathedraEntity}
 */
@Component
public class CathedraFormatter implements Formatter<CathedraEntity> {
    @Resource
    private CathedraService cathedraService;

    /**
     * {@inheritDoc}
     *
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public CathedraEntity parse(String text, Locale locale) throws ParseException {
        return cathedraService.getById(Integer.valueOf(text));
    }

    /**
     * {@inheritDoc}
     *
     * @param object
     * @param locale
     * @return
     */
    @Override
    public String print(CathedraEntity object, Locale locale) {
        return String.valueOf(object.getId());
    }
}