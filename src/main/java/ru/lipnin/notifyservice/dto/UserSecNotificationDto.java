package ru.lipnin.notifyservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

//Костыль, так как пока не знаю, как прокидывать токен в другой сервис
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserSecNotificationDto(
        String message,
        String email
) {
}
