package switch2019.project.domain.domainEntities.shared;

import java.util.Currency;
import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MonetaryValue that = (MonetaryValue) obj;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
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
