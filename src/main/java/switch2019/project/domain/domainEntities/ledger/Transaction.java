package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;

import java.util.Objects;

public class Transaction {

    // Private Transaction variables
    private final MonetaryValue amount;
    private final Description description;
    private DateAndTime date;
    private final Category category;
    private final Account accountFrom;
    private final Account accountTo;
    private final Type type;

    /**
     * Default Transaction constructor
     *
     * @param amount
     * @param description
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */


    public Transaction(MonetaryValue amount, Description description, DateAndTime date, Category category, Account accountFrom, Account accountTo, Type type) {
        this.amount = setValidMonetaryValue(amount);
        this.description = description;
        this.category = setValidCategory(category);
        this.accountFrom = setValidAccount(accountFrom);
        this.accountTo = setValidAccount(accountTo);
        this.type = type;
        setDate(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date) &&
                Objects.equals(category, that.category) &&
                Objects.equals(accountFrom, that.accountFrom) &&
                Objects.equals(accountTo, that.accountTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, date, category, accountFrom, accountTo, type);
    }

    @Override
    public String toString() {
        return dateToString() + " | " + amount + " " + typeToString() + " | " +
                accountFrom.denominationToString() + " -> " + accountTo.denominationToString()
                + " | Description: \"" + description + "\" " + " | " + category.getNameOfCategory();
    }

    /**
     * toString() of type
     */
    private String typeToString() {
        return type.toString();
    }

    /**
     * Method toString() of date
     */
    public String dateToString() {
        return date.yearMonthDayHourMinuteToString();
    }

    /**
     * Verify is a given transaction is valid or not
     *
     * @return true if all parameters are valid
     */

    public MonetaryValue setValidMonetaryValue(MonetaryValue newAmount) {
        if (newAmount == null )
            throw new IllegalArgumentException("The monetary value cannot be null.");
        if(!newAmount.validateIfAmountIsPositive())
            throw new IllegalArgumentException("The monetary value cannot be negative.");
        else return newAmount;
    }

    public Category setValidCategory(Category newCategory) {
        if (newCategory == null)
            throw new IllegalArgumentException("The category cannot be null.");
        else return newCategory;
    }

    public Account setValidAccount(Account newAccount) {
        if (newAccount == null)
            throw new IllegalArgumentException("The accounts cannot be null.");
        else return newAccount;
    }

    /**
     * Set and format date
     *
     * @param date
     */
    public void setDate(DateAndTime date) {
        if (date == null) {
            this.date = new DateAndTime();
        } else
            this.date = date;
    }

    /**
     * Get date
     */
    public DateAndTime getDate() {
        return this.date;
    }

    /**
     * Get Type
     */
    public boolean getType() {
        return type.getType();
    }

    /**
     * Get Amount
     */
    public double getAmount() {
        return amount.getAmount();
    }

    /**
     * Get AccountFrom
     */
    public Account getAccountFrom() {
        return accountFrom.getCopyOfAccount();
    }

    /**
     * Get AccountTo
     */
    public Account getAccountTo() {
        return accountTo.getCopyOfAccount();
    }


}
