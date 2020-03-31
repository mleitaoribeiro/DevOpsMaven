package switch2019.project.infrastructure.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.repositories.Repository;

public class LedgerRepository implements Repository {


    /**
     * Find Ledger by ID
     *
     * @param ledgerID
     * @return
     */
    public Ledger getByID (ID ledgerID) {
        return null;
    }



    /**
     * Verifies if ID exists on the Repository
     *
     * @param ledgerID
     * @return
     */
   public boolean isIDOnRepository (ID ledgerID) {
        return false;
    }


}
