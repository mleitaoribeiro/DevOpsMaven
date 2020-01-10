package switch2019.project.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    // Private Transaction variables
    private MonetaryValue amount;
    private String description;
    private LocalDateTime date;
    private Category category;
    private Account accountFrom;
    private Account accountTo;
    private Type type;

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

    public Transaction(MonetaryValue amount, String description, Category category, Account accountFrom, Account accountTo, Type type) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
        setDate();
    }


    /**
     * Verify is a given transaction is valid or not
     *
     * @return boolean
     */

    public boolean isAValidTransaction() {
        return amount != null && description != null && category != null && accountFrom != null && accountTo != null && type != null;
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
