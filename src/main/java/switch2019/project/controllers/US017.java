package switch2019.project.controllers;

import switch2019.project.model.Person;
import switch2019.project.model.Transaction;

import java.time.LocalDateTime;

public class US017 {
    public double getPersonalBalanceInDateRange(LocalDateTime initialDate, LocalDateTime finalDate, Person person1) {
       return person1.getPersonalBalanceInDateRange(initialDate, finalDate);
    }
}
