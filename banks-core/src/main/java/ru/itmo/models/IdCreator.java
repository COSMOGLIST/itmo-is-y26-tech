package ru.itmo.models;

public class IdCreator {
    private int lastId = 0;

    public int getNewId() {
        lastId += 1;
        return lastId - 1;
    }
}
