package ru.itmo.controllers;

import ru.itmo.dto.CatDto;
import ru.itmo.services.CatService;
import ru.itmo.services.CatServiceImpl;

import java.time.LocalDate;

public class CatControllerImpl implements CatController {
    private final CatService catService;

    public CatControllerImpl(CatService catService) {
        this.catService = catService;
    }

    public CatDto findById(int id) {
        return catService.findById(id);
    }
    public CatDto creation(String name, LocalDate localDate, String breed, String color, int ownerId) {
        return catService.creation(name, localDate, breed, color, ownerId);
    }
    public void delete(CatDto cat) {
        catService.delete(cat);
    }
    public void makeFriends(int cat1Id, int cat2Id) {
        catService.makeFriends(cat1Id, cat2Id);
    }
    public void dropFriends(int cat1Id, int cat2Id) {
        catService.dropFriends(cat1Id, cat2Id);
    }
    public void changeOwner(int catId, int newOwnerId) {
        catService.changeOwner(catId, newOwnerId);
    }
}
