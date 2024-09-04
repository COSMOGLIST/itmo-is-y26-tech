package ru.itmo.banks;

import lombok.Data;

@Data
public class DepositCondition {
    private double bottomLimit;
    private double upperLimit;
    private double percent;

    public DepositCondition(double bottomLimit, double upperLimit, double percent) {
        this.bottomLimit = bottomLimit;
        this.upperLimit = upperLimit;
        this.percent = percent;
    }
}
