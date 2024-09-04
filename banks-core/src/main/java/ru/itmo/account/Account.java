package ru.itmo.account;

import ru.itmo.banks.Bank;
import ru.itmo.transactions.Transaction;
public interface Account {

    /**
     * @param moneyAmount Amount of money to add to this account
     */
    void addMoney(double moneyAmount);

    /**
     * @return returns current balance
     */
    double getBalance();

    /**
     * @return returns the bank that owns this account
     */
    Bank getBank();

    /**
     * @param moneyAmount Amount of money to take from this account
     */
    void takeMoney(double moneyAmount);

    /**
     * This method remembers the transaction that was performed on this account
     * @param transaction transaction to remember
     */
    void rememberTransaction(Transaction transaction);
    void undoLastTransaction();

    /**
     * This method notifies the account that a day has passed
     */
    void endOfDayNotification();

    /**
     * This method notifies the account that a month has passed
     */
    void endOfMonthNotification();

    /**
     * @return returns id of this account
     */
    int getAccountId();

    /**
     * This method sets information if this account suspicious or not
     * @param suspicious information if this account suspicious or not
     */
    void setSuspicious(Suspicious suspicious);
}