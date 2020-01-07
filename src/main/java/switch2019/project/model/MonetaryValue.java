package switch2019.project.model;

import java.util.Currency;

public class MonetaryValue {
    private double amount;
    private Currency currency;

    public MonetaryValue(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }
}
