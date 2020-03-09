package switch2019.project.controllers;

import switch2019.project.model.person.Person;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.model.ledger.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GetPersonalAndGroupTransactionsController {

    /**
     * US019
     * As a user, i want to obtain my personal transactions and the transactions
     * from all the groups I'm a member of in a given date range
     *
     * @param person
     * @param initialDate
     * @param finalDate
     * @param groupsRepository
     * @return personal and group transaction in date range
     */

    public List<Transaction> getPersonalAndGroupTransactions(Person person, LocalDateTime initialDate, LocalDateTime finalDate, GroupsRepository groupsRepository) {
           List<Transaction> selectedTransactions = new ArrayList<>();

        // add all the personal transactions
        selectedTransactions.addAll(person.returnPersonLedgerInDateRange(initialDate, finalDate));

        // add all the group transactions
        selectedTransactions.addAll(groupsRepository.returnTransactionsFromAllGroupsAPersonIsIn(person, initialDate, finalDate));

        // sort the transactions by date and return
        selectedTransactions.sort(Comparator.comparing(Transaction::getDate));
        return selectedTransactions;
    }
}
