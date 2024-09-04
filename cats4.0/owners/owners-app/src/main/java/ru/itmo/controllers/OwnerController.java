package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.OwnerDto;
import ru.itmo.services.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(value = "/{id}")
    public OwnerDto findById(@PathVariable(name = "id") int id) {
        return ownerService.findById(id);
    }
    @GetMapping
    public List<OwnerDto> getAll() {
        return ownerService.getAll();
    }
}