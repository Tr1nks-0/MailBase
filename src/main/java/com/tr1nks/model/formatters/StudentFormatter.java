package com.tr1nks.model.formatters;

import com.tr1nks.model.entities.StudentEntity;
import com.tr1nks.model.services.StudentService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter для {@link StudentEntity StudentEntity}
 */
@Component
public class StudentFormatter implements Formatter<StudentEntity> {
    @Resource
    private StudentService StudentService;

    /**
     * {@inheritDoc}
     *
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public StudentEntity parse(String text, Locale locale) throws ParseException {
        return StudentService.getById(Integer.valueOf(text));
    }

    /**
     * {@inheritDoc}
     *
     * @param object
     * @param locale
     * @return
     */
    @Override
    public String print(StudentEntity object, Locale locale) {
        return String.valueOf(object.getId());
    }
}