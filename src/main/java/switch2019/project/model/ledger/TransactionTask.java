package switch2019.project.model.ledger;

import switch2019.project.model.shared.MonetaryValue;
import switch2019.project.model.account.Account;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;

import switch2019.project.model.category.Category;

import java.time.LocalDateTime;
import java.util.TimerTask;

public class TransactionTask extends TimerTask {

    // Private Task attributes
    private final Ledger ledger;
    private final MonetaryValue amount;
    private final String description;
    private final LocalDateTime date;
    private final Category category;
    private final Account accountFrom;
    private final Account accountTo;
    private final Type type;

    /**
     * TransactionTask Constructor
     */
    public TransactionTask(Ledger ledger, MonetaryValue amount, String description, LocalDateTime date,
                           Category category, Account accountFrom, Account accountTo, Type type) {
        this.ledger = ledger;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

    public void run() {
        ledger.addTransactionToLedger(amount, description, date, category, accountFrom, accountTo, type);
    }
}
