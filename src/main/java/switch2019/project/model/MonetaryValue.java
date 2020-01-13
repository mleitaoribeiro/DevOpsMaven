package switch2019.project.model;

import java.util.Currency;

public class MonetaryValue {
    private double amount;
    private Currency currency;

    /**
     * Monetary value constructor
     *
     * @param amount   valor
     * @param currency tipo de moeda
     */
    public MonetaryValue(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Validate PositiveAmount
     *
     * @return true
     */
    public boolean validateIfAmountIsPositive() {
        return true;
    }
}
