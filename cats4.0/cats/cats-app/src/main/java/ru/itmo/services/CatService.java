package ru.itmo.services;
import ru.itmo.models.CatDto;

import java.util.List;

public interface CatService {
    void creation(CatDto catDto);
    CatDto findById(int id);
    void deleteById(int id);
    void makeFriends(int id1, int id2);
    void dropFriends(int id1, int id2);
    void changeOwner(int catId, int ownerId);
    List<CatDto> getCatsByCriteria(String color, String breed, String id, String ownerId);
}