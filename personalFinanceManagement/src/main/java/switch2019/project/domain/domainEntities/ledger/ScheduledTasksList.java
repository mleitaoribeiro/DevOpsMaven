package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.shared.*;

import java.util.HashSet;
import java.util.Set;

public class ScheduledTasksList {

    private final Set<Schedule> scheduledTransactions;

    public ScheduledTasksList() {
        scheduledTransactions = new HashSet<>();
    }

    public boolean addNewSchedule(Ledger ledger, Periodicity periodicity, MonetaryValue amount, Description description, DateAndTime date,
                                  CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {

        Schedule newSchedule = new Schedule(ledger, periodicity, amount, description, date, category, accountFrom, accountTo, type);
        scheduledTransactions.add(newSchedule);
        return scheduledTransactions.contains(newSchedule);
    }
}
