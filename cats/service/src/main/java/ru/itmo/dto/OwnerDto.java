package ru.itmo.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class OwnerDto {
    private int id;
    private String name;
    private LocalDate birthDate;
    private List<Integer> catsId;

    public OwnerDto(int id, String name, LocalDate birthDate, List<Integer> catsId) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.catsId = catsId;
    }
}
