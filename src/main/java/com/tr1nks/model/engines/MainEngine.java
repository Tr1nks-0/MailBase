package com.tr1nks.model.engines;

import com.tr1nks.model.entities.FacultyEntity;
import com.tr1nks.model.services.FacultyService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * обработчик для контроллера {@link com.tr1nks.controller.MainController MainController}
 */
@Component
public class MainEngine {
    @Resource
    private FacultyService facultyService;
//    @Resource
//    private CathedraService cathedraService;
//    @Resource
//    private GroupService groupService;
//    @Resource
//    private StudentService studentService;
//    @Resource
//    private TeacherService teacherService;

    /**
     * прверить - заполнить бд
     * проверяет на наличие в таблице факультетов записей, если записей нет то заполняет таблицу
     */
    public void testCreateDB() {
        List list = facultyService.getAll();
        if (null == list || list.size() < 1) {
            Arrays.stream(faculties).forEach(fe -> {
                facultyService.addFaculty(fe);
            });
        }
    }

    private static final FacultyEntity[] faculties = {
            new FacultyEntity(1, "Консалтинга и международного бизнеса", "КИМБ"),
            new FacultyEntity(2, "Финансовый", "ФФ"),
            new FacultyEntity(3, "Менеджмента и маркетинга", "МИМ"),
            new FacultyEntity(4, "Экономической Информатики", "ЭИ"),
            new FacultyEntity(5, "Экономики и права", "ЭП"),
            new FacultyEntity(6, "Международных экономических отношений", "МЭО"),
            new FacultyEntity(46, "Подготовки иностранных граждан", "ПИГ"),
    };
}