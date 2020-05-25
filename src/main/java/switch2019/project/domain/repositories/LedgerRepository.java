package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.shared.*;

public interface LedgerRepository extends Repository {

    /**
     * Find Ledger by ID
     *
     * @param ledgerID
     * @return
     */

    Ledger getByID(ID ledgerID);

    /**
     * Verifies if ID exists on the Repository
     *
     * @param ledgerID
     * @return
     */

    boolean isIDOnRepository(ID ledgerID);

    /**
     * Method to check the number of Ledgers inside the Repository.
     *
     * @return
     */

    long repositorySize();

    /**
     * Add a new ledger to Ledger Repository
     * @param ownerID
     * @return ledger
     *
     */

    Ledger createLedger(OwnerID ownerID);


    /**
     *
     * Method to Add Transactions to Ledger
     *
     * @param ledgerID
     * @param amount
     * @param description
     * @param localDate
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     * @return boolen
     */

    Transaction addTransactionToLedger(LedgerID ledgerID, MonetaryValue amount, Description description, DateAndTime localDate,
                                       CategoryID category, AccountID accountFrom, AccountID accountTo, Type type);

}


