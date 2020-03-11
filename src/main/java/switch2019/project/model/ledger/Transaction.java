package switch2019.project.model.ledger;

import switch2019.project.model.account.Account;
import switch2019.project.model.category.Category;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.MonetaryValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Transaction {

    // Private Transaction variables
    private final MonetaryValue amount;
    private final String description; //we need to change this
    private final LocalDateTime date;
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


    public Transaction(MonetaryValue amount, String description, LocalDateTime date, Category category, Account accountFrom, Account accountTo, Type type) {
            this.amount = setValidMonetaryValue(amount);
            this.description = setValidDescription(description);
            this.category = setValidCategory(category);
            this.accountFrom = setValidAccount(accountFrom);
            this.accountTo = setValidAccount(accountTo);
            this.type = type;
            this.date = setDate(date);
        }


    /**
     * Develop @override of equals for Transaction and @override of hashcode
     *
     * @param o
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(type,that.type) &&
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    /**
     * Develop @override of toString()
     */
    @Override
    public String toString() {
        return dateToString() + " | " + amount + " " + typeToString() + " | " +
                accountFrom.denominationToString() + " -> " + accountTo.denominationToString()
                + " | Description: \"" + description + "\" " + " | " + category.getNameOfCategory();
    }

    /**
     * Verify is a given transaction is valid or not
     *
     * @return true if all parameters are valid
     */

    public MonetaryValue setValidMonetaryValue(MonetaryValue newAmount) {
        if (newAmount == null || !newAmount.validateIfAmountIsPositive())
        throw new IllegalArgumentException("The monetary value can´t be null or negative. Please try again.");
        else return newAmount;
    }

    public String setValidDescription(String newDescription) {
        if (newDescription == null)
            throw new IllegalArgumentException("The description can´t be null. Please try again.");
        else return newDescription;
    }

    public Category setValidCategory(Category newCategory) {
        if (newCategory == null)
            throw new IllegalArgumentException("The category can´t be null. Please try again.");
        else return newCategory;
    }

    public Account setValidAccount(Account newAccount) {
        if (newAccount == null)
            throw new IllegalArgumentException("The accounts can´t be null. Please try again.");
        else return newAccount;
    }


    /**
     * Set and format date
     *
     * @param date
     */
    public LocalDateTime setDate(LocalDateTime date) {
        if (date == null) {
            LocalDateTime dateNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            date = LocalDateTime.parse(dateNow.format(formatter), formatter);
            return date;
        } else return date;
    }

    /**
     * Get date
     */
    public LocalDateTime getDate() {
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
