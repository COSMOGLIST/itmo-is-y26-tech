package ru.itmo.services;

import lombok.experimental.ExtensionMethod;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.models.OwnerDto;
import ru.itmo.mappers.OwnerMapper;
import ru.itmo.models.Owner;
import ru.itmo.repositories.OwnerRepository;

import java.util.List;
@Service
@EnableRabbit
@ExtensionMethod(OwnerMapper.class)
@Transactional(readOnly = true)
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
    public OwnerDto findById(int id) {
        return ownerRepository.getReferenceById(id).asDto();
    }
    @Transactional
    public void creation(OwnerDto ownerDto) {
        Owner owner = new Owner(ownerDto.getName(), ownerDto.getBirthDate());
        ownerRepository.save(owner);
    }
    @Transactional
    public void deleteById(int id) {
        ownerRepository.deleteById(id);
    }
    public List<OwnerDto> getAll() {
        return ownerRepository.findAll().stream().map(owner -> owner.asDto()).toList();
    }
}