package switch2019.project.controllers;

import switch2019.project.model.Person;
import switch2019.project.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class PersonalTransactionsFromPeriodController {

    public List<Transaction> returnPersonLedgerFromPeriod(LocalDateTime initialDate, LocalDateTime finalDate, Person personController) {
        return personController.returnPersonLedgerFromPeriod(initialDate, finalDate);
    }
}
