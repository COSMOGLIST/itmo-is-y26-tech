package ru.itmo.services;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.dto.OwnerDto;
import ru.itmo.mappers.OwnerMapper;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;
import ru.itmo.repositories.CatRepository;
import ru.itmo.repositories.OwnerRepository;
import java.util.List;
@Service
@ExtensionMethod(OwnerMapper.class)
@Transactional(readOnly = true)
public class OwnerServiceImpl implements OwnerService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }
    public OwnerDto findById(int id) {
        return ownerRepository.getReferenceById(id).asDto();
    }
    @Transactional
    public OwnerDto creation(OwnerDto ownerDto) {
        Owner owner = new Owner(ownerDto.getName(), ownerDto.getBirthDate());
        ownerRepository.save(owner);
        return OwnerMapper.asDto(owner);
    }
    @Transactional
    public void deleteById(int id) {
        Owner owner1 = ownerRepository.getReferenceById(id);
        for (Cat cat : owner1.getCats()) {
            cat.setOwner(null);
            catRepository.save(cat);
        }
        owner1.getCats().clear();
        ownerRepository.save(owner1);
        ownerRepository.save(owner1);
    }
    public List<OwnerDto> getAll() {
        return ownerRepository.findAll().stream().map(owner -> owner.asDto()).toList();
    }
}
