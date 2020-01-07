package switch2019.project.model;

import java.util.Currency;

public class MonetaryValue {
    double amount;
    Currency currency;

    public MonetaryValue(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
