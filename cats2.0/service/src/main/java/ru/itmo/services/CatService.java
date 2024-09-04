package ru.itmo.services;

import ru.itmo.dto.CatDto;

import java.time.LocalDate;
import java.util.List;

public interface CatService {
    CatDto findById(int id);
    CatDto creation(CatDto catDto);
    void deleteById(int id);
    void makeFriends(int cat1Id, int cat2Id);
    void dropFriends(int cat1Id, int cat2Id);
    void changeOwner(int catId, int newOwnerId);
    List<CatDto> getAll();
    List<CatDto> getCatsByBreed(String breed);
    List<CatDto> getCatsByColor(String color);
    List<CatDto> getCatsByColorAndBreed(String color, String breed);
}