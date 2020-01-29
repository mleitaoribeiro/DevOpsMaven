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
     * Validate if amount is positive
     *
     * @return true if amount is positive or false if it's not
     */
    public boolean validateIfAmountIsPositive() {
        return this.amount > 0;
    }

    /**
     * Develop @override of toString()
     */
    @Override
    public String toString() {
        return amount + " " + currency;
    }

    /**
     * Get Amount
     */
    public double getAmount() {
        return amount;
    }
}
