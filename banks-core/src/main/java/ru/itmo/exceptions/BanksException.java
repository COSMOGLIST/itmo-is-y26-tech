package ru.itmo.exceptions;

public class BanksException extends RuntimeException {
    public BanksException(String errorText) {
        super(errorText);
    }
}
