package ru.itmo.mappers;

import lombok.experimental.UtilityClass;
import ru.itmo.dto.CatDto;
import ru.itmo.models.Cat;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CatMapper {
    public CatDto asDto(Cat cat) {
        List<Integer> friendsId = cat.getFriends().stream().map(Cat::getId).toList();
        return new CatDto(cat.getId(), cat.getName(), cat.getBreed().name(),
                cat.getColor().name(), cat.getBirthDate(), cat.getOwner().getId(), friendsId);
    }
}
