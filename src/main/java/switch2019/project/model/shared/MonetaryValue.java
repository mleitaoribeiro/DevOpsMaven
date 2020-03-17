package switch2019.project.model.shared;

import java.util.Currency;

public class MonetaryValue {

    //Private MonetaryValue instance Variables
    private final double amount;
    private final Currency currency;

    /**
     * MonetaryValue constructor
     *
     * @param amount amount of money
     * @param currency currency type
     */

    public MonetaryValue(double amount, Currency currency) {
        if (currency!= null){
            this.amount = amount;
            this.currency = currency;
        }
        else throw new IllegalArgumentException("The currency can't be null.");
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
     * Override toString() method
     */
    @Override
    public String toString() {
        return amount + " " + currency;
    }


    /**
     * Method to get amount
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

}
