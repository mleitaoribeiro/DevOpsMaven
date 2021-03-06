package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import switch2019.project.dataModel.dataAssemblers.LedgerDomainDataAssembler;
import switch2019.project.dataModel.dataAssemblers.TransactionDomainDataAssembler;
import switch2019.project.dataModel.entities.LedgerJpa;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.infrastructure.jpa.LedgerJpaRepository;
import switch2019.project.infrastructure.jpa.TransactionJpaRepository;
import switch2019.project.utils.StringUtils;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class LedgerDbRepository implements LedgerRepository {

    @Autowired
    LedgerJpaRepository ledgerJpaRepository;

    @Autowired
    TransactionJpaRepository transactionJpaRepository;

    //String literals - Exceptions
    private static final String LEDGER_ALREADY_EXISTS = "This Ledger already exists.";
    private static final String NO_LEDGER_FOUND = "No Ledger found with that ID.";
    private static final String NULL_OWNER = "Owner ID can't be null.";
    private static final String NO_TRANSACTION_FOUND = "No transaction found with that ID.";
    private static final String NO_PERMISSION = "No permission.";
    private static final String INVALID_DATE = "The date is not valid.";

    public Ledger createLedger(OwnerID ownerID) {
        Ledger ledger = new Ledger(ownerID);
        LedgerJpa newLedgerJPA = ledgerJpaRepository.save(LedgerDomainDataAssembler.toData(ledger));
        return LedgerDomainDataAssembler.toDomain(newLedgerJPA);
    }

    @Transactional
    public Transaction addTransactionToLedger(LedgerID ledgerID, MonetaryValue amount, Description description, DateAndTime localDate,
                                              CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {

        OwnerID owner = ledgerID.getOwnerID();
        Ledger ledger;

        if (!isIDOnRepository(owner))
            ledger = createLedger(owner);
        else ledger = getByID(owner);

        LedgerJpa ledgerJpa = LedgerDomainDataAssembler.toData(ledger);
        Transaction transaction = new Transaction(amount, description, localDate, category, accountFrom, accountTo, type);

        TransactionJpa newTransactionJpa = transactionJpaRepository.save(TransactionDomainDataAssembler.toData(ledger, transaction));
        ledgerJpa.addTransaction(newTransactionJpa);

        return TransactionDomainDataAssembler.toDomain(newTransactionJpa);
    }

    @Transactional
    public List<Transaction> findAllTransactionsByLedgerID(String ledgerID) {

        boolean isLedgerOnRepository;

        if (StringUtils.isEmail(ledgerID)) {
            isLedgerOnRepository = isIDOnRepository(new PersonID(new Email(ledgerID)));
        } else {
            isLedgerOnRepository = isIDOnRepository(new GroupID(new Description(ledgerID)));
        }

        if (isLedgerOnRepository) {
            List<TransactionJpa> transactionJpaList = transactionJpaRepository.findAllByLedger_Owner(ledgerID);
            List<Transaction> transactionsList = new ArrayList<>();

            for (TransactionJpa transactionJpa : transactionJpaList) {
                Transaction convertedTransaction = TransactionDomainDataAssembler.toDomain(transactionJpa);
                transactionsList.add(convertedTransaction);
            }
            return transactionsList;
        }
        throw new ArgumentNotFoundException(NO_LEDGER_FOUND);
    }

    public Ledger getByID(ID owner) {
        if(owner != null) {
            Optional<LedgerJpa> ledgerJpa = ledgerJpaRepository.findLedgerJpaByOwner(owner.toString());
            if (ledgerJpa.isPresent()) {
                return LedgerDomainDataAssembler.toDomain(ledgerJpa.get());
            } else throw new ArgumentNotFoundException(NO_LEDGER_FOUND);
        } else throw new IllegalArgumentException(NULL_OWNER);
    }

    @Transactional
    public Transaction getTransactionByID(String ownerId, Long id) {
        Optional<TransactionJpa> transactionJpa = transactionJpaRepository.findById(id);
        if (transactionJpa.isPresent()) {
            if (transactionJpa.get().getLedger().getOwner().equals(ownerId)) {
                return TransactionDomainDataAssembler.toDomain(transactionJpa.get());
            } else throw new NoPermissionException(NO_PERMISSION);

        } else throw new ArgumentNotFoundException(NO_TRANSACTION_FOUND);
    }

    public boolean isIDOnRepository(ID ledgerID) {
        Optional<LedgerJpa> ledgerJpa = ledgerJpaRepository.findLedgerJpaByOwner(ledgerID.toString());
        return ledgerJpa.isPresent();
    }

    public long repositorySize() {
        return ledgerJpaRepository.count();
    }

    public List<Transaction> getTransactionsInDateRange(OwnerID ledgerID, String initDate, String finDate){
        Ledger ledger = getByID(ledgerID);
        if (StringUtils.isCorrectDateRange(initDate, finDate)) {
            DateAndTime initialDate = StringUtils.toDateHourMinute(initDate);
            DateAndTime finalDate = StringUtils.toDateHourMinute(finDate);

            return ledger.getTransactionsInDateRange(initialDate.getYearMonthDayHourMinute(),
                    finalDate.getYearMonthDayHourMinute());
        }
        throw new IllegalArgumentException(INVALID_DATE);
    }
}
