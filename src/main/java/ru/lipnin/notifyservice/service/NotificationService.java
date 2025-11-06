package ru.lipnin.notifyservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.lipnin.notifyservice.client.UserClient;
import ru.lipnin.notifyservice.dto.GeneralNotificationDto;
import ru.lipnin.notifyservice.dto.UserDto;
import ru.lipnin.notifyservice.dto.UserNotificationDto;


@RequiredArgsConstructor
@Service
public class NotificationService {

    @Value("${notify.sent.from}")
    private String from;
    private final UserClient userClient;
    private final JavaMailSender mailSender;

    public void sendNotification(UserNotificationDto notification) {
        String email = userClient.getUserDataById(notification.userId())
                .email();

        mailSender.send(getSimpleMessage(notification.message(),
                "Уведомление",
                new String[]{email}));
    }

    public void sendNotification(GeneralNotificationDto generalNotification) {
        String[] usersEmails = userClient.getUsersData()
                .stream()
                .map(UserDto::email)
                .toArray(String[]::new);

        mailSender.send(getSimpleMessage(generalNotification.message(),
                "Рассылка",
                usersEmails));
    }

    private SimpleMailMessage getSimpleMessage(String message, String subject, String[] setTo) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("admin@gmail.com");
        mailMessage.setTo(setTo); // кому
        mailMessage.setSubject(subject); // тема письма
        mailMessage.setText(message); // текст письма
        return mailMessage;
    }

//    private String getSorryMessage(String name) {
//        return """
//        Уважаемый(ая), %s!
//
//        Приносим свои извинения за доставленные неудобства.
//        Дарим Вам скидку 15%% на следующую услугу!
//
//        С уважением,
//        Ваша служба поддержки
//        """.formatted(name);
//    }
}
