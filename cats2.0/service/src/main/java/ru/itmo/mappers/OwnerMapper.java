package ru.itmo.mappers;

import lombok.experimental.UtilityClass;
import ru.itmo.dto.OwnerDto;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.util.List;

@UtilityClass
public class OwnerMapper {
    public static OwnerDto asDto(Owner owner) {
        return OwnerDto.builder()
                .id(owner.getId())
                .name(owner.getName())
                .birthDate(owner.getBirthDate())
                .build();
    }
}
