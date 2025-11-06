package ru.lipnin.notifyservice.client;

import ru.lipnin.notifyservice.dto.UserDto;

import java.util.List;

public interface UserClient {
    List<UserDto> getUsersData();
    UserDto getUserDataById(Long id);
}
