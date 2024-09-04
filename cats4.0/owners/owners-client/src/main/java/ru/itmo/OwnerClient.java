package ru.itmo;

import ru.itmo.models.OwnerDto;

import java.util.List;

public interface OwnerClient {
    OwnerDto findById(int id);
    List<OwnerDto> getAll();
}
