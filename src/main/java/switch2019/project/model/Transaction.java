package switch2019.project.model;

import java.time.LocalDateTime;

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
        date = LocalDateTime.now();
    }


    public boolean isAValidTransaction() {
        return amount != null && description != null && category != null && accountFrom != null && accountTo != null && type != null;
    }
}
