package switch2019.project.controllers;

import switch2019.project.model.Group;
import switch2019.project.model.GroupsList;
import switch2019.project.model.Person;
import switch2019.project.model.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class getPersonalAndGroupTransactionsController {
    public ArrayList<Transaction> getPersonalAndGroupTransactions(Person person, LocalDateTime initialDate, LocalDateTime finalDate, GroupsList groupsList) {
        ArrayList<Transaction> personalAndGroupTransactions = new ArrayList<Transaction>();

        // add all the personal movements
        personalAndGroupTransactions.addAll(person.returnPersonLedgerFromPeriod(initialDate, finalDate));

        // add all the group movements
        for (Group oneGroup : groupsList.returnAllGroupsAPersonIsIn(person)) {
            personalAndGroupTransactions.addAll(oneGroup.returnGroupLedgerFromPeriod(initialDate, finalDate));
        }

        // sort the movements by date and return
        personalAndGroupTransactions.sort(Comparator.comparing(Transaction::getDate));
        return personalAndGroupTransactions;
    }
}
