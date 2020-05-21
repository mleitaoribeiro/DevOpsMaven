package switch2019.project.domain.domainEntities.shared;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Currency;

public class MonetaryValue {

    //Private MonetaryValue instance Variables
    private final double amount;
    private Currency currency;

    public MonetaryValue(double amount, Currency currency) {
        if (currency!= null){
            this.amount = amount;
            this.currency = currency;
        } else throw new IllegalArgumentException("The currency can't be null.");
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    /**
     * Method to validate if amount is positive
     *
     * @return true if amount is positive or false if it's not
     */
    public boolean validateIfAmountIsPositive() {
        return this.amount > 0;
    }

    /**
     * Method to get amount
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Method to get currency
     *
     * @return currency
     */
    public Currency getCurrency() {
        return currency;
    }

}
