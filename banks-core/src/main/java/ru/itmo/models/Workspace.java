package ru.itmo.models;

import ru.itmo.banks.CentralBank;
import ru.itmo.client.Client;
import lombok.Data;

import java.util.List;

@Data
public class Workspace {
    private CentralBank centralBank;
    private List<Client> clients;
    private IdCreator clientIdCreator;

    public Workspace(CentralBank centralBank, List<Client> clients, IdCreator clientIdCreator) {
        this.centralBank = centralBank;
        this.clients = clients;
        this.clientIdCreator = clientIdCreator;
    }
}
