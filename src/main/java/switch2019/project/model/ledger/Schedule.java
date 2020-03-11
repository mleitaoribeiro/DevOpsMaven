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
    private Periodicity periodicity;

    /**
     * Personal Schedule Constructor
     *
     * @param person
     * @param periodicity
     * @param amount
     * @param description
     * @param date
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */
    public Schedule(Person person, Periodicity periodicity, MonetaryValue amount, String description, LocalDateTime date,
                    Category category, Account accountFrom, Account accountTo, Type type) {
        this.periodicity = periodicity;
        Timer timer = new Timer();
        TransactionTask scheduledTransactionTask = new TransactionTask(person, amount,
                                                        description, date, category, accountFrom, accountTo, type);
        timer.schedule(scheduledTransactionTask, 0, this.periodicity.getPeriodicityInMilliseconds());
    }

    /**
     * Group Schedule Constructor
     *
     * @param group
     * @param periodicity
     * @param amount
     * @param description
     * @param date
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public Schedule(Group group, Periodicity periodicity, MonetaryValue amount, String description, LocalDateTime date,
                    Category category, Account accountFrom, Account accountTo, Type type) {
        this.periodicity = periodicity;
        Timer timer = new Timer();
        TransactionTask scheduledGroupTransactionTask = new TransactionTask(group, amount,
                                                        description, date, category, accountFrom, accountTo, type);
        timer.schedule(scheduledGroupTransactionTask, 0, this.periodicity.getPeriodicityInMilliseconds());
    }
}
