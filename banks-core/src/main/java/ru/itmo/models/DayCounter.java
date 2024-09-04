package ru.itmo.models;

import lombok.Getter;

@Getter
public class DayCounter {
    private int days = 0;

    public void endOfDay() {
        days += 1;
    }
}
