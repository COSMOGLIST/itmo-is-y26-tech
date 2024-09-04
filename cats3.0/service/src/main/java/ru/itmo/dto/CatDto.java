package ru.itmo.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CatDto {
    private int id;
    private String name;
    private String breed;
    private String color;
    private LocalDate birthDate;
    private int ownerId;

    public CatDto(int id, String name, String breed, String color, LocalDate birthDate, int ownerId) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
    }
}