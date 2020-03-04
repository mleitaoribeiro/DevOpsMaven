package switch2019.project.model.ledger;

import switch2019.project.model.account.Account;
import switch2019.project.model.category.Category;
import switch2019.project.model.valueObject.MonetaryValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Transaction {

    // Private Transaction variables
    private MonetaryValue amount;
    private String description;
    private LocalDateTime date;
    private Category category;
    private Account accountFrom;
    private Account accountTo;
    private boolean type;

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


    public Transaction(MonetaryValue amount, String description, LocalDateTime date, Category category, Account accountFrom, Account accountTo, boolean type) {
        if (isAValidTransaction(amount, description, category, accountFrom, accountTo, type)) {
            this.amount = amount;
            this.description = description;
            this.category = category;
            this.accountFrom = accountFrom;
            this.accountTo = accountTo;
            this.type = type;
            setDate(date);
        }
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
        return type == that.type &&
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
        if (type) return "CREDIT";
        else return "DEBIT";
    }

    /**
     * Methood toString() of date
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
                + " | Description: \"" + description + "\" " + " | " + category;
    }

    /**
     * Verify is a given transaction is valid or not
     *
     * @return true if all parameters are valid
     */
    public boolean isAValidTransaction(MonetaryValue amount, String description, Category category, Account accountFrom, Account accountTo, boolean type) {
        if (amount == null || !amount.validateIfAmountIsPositive())
            throw new IllegalArgumentException("The monetary value can´t be null or negative. Please try again.");
        else if (description == null)
            throw new IllegalArgumentException("The description can´t be null. Please try again.");
        else if (category == null)
            throw new IllegalArgumentException("The category can´t be null. Please try again.");
        else if (accountFrom == null || accountTo == null)
            throw new IllegalArgumentException("The accounts can´t be null. Please try again.");
        else return true;
    }

    /**
     * Set and format date
     *
     * @param date
     */
    public void setDate(LocalDateTime date) {
        if (date == null) {
            LocalDateTime dateNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.date = LocalDateTime.parse(dateNow.format(formatter), formatter);
        } else this.date = date;
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
        return type;
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
