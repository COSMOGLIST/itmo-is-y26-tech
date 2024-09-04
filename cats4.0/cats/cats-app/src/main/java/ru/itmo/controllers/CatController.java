package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.CatDto;
import ru.itmo.services.CatService;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public List<CatDto> getCatsByColorAndBreed(@RequestParam(name = "color", required = false) String color,
                                               @RequestParam(name = "breed", required = false) String breed,
                                               @RequestParam(name = "id", required = false) String id,
                                               @RequestParam(name = "ownerId", required = false) String ownerId) {
        return catService.getCatsByCriteria(color, breed, id, ownerId);
    }

    @GetMapping(value = "/{id}")
    public CatDto findById(@PathVariable(name = "id") int id) {
        return catService.findById(id);
    }

}
