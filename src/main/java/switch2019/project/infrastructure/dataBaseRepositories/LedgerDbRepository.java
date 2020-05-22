package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.LedgerDomainDataAssembler;
import switch2019.project.dataModel.entities.LedgerJpa;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.infrastructure.jpa.LedgerJpaRepository;
import switch2019.project.infrastructure.jpa.TransactionJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.List;
import java.util.Optional;

@Component("LedgerDbRepository")
public class LedgerDbRepository implements LedgerRepository {

    @Autowired
    LedgerJpaRepository ledgerJpaRepository;

    @Autowired
    TransactionJpaRepository transactionJpaRepository;

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
     * Method to add a new Transaction to ledgerJpa
     *
     * @param ledger
     * @param amount
     * @param description
     * @param localDate
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     * @return boolean
     */

    public boolean addTransactionToLedger(Ledger ledger, Long serialNumber, MonetaryValue amount, Description description, DateAndTime localDate,
                                          CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {

        if (!isIDOnRepository(ledger.getID()))
            createLedger(ledger.getID().getOwnerID());

        List<TransactionJpa> transactionJpaList = findAllTransactionsByLedgerID(ledger.getID().toString());

        LedgerJpa ledgerJpa = LedgerDomainDataAssembler.toData(ledger);

        TransactionJpa transactionJpa = new TransactionJpa(serialNumber, ledger.getID().toString(), amount.getAmount(), amount.getCurrency().toString(),
                description.getDescription(), localDate.yearMonthDayHourMinuteToString(), category.getOwnerIDString(),
                accountFrom.getDenominationToString(), accountTo.getDenominationToString(), type.toString());

        if (!transactionJpaList.contains(transactionJpa)) {
            TransactionJpa newTransactionJpa = transactionJpaRepository.save(transactionJpa);
            //ledgerJpa.addTransactionToLedgerJpa(newTransactionJpa);
            return true;
        } else return false;
    }

    /**
     *
     * Method to find all transactions by Ledger ID
     *
     * @param ledgerID
     * @return List<TransactionJpa>
     */

    public List<TransactionJpa> findAllTransactionsByLedgerID (String ledgerID) {
        return transactionJpaRepository.findAllByTransactionIDJpa_LedgerId(ledgerID);
    }

    /**
     *
     * Find a Ledger by it´s ID
     *
     * @param ledgerID
     * @return
     */

    public Ledger getByID(ID ledgerID) {
      Optional<LedgerJpa> ledgerJpa = ledgerJpaRepository.findByLedgerIdJpa_Owner(ledgerID.toString());
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
        Optional<LedgerJpa> ledgerJpa = ledgerJpaRepository.findByLedgerIdJpa_Owner(ledgerID.toString());
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