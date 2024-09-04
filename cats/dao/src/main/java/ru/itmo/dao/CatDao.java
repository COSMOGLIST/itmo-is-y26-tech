package ru.itmo.dao;

import ru.itmo.models.Cat;

public interface CatDao {
    Cat findById(int id);
    void add(Cat cat);
    void delete(Cat cat);
    void update(Cat cat);
}
