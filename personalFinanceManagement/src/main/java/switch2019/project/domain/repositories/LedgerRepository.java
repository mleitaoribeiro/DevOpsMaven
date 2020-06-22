package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.List;

public interface LedgerRepository extends Repository {

    /**
     * Find Ledger by ID
     *
     * @param ledgerID - LedgerID
     * @return - Ledger
     */
    Ledger getByID(ID ledgerID);

    /**
     * Verifies if ID exists on the Repository
     *
     * @param ledgerID - LedgerID
     * @return - Boolean
     */
    boolean isIDOnRepository(ID ledgerID);

    /**
     * Find Transaction by TransactionID
     *
     * @param ownerID - ownerID
     * @param id - id
     * @return - Transactions by ID
     */
    Transaction getTransactionByID(String ownerID, Long id);

    /**
     * Method to check the number of Ledgers inside the Repository.
     *
     * @return Long
     */
    long repositorySize();

    /**
     * Add a new ledger to Ledger Repository
     *
     * @param ownerID - ownerID
     * @return - Ledger
     */
    Ledger createLedger(OwnerID ownerID);


    /**
     * Method to Add Transactions to Ledger
     *
     * @param ledgerID - ledgerID
     * @param amount - amount
     * @param description - description
     * @param localDate - localDate
     * @param category - category
     * @param accountFrom - accountFrom
     * @param accountTo - accountTo
     * @param type - type
     * @return - added Transaction
     */
    Transaction addTransactionToLedger(LedgerID ledgerID, MonetaryValue amount, Description description, DateAndTime localDate,
                                       CategoryID category, AccountID accountFrom, AccountID accountTo, Type type);

    /**
     * Method to find all the transactions associated with a Ledger
     *
     * @param ownerID - ownerID
     * @return - List of Transactions
     */
    List<Transaction> findAllTransactionsByLedgerID(String ownerID);

    /**
     * Method to get all transactions between two dates on a particular Ledger.
     *
     * @param ledgerID - ledgerID
     * @param initDate - initDate
     * @param finDate - finDate
     * @return - List of Transactions
     */
    List<Transaction> getTransactionsInDateRange(OwnerID ledgerID, String initDate, String finDate);
}


