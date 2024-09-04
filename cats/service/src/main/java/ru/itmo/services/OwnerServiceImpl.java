package ru.itmo.services;

import lombok.experimental.ExtensionMethod;
import ru.itmo.dao.CatDao;
import ru.itmo.dao.CatDaoImp;
import ru.itmo.dao.OwnerDao;
import ru.itmo.dao.OwnerDaoImp;
import ru.itmo.dto.CatDto;
import ru.itmo.dto.OwnerDto;
import ru.itmo.mappers.CatMapper;
import ru.itmo.mappers.OwnerMapper;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@ExtensionMethod(OwnerMapper.class)
public class OwnerServiceImpl implements OwnerService {
    private OwnerDao ownerDao;
    private CatDao catDao;

    public OwnerServiceImpl(CatDao catDao, OwnerDao ownerDao) {
        this.catDao = catDao;
        this.ownerDao = ownerDao;
    }

    public OwnerDto findById(int id) {
        return ownerDao.findById(id).asDto();
    }
    public OwnerDto creation(String name, LocalDate birthDate) {
        Owner owner = new Owner(name, birthDate);
        ownerDao.add(owner);
        return owner.asDto();
    }
    public void delete(OwnerDto owner) {
        Owner owner1 = ownerDao.findById(owner.getId());
        for (Cat cat : owner1.getCats()) {
            cat.setOwner(null);
            catDao.update(cat);
        }
        owner1.getCats().clear();
        ownerDao.update(owner1);
        ownerDao.delete(owner1);
    }
}
