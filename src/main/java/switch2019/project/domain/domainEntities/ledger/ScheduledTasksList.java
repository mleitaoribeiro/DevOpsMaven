package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;

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

    public boolean addNewSchedule(Ledger ledger, Periodicity periodicity, MonetaryValue amount, Description description, DateAndTime date,
                                  Category category, Account accountFrom, Account accountTo, Type type) {

        Schedule newSchedule = new Schedule(ledger, periodicity, amount, description, date, category, accountFrom, accountTo, type);
        scheduledTransactions.add(newSchedule);
        return scheduledTransactions.contains(newSchedule);
    }
}
