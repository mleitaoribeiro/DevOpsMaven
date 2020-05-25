package switch2019.project.infrastructure.inMemoryRepositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.utils.StringUtils;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.LinkedHashSet;
import java.util.List;
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

    public long repositorySize() {
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

    public boolean addTransactionToLedger(LedgerID ledgerID, MonetaryValue amount, Description description, DateAndTime localDate,
                                   CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {

        if (isIDOnRepository(ledgerID)) {
            Ledger ledger = getByID(ledgerID);
            return ledger.addTransactionToLedger(amount, description, localDate, category, accountFrom, accountTo, type);
        } else  return false;
    }


    public List<Transaction> findAllTransactionsByLedgerID(String ownerID) {
        Ledger ledger;
        if (StringUtils.isEmail(ownerID)){
            ledger = getByID(new PersonID(new Email(ownerID)));
        } else {
            ledger = getByID(new GroupID(new Description(ownerID)));
        }
        return ledger.getLedgerTransactions();
    }


}
