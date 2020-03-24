package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;

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
