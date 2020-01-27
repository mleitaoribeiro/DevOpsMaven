package switch2019.project.controllers;

import switch2019.project.model.*;

import java.time.LocalDateTime;

public class MemberCreatesGroupTransactionController {

    /**
     * US008.1
     * As a group member, i want to create a group transaction by atribuing a value, a description, a category,
     * a debit account and a credit account
     */

    public boolean memberCreatesAGroupTransaction (MonetaryValue amount, String description, LocalDateTime localDate,
                                                          Category category, Account accountFrom, Account accountTo, boolean type,
                                                          Group group1, Person transactionCreator) {

        //Check if the Transaction Creator is a member of group1.
        if (group1.isGroupMember(transactionCreator)) {

            // Add the transaction to the Group Ledger:
            return group1.createGroupTransaction(amount,description,localDate,category,accountFrom,accountTo,type);
        }

        //Check if the Transaction is Inside the Ledger:
        else return false;
    }
}
