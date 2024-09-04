package ru.itmo.services;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.dto.UserDto;
import ru.itmo.mappers.UserMapper;
import ru.itmo.models.Role;
import ru.itmo.models.User;
import ru.itmo.repositories.OwnerRepository;
import ru.itmo.repositories.UserRepository;

@Service
@ExtensionMethod(UserMapper.class)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OwnerRepository ownerRepository) {
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
    }
    public UserDto CreateUser(UserDto userDto) {
        User user = new User(
                userDto.getName(),
                encoder().encode(userDto.getPassword()),
                Role.valueOf(userDto.getRole()),
                ownerRepository.getReferenceById(userDto.getOwnerId()));
        userRepository.save(user);
        return user.asDto();
    }

    public UserDto getUserByName(String name) {
        return userRepository.findUserByName(name).asDto();
    }
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}