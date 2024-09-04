package ru.itmo.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void clientCreationTest() {
        Client client = new Client(0, "abba", "baba", "123", null);
        Assertions.assertEquals(client.getRegistration(), Registration.NOT_FULLY_REGISTERED);
        client.setNumber("123");
        Assertions.assertEquals(client.getRegistration(), Registration.FULLY_REGISTERED);
    }
}