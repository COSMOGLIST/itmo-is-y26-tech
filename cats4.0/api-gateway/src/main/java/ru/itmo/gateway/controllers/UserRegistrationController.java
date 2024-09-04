package ru.itmo.gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.security.models.UserDto;
import ru.itmo.security.services.UserService;

@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto creation(@RequestBody UserDto userDto) {
        return userService.CreateUser(userDto);
    }
}