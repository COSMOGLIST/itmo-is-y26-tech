package ru.itmo.mappers;

import lombok.experimental.UtilityClass;
import ru.itmo.dto.OwnerDto;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.util.List;

@UtilityClass
public class OwnerMapper {
    public OwnerDto asDto(Owner owner) {
        List<Integer> friendsId = owner.getCats().stream().map(Cat::getId).toList();
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthDate(), friendsId);
    }
}
