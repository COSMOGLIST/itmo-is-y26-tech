package ru.itmo.mappers;

import lombok.experimental.UtilityClass;
import ru.itmo.dto.CatDto;
import ru.itmo.models.Cat;

@UtilityClass
public class CatMapper {
    public static CatDto asDto(Cat cat) {
        return CatDto.builder()
                .id(cat.getId())
                .name(cat.getName())
                .breed(cat.getBreed().name())
                .color(cat.getColor().name())
                .birthDate(cat.getBirthDate())
                .ownerId(cat.getOwner().getId())
                .build();
    }
}
