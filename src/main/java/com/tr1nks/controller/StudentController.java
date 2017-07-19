package com.tr1nks.controller;

import com.tr1nks.model.engines.StudentEngine;
import com.tr1nks.model.pagedatas.StudentPageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

/**
 * контроллер страницы студенты
 */
@Controller
@RequestMapping({"/students"})
public class StudentController {
    private static final String VIEW_NAME = "students";
    private static final String MODEL_NAME = "studentsPD";
    @Resource
    private StudentEngine studentEngine;

    /**
     * GET mapping обработчик
     *
     * @return имя view и данные страницы с данными для фильтров
     */
    @GetMapping
    public ModelAndView get() {
        return new ModelAndView(VIEW_NAME, MODEL_NAME, studentEngine.get());
    }

    /**
     * POST mapping обработчик для обработчи с данными
     *
     * @param studentPD данные страницы
     * @return входные studentPD
     */
    @PostMapping
    public ModelAndView post(@ModelAttribute(MODEL_NAME) StudentPageData studentPD) {
        return new ModelAndView(VIEW_NAME, MODEL_NAME, studentPD);
    }

    /**
     * POST mapping обработчик действия фильтрации на странице
     *
     * @param studentPD данные страницы
     * @return имя представления и заполненые по фильтрам данные страницы
     */
    @PostMapping({"/filter"})
    public ModelAndView postFilter(@ModelAttribute(MODEL_NAME) StudentPageData studentPD) {
        studentEngine.fillFilterData(studentPD);
        return new ModelAndView(VIEW_NAME, MODEL_NAME, studentPD);
    }

    /**
     * POST mapping обработчик кнопок действия на странице
     *
     * @param action    имя действия
     * @param studentPD данные страницы
     * @return имя представления и данные страницы с соотв. изменениями если они были
     */
    @PostMapping({"/formProcess/{action}"})
    public ModelAndView postFormProcess(@PathVariable("action") String action, @ModelAttribute(MODEL_NAME) StudentPageData studentPD, Principal principal) {
        switch (action) {
            case "setBudget":
                studentEngine.budget(studentPD, true);
                break;
            case "removeBudget":
                studentEngine.budget(studentPD, false);
                break;
            case "setImagine":
                studentEngine.imagine(studentPD, true);
                break;
            case "removeImagine":
                studentEngine.imagine(studentPD, false);
                break;
            case "setOffice":
                studentEngine.office(studentPD, true);
                break;
            case "removeOffice":
                studentEngine.office(studentPD, false);
                break;
            case "sendData":
                studentEngine.sendData(studentPD, principal.getName());
                break;
            case "reload":
                //TODO
                break;
            default:
                break;
        }
        return new ModelAndView(VIEW_NAME, MODEL_NAME, studentPD);
    }

    /**
     * подтвердить редактирование на странице
     * @param studentPD
     * @return
     */
    @PostMapping(path = "/commitEdit")
    public ModelAndView commitEdit(@ModelAttribute(MODEL_NAME) StudentPageData studentPD) {
        studentEngine.commitEdit(studentPD);

        return new ModelAndView(VIEW_NAME, MODEL_NAME, studentPD);
    }

    /**
     * получить pdf архив из данных страницы
     * @param studentPD данные страницы
     * @param response ответ для передачи архива
     */
    @PostMapping(path = "/pdf")
    public void getPDFArchives(@ModelAttribute(MODEL_NAME) StudentPageData studentPD, HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/zip");
            byte[] arr = studentEngine.createPDFArchive(studentPD);
            outputStream.write(arr);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return new ModelAndView(VIEW_NAME, MODEL_NAME, studentPD);//fixme
    }
}
