package ru.itmo.commands;

import ru.itmo.client.Client;
import ru.itmo.client.ClientBuilder;
import ru.itmo.models.Workspace;
import picocli.CommandLine;

import java.util.Objects;
import java.util.Scanner;

@CommandLine.Command(name = "Main menu", mixinStandardHelpOptions = true)
public class MainMenu implements Runnable {
    private final Workspace workspace;

    public MainMenu(Workspace workspace) {
        this.workspace = workspace;
    }

    @CommandLine.Command(name = "CentralBank", description = "Take control of Central Bank")
    void centralBankControl() {
        CommandLine commandLine = new CommandLine(new CentralBankController(workspace));
        System.out.println(
                """
                        Welcome to Central Bank control!
                        Print --help for commands or print --quit to return to Main menu""");
        while (true) {
            String line = (new Scanner(System.in)).nextLine();
            if (Objects.equals(line, "--quit")) {
                break;
            } else {
                commandLine.setExecutionExceptionHandler(new BankExceptionHandler()).execute(line.split(" "));
            }
        }
    }

    @CommandLine.Command(name = "Client", description = "Take control of client")
    void clientControl(
            @CommandLine.Option(names = {"id"}, required = true, description = "Client id") int clientId) {
        for (Client client : workspace.getClients()) {
            if (client.getId() == clientId) {
                CommandLine commandLine = new CommandLine(new ClientController(workspace, client));
                System.out.println(
                        """
                                Welcome to Client control!
                                Print --help for commands or print --quit to return to Main menu""");
                while (true) {
                    String line = (new Scanner(System.in)).nextLine();
                    if (Objects.equals(line, "--quit")) {
                        break;
                    } else {
                        commandLine.setExecutionExceptionHandler(new BankExceptionHandler()).execute(line.split(" "));
                    }
                }
            }
        }
    }

    @CommandLine.Command(name = "ClientList", description = "Watch all clients")
    void clientList() {
        for (Client client : workspace.getClients()) {
            System.out.println(
                    "Id: " +
                            client.getId() + ", Name: " +
                            client.getFirstName() + ' ' +
                            client.getLastName() + ", Passport number: " +
                            client.getNumber() + ", Address: " +
                            client.getAddress());
        }
    }

    @CommandLine.Command(name = "NewClient", description = "Create new client")
    public void newClient(
            @CommandLine.Option(names = {"fn", "firstname"}, required = true, description = "Client FirstName") String firstName,
            @CommandLine.Option(names = {"ln", "lastname"}, required = true, description = "Client LastName") String lastName,
            @CommandLine.Option(names = {"pn", "passportnumber"}, description = "Client Passport number") String passportNumber,
            @CommandLine.Option(names = {"a", "address"}, description = "Client name") String address) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder
                .setId(workspace.getClientIdCreator().getNewId())
                .setNewFirstName(firstName)
                .setNewLastName(lastName);
        if (passportNumber != null) {
            clientBuilder.setNewNumber(passportNumber);
        }
        if (address != null) {
            clientBuilder.setNewAddress(address);
        }
        workspace.getClients().add(clientBuilder.createClient());
        System.out.println("New client " + firstName + ' ' + lastName + " with id " + workspace.getClients().getLast().getId() + " registered");
    }

    public void run() {
    }
}