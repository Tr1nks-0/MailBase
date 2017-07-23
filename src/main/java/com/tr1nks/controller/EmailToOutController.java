package com.tr1nks.controller;

import com.tr1nks.model.engines.EmailToOutEngine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * контроллер получения адресов почты снаружи другили приложениями
 */
@RestController
@RequestMapping({"/emailToOutController", "/EmailToOutController"})//EmailToOutController --backward capability
public class EmailToOutController {
    @Resource
    private
    EmailToOutEngine emailToOutEngine;

    @GetMapping
    public void get(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("groupId") String groupId, HttpServletResponse response) {
        try {
            response.getWriter().write(emailToOutEngine.get(name, surname, groupId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}