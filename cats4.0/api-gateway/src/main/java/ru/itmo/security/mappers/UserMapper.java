package ru.itmo.security.mappers;

import lombok.experimental.UtilityClass;
import ru.itmo.security.models.UserDto;
import ru.itmo.security.models.User;

@UtilityClass
public class UserMapper {
    public static UserDto asDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .id(user.getId())
                .ownerId(user.getOwnerId())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }
}
