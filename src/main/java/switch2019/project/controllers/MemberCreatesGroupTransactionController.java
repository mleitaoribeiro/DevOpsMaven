package switch2019.project.controllers;

import switch2019.project.model.*;
import switch2019.project.model.account.Account;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;

import java.time.LocalDateTime;

public class MemberCreatesGroupTransactionController {

    /**
     * Controller that enables a member to create a Group Transaction
     *
     * @param amount
     * @param description
     * @param localDate
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     * @param group1
     * @param transactionCreator
     * @return True .- if transactionCreator is a group member AND the transaction is verified to be inside the groupLedger.
     *         False .- if transactionCreator is not a group member OR the transaction is not inside the groupLedger.
     */

    public boolean memberCreatesAGroupTransaction (MonetaryValue amount, String description, LocalDateTime localDate,
                                                   Category category, Account accountFrom, Account accountTo, boolean type,
                                                   Group group1, Person transactionCreator) {

        //Check if the Transaction Creator is a member of group1.
        if (group1.isGroupMember(transactionCreator)) {

            // Add the transaction to the Group Ledger and verify if it is inside it afterwards:
            return group1.createGroupTransaction(amount,description,localDate,category,accountFrom,accountTo,type);
        }
        else return false;
    }
}
