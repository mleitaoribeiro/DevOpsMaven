package switch2019.project.controllers;

import switch2019.project.model.Group;
import switch2019.project.model.GroupsList;
import switch2019.project.model.Person;
import switch2019.project.model.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class getPersonalAndGroupTransactionsController {
    public ArrayList getPersonalAndGroupTransactions(Person person, LocalDateTime initialDate, LocalDateTime finalDate, GroupsList groupsList) {
        ArrayList personalAndGroupTransactions = new ArrayList();

        for (Transaction transaction : person.returnPersonLedgerFromPeriod(initialDate, finalDate)) {
        personalAndGroupTransactions.add(transaction);
        }

        HashSet<Group> groups = groupsList.returnAllGroupsAPersonIsIn(person);
        for (Group oneGroup : groups) {
            personalAndGroupTransactions.add(oneGroup.returnGroupLedgerFromPeriod(initialDate, finalDate));
        }

        return personalAndGroupTransactions;
    }


}
