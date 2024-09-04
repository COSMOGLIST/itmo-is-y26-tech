package ru.itmo.banks;

import ru.itmo.account.*;
import ru.itmo.client.Registration;
import ru.itmo.models.IdCreator;
import ru.itmo.client.Client;
import ru.itmo.exceptions.BanksException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    @Getter
    private final String name;
    private List<Client> clients;
    private List<Client> subscribers;
    private List<Account> accounts;
    @Getter
    private double debitPercent;
    @Getter
    private double creditCommission;
    @Getter
    private double maximumTransactionForSuspiciousAccounts;
    private List<DepositCondition> depositConditions;
    private IdCreator AccountIdCreator;

    public Bank(String name, double DebitPercent, double creditCommission, double maximumTransactionForSuspiciousAccounts, List<DepositCondition> depositConditions) {
        this.debitPercent = DebitPercent;
        this.creditCommission = creditCommission;
        this.maximumTransactionForSuspiciousAccounts = maximumTransactionForSuspiciousAccounts;
        clients = new ArrayList<>();
        accounts = new ArrayList<>();
        subscribers = new ArrayList<>();
        this.depositConditions = depositConditions;
        this.name = name;
        this.AccountIdCreator = new IdCreator();
    }

    /**
     * @param client new client of this bank
     */
    public void addNewClient(Client client) {
        clients.add(client);
    }

    /**
     * @param client owner of this account
     * @param amount the initial amount of money on the account
     */
    public void newDebitAccount(Client client, double amount) {
        if (clients.contains(client)) {
            Account newAccount = new DebitAccount(this, AccountIdCreator.getNewId(), amount, debitPercent);
            if (client.getRegistration() == Registration.NOT_FULLY_REGISTERED) {
                newAccount.setSuspicious(Suspicious.SUSPICIOUS);
            }
            accounts.add(newAccount);
            client.addNewAccount(newAccount);
        } else {
            throw new BanksException("This client is not registered in this bank");
        }

    }

    /**
     * @param client owner of this account
     * @param amount the initial amount of money on the account
     * @param days the number of days for which the account was created
     */
    public void newDepositAccount(Client client, double amount, int days) {
        if (clients.contains(client)) {
            Account newAccount = new DepositAccount(this, AccountIdCreator.getNewId(), amount, getProperDepositPercent(amount), days);
            if (client.getRegistration() == Registration.NOT_FULLY_REGISTERED) {
                newAccount.setSuspicious(Suspicious.SUSPICIOUS);
            }
            accounts.add(newAccount);
            client.addNewAccount(newAccount);
        } else {
            throw new BanksException("This client is not registered in this bank");
        }
    }

    /**
     * @param client owner of this account
     * @param amount the initial amount of money on the account
     */
    public void newCreditAccount(Client client, double amount) {
        if (clients.contains(client)) {
            Account newAccount = new CreditAccount(this, AccountIdCreator.getNewId(), amount, creditCommission);
            if (client.getRegistration() == Registration.NOT_FULLY_REGISTERED) {
                newAccount.setSuspicious(Suspicious.SUSPICIOUS);
            }
            accounts.add(newAccount);
            client.addNewAccount(newAccount);
        } else {
            throw new BanksException("This client is not registered in this bank");
        }
    }

    public Account getAccount(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        throw new BanksException("No such account in this bank");
    }

    /**
     * This method notifies the bank that a day has passed
     */
    public void endOfDayNotification() {
        for (Account currentAccount : accounts) {
            currentAccount.endOfDayNotification();
        }
    }

    /**
     * This method notifies the bank that a month has passed
     */
    public void endOfMonthNotification() {
        for (Account currentAccount : accounts) {
            currentAccount.endOfMonthNotification();
        }
    }

    /**
     * Adds new money interval for deposit accounts
     * @param bottomLimit bottom limit of this interval
     * @param upperLimit upper limit of this interval
     * @param percent the percentage under which the account is placed
     */
    public void addNewDepositCondition(double bottomLimit, double upperLimit, double percent) {
        depositConditions.add(new DepositCondition(bottomLimit, upperLimit, percent));
        for (Client client : subscribers) {
            client.notify("New deposit condition");
        }
    }

    /**
     * @param debitPercent new debit percent
     */
    public void setDebitPercent(double debitPercent) {
        this.debitPercent = debitPercent;
        for (Client client : subscribers) {
            client.notify("New debit percent");
        }
    }

    /**
     * @param creditCommission new commission for credit account
     */
    public void setCreditCommission(double creditCommission) {
        this.creditCommission = creditCommission;
        for (Client client : subscribers) {
            client.notify("New credit commission");
        }
    }

    /**
     * @param maximumTransactionForSuspiciousAccounts new maximum transaction limit for suspicious accounts
     */
    public void setMaximumTransactionForSuspiciousAccounts(double maximumTransactionForSuspiciousAccounts) {
        this.maximumTransactionForSuspiciousAccounts = maximumTransactionForSuspiciousAccounts;
        for (Client client : subscribers) {
            client.notify("New maximum transaction for suspicious accounts");
        }
    }

    /**
     * @param amount the amount of money for which we want to know the terms of the deposit account
     * @return a percentage for a given amount of money
     */
    private double getProperDepositPercent(double amount) {
        for (DepositCondition condition : depositConditions) {
            if (condition.getBottomLimit() <= amount && condition.getUpperLimit() >= amount) {
                return condition.getPercent();
            }
        }
        throw new BanksException("No deposit account available for such percent");
    }

    /**
     * subscribes client for notifications about changes in this bank
     * @param client client to subscribe
     */
    public void subscribe(Client client) {
        if (clients.contains(client)) {
            if (!subscribers.contains(client)) {
                subscribers.add(client);
            } else {
                throw new BanksException("Already subscribed");
            }
        } else {
            throw new BanksException("You should register in this bank to subscribe");
        }
    }

    /**
     * unsubscribes client from notifications about changes in this bank
     * @param client client to unsubscribe
     */
    public void unsubscribe(Client client) {
        if (subscribers.contains(client)) {
            subscribers.remove(client);
        } else {
            throw new BanksException("No such client in subscribers");
        }
    }
}
