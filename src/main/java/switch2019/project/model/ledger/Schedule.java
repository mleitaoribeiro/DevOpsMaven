package switch2019.project.model.ledger;

import switch2019.project.model.account.Account;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;

import switch2019.project.model.category.Category;
import switch2019.project.model.shared.MonetaryValue;

import java.time.LocalDateTime;
import java.util.Timer;

public class Schedule {

    // Private Task attributes
    private final Periodicity periodicity;

    /**
     *  Schedule Constructor
     *
     * @param ledger
     * @param periodicity
     * @param amount
     * @param description
     * @param date
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */
    public Schedule(Ledger ledger, Periodicity periodicity, MonetaryValue amount, String description, LocalDateTime date,
                    Category category, Account accountFrom, Account accountTo, Type type) {
        this.periodicity = periodicity;
        Timer timer = new Timer();
        TransactionTask scheduledTransactionTask = new TransactionTask(ledger, amount,
                                                        description, date, category, accountFrom, accountTo, type);
        timer.schedule(scheduledTransactionTask, 0, this.periodicity.getPeriodicityInMilliseconds());
    }
}
