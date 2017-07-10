package com.tr1nks.model.formatters;

import com.tr1nks.model.entities.UserEntity;
import com.tr1nks.model.services.UserService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter для {@link UserEntity UserEntity}
 */
@Component
public class UserFormatter implements Formatter<UserEntity> {
    @Resource
    private UserService UserService;

    /**
     * {@inheritDoc}
     *
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public UserEntity parse(String text, Locale locale) throws ParseException {
        return UserService.getById(Integer.valueOf(text));
    }

    /**
     * {@inheritDoc}
     *
     * @param object
     * @param locale
     * @return
     */
    @Override
    public String print(UserEntity object, Locale locale) {
        return String.valueOf(object.getId());
    }
}