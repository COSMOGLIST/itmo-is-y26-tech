package ru.itmo.controllers;

import ru.itmo.dto.CatDto;
import ru.itmo.dto.OwnerDto;

import java.time.LocalDate;

public interface OwnerController {
    OwnerDto findById(int id);
    OwnerDto creation(String name, LocalDate birthDate);
    void delete(OwnerDto owner);
}
