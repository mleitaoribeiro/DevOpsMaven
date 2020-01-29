package switch2019.project.model;

import java.time.LocalDateTime;
import java.util.HashSet;

public class ScheduledTasksList {

    private HashSet<Schedule> scheduledTasksList;

    /**
     * Method to add a new schedule do the scheduledTasksList
     */
    public boolean addNewSchedule(Person person, String periodicity, MonetaryValue amount, String descripiton, LocalDateTime date,
                                  Category category, Account accountFrom, Account accountTo, boolean type) {

        Schedule newSchedule = new Schedule(person, periodicity, amount, descripiton, date, category, accountFrom, accountTo, type);
        return scheduledTasksList.add(newSchedule);
    }

}
