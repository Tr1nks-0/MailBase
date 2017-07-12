package com.tr1nks.controller;

import com.tr1nks.model.engines.StudentEngine;
import com.tr1nks.model.pagedatas.StudentPageData;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

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
    public ModelAndView postFormProcess(@PathVariable("action") String action, @ModelAttribute(MODEL_NAME) StudentPageData studentPD) {
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
                studentEngine.sendData(studentPD);
                break;
//            case "getArchives":
//                //TODO
//                break;
            case "reload":
                //TODO
                break;
            default:
                break;
        }
        return new ModelAndView(VIEW_NAME, MODEL_NAME, studentPD);
    }

    @PostMapping(path = "/pdf")
    public void getPDFArchives(@ModelAttribute(MODEL_NAME) StudentPageData studentPD, HttpServletResponse response) {
//        try (OutputStream outputStream = response.getOutputStream()) {
        try{
            response.setContentType("application/zip");
            byte[]arr=studentEngine.createPDFArchive(studentPD);
//            outputStream.write(arr);
            IOUtils.copy(new ByteArrayInputStream(arr),response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream())) {
//
//            writer.write();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try (){
//            studentEngine.createPDF(studentPD, response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ModelAndView(VIEW_NAME, MODEL_NAME, studentPD);
    }
}