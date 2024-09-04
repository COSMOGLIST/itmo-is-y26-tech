package ru.itmo.dao;

import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

public interface OwnerDao {
    Owner findById(int id);
    void add(Owner owner);
    void delete(Owner owner);
    void update(Owner owner);
}
