package com.tr1nks.model.formatters;

import com.tr1nks.model.entities.TeacherEntity;
import com.tr1nks.model.services.TeacherService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter для {@link TeacherEntity TeacherEntity}
 */
@Component
public class TeacherFormatter implements Formatter<TeacherEntity> {
    @Resource
    private TeacherService TeacherService;

    /**
     * {@inheritDoc}
     *
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public TeacherEntity parse(String text, Locale locale) throws ParseException {
        return TeacherService.getById(Integer.valueOf(text));
    }

    /**
     * {@inheritDoc}
     *
     * @param object
     * @param locale
     * @return
     */
    @Override
    public String print(TeacherEntity object, Locale locale) {
        return String.valueOf(object.getId());
    }
}