package switch2019.project.model.ledger;

import switch2019.project.model.shared.MonetaryValue;
import switch2019.project.model.account.Account;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;

import switch2019.project.model.category.Category;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ScheduledTasksList {

    private Set<Schedule> scheduledTransactions;

    /**
     * ScheduledTasksList Constructor
     */
    public ScheduledTasksList() {
        scheduledTransactions = new HashSet<>();
    }

    /**
     * Method to add a new schedule do the scheduledTasksList
     */

    public boolean addNewSchedule(Person person, Periodicity periodicity, MonetaryValue amount, String descripiton, LocalDateTime date,
                                  Category category, Account accountFrom, Account accountTo, Type type) {

        Schedule newSchedule = new Schedule(person, periodicity, amount, descripiton, date, category, accountFrom, accountTo, type);
        scheduledTransactions.add(newSchedule);
        return scheduledTransactions.contains(newSchedule);
    }

    public boolean addNewSchedule(Group group, Periodicity periodicity, MonetaryValue amount, String descripiton, LocalDateTime date,
                                  Category category, Account accountFrom, Account accountTo, Type type) {

        Schedule newSchedule = new Schedule(group, periodicity, amount, descripiton, date, category, accountFrom, accountTo, type);
        scheduledTransactions.add(newSchedule);
        return scheduledTransactions.contains(newSchedule);
    }

}
