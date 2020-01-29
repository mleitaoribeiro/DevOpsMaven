package switch2019.project.model;

import java.time.LocalDateTime;
import java.util.TimerTask;

public class PersonalTransactionTask extends TimerTask {

    // Private Task attributes
    private Person person;
    private MonetaryValue amount;
    private String description;
    private LocalDateTime date;
    private Category category;
    private Account accountFrom;
    private Account accountTo;
    private boolean type;

    /**
     * PersonalTransactionTask Constructor
     */
    public PersonalTransactionTask(Person person, MonetaryValue amount, String description, LocalDateTime date,
                                   Category category, Account accountFrom, Account accountTo, boolean type) {
        this.person = person;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

    public void run() {
        person.createTransaction(amount, description, date, category, accountFrom, accountTo, type);
    }
}
