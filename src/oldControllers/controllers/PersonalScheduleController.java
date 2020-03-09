package switch2019.project.controllers;

import switch2019.project.model.account.Account;
import switch2019.project.model.category.Category;
import switch2019.project.model.valueObject.MonetaryValue;
import switch2019.project.model.person.Person;

import java.time.LocalDateTime;

public class PersonalScheduleController {

    /**
     * US020
     * As a user, i want to create a schedule with daily, working days, weekly and monthly periodicity,
     * so the transactions are automatically generated.
     */

    public boolean schedulePersonalTransaction(Person person, String periodicity, MonetaryValue amount, String description, LocalDateTime date,
                                               Category category, Account accountFrom, Account accountTo, boolean type) {

        return person.scheduleNewTransaction(periodicity, amount, description, date, category, accountFrom, accountTo, type);
    }

    public int ledgerSize(Person person) {
        return person.ledgerSize();
    }
}
