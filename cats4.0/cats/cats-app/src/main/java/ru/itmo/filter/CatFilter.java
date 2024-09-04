package ru.itmo.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatFilter {
    private CatFields catFields;
    private String value;
}
