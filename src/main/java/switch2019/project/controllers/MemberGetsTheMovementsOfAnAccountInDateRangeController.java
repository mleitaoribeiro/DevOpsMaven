package switch2019.project.controllers;

import switch2019.project.model.Account;
import switch2019.project.model.Group;
import switch2019.project.model.Person;
import switch2019.project.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class MemberGetsTheMovementsOfAnAccountInDateRangeController {

    /**
     * US010: As a member of a Group, I want to get my transactions from a account in a specific date range
     *
     * @param account
     * @param initialDate
     * @param finalDate
     * @param group1
     * @param member
     */

    public List<Transaction> getOneAccountMovementsFromGroup(Account account, LocalDateTime initialDate, LocalDateTime finalDate, Group group1, Person member) {
        return group1.getOneAccountMovementsFromGroup(account,initialDate,finalDate,member);
    }
}