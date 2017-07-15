package com.tr1nks.model.utils;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * отправка писем на эл. ящики
 */
@Component
@PropertySource({"classpath:static/application.properties", "classpath:static/mail.properties"})

public class MailSender {
    private static final String EMAIL_SUBJECT_KEY = "email.email.subject";
    private static final String EMAIL_TEXT_KEY = "email.email.text";
    private static final Pattern NOW_DATETIME_PATTERN = Pattern.compile("@@NOW-DATETIME");
    private static final Pattern SEND_USER_PATTERN = Pattern.compile("@@SEND-USER");
    @Resource
    private Environment environment;
    private static Properties props;
    private static final DateFormatSymbols MY_DATE_FORMAT_SYMBOLS = new DateFormatSymbols() {
        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
    };
    public static final String SENDER_LOGIN = "email.sender.login";
    public static final String SENDER_PASSWORD = "email.sender.password";
    public static final String RECEIVER = "email.receiver";

    static {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    /**
     * отправить почту по TSL
     *
     * @param subject     тема письма
     * @param text        текст письма
     * @param attachments вложения
     */
    public void sendTLSFiles(String subject, String text, File... attachments) {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(environment.getRequiredProperty(SENDER_LOGIN), environment.getRequiredProperty(SENDER_PASSWORD));
            }
        });
        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(environment.getRequiredProperty(SENDER_LOGIN)));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(environment.getRequiredProperty(RECEIVER)));
            //Заголовок письма
            message.setSubject(subject);
            //Содержимое
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(text, "text/plain; charset=UTF-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            //для всех фалов добавляем их как части письма (будут видны как вложение)
            for (File f : attachments) {
                if (null != f) {
                    DataSource source = new FileDataSource(f);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName(), "UTF-8", "UTF-8"));
                    //      attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName()));
                    multipart.addBodyPart(attachmentBodyPart);
                }
            }
            message.setContent(multipart);
            //Отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendTLSMailPropFileText(byte[][] attachmentsBytes, String userLogin) {
        sendTLSBytes(environment.getProperty(EMAIL_SUBJECT_KEY), replaceInText(environment.getProperty(EMAIL_TEXT_KEY), userLogin), attachmentsBytes, new String[]{"gmail.csv", "Imagine.csv", "office.csv"});
    }

    public String replaceInText(String inText) {
        return SEND_USER_PATTERN.matcher(NOW_DATETIME_PATTERN.matcher(inText).replaceAll(new SimpleDateFormat("dd MMMM YYYY : H:mm:ss", MY_DATE_FORMAT_SYMBOLS).format(new Date()))).replaceAll("");//todo get a USER login
    }

    public String replaceInText(String inText, String userLogin) {
        return SEND_USER_PATTERN.matcher(NOW_DATETIME_PATTERN.matcher(inText).replaceAll(new SimpleDateFormat("dd MMMM YYYY : H:mm:ss", MY_DATE_FORMAT_SYMBOLS).format(new Date()))).replaceAll(userLogin);
    }

    public void sendTLSBytes(String subject, String text, byte[][] attachmentsBytes, String[] filenames) {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(environment.getRequiredProperty(SENDER_LOGIN), environment.getRequiredProperty(SENDER_PASSWORD));
            }
        });
        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(environment.getRequiredProperty(SENDER_LOGIN)));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(environment.getRequiredProperty(RECEIVER)));
            //Заголовок письма
            message.setSubject(subject);
            //Содержимое
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(text, "text/plain; charset=UTF-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            //для всех фалов добавляем их как части письма (будут видны как вложение)
            int i = 0;
            for (byte[] arr : attachmentsBytes) {
                if (null != arr) {//todo Исправить отправку файло - отправляются файлы с последним именем и одинаковые
                    DataSource source = new ByteArrayDataSource(arr, "text/csv");
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    attachmentBodyPart.setFileName(MimeUtility.encodeText("filenames[i]"+i, "UTF-8", "UTF-8"));
//                    attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName()));
                    multipart.addBodyPart(attachmentBodyPart);
                }
                i++;
            }
            /*for (File f : attachments) {
                if (null != f) {
                    DataSource source = new FileDataSource(f);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName(), "UTF-8", "UTF-8"));
                    //      attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName()));
                    multipart.addBodyPart(attachmentBodyPart);
                }
            }
            message.setContent(multipart);*/
            message.setContent(multipart);
            //Отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
