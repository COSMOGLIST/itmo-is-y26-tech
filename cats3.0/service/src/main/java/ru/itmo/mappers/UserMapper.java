package ru.itmo.mappers;

import lombok.experimental.UtilityClass;
import ru.itmo.dto.UserDto;
import ru.itmo.models.User;

@UtilityClass
public class UserMapper {
    public static UserDto asDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .id(user.getId())
                .ownerId(user.getOwner().getId())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }
}
