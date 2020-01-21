package switch2019.project.model;

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
     * @param amount
     * @param description
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */


    public Transaction(MonetaryValue amount, String description, Category category, Account accountFrom, Account accountTo, boolean type) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
        setDate();
    }

    /**
     * Transaction constructor with date inserted by user
     *
     * @param amount
     * @param description
     * @param date
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public Transaction(MonetaryValue amount, String description, LocalDateTime date, Category category, Account accountFrom, Account accountTo, boolean type) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

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
     * Verify is a given transaction is valid or not
     *
     * @return boolean
     */

    public boolean isAValidTransaction() {
        if (amount == null)
            throw new IllegalArgumentException("The monetary value can´t be null. Please try again.");
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
     */

    public void setDate() {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date = LocalDateTime.parse(dateNow.format(formatter), formatter);

        // for the future to return a date without the 'T' in the middle
        // System.out.println("STRING: " + date.format(formatter));
    }
}
