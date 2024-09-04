package ru.itmo.commands;

import ru.itmo.banks.Bank;
import picocli.CommandLine;

@CommandLine.Command(name = "Bank controller", mixinStandardHelpOptions = true)
public class BankController implements Runnable {
    private final Bank bank;

    public BankController(Bank bank) {
        this.bank = bank;
    }

    @CommandLine.Command(name = "DebitPercent", description = "update debit percent")
    public void updateDebitPercent(double percent) {
        bank.setDebitPercent(percent);
    }

    @CommandLine.Command(name = "Commission", description = "update credit commission")
    public void updateCreditCommission(double percent) {
        bank.setCreditCommission(percent);
    }

    @CommandLine.Command(name = "MaxTransaction", description = "update maximum transaction for suspicious accounts")
    public void updateMaximumTransactionForSuspiciousAccounts(double percent) {
        bank.setMaximumTransactionForSuspiciousAccounts(percent);
    }

    @CommandLine.Command(name = "NewDeposit", description = "new deposit condition")
    public void newDepositCondition(
            @CommandLine.Option(names = "min", required = true, description = "lower bound") double bottomLimit,
            @CommandLine.Option(names = "max", required = true, description = "upper bound") double upperLimit,
            @CommandLine.Option(names = "percent", required = true, description = "percent for this interval") double percent
    ) {
        bank.addNewDepositCondition(bottomLimit, upperLimit, percent);
    }

    @CommandLine.Command(name = "UndoLastTransaction", description = "undo last transaction")
    public void undoLastTransaction(int accountId) {
        bank.getAccount(accountId).undoLastTransaction();
    }

    public void run() {

    }
}
