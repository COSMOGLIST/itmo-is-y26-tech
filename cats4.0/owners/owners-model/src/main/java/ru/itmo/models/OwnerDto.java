package ru.itmo.models;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OwnerDto {
    private int id;
    private String name;
    private LocalDate birthDate;
}