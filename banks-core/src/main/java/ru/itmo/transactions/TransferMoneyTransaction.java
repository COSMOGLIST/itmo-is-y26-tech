package ru.itmo.transactions;

import ru.itmo.account.Account;
import ru.itmo.exceptions.BanksException;

public class TransferMoneyTransaction implements Transaction {
    private final Account accountDonor;
    private final Account accountAcceptor;
    private final double amountOfMoney;
    private TransactionStatus status;

    public TransferMoneyTransaction(Account accountDonor, Account accountAcceptor, double amountOfMoney) {
        this.accountDonor = accountDonor;
        this.accountAcceptor = accountAcceptor;
        this.amountOfMoney = amountOfMoney;
        status = TransactionStatus.NOT_COMPLETED;
    }

    public void execute() {
        accountAcceptor.rememberTransaction(this);
        accountAcceptor.rememberTransaction(this);
        accountDonor.takeMoney(amountOfMoney);
        accountAcceptor.addMoney(amountOfMoney);
        status = TransactionStatus.COMPLETED;
    }

    public void undo() {
        if (status == TransactionStatus.COMPLETED) {
            accountAcceptor.takeMoney(amountOfMoney);
            accountDonor.addMoney(amountOfMoney);
            status = TransactionStatus.UNDONE;
        } else {
            throw new BanksException("Transaction can not be undone.");
        }
    }
}
