package switch2019.project.model;

import java.time.LocalDateTime;
import java.util.Timer;

public class Schedule {

    private String description;
    private LocalDateTime date;
    private Transaction transaction;
    private String periodicity;

    public Schedule(Person person, String periodicity, MonetaryValue amount, String description, LocalDateTime date,
                               Category category, Account accountFrom, Account accountTo, boolean type) {

        Timer timer = new Timer();

    }

    public Schedule scheduleATransaction(Schedule schedule) {
        switch (schedule.periodicity) {
            case "monthly":

                break;
            case "daily":

                break;
            case "dias úteis":

                break;
            case "semanal":

                break;
            default:
                throw new IllegalArgumentException("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.");

        }
        return schedule;
    }

}
