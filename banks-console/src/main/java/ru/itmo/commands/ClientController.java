package ru.itmo.commands;

import ru.itmo.account.Account;
import ru.itmo.client.Client;
import ru.itmo.models.Workspace;
import picocli.CommandLine;

import java.util.Objects;
import java.util.Scanner;


@CommandLine.Command(name = "Client controller", mixinStandardHelpOptions = true)
public class ClientController implements Runnable {

    private final Workspace workspace;
    private final Client client;

    public ClientController(Workspace workspace, Client client) {
        this.workspace = workspace;
        this.client = client;
    }

    @CommandLine.Command(name = "NewDebitAccount", description = "makes new account")
    public void newDebitAccount(
            @CommandLine.Option(names = "bankName", required = true, description = "Bank name") String bankName,
            @CommandLine.Option(names = "money", required = true, description = "money amount") double moneyAmount
    ) {
        workspace.getCentralBank().getBank(bankName).newDebitAccount(client, moneyAmount);
    }

    @CommandLine.Command(name = "NewCreditAccount", description = "makes new account")
    public void newCreditAccount(
            @CommandLine.Option(names = "bankName", required = true, description = "Bank name") String bankName,
            @CommandLine.Option(names = "money", required = true, description = "money amount") double moneyAmount
    ) {
        workspace.getCentralBank().getBank(bankName).newCreditAccount(client, moneyAmount);
    }

    @CommandLine.Command(name = "NewDepositAccount", description = "makes new account")
    public void newDepositAccount(
            @CommandLine.Option(names = "bankName", required = true, description = "Bank name") String bankName,
            @CommandLine.Option(names = "money", required = true, description = "money amount") double moneyAmount,
            @CommandLine.Option(names = "days", required = true, description = "days for this account") int days
    ) {
        workspace.getCentralBank().getBank(bankName).newDepositAccount(client, moneyAmount, days);
    }

    @CommandLine.Command(name = "register", description = "registers to new bank")
    public void registerToNewBank(
            @CommandLine.Option(names = "bankName", required = true, description = "Bank name") String bankName
    ) {
        workspace.getCentralBank().getBank(bankName).addNewClient(client);
    }

    @CommandLine.Command(name = "accountList", description = "shows all client accounts")
    public void accountsList() {
        for (Account account : client.getAccounts()) {
            System.out.println(account.getClass().getName() + " in bank: " + account.getBank().getName() + " with id: " + account.getAccountId());
        }
    }

    @CommandLine.Command(name = "passportNumber", description = "updates passport number")
    public void setPassportNumber(String passportNumber) {
        client.setNumber(passportNumber);
    }

    @CommandLine.Command(name = "address", description = "updates address")
    public void setAddress(String address) {
        client.setAddress(address);
    }

    @CommandLine.Command(name = "ru/itmo/account", description = "enters account")
    public void connectToAccount(
            @CommandLine.Option(names = "bankName", required = true, description = "Bank name") String bankName,
            @CommandLine.Option(names = "id", required = true, description = "money amount") int id
    ) {
        for (Account account : client.getAccounts()) {
            if (Objects.equals(account.getBank().getName(), bankName) && account.getAccountId() == id) {
                CommandLine commandLine = new CommandLine(new AccountController(workspace, account));
                System.out.println(
                        """
                                Welcome to Account control!
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

    @CommandLine.Command(name = "subscribe", description = "subscribes to bank notifications")
    public void subscribe(String bankName) {
        workspace.getCentralBank().getBank(bankName).subscribe(client);
    }

    @CommandLine.Command(name = "unsubscribe", description = "unsubscribes to bank notifications")
    public void unsubscribe(String bankName) {
        workspace.getCentralBank().getBank(bankName).unsubscribe(client);
    }

    public void run() {
    }
}
