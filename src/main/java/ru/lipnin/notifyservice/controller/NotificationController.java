package ru.lipnin.notifyservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lipnin.notifyservice.dto.GeneralNotificationDto;
import ru.lipnin.notifyservice.dto.UserNotificationDto;
import ru.lipnin.notifyservice.dto.UserSecNotificationDto;
import ru.lipnin.notifyservice.exception.NotificationException;
import ru.lipnin.notifyservice.service.NotificationService;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/notifier")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/user")
    public ResponseEntity<Void> notifyUser(@RequestBody UserNotificationDto notification) {
        if (notification.message() == null || notification.userId() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            notificationService.sendNotification(notification);
            return ResponseEntity.ok().build();
        } catch (NotificationException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/general")
    public ResponseEntity<Void> sendGeneral(@RequestBody GeneralNotificationDto notification) {
        if (notification.message() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            notificationService.sendNotification(notification);
            return ResponseEntity.ok().build();
        } catch (NotificationException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/user_second")
    public ResponseEntity<Void> notifySecUser(@RequestBody UserSecNotificationDto notification) {
        if (notification.message() == null || notification.email() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            log.info("Notification email: " + notification.email());
            notificationService.sendSecNotification(notification);
            return ResponseEntity.ok().build();
        } catch (NotificationException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
