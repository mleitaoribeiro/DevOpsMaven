package switch2019.project.model;

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

    public boolean addNewSchedule(Person person, String periodicity, MonetaryValue amount, String descripiton, LocalDateTime date,
                                  Category category, Account accountFrom, Account accountTo, boolean type) {

        Schedule newSchedule = new Schedule(person, periodicity, amount, descripiton, date, category, accountFrom, accountTo, type);
        return scheduledTransactions.add(newSchedule) && scheduledTransactions.contains(newSchedule);
    }

    public boolean addNewSchedule(Group group, String periodicity, MonetaryValue amount, String descripiton, LocalDateTime date,
                                  Category category, Account accountFrom, Account accountTo, boolean type) {

        Schedule newSchedule = new Schedule(group, periodicity, amount, descripiton, date, category, accountFrom, accountTo, type);
        return scheduledTransactions.add(newSchedule) && scheduledTransactions.contains(newSchedule);
    }

}
