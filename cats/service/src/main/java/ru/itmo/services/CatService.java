package ru.itmo.services;

import ru.itmo.dto.*;

import java.time.LocalDate;

public interface CatService {
    CatDto findById(int id);
    CatDto creation(String name, LocalDate birthDate, String breed, String color, int ownerId);
    void delete(CatDto cat);
    void makeFriends(int cat1Id, int cat2Id);
    void dropFriends(int cat1Id, int cat2Id);
    void changeOwner(int catId, int newOwnerId);
}