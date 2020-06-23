package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.List;

public interface LedgerRepository extends Repository {

    Ledger getByID(ID ledgerID);

    boolean isIDOnRepository(ID ledgerID);

    Transaction getTransactionByID(String ownerID, Long id);

    long repositorySize();

    Ledger createLedger(OwnerID ownerID);

    Transaction addTransactionToLedger(LedgerID ledgerID, MonetaryValue amount, Description description, DateAndTime localDate,
                                       CategoryID category, AccountID accountFrom, AccountID accountTo, Type type);

    List<Transaction> findAllTransactionsByLedgerID(String ownerID);

    List<Transaction> getTransactionsInDateRange(OwnerID ledgerID, String initDate, String finDate);
}


