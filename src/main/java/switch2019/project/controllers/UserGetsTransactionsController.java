package switch2019.project.controllers;

import switch2019.project.model.Account;
import switch2019.project.model.Person;
import switch2019.project.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class UserGetsTransactionsController {

    /**
     * US010.1: As a person, I want to get my transactions from a account in a specific date range
     *
     * @param account
     * @param initialDate
     * @param finalDate
     * @param member
     */

    public List<Transaction> getOneAccountMovementsFromPerson(Account account, LocalDateTime initialDate, LocalDateTime finalDate, Person member) {
        return member.getOneAccountMovementsFromUser(account,initialDate,finalDate);
    }
}
