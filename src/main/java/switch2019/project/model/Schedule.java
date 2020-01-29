package switch2019.project.model;

import java.time.LocalDateTime;
import java.util.Timer;

public class Schedule {

    /**
     * Personal Schedule Constructor
     */
    public Schedule(Person person, String periodicityString, MonetaryValue amount, String description, LocalDateTime date,
                               Category category, Account accountFrom, Account accountTo, boolean type) {
        int periodicity = convertKeyWordIntoMilliseconds(periodicityString);
        Timer timer = new Timer();
        PersonalTransactionTask scheduledPersonalTransactionTask = new PersonalTransactionTask(person, amount, description, date, category, accountFrom, accountTo, type);
        timer.schedule(scheduledPersonalTransactionTask, 0, periodicity);
    }

/*    *//**
     * Group Schedule Constructor
     *//*
    public Schedule(Group group, String periodicityString, MonetaryValue amount, String description, LocalDateTime date,
                    Category category, Account accountFrom, Account accountTo, boolean type) {
        int periodicity = convertKeyWordIntoMilliseconds(periodicityString);
        Timer timer = new Timer();
        PersonalTransactionTask scheduledPersonalTransactionTask = new PersonalTransactionTask(group, amount, description, date, category, accountFrom, accountTo, type);
        timer.scheduleAtFixedRate(scheduledPersonalTransactionTask, 0, periodicity);
    }*/

    /**
     * Method to convert key word into milliseconds
     */
    public int convertKeyWordIntoMilliseconds(String periodicityString) {
        switch (periodicityString) {
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
