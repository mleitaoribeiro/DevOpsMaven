package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.shared.*;

import java.util.TimerTask;

public class TransactionTask extends TimerTask {

    // Private Task attributes
    private final Ledger ledger;
    private final MonetaryValue amount;
    private final Description description;
    private final DateAndTime date;
    private final CategoryID category;
    private final AccountID accountFrom;
    private final AccountID accountTo;
    private final Type type;

    public TransactionTask(Ledger ledger, MonetaryValue amount, Description description, DateAndTime date,
                           CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {
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
