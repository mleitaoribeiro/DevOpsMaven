package switch2019.project.model.ledger;

import switch2019.project.model.MonetaryValue;
import switch2019.project.model.account.Account;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;

import switch2019.project.model.category.Category;
import switch2019.project.model.valueObject.MonetaryValue;

import java.time.LocalDateTime;
import java.util.TimerTask;

public class TransactionTask extends TimerTask {

    // Private Task attributes
    private Person person;
    private Group group;
    private MonetaryValue amount;
    private String description;
    private LocalDateTime date;
    private Category category;
    private Account accountFrom;
    private Account accountTo;
    private boolean type;

    /**
     * TransactionTask Constructor
     */
    public TransactionTask(Person person, MonetaryValue amount, String description, LocalDateTime date,
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

    public TransactionTask(Group group, MonetaryValue amount, String description, LocalDateTime date,
                           Category category, Account accountFrom, Account accountTo, boolean type) {
        this.group = group;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

    public void run() {
        if(person != null)
            person.createTransaction(amount, description, date, category, accountFrom, accountTo, type);
        else group.createGroupTransaction(amount, description, date, category, accountFrom, accountTo, type);
    }
}
