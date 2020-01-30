package switch2019.project.controllers;

import switch2019.project.model.*;

import java.time.LocalDateTime;

public class GroupScheduleController {

    /**
     * US020
     * As a group member, i want to create a schedule with daily, working days, weekly and monthly periodicity,
     * so the transactions are automatically generated.
     */

    public boolean scheduleGroupTransaction(Group group, Person person, String periodicity,
                                            MonetaryValue amount, String description, LocalDateTime date,
                                            Category category, Account accountFrom, Account accountTo, boolean type) {

        if(group.isGroupMember(person))
            return group.scheduleNewTransaction(periodicity, amount, description, date, category, accountFrom, accountTo, type);
        else throw new IllegalArgumentException("You are not a member of that group.");
    }

    public int ledgerSize(Group group) {
        return group.ledgerSize();
    }
}
