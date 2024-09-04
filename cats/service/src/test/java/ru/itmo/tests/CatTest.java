package ru.itmo.tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.itmo.dao.CatDao;
import ru.itmo.dao.CatDaoImp;
import ru.itmo.dao.OwnerDao;
import ru.itmo.dao.OwnerDaoImp;
import ru.itmo.models.Breed;
import ru.itmo.models.Cat;
import ru.itmo.models.Color;
import ru.itmo.models.Owner;
import ru.itmo.services.CatService;
import ru.itmo.services.CatServiceImpl;
import ru.itmo.services.OwnerService;
import ru.itmo.services.OwnerServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class CatTest {

    @Test
    void addFriendsTest() {
        CatDao catDao = mock(CatDaoImp.class);
        OwnerDao ownerDao = mock(OwnerDaoImp.class);
        CatService catService = new CatServiceImpl(catDao, ownerDao);
        Owner owner = new Owner("Petr", LocalDate.ofEpochDay(2022-01-01));
        Cat cat1 = new Cat("Murzik",  Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner);
        Cat cat2 = new Cat("Musya",  Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner);
        Cat cat3 = new Cat("Tom",   Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner);
        cat1.setId(1);
        cat1.setFriends(new ArrayList<>());
        cat2.setId(2);
        cat2.setFriends(new ArrayList<>());
        cat3.setId(3);
        cat3.setFriends(new ArrayList<>());
        Mockito.when(catDao.findById(1)).thenReturn(cat1);
        Mockito.when(catDao.findById(2)).thenReturn(cat2);
        Mockito.when(catDao.findById(3)).thenReturn(cat3);
        catService.makeFriends(cat1.getId(), cat2.getId());
        catService.makeFriends(cat1.getId(), cat3.getId());
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        var actual = catService.findById(1).getFriendsId();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void dropFriendsTest() {
        CatDao catDao = mock(CatDaoImp.class);
        OwnerDao ownerDao = mock(OwnerDaoImp.class);
        CatService catService = new CatServiceImpl(catDao, ownerDao);
        Owner owner = new Owner("Petr", LocalDate.ofEpochDay(2022-01-01));
        Cat cat1 = new Cat("Murzik",  Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner);
        Cat cat2 = new Cat("Musya",  Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner);
        Cat cat3 = new Cat("Tom",   Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner);
        cat1.setId(1);
        cat1.setFriends(new ArrayList<>());
        cat2.setId(2);
        cat2.setFriends(new ArrayList<>());
        cat3.setId(3);
        cat3.setFriends(new ArrayList<>());
        Mockito.when(catDao.findById(1)).thenReturn(cat1);
        Mockito.when(catDao.findById(2)).thenReturn(cat2);
        Mockito.when(catDao.findById(3)).thenReturn(cat3);
        catService.makeFriends(cat1.getId(), cat2.getId());
        catService.makeFriends(cat1.getId(), cat3.getId());
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        var actual = catService.findById(1).getFriendsId();
        Assertions.assertEquals(expected, actual);
        catService.dropFriends(1, 2);
        catService.dropFriends(1, 3);
        expected = new ArrayList<>();
        actual = catService.findById(1).getFriendsId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void changeOwnerTest() {
        CatDao catDao = mock(CatDaoImp.class);
        OwnerDao ownerDao = mock(OwnerDaoImp.class);
        CatService catService = new CatServiceImpl(catDao, ownerDao);
        Owner owner1 = new Owner("Petr1", LocalDate.ofEpochDay(2022-01-01));
        Owner owner2 = new Owner("Petr2", LocalDate.ofEpochDay(2022-01-01));
        Cat cat1 = new Cat("Murzik",  Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner1);
        cat1.setId(1);
        cat1.setFriends(new ArrayList<>());
        owner1.setId(1);
        owner1.setCats(new ArrayList<>());
        owner2.setId(2);
        owner2.setCats(new ArrayList<>());
        Mockito.when(catDao.findById(1)).thenReturn(cat1);
        Mockito.when(ownerDao.findById(1)).thenReturn(owner1);
        Mockito.when(ownerDao.findById(2)).thenReturn(owner2);
        var expected = 1;
        var actual = catService.findById(1).getOwnerId();
        Assertions.assertEquals(expected, actual);
        catService.changeOwner(1, 2);
        expected = 2;
        actual = catService.findById(1).getOwnerId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        CatDao catDao = mock(CatDaoImp.class);
        OwnerDao ownerDao = mock(OwnerDaoImp.class);
        CatService catService = new CatServiceImpl(catDao, ownerDao);
        OwnerService ownerService = new OwnerServiceImpl(catDao, ownerDao);
        Owner owner1 = new Owner("Petr1", LocalDate.ofEpochDay(2022-01-01));
        Owner owner2 = new Owner("Petr2", LocalDate.ofEpochDay(2022-01-01));
        Cat cat1 = new Cat("Murzik",  Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner1);
        Cat cat2 = new Cat("Murzik",  Color.BLACK, Breed.BENGAL, LocalDate.ofEpochDay(2022-01-01), owner1);
        cat1.setId(1);
        cat1.setFriends(new ArrayList<>());
        cat2.setId(2);
        cat2.setFriends(new ArrayList<>());
        owner1.setId(1);
        owner1.setCats(new ArrayList<>());
        owner2.setId(2);
        owner2.setCats(new ArrayList<>());
        Mockito.when(catDao.findById(1)).thenReturn(cat1);
        Mockito.when(catDao.findById(2)).thenReturn(cat2);
        Mockito.when(ownerDao.findById(1)).thenReturn(owner1);
        Mockito.when(ownerDao.findById(2)).thenReturn(owner2);
        catService.makeFriends(1, 2);
        catService.delete(catService.findById(1));
        List<Integer> expected = new ArrayList<>();
        var actual = catService.findById(2).getFriendsId();
        Assertions.assertEquals(expected, actual);
        actual = ownerService.findById(1).getCatsId();
        Assertions.assertEquals(expected, actual);
    }
}
