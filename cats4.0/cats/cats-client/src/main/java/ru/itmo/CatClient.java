package ru.itmo;

import ru.itmo.models.CatDto;

import java.util.List;

public interface CatClient {
    CatDto findById(int id);
    List<CatDto> getCatsByCriteria(String color, String breed, String id, String ownerId);
}
