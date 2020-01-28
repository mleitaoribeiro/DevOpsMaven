package switch2019.project.controllers;

import switch2019.project.model.Group;
import switch2019.project.model.Person;
import switch2019.project.model.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberGetsTheMovimentsInAGivenPeriodController {

    /**
     * Get the group's ledger movements in a given period (US012)
     *
     * @param initialDate
     * @param finalDate
     */

    public ArrayList<Transaction> returnGroupLedgerFromPeriod(LocalDateTime initialDate, LocalDateTime finalDate, Group group, Person person) {
        return group.returnGroupLedgerInDateRange(initialDate, finalDate, person);
    }
}
