package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.LedgerDomainDataAssembler;
import switch2019.project.dataModel.entities.LedgerJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.shared.LedgerID;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.infrastructure.jpa.LedgerJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.Optional;

@Component("LedgerDbRepository")
public class LedgerDbRepository implements LedgerRepository {

    @Autowired
    LedgerJpaRepository ledgerJpaRepository;

    //String literals - Exceptions
    private static final String LEDGER_ALREADY_EXISTS = "This Ledger already exists.";
    private static final String NO_LEDGER_FOUND = "No Ledger found with that ID.";

    /**
     *
     * Create/Add a new Ledger
     *
     * @param ownerID
     * @return
     */

    public Ledger createLedger(OwnerID ownerID) {

        if (!isIDOnRepository(new LedgerID(ownerID))) {
            Ledger ledger = new Ledger(ownerID);
            LedgerJpa newLedgerJPA = ledgerJpaRepository.save(LedgerDomainDataAssembler.toData(ledger));
            return LedgerDomainDataAssembler.toDomain(newLedgerJPA);
        }
        else throw new ResourceAlreadyExistsException(LEDGER_ALREADY_EXISTS);
    }

    /**
     *
     * Find a Ledger by it´s ID
     *
     * @param ledgerID
     * @return
     */

    public Ledger getByID(ID ledgerID) {
      Optional<LedgerJpa> ledgerJpa = ledgerJpaRepository.findById(ledgerID.toString());
        if (ledgerJpa.isPresent()) {
            return LedgerDomainDataAssembler.toDomain(ledgerJpa.get());
        }
        else throw new ArgumentNotFoundException(NO_LEDGER_FOUND);
    }

    /**
     *
     * Method to validate if the ledger is in the ledger Repository
     *
     * @param ledgerID
     * @return boolean
     */

    public boolean isIDOnRepository(ID ledgerID) {
        Optional<LedgerJpa> ledgerJpa = ledgerJpaRepository.findById(ledgerID.toString());
        return ledgerJpa.isPresent();
    }

    /**
     *
     * Method to get the number of Ledgers in the Repository
     *
     * @return long
     */

    public long repositorySize() {
        return ledgerJpaRepository.count();
    }

}
