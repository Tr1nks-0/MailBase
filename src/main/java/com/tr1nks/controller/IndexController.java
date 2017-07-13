package com.tr1nks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер страницы index
 * начальная страница сайта
 * авторизация
 */
@Controller
@RequestMapping({"/index", "/", "/login"})
public class IndexController {
    private static final String VIEW_NAME = "index";

//    public IndexController() {
//        super(null, VIEW_NAME);
//    }

    //    @GetMapping({"/","/main"})
//    public String main(Model model) {
//        return new ModelAndView("main", Collections.singletonMap("hellos", reviewRepository.findAll()));
//        model.addAttribute("hellos", reviewRepository.findAll());
//        return "main";
//    }

    /**
     * GET mapping обработчик
     *
     * @return имя view
     */
    @GetMapping
    public String get() {
        return VIEW_NAME;
    }


//    @PostMapping({"/","/main"})
//    public String createPdfBytes(@RequestParam(getText = "name") String name) {
//        reviewRepository.add(name);
//        return "redirect:/";
//    }
}
