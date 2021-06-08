package ru;

import org.springframework.lang.NonNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * В конструкторе конфигурируем отправителя сообщения
 */

@Component
public class MailSender {
    private JavaMailSenderImpl javaMailSender;

    public MailSender() {
        propSender();
    }

    public SimpleMailMessage messageToMail(SimpleMailMessage formMessage,
                                           String text,
                                           String email) {

        formMessage.setTo(email);
        formMessage.setSubject("Test mail");
        formMessage.setFrom(Const.EMAIL);
        formMessage.setText(text);
        return formMessage;
    }

    public @NonNull
    JavaMailSenderImpl getJavaMailSender() {
        return javaMailSender;
    }


    private JavaMailSenderImpl configureSender(JavaMailSenderImpl sender) {
        javaMailSender = sender;
        javaMailSender.setHost("smtp.mail.ru");
        javaMailSender.setPort(465);
        javaMailSender.setUsername(Const.EMAIL);
        javaMailSender.setPassword(Const.PASS);
        return javaMailSender;
    }
/** Настройка SMPT протокола */
    private void propSender() {
        Properties properties = configureSender(new JavaMailSenderImpl()).getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.smtps.ssl.checkserveridentity", "true");
        properties.put("mail.smtps.ssl.trust", "*");
        properties.put("mail.smtp.ssl.enable", "true");
    }

    @Async
    public void sentMessage(String message, String mail) {
        javaMailSender.send(messageToMail(new SimpleMailMessage(), message, mail));
    }
}
