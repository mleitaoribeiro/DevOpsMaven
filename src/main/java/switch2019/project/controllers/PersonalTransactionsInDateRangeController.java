package switch2019.project.controllers;

import switch2019.project.model.person.Person;
import switch2019.project.model.Transaction;
import switch2019.project.model.Person;
import switch2019.project.model.Legder.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class PersonalTransactionsInDateRangeController {

    /**
     * US011: As a user, I want to get my transactions in a specific date range
     * @param initialDate
     * @param finalDate
     * @param personController
     * @return
     */

    public List<Transaction> returnPersonLedgerInDateRange(LocalDateTime initialDate, LocalDateTime finalDate, Person personController) {
        return personController.returnPersonLedgerInDateRange(initialDate, finalDate);
    }
}
