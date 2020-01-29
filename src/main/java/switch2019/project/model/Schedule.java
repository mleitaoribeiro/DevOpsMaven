package switch2019.project.model;

import java.time.LocalDateTime; 

public class Schedule {

    private String description;
    private LocalDateTime date;
    private Transaction transaction;
    private String periodicity;

    public Schedule(Transaction transaction, String periodicity) {
        this.transaction = transaction;
        this.periodicity = periodicity;
    }

    public Schedule scheduleATransaction(Schedule schedule) {
        switch (schedule.periodicity) {
            case "mensal":

                break;
            case "diário":

                break;
            case "dias úteis":

                break;
            case "semanal":

                break;

        }
        return schedule;
    }

}
