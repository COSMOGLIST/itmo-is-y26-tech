package ru.itmo.services;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.dto.CatDto;
import ru.itmo.mappers.CatMapper;
import ru.itmo.models.*;
import ru.itmo.repositories.*;

import java.util.ArrayList;
import java.util.List;
@Service
@ExtensionMethod(CatMapper.class)
@Transactional(readOnly = true)
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository, UserRepository userRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }
    public CatDto findById(int id) {
        User user = userRepository.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole() == Role.ADMIN)
        {
            return catRepository.getReferenceById(id).asDto();
        } else {
            return catRepository.findCatByIdAndOwnerId(id, user.getOwner().getId()).asDto();
        }
    }
    @Transactional
    public CatDto creation(CatDto catDto) {
        Owner owner = ownerRepository.getReferenceById(catDto.getOwnerId());
        Cat cat = new Cat(catDto.getName(),
                Color.valueOf(catDto.getColor()),
                Breed.valueOf(catDto.getBreed()),
                catDto.getBirthDate(),
                owner);
        catRepository.save(cat);
        owner.addCat(cat);
        ownerRepository.save(owner);
        return cat.asDto();
    }
    @Transactional
    public void deleteById(int id) {
        Cat cat1 = catRepository.getReferenceById(id);
        for (Cat friend : cat1.getFriends()) {
            friend.dropFriend(cat1);
            catRepository.save(friend);
        }
        cat1.setFriends(new ArrayList<>());
        cat1.setOwner(null);
        catRepository.save(cat1);
        Owner owner = ownerRepository.getReferenceById(id);
        owner.dropCat(cat1);
        ownerRepository.save(owner);
        catRepository.delete(cat1);
    }
    @Transactional
    public void makeFriends(int cat1Id, int cat2Id) {
        Cat cat1 = catRepository.getReferenceById(cat1Id);
        Cat cat2 = catRepository.getReferenceById(cat2Id);
        cat1.addFriend(cat2);
        catRepository.save(cat1);
        cat2.addFriend(cat1);
        catRepository.save(cat2);
    }

    @Transactional
    public void dropFriends(int cat1Id, int cat2Id) {
        Cat cat1 = catRepository.getReferenceById(cat1Id);
        Cat cat2 = catRepository.getReferenceById(cat2Id);
        cat1.dropFriend(cat2);
        catRepository.save(cat1);
        cat2.dropFriend(cat1);
        catRepository.save(cat2);
    }

    @Transactional
    public void changeOwner(int catId, int newOwnerId) {
        Owner newOwner = ownerRepository.getReferenceById(newOwnerId);
        Cat cat = catRepository.getReferenceById(catId);
        Owner oldOwner = cat.getOwner();
        if (oldOwner != null) {
            oldOwner.dropCat(cat);
            ownerRepository.save(oldOwner);
        }
        newOwner.addCat(cat);
        cat.setOwner(newOwner);
        catRepository.save(cat);
        ownerRepository.save(newOwner);
    }

    public List<CatDto> getAll() {
        User user = userRepository.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole() == Role.ADMIN)
        {
            return catRepository.findAll().stream().map(cat -> cat.asDto()).toList();
        } else {
            return catRepository.findCatByOwnerId(user.getOwner().getId()).stream().map(cat -> cat.asDto()).toList();
        }
    }
    public List<CatDto> getCatsByBreed(String breed) {
        User user = userRepository.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole() == Role.ADMIN)
        {
            return catRepository.findCatByBreed(Breed.valueOf(breed)).stream().map(cat -> cat.asDto()).toList();
        } else {
            return catRepository.findCatByBreedAndOwnerId(Breed.valueOf(breed), user.getOwner().getId()).stream().map(cat -> cat.asDto()).toList();
        }
    }
    public List<CatDto> getCatsByColor(String color) {
        User user = userRepository.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole() == Role.ADMIN)
        {
            return catRepository.findCatByColor(Color.valueOf(color)).stream().map(cat -> cat.asDto()).toList();
        } else {
            return catRepository.findCatByColorAndOwnerId(Color.valueOf(color), user.getOwner().getId()).stream().map(cat -> cat.asDto()).toList();
        }
    }
    public List<CatDto> getCatsByColorAndBreed(String color, String breed) {
        User user = userRepository.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole() == Role.ADMIN)
        {
            return catRepository.findCatByColorAndBreed(Color.valueOf(color), Breed.valueOf(breed)).stream().map(cat -> cat.asDto()).toList();
        } else {
            return catRepository.findCatByColorAndBreedAndOwnerId(Color.valueOf(color), Breed.valueOf(breed), user.getOwner().getId()).stream().map(cat -> cat.asDto()).toList();
        }
    }
}
