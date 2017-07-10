package com.tr1nks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * контроллер получения адресов почты снаружи другили приложениями
 */
@RestController
@RequestMapping({"/EmailToOutController"})
public class EmailToOutController {
    //    private static final String VIEW_NAME = "upload";

//    public EmailToOutController() {
////        super(null, VIEW_NAME);
//        super(null, null);
//    }

    /**
     * GET mapping обработчик
     */
    @GetMapping
    public void get() {

    }
}

//
//@RestController
//public class HelloController {
//
//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
//
//    @GetMapping("/hello")
//    public Hello hello(@RequestParam(getText = "name", defaultValue = "World") String name) {
//        return new Hello(counter.incrementAndGet(), String.format(template, name));
//    }


//import javax.servlet.ServletExceptiogetn;
//        import javax.servlet.annotation.WebServlet;
//        import javax.servlet.http.HttpServlet;
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;
//        import java.io.IOException;
//
///**
// * @author Tr1nks
// *         19.05.2016
// */
//@WebServlet("/EmailToOutController")
//public class EmailToOutController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().write(DBWorker.getInstance().askForMailFrEmailToOutContr(request.getParameter("name"), request.getParameter("surname"), request.getParameter("groupId")));
//        response.getWriter().close();
//        process(request, response);// получим диспетчет запроса);
//    }
//
//    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.setCharacterEncoding("utf-8");// определим что его кодировка - utf-8
//        request.setAttribute("CURRENT_VERSION", Constants.CURRENT_VERSION);
//        response.setStatus(HttpServletResponse.SC_OK);// дадим ответ странице что все ок
//    }
//}