package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.shared.*;

import java.util.Timer;

public class Schedule {

    // Private Task attributes
    private final Periodicity periodicity;

    public Schedule(Ledger ledger, Periodicity periodicity, MonetaryValue amount, Description description, DateAndTime date,
                    CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {
        this.periodicity = periodicity;
        Timer timer = new Timer();
        TransactionTask scheduledTransactionTask = new TransactionTask(ledger, amount,
                description, date, category, accountFrom, accountTo, type);
        timer.schedule(scheduledTransactionTask, 0, this.periodicity.getPeriodicityInMilliseconds());
    }
}
