package com.tr1nks.model.formatters;

import com.tr1nks.model.entities.GroupEntity;
import com.tr1nks.model.services.GroupService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter для {@link GroupEntity GroupEntity}
 */
@Component
public class GroupFormatter implements Formatter<GroupEntity> {
    @Resource
    private GroupService groupService;

    /**
     * {@inheritDoc}
     *
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public GroupEntity parse(String text, Locale locale) throws ParseException {
        return groupService.getById(Integer.valueOf(text));
    }

    /**
     * {@inheritDoc}
     *
     * @param object
     * @param locale
     * @return
     */
    @Override
    public String print(GroupEntity object, Locale locale) {
        return String.valueOf(object.getId());
    }
}