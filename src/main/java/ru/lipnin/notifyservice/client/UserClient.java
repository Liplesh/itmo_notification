package ru.lipnin.notifyservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lipnin.notifyservice.dto.UserDto;

import java.util.List;

@FeignClient(value = "users", url = "http://localhost:8081/api/v1/users")
@Component
public interface UserClient {
    @GetMapping
    List<UserDto> getAllUsersData();

    @GetMapping("/{id}")
    UserDto getUserDataById(@PathVariable Long id);
}
