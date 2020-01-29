package switch2019.project.model;

import java.time.LocalDateTime;
import java.util.Timer;

public class Schedule {

    // Schedule private attribute
    private int periodicity;

    /**
     * Schedule Constructor
     */
    public Schedule(Person person, String periodicityString, MonetaryValue amount, String description, LocalDateTime date,
                               Category category, Account accountFrom, Account accountTo, boolean type) {
        convertKeyWordIntoMilliseconds(periodicityString);
        Timer timer = new Timer();
        PersonalTransactionTask scheduledPersonalTransactionTask = new PersonalTransactionTask(person, amount, description, date, category, accountFrom, accountTo, type);
        timer.schedule(scheduledPersonalTransactionTask, 0, periodicity);
    }

    /**
     * Method to convert key word into milliseconds
     */
    public void convertKeyWordIntoMilliseconds(String periodicityString) {
        switch (periodicityString) {
            case "daily":
                periodicity = 100;
                break;
            case "dias úteis":
                periodicity = 200;
                break;
            case "semanal":
                periodicity = 300;
                break;
            case "monthly":
                periodicity = 400;
                break;
            default:
                throw new IllegalArgumentException("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.");
        }
    }

}
