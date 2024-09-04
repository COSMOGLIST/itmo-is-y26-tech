package ru.itmo.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.banks.Bank;
import ru.itmo.exceptions.BanksException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditAccountTest {
    Bank bank = new Bank(
            "SBER",
            0.1,
            0.1,
            10000,
            new ArrayList<>());
    CreditAccount creditAccount = new CreditAccount(bank, 0, 10000, 1000);

    @Test
    void endOfMonthNotification() {
        creditAccount.takeMoney(11000);
        creditAccount.endOfMonthNotification();
        double actual = creditAccount.getBalance();
        double expected = -2000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void suspiciousAccountTest() {
        creditAccount.setSuspicious(Suspicious.SUSPICIOUS);
        BanksException thrown = assertThrows(
                BanksException.class,
                () -> creditAccount.takeMoney(11000)
        );
        assertTrue(thrown.getMessage().contains("This account is suspicious! Maximum transaction is 10000."));
    }
}