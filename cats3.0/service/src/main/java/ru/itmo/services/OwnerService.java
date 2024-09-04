package ru.itmo.services;

import ru.itmo.dto.OwnerDto;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {
    OwnerDto findById(int id);
    OwnerDto creation(OwnerDto ownerDto);
    void deleteById(int id);
    List<OwnerDto> getAll();
}