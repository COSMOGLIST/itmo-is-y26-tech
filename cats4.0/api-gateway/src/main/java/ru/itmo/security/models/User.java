package ru.itmo.security.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private Role role;
    @Column
    private int ownerId;
    public User(String name, String password, Role role, int ownerId) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.ownerId = ownerId;
    }
}
