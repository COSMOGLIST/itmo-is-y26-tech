package ru.itmo.controllers;

import ru.itmo.dto.CatDto;
import ru.itmo.services.CatServiceImpl;

import java.time.LocalDate;

public interface CatController {
    CatDto findById(int id);
    CatDto creation(String name, LocalDate localDate, String breed, String color, int ownerId);
    void delete(CatDto cat);
    void makeFriends(int cat1Id, int cat2Id);
    void dropFriends(int cat1Id, int cat2Id);
    void changeOwner(int catId, int newOwnerId);
}
