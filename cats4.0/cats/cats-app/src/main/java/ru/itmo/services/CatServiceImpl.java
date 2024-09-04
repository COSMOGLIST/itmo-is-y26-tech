package ru.itmo.services;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.filter.CatFields;
import ru.itmo.filter.CatFilter;
import ru.itmo.models.CatDto;
import ru.itmo.mappers.CatMapper;
import ru.itmo.models.Breed;
import ru.itmo.models.Cat;
import ru.itmo.models.Color;
import ru.itmo.repositories.CatRepository;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;
import static ru.itmo.filter.CatSpecification.getSpecification;

@Service
@ExtensionMethod(CatMapper.class)
@Transactional(readOnly = true)
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }
    @Transactional
    public void creation(CatDto catDto) {
        Cat cat = new Cat(catDto.getName(),
                Color.valueOf(catDto.getColor()),
                Breed.valueOf(catDto.getBreed()),
                catDto.getBirthDate(),
                catDto.getOwnerId());
        catRepository.save(cat);
    }
    public CatDto findById(int id) {
        return catRepository.getReferenceById(id).asDto();
    }
    @Transactional
    public void deleteById(int id) {
        Cat cat1 = catRepository.getReferenceById(id);
        for (Cat friend : cat1.getFriends()) {
            friend.dropFriend(cat1);
            catRepository.save(friend);
        }
        cat1.setFriends(new ArrayList<>());
        catRepository.save(cat1);
        catRepository.delete(cat1);
    }

    @Transactional
    public void makeFriends(int id1, int id2) {
        Cat cat1 = catRepository.getReferenceById(id1);
        Cat cat2 = catRepository.getReferenceById(id2);
        cat1.addFriend(cat2);
        catRepository.save(cat1);
        cat2.addFriend(cat1);
        catRepository.save(cat2);
    }

    @Transactional
    public void dropFriends(int id1, int id2) {
        Cat cat1 = catRepository.getReferenceById(id1);
        Cat cat2 = catRepository.getReferenceById(id2);
        cat1.dropFriend(cat2);
        catRepository.save(cat1);
        cat2.dropFriend(cat1);
        catRepository.save(cat2);
    }

    @Transactional
    public void changeOwner(int catId, int ownerId) {
        Cat cat = catRepository.getReferenceById(catId);
        cat.setOwnerId(ownerId);
        catRepository.save(cat);
    }
    public List<CatDto> getCatsByCriteria(String color, String breed, String id, String ownerId) {
        List<CatFilter> catFilters = new ArrayList<>();
        if (color != null) {
            catFilters.add(new CatFilter(CatFields.color, color));
        }
        if (breed != null) {
            catFilters.add(new CatFilter(CatFields.breed, breed));
        }
        if (id != null) {
            catFilters.add(new CatFilter(CatFields.id, id));
        }
        if (ownerId != null) {
            catFilters.add(new CatFilter(CatFields.ownerId, ownerId));
        }
        Specification<Cat> specification = getSpecification(catFilters);
        return catRepository.findAll(specification).stream().map(cat -> cat.asDto()).toList();
    }
}
