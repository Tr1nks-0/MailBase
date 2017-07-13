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
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * отправка писем на эл. ящики
 */
@Component
@PropertySource("classpath:static/application.properties")
public class MailSender {
    @Resource
    private Environment environment;
    private static Properties props;
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
    public void sendTLS(String subject, String text, File... attachments) {
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
//                    DataSource source2 = new FileDataSource(f);
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
}
