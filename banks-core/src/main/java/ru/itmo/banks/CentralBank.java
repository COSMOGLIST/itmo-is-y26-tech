package ru.itmo.banks;

import ru.itmo.models.DayCounter;
import ru.itmo.exceptions.BanksException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CentralBank {
    private List<Bank> banks = new ArrayList<Bank>();
    private DayCounter timeCounter;

    public CentralBank(DayCounter timeCounter) {
        this.timeCounter = timeCounter;
    }

    /**
     * Creates new bank
     * @param name name of new bank
     * @param debitPercent debit percent for this bank
     * @param creditCommission credit commission in this bank
     * @param maximumTransactionForSuspiciousAccounts maximum transaction limit for suspicious accounts in this bank
     * @param depositConditions deposit conditions in this bank
     */
    public void createBank(String name, double debitPercent, double creditCommission, double maximumTransactionForSuspiciousAccounts, List<DepositCondition> depositConditions) {
        banks.add(new Bank(name, debitPercent, creditCommission, maximumTransactionForSuspiciousAccounts, depositConditions));
    }

    /**
     * @param name name of a bank we want to get
     * @return returns bank by name
     */
    public Bank getBank(String name) {
        for (Bank bank : banks) {
            if (Objects.equals(bank.getName(), name)) {
                return bank;
            }
        }
        throw new BanksException("No such bank");
    }

    /**
     * This method symbolizes the end of the day, notifying all banks about it
     */
    public void endOfDay() {
        timeCounter.endOfDay();
        int daysGone = timeCounter.getDays();
        if (daysGone % 30 == 0) {
            for (Bank bank : banks) {
                bank.endOfMonthNotification();
            }
        } else {
            for (Bank bank : banks) {
                bank.endOfDayNotification();
            }
        }
    }
}
