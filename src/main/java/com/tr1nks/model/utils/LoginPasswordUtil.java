package com.tr1nks.model.utils;

import com.tr1nks.model.services.StudentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * генератор логина и пароля
 */
@Component
public class LoginPasswordUtil {
    private Transliterator trs = new Transliterator();
    @Resource
    private StudentService studentService;

    /**
     * генерировать пароль
     *
     * @param lenthOfPass длинна пароля в символах (штук)
     * @return строку пароля
     */
    public String generatePassword(int lenthOfPass) {
        int typeOfSymbol;    //тип символов
        //массив символов, которые используются для генерации пароля:
        String[] Symbols = {
                "abcdefghijklmnopqrstuvwxyz",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "0123456789"};
//Строка, хранящая пароль с первой заглавной буквой:
        StringBuilder Password = new StringBuilder("" + Symbols[1].charAt((int) (Math.random() * Symbols[1].length())));
        for (int i = 0; i < lenthOfPass - 1; ++i) {
            // генерация типа символа:
            typeOfSymbol = (int) (Math.random() * Symbols.length);
            //добавление к паролю случайного символа из строки типа typeOfSymbol:
            Password.append(Symbols[typeOfSymbol].charAt((int) (Math.random() * Symbols[typeOfSymbol].length())));
        }
        return Password.toString();
    }

    /**
     * создать логин
     *
     * @param surname фамилия
     * @param name    имя
     * @return логин
     */
    public String createLogin(String surname, String name) {
        String enSurn = trs.generate(surname);
        String enName = trs.generate(name);
        StringBuilder builder = new StringBuilder();
        builder.append(enSurn);
        int i = 1;
        do {
            builder.append(i).append(enName);
            if (studentService.testEmail(builder.toString())) {
//                logins.add(builder.toString());
                return builder.toString();
            }
            builder.delete(name.length(), builder.length());
            i++;
        } while (true);
    }
}
