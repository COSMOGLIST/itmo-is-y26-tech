package ru.itmo.banks;

import org.junit.jupiter.api.Test;
import ru.itmo.client.Client;
import ru.itmo.exceptions.BanksException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankTest {
    Bank bank = new Bank(
            "SBER",
            0.1,
            0.1,
            10000,
            new ArrayList<>());
    @Test
    void NotRegisteredTest() {
        Client client = new Client(0, "ee", "ww", "zz", "ss");
        BanksException thrown = assertThrows(
                BanksException.class,
                () -> bank.newDebitAccount(client, 0)
        );

        assertTrue(thrown.getMessage().contains("This client is not registered in this bank"));
    }
}