package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.LedgerDomainDataAssembler;
import switch2019.project.dataModel.dataAssemblers.TransactionDomainDataAssembler;
import switch2019.project.dataModel.entities.LedgerJpa;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
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
     * @param ledgerID
     * @param amount
     * @param description
     * @param localDate
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     * @return boolean
     */

    public Transaction addTransactionToLedger(LedgerID ledgerID, MonetaryValue amount, Description description, DateAndTime localDate,
                                              CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {

        OwnerID owner = ledgerID.getOwnerID();

        Ledger ledger;

        if (!isIDOnRepository(ledgerID))
           ledger = createLedger(owner);
        else ledger = new Ledger (owner);

        LedgerJpa ledgerJpa = LedgerDomainDataAssembler.toData(ledger);

        TransactionJpa transactionJpa = new TransactionJpa(ledger.getID().getOwnerID().toString(),
                amount.getAmount(), amount.getCurrency().toString(), description.getDescription(),
                localDate.yearMonthDayHourMinuteToString(), category.getDenomination().toString(),
                accountFrom.getDenominationToString(), accountTo.getDenominationToString(), type.toString());


        TransactionJpa newTransactionJpa = transactionJpaRepository.save(transactionJpa);
        //ledgerJpa.addTransactionToLedgerJpa(newTransactionJpa);

        return TransactionDomainDataAssembler.toDomain(newTransactionJpa);

    }

    /**
     *
     * Method to find all transactions by Ledger ID
     *
     * @param ledgerID
     * @return List<TransactionJpa>
     */

    public List<TransactionJpa> findAllTransactionsByLedgerID (String ledgerID) {
        return transactionJpaRepository.findAllByLedgerIdJpa_Owner(ledgerID);
    }


    /**
     *
     * Method to find all transactions
     *
     * @return List<TransactionJpa>
     */

    public List<TransactionJpa> findAllTransactions () {
        return transactionJpaRepository.findAll();
    }


    /**
     *
     * Find a Ledger by it´s ID
     *
     * @param owner
     * @return
     */

    public Ledger getByID(ID owner) {
      Optional<LedgerJpa> ledgerJpa = ledgerJpaRepository.findByLedgerIdJpa_Owner(owner.toString());
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
