package com.tr1nks.model.formatters;

import com.tr1nks.model.entities.FacultyEntity;
import com.tr1nks.model.services.FacultyService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter для {@link FacultyEntity FacultyEntity}
 */
@Component
public class FacultyFormatter implements Formatter<FacultyEntity> {
    @Resource
    private FacultyService facultyService;

    /**
     * {@inheritDoc}
     *
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public FacultyEntity parse(String text, Locale locale) throws ParseException {
        return facultyService.getById(Integer.valueOf(text));
    }

    /**
     * {@inheritDoc}
     *
     * @param object
     * @param locale
     * @return
     */
    @Override
    public String print(FacultyEntity object, Locale locale) {
        return String.valueOf(object.getId());
    }
}