package ru.itmo.exceptions;

public class CatException extends RuntimeException {
    public CatException(String errorText) {
        super(errorText);
    }
}
