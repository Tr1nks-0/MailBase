package com.tr1nks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * контроллер страницы ошибок
 */
@ControllerAdvice
public class ErrorController {
    private static final String VIEW_NAME = "error";
    private static final String MODEL_NAME = "errorMessage";

//        private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    /**
     * обработчик ошибки
     *
     * @param throwable ошибка
     * @param model     модель
     * @return модель и представление страницы ошибки
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(final Throwable throwable, final Model model) {
        String errorMessage;
        if (null != throwable) {
            errorMessage = throwable.getMessage();
            throwable.printStackTrace();
        } else {
            errorMessage = "Unknown error";
            Throwable t = new UnknownError(errorMessage);
            t.printStackTrace();
        }
        return new ModelAndView(VIEW_NAME, MODEL_NAME, errorMessage);
    }
}
