package switch2019.project.controllers;

import switch2019.project.model.person.Person;

import java.time.LocalDateTime;

public class PersonalBalanceInDateRangeController {
    /**
     * US017: As a user, I want to see the balance of my personal transactions in a specific date range.
     * @param initialDate
     * @param finalDate
     * @param person1
     * @return
     */
    public double getBalanceInDateRange(LocalDateTime initialDate, LocalDateTime finalDate, Person person1) {
       return person1.getPersonalBalanceInDateRange(initialDate, finalDate);
    }
}
