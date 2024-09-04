package ru.itmo.services;

import ru.itmo.dto.UserDto;

public interface UserService {
    UserDto CreateUser(UserDto userDto);
    UserDto getUserByName(String name);
}
