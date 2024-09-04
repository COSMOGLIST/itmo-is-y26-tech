package ru.itmo.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.banks.Bank;

import java.util.ArrayList;

class DebitAccountTest {
    Bank bank = new Bank(
            "SBER",
            0.1,
            0.1,
            10000,
            new ArrayList<>());
    DebitAccount debitAccount = new DebitAccount(bank, 0, 10000, 0.1);

    @Test
    void addMoney() {
        debitAccount.addMoney(10000);
        double actual = debitAccount.getBalance();
        double expected = 20000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void takeMoney() {
        debitAccount.takeMoney(10000);
        double actual = debitAccount.getBalance();
        double expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void endOfMonthNotification() {
        debitAccount.endOfMonthNotification();
        double actual = debitAccount.getBalance();
        double expected = 11000;
        Assertions.assertEquals(expected, actual);
    }
}