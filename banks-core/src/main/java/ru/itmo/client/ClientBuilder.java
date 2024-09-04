package ru.itmo.client;

import ru.itmo.exceptions.BanksException;

public class ClientBuilder {
    private int id;
    private String newFirstName;
    private String newLastName;
    private String newAddress;
    private String newNumber;

    public ClientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ClientBuilder setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
        return this;
    }

    public ClientBuilder setNewLastName(String newLastName) {
        this.newLastName = newLastName;
        return this;
    }

    public ClientBuilder setNewAddress(String newAddress) {
        this.newAddress = newAddress;
        return this;
    }

    public ClientBuilder setNewNumber(String newNumber) {
        this.newNumber = newNumber;
        return this;
    }

    public Client createClient() {
        if (id == -1 || newFirstName == null || newLastName == null) {
            throw new BanksException("Full name and id should be inserted");
        }
        return new Client(id, newFirstName, newLastName, newAddress, newNumber);
    }
}