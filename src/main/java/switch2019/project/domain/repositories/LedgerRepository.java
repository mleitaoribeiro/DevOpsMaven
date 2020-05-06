package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;

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

    int repositorySize();

    /**
     * Add a new ledger to Ledger Repository
     * @param ownerID
     * @return ledger
     *
     */

    Ledger createLedger(OwnerID ownerID);
}
