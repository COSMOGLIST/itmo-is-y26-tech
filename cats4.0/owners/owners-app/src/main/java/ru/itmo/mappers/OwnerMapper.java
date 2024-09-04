package ru.itmo.mappers;

import lombok.experimental.UtilityClass;
import ru.itmo.models.OwnerDto;
import ru.itmo.models.Owner;

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
