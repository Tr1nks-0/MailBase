package com.tr1nks.controller;

import com.tr1nks.model.engines.MainEngine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * контроллер главной страницы
 */
@Controller
@RequestMapping({"/main"})
public class MainController {
    private static final String VIEW_NAME = "main";
    private static boolean needCreate = true;
    @Resource
    private MainEngine mainEngine;

    /**
     * GET mapping обработчик
     * проверяет на заполненость таблицу faculty, и заполняет если она пуста
     *
     * @return имя view
     */
    @GetMapping
    public String get() {
        if (needCreate) {
            mainEngine.testCreateDB();
            needCreate = false;
        }
        return VIEW_NAME;
    }
}
