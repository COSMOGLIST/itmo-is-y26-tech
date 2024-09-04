package ru.itmo.dto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private int id;
    private String name;
    private String password;
    private String role;
    private int ownerId;
    public UserDto(int id, String name, String password, String role, int ownerId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.ownerId = ownerId;
    }
}
