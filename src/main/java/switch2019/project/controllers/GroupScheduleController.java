package switch2019.project.controllers;

import switch2019.project.model.*;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;

import java.time.LocalDateTime;

public class GroupScheduleController {

    /**
     * US020.2
     * As a group member, i want to create a schedule with daily, working days, weekly and monthly periodicity,
     * so the transactions are automatically generated.
     *
     * @param group
     * @param person
     * @param periodicity
     * @param amount
     * @param description
     * @param date
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     * @return true if new group schedule was created, else false
     */

    public boolean scheduleGroupTransaction(Group group, Person person, String periodicity,
                                            MonetaryValue amount, String description, LocalDateTime date,
                                            Category category, Account accountFrom, Account accountTo, boolean type) {

        //verify if person is a group member
        if(group.isGroupMember(person))
            return group.scheduleNewTransaction(periodicity, amount, description, date, category, accountFrom, accountTo, type);
        else throw new IllegalArgumentException("You are not a member of that group.");
    }

    /**
     * Method to get the size of the ledger
     *
     * @param group
     * @return size of legder
     */
    public int ledgerSize(Group group) {
        return group.ledgerSize();
    }
}
