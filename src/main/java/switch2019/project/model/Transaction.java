package switch2019.project.model;

public class Transaction {

    // Private Transaction variables
    private MonetaryValue amount;
    private String description;
    Category category;
    Account accountFrom;
    Account accountTo;
    Type type;

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
    }
}
