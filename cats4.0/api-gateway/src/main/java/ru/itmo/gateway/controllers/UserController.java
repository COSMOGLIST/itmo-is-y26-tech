package ru.itmo.gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.CatDto;
import ru.itmo.gateway.services.CatServiceUser;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CatServiceUser catServiceUser;

    @Autowired
    public UserController(CatServiceUser catServiceUser) {
        this.catServiceUser = catServiceUser;
    }

    @GetMapping(value = "/{id}")
    public CatDto findById(@PathVariable(name = "id") int id) {
        return catServiceUser.findById(id);
    }

    @GetMapping(value = "/cats")
    public List<CatDto> getCatsByCriteria(@RequestParam(name = "color", required = false) String color,
                                          @RequestParam(name = "breed", required = false) String breed,
                                          @RequestParam(name = "id", required = false) String id) {
        return catServiceUser.getCatsByCriteria(color, breed, id);
    }

    @PostMapping(value = "/cats")
    public void createCat(@RequestBody CatDto catDto) {
        catServiceUser.createCat(catDto);
    }

    @PutMapping("/cats/{id}/friends")
    public void makeCatFriends(@PathVariable(value = "id") int cat1Id, @RequestParam(value = "id") int cat2Id) {
        catServiceUser.makeFriends(cat1Id, cat2Id);
    }

    @PutMapping("/cats/{id}/foes")
    public void dropCatFriends(@PathVariable(value = "id") int cat1Id, @RequestParam(value = "id") int cat2Id) {
        catServiceUser.dropFriends(cat1Id, cat2Id);
    }
}
