package ru.itmo.services;

import ru.itmo.models.OwnerDto;
import java.util.List;

public interface OwnerService {
    OwnerDto findById(int id);
    void creation(OwnerDto ownerDto);
    void deleteById(int id);
    List<OwnerDto> getAll();
}