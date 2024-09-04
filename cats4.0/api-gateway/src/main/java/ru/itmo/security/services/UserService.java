package ru.itmo.security.services;

import ru.itmo.security.models.UserDto;

public interface UserService {
    UserDto CreateUser(UserDto userDto);
    UserDto getUserByName(String name);
    UserDto getCurrentUser();
}
