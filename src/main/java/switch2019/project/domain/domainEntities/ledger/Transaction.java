package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.shared.*;

import java.util.Currency;
import java.util.Objects;

public class Transaction {

    // Private Transaction variables
    private final MonetaryValue amount;
    private final Description description;
    private DateAndTime date;
    private final CategoryID category;
    private final AccountID accountFrom;
    private final AccountID accountTo;
    private final Type type;
    private final Long id;

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


    public Transaction(MonetaryValue amount, Description description, DateAndTime date, CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {
        this.amount = setValidMonetaryValue(amount);
        this.description = description;
        this.category = setValidCategory(category);
        this.accountFrom = setValidAccount(accountFrom);
        this.accountTo = setValidAccount(accountTo);
        this.type = type;
        setDate(date);
        id = null;
    }

    public Transaction(MonetaryValue amount, Description description, DateAndTime date, CategoryID category,
                       AccountID accountFrom, AccountID accountTo, Type type, Long id) {
        this.amount = setValidMonetaryValue(amount);
        this.description = description;
        this.category = setValidCategory(category);
        this.accountFrom = setValidAccount(accountFrom);
        this.accountTo = setValidAccount(accountTo);
        this.type = type;
        setDate(date);
        this.id = id;
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
                accountFrom.getDenominationToString() + " -> " + accountTo.getDenominationToString()
                + " | Description: \"" + description + "\" " + " | " + category.getDenominationString();
    }

    public Long getId() {
        return id;
    }

    /**
     * toString() of type
     */
    public String typeToString() {
        return type.toString();
    }

    /**
     * Method toString() of date
     */
    public String dateToString() {
        return date.yearMonthDayHourMinuteToString();
    }

    /**
     * toString() of category
     */
    public String categoryToString() {
        return category.toString();
    }

    /**
     * toString() of amount
     */
    public String amountToString() {
        return amount.toString();
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

    public CategoryID setValidCategory(CategoryID newCategory) {
        if (newCategory == null)
            throw new IllegalArgumentException("The category cannot be null.");
        else return newCategory;
    }

    public AccountID setValidAccount(AccountID newAccount) {
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
     * Get Description
     */
    public Description getDescription() {
        return description;
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
     * Get currency
     * @return currency
     */
    public Currency getCurrency() {
        return amount.getCurrency();
    }

    /**
     * Get AccountFrom
     */
    public AccountID getAccountFrom() {
        return accountFrom.getCopyOfAccountID();
    }

    /**
     * Get AccountTo
     */
    public AccountID getAccountTo() {
        return accountTo.getCopyOfAccountID();
    }

    /**
     * Get copy of CategoryID
     *
     * @return CategoryID
     */

    public CategoryID getCategoryID() { return category.getCopyOfCategory(); }
}
