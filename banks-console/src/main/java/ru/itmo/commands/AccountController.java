package ru.itmo.commands;

import ru.itmo.account.Account;
import ru.itmo.models.Workspace;
import picocli.CommandLine;
import ru.itmo.transactions.AddMoneyTransaction;
import ru.itmo.transactions.Transaction;
import ru.itmo.transactions.TakeMoneyTransaction;
import ru.itmo.transactions.TransferMoneyTransaction;

@CommandLine.Command(name = "Account controller", mixinStandardHelpOptions = true)
public class AccountController implements Runnable {
    private final Account account;
    private final Workspace workspace;

    public AccountController(Workspace workspace, Account account) {
        this.account = account;
        this.workspace = workspace;
    }

    @CommandLine.Command(name = "Balance", description = "shows balance")
    public void balance() {
        System.out.println("Your balance is: " + account.getBalance());
    }

    @CommandLine.Command(name = "AddMoney", description = "adds money to account")
    public void addMoney(double moneyAmount) {
        (new AddMoneyTransaction(account, moneyAmount)).execute();
    }

    @CommandLine.Command(name = "TakeMoney", description = "takes money from account")
    public void takeMoney(double moneyAmount) {
        (new TakeMoneyTransaction(account, moneyAmount)).execute();
    }

    @CommandLine.Command(name = "TransferSB", description = "transfers money from account to other account in the same bank")
    public void transferMoneySameBank(
            @CommandLine.Option(names = "money", required = true, description = "money amount to transfer") double moneyAmount,
            @CommandLine.Option(names = "id", required = true, description = "account id to transfer to") int accountId
    ) {
        (new TransferMoneyTransaction(account, account.getBank().getAccount(accountId), moneyAmount)).execute();
    }

    @CommandLine.Command(name = "TransferOB", description = "transfers money from account to other account in other bank")
    public void transferMoneyOtherBank(
            @CommandLine.Option(names = "money", required = true, description = "money amount to transfer") double moneyAmount,
            @CommandLine.Option(names = "id", required = true, description = "account id to transfer to") int accountId,
            @CommandLine.Option(names = "bankName", required = true, description = "bank of account to transfer to") String bankName
    ) {
        (new TransferMoneyTransaction(account, workspace.getCentralBank().getBank(bankName).getAccount(accountId), moneyAmount)).execute();
    }

    public void run() {

    }
}
