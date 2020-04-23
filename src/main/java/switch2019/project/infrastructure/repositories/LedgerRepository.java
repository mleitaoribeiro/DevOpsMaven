package switch2019.project.infrastructure.repositories;

import switch2019.project.domain.domainEntities.ledger.Ledger;

public class LedgerRepository { //implements Repository {


    /**
     * Find Ledger by ID
     *
     * @param ledgerID
     * @return
     */

    public Ledger getByID (Ledger ledgerID) {
        return null;
    }

    /**
     * Verifies if ID exists on the Repository
     *
     * @param ledgerID
     * @return
     */

   public boolean isIDOnRepository (Ledger ledgerID) {
        return false;
    }

    /**
     * Method to check the number of Ledgers inside the Repository.
     *
     * @return
     */

    public int repositorySize () {
        return 0;
    }

}
