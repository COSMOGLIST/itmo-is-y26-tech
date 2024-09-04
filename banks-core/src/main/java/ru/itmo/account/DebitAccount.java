package ru.itmo.account;

import ru.itmo.banks.Bank;
import ru.itmo.exceptions.BanksException;
import ru.itmo.transactions.Transaction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DebitAccount implements Account {
    @Getter
    private final Bank bank;
    private List<Transaction> transactions;
    @Getter
    private final int accountId;
    private double amountOfMoney;
    private double oneMonthPiggyBox;
    private final double percent;
    private Suspicious isSuspicious = Suspicious.NOT_SUSPICIOUS;

    public DebitAccount(Bank bank, int accountId, double startAmount, double newPercent) {
        this.bank = bank;
        this.accountId = accountId;
        amountOfMoney = startAmount;
        percent = newPercent;
        oneMonthPiggyBox = 0;
        transactions = new ArrayList<Transaction>();
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
            if (moneyAmount > amountOfMoney) {
                throw new BanksException("Not enough money on the account");
            }
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
        oneMonthPiggyBox += amountOfMoney * percent;
    }

    @Override
    public void endOfMonthNotification() {
        endOfDayNotification();
        amountOfMoney += oneMonthPiggyBox;
        oneMonthPiggyBox = 0;
    }

    @Override
    public void setSuspicious(Suspicious suspicious) {
        isSuspicious = suspicious;
    }
}
