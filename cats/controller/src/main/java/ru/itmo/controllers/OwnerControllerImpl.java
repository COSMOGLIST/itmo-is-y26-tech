package ru.itmo.controllers;

import ru.itmo.dto.CatDto;
import ru.itmo.dto.OwnerDto;
import ru.itmo.services.OwnerService;
import ru.itmo.services.OwnerServiceImpl;

import java.time.LocalDate;

public class OwnerControllerImpl implements OwnerController {
    private final OwnerService ownerService;

    public OwnerControllerImpl(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public OwnerDto findById(int id) {
        return ownerService.findById(id);
    }
    public OwnerDto creation(String name, LocalDate birthDate) {
        return ownerService.creation(name, birthDate);
    }
    public void delete(OwnerDto owner) {
        ownerService.delete(owner);
    }
}
