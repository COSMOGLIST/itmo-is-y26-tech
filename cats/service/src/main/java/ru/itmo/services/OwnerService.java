package ru.itmo.services;

import ru.itmo.dto.CatDto;
import ru.itmo.dto.OwnerDto;

import java.time.LocalDate;

public interface OwnerService {
    OwnerDto findById(int id);
    OwnerDto creation(String name, LocalDate birthDate);
    void delete(OwnerDto owner);
}