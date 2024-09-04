package ru.itmo.transactions;

import ru.itmo.account.Account;
import ru.itmo.exceptions.BanksException;

public class TakeMoneyTransaction implements Transaction {
    private final Account account;
    private final double amountOfMoney;
    private TransactionStatus status;

    public TakeMoneyTransaction(Account account, double amountOfMoney) {
        this.account = account;
        this.amountOfMoney = amountOfMoney;
        status = TransactionStatus.NOT_COMPLETED;
    }

    public void execute() {
        account.addMoney(amountOfMoney);
        account.rememberTransaction(this);
        status = TransactionStatus.COMPLETED;
    }

    public void undo() {
        if (status == TransactionStatus.COMPLETED) {
            account.takeMoney(amountOfMoney);
            status = TransactionStatus.UNDONE;
        } else {
            throw new BanksException("Transaction can not be undone.");
        }

    }
}
