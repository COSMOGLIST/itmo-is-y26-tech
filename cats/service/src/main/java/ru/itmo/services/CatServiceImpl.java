package ru.itmo.services;

import lombok.experimental.ExtensionMethod;
import ru.itmo.dao.*;
import ru.itmo.dto.CatDto;
import ru.itmo.dto.OwnerDto;
import ru.itmo.exceptions.*;
import ru.itmo.mappers.CatMapper;
import ru.itmo.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtensionMethod(CatMapper.class)
public class CatServiceImpl implements CatService {
    private final CatDao catDao;
    private final OwnerDao ownerDao;

    public CatServiceImpl(CatDao catDao, OwnerDao ownerDao) {
        this.catDao = catDao;
        this.ownerDao = ownerDao;
    }

    public CatDto findById(int id) {
        return catDao.findById(id).asDto();
    }
    public CatDto creation(String name, LocalDate birthDate, String breed, String color, int ownerId) {
        Owner owner = ownerDao.findById(ownerId);
        Cat cat = new Cat(
                name,
                getColor(color),
                getBreed(breed),
                birthDate,
                owner);
        catDao.add(cat);
        owner.addCat(cat);
        ownerDao.update(owner);
        return cat.asDto();
    }
    public void delete(CatDto cat) {
        Cat cat1 = catDao.findById(cat.getId());
        for (Cat friend : cat1.getFriends()) {
            friend.dropFriend(cat1);
            catDao.update(friend);
        }
        cat1.setFriends(new ArrayList<>());
        cat1.setOwner(null);
        catDao.update(cat1);
        Owner owner = ownerDao.findById(cat.getId());
        owner.dropCat(cat1);
        ownerDao.update(owner);
        catDao.delete(cat1);
    }
    public void makeFriends(int cat1Id, int cat2Id) {
        Cat cat1 = catDao.findById(cat1Id);
        Cat cat2 = catDao.findById(cat2Id);
        cat1.addFriend(cat2);
        catDao.update(cat1);
        cat2.addFriend(cat1);
        catDao.update(cat2);
    }

    public void dropFriends(int cat1Id, int cat2Id) {
        Cat cat1 = catDao.findById(cat1Id);
        Cat cat2 = catDao.findById(cat2Id);
        cat1.dropFriend(cat2);
        catDao.update(cat1);
        cat2.dropFriend(cat1);
        catDao.update(cat2);
    }

    public void changeOwner(int catId, int newOwnerId) {
        Owner newOwner = ownerDao.findById(newOwnerId);
        Cat cat = catDao.findById(catId);
        Owner oldOwner = cat.getOwner();
        if (oldOwner != null) {
            oldOwner.dropCat(cat);
            ownerDao.update(oldOwner);
        }
        newOwner.addCat(cat);
        cat.setOwner(newOwner);
        catDao.update(cat);
        ownerDao.update(newOwner);
    }
    private Breed getBreed(String breed) {
        for(Breed toChoose : Breed.values()) {
            if (toChoose.name().equals(breed)) {
                return toChoose;
            }
        }
        throw new CatException("No such breed");
    }

    private Color getColor(String color) {
        for(Color toChoose : Color.values()) {
            if (toChoose.name().equals(color)) {
                return toChoose;
            }
        }
        throw new CatException("No such color");
    }
}