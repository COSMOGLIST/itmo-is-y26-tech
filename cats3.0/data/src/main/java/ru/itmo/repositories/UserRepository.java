package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
    User findUserByName(String name);
}
