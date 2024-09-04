package ru.itmo.account;

import ru.itmo.banks.Bank;
import ru.itmo.transactions.Transaction;
import ru.itmo.exceptions.BanksException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CreditAccount implements Account {
    @Getter
    private final Bank bank;
    private List<Transaction> transactions;
    @Getter
    private final int accountId;
    private double amountOfMoney;
    private final double commission;
    private double oneMonthPiggyBox;
    private Suspicious isSuspicious = Suspicious.NOT_SUSPICIOUS;

    public CreditAccount(Bank bank, int accountId, double startAmount, double newCommission) {
        this.bank = bank;
        this.accountId = accountId;
        amountOfMoney = startAmount;
        commission = newCommission;
        oneMonthPiggyBox = 0;
        transactions = new ArrayList<>();
    }

    @Override
    public void addMoney(double moneyAmount) {
        if (isSuspicious == Suspicious.SUSPICIOUS && bank.getMaximumTransactionForSuspiciousAccounts() < moneyAmount) {
            throw new BanksException("This account is suspicious! Maximum transaction is " + bank.getMaximumTransactionForSuspiciousAccounts() + '.');
        } else {
            amountOfMoney += moneyAmount;
        }
    }

    @Override
    public double getBalance() {
        return amountOfMoney;
    }

    @Override
    public void takeMoney(double moneyAmount) {
        if (isSuspicious == Suspicious.SUSPICIOUS && bank.getMaximumTransactionForSuspiciousAccounts() < moneyAmount) {
            throw new BanksException("This account is suspicious! Maximum transaction is " + bank.getMaximumTransactionForSuspiciousAccounts() + '.');
        } else {
            amountOfMoney -= moneyAmount;
        }
    }

    @Override
    public void rememberTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public void undoLastTransaction() {
        transactions.getLast().undo();
    }

    @Override
    public void endOfDayNotification() {
        if (amountOfMoney < 0) {
            oneMonthPiggyBox += commission;
        }
    }

    @Override
    public void endOfMonthNotification() {
        endOfDayNotification();
        amountOfMoney -= oneMonthPiggyBox;
        oneMonthPiggyBox = 0;
    }

    @Override
    public void setSuspicious(Suspicious suspicious) {
        isSuspicious = suspicious;
    }
}
