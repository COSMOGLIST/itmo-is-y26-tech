package ru.itmo.gateway.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.models.CatDto;
import ru.itmo.models.OwnerDto;
import ru.itmo.gateway.services.CatServiceAdmin;
import ru.itmo.gateway.services.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final CatServiceAdmin catServiceAdmin;
    private final OwnerService ownerService;

    @Autowired
    public AdminController(CatServiceAdmin catServiceAdmin, OwnerService ownerService) {
        this.catServiceAdmin = catServiceAdmin;
        this.ownerService = ownerService;
    }

    @GetMapping(value = "/owners/{id}")
    public OwnerDto findOwnerById(@PathVariable(name = "id") int id) {
        return ownerService.findById(id);
    }

    @PostMapping(value = "/owners")
    public void createOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.creation(ownerDto);
    }

    @DeleteMapping(value = "/owners/{id}")
    public void deleteOwnerById(@PathVariable(name = "id") int id) {
        ownerService.deleteById(id);
    }

    @GetMapping(value = "/owners")
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAll();
    }

    @PostMapping(value = "/cats")
    public void createCat(@RequestBody CatDto catDto) {
        catServiceAdmin.createCat(catDto);
    }

    @DeleteMapping(value = "/cats/{id}")
    public void deleteCatById(@PathVariable(name = "id") int id) {
        catServiceAdmin.deleteById(id);
    }

    @PutMapping("/cats/{id}/friends/{friendId}")
    public void makeCatFriends(@PathVariable(value = "id") int cat1Id, @PathVariable(value = "friendId") int cat2Id) {
        catServiceAdmin.makeFriends(cat1Id, cat2Id);
    }

    @PutMapping("/cats/{id}/foes/{foeId}")
    public void dropCatFriends(@PathVariable(value = "id") int cat1Id, @PathVariable(value = "foeId") int cat2Id) {
        catServiceAdmin.dropFriends(cat1Id, cat2Id);
    }

    @PutMapping("/cats/{id}/relocation/{newOwnerId}")
    public void changeOwnerForCat(@PathVariable(name = "id") int catId, @PathVariable(name = "newOwnerId") int newOwnerId) {
        catServiceAdmin.changeOwner(catId, newOwnerId);
    }

    @GetMapping(value = "/cats/{id}")
    public CatDto findCatById(@PathVariable(name = "id") int id) {
        return catServiceAdmin.findById(id);
    }

    @GetMapping(value = "/cats")
    public List<CatDto> getCatsByCriteria(@RequestParam(name = "color", required = false) String color,
                                          @RequestParam(name = "breed", required = false) String breed,
                                          @RequestParam(name = "id", required = false) String id,
                                          @RequestParam(name = "owner-id", required = false) String ownerId) {
        return catServiceAdmin.getCatsByCriteria(color, breed, id, ownerId);
    }
}