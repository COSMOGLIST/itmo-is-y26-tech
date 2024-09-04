package ru.itmo.client;

import ru.itmo.account.Account;
import ru.itmo.account.Suspicious;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Client {
    private final int id;
    private final String firstName;
    private final String lastName;
    private String address;
    private String number;

    private List<Account> accounts;
    private List<String> messages;
    private Registration registration;

    public Client(int id, String newFirstName, String newLastName, String newAddress, String newNumber) {
        this.id = id;
        firstName = newFirstName;
        lastName = newLastName;
        address = newAddress;
        number = newNumber;
        accounts = new ArrayList<>();
        messages = new ArrayList<>();
        if (address == null || number == null) {
            registration = Registration.NOT_FULLY_REGISTERED;
        } else {
            registration = Registration.FULLY_REGISTERED;
        }
    }

    public void addNewAccount(Account account) {
        accounts.add(account);
    }

    public void setAddress(String newAddress) {
        address = newAddress;
        if (address == null || number == null) {
            registration = Registration.NOT_FULLY_REGISTERED;
        } else {
            registration = Registration.FULLY_REGISTERED;
            for (Account account : accounts) {
                account.setSuspicious(Suspicious.NOT_SUSPICIOUS);
            }
        }
    }

    public void setNumber(String newNumber) {
        number = newNumber;
        if (address == null || number == null) {
            registration = Registration.NOT_FULLY_REGISTERED;
        } else {
            registration = Registration.FULLY_REGISTERED;
            for (Account account : accounts) {
                account.setSuspicious(Suspicious.NOT_SUSPICIOUS);
            }
        }
    }

    public void notify(String message) {
        messages.add(message);
    }

}
