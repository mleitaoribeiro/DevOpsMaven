package switch2019.project.controllers;

import switch2019.project.model.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.legder.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class MemberGetsTransactionsInDateRangeController {

    /**
     * US012
     * Get the group's ledger transactions in a given period
     *
     * @param initialDate
     * @param finalDate
     * @return list of group transactions
     */

    public List<Transaction> returnGroupLedgerFromPeriod(LocalDateTime initialDate, LocalDateTime finalDate, Group group, Person person) {
        return group.returnGroupLedgerInDateRange(initialDate, finalDate, person);
    }
}
