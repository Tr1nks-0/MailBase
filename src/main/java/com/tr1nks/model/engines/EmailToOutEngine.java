package com.tr1nks.model.engines;

import com.tr1nks.model.entities.StudentEntity;
import com.tr1nks.model.services.DomensService;
import com.tr1nks.model.services.GroupService;
import com.tr1nks.model.services.StudentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Pattern;

@Component
public class EmailToOutEngine {
    @Resource
    private StudentService studentService;
    @Resource
    private GroupService groupService;
    @Resource
    private DomensService domensService;
    private static final Pattern patternInnerGroupChiper = Pattern.compile("^0");
    private static final Pattern patternOuterGroupChiper = Pattern.compile("\\.0");

    public String get(String name, String surname, String groupChiper) {
        String grr = patternOuterGroupChiper.matcher((patternInnerGroupChiper.matcher(groupChiper).replaceAll(""))).replaceAll(".");
        StudentEntity student = studentService.getByGroupAndSurnameAndName(groupService.getByCipher(grr), surname, name);
        if (student == null) {
            return "{null}";
        } else {
            return student.getLogin() + domensService.getEmailDomen();
        }
    }
}
