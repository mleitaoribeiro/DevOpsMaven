package switch2019.project.model.Legder;

import switch2019.project.model.*;

import switch2019.project.model.person.Person;

import java.time.LocalDateTime;
import java.util.Timer;

public class Schedule {

    // Private Task attributes
    private int periodicity;

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
    public Schedule(Person person, String periodicity, MonetaryValue amount, String description, LocalDateTime date,
                    Category category, Account accountFrom, Account accountTo, boolean type) {
        this.periodicity = convertKeyWordIntoMilliseconds(periodicity);
        Timer timer = new Timer();
        TransactionTask scheduledTransactionTask = new TransactionTask(person, amount,
                                                        description, date, category, accountFrom, accountTo, type);
        timer.schedule(scheduledTransactionTask, 0, this.periodicity);
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

    public Schedule(Group group, String periodicity, MonetaryValue amount, String description, LocalDateTime date,
                    Category category, Account accountFrom, Account accountTo, boolean type) {
        this.periodicity = convertKeyWordIntoMilliseconds(periodicity);
        Timer timer = new Timer();
        TransactionTask scheduledGroupTransactionTask = new TransactionTask(group, amount,
                                                        description, date, category, accountFrom, accountTo, type);
        timer.schedule(scheduledGroupTransactionTask, 0, this.periodicity);
    }

    /**
     * Method to convert key word into milliseconds
     */
    public int convertKeyWordIntoMilliseconds(String periodicity) {
        switch (periodicity) {
            case "daily":
                return 250;
            case "working days":
                return 500;
            case "weekly":
                return 750;
            case "monthly":
                return 1000;
            default:
                throw new IllegalArgumentException("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.");
        }
    }

}
