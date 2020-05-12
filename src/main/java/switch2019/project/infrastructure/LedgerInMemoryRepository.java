package switch2019.project.infrastructure;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.LinkedHashSet;
import java.util.Set;

public class LedgerInMemoryRepository implements LedgerRepository {

    // Private instance variables
    private Set<Ledger> ledgers;

    //Constructor
    public LedgerInMemoryRepository() {
        ledgers = new LinkedHashSet<>();
    }

    /**
     * Find Ledger by ID
     *
     * @param ledgerID
     * @return ledger
     */

    public Ledger getByID(ID ledgerID) {
        for (Ledger ledger : ledgers) {
            if(ledger.getID().equals(ledgerID))
                return ledger;
        } throw new ArgumentNotFoundException("No ledger found with that ID.");
    }

    /**
     * Verifies if ID exists on the Repository
     *
     * @param ledgerID
     * @return boolean
     */

    public boolean isIDOnRepository(ID ledgerID) {
        for (Ledger ledger : ledgers) {
            if (ledger.getID().equals(ledgerID))
                return true;
        } return false;
    }

    /**
     * Method to check the number of Ledgers inside the Repository.
     *
     * @return ledger size
     */

    public int repositorySize() {
        return ledgers.size();
    }

    /**
     * Add a new ledger to Ledger Repository
     * @param ownerID
     * @return ledger
     *
     */

    public Ledger createLedger(OwnerID ownerID) {
        if (this.ledgers.contains(new Ledger(ownerID)))
            throw new ResourceAlreadyExistsException("This ledger already exists.");
        else {
            Ledger ledger = new Ledger(ownerID);
            ledgers.add(ledger);
            return ledger;
        }
    }
}
