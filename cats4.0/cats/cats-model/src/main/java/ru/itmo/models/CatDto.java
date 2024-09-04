package ru.itmo.models;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CatDto {
    private int id;
    private String name;
    private String breed;
    private String color;
    private LocalDate birthDate;
    private int ownerId;
}