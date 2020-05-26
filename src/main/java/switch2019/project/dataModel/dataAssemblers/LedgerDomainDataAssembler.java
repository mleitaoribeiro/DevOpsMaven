package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.LedgerJpa;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.StringUtils;

import java.util.ArrayList;

public class LedgerDomainDataAssembler {

    public static Ledger toDomain(LedgerJpa ledgerJpa) {

        // Assembling OwnerID
        String owner = ledgerJpa.getOwner();
        OwnerID ownerId;

        // Checking if the owner is a Group or a Person:
        if (StringUtils.isEmail(owner))
            ownerId = new PersonID(new Email(owner));
        else
            ownerId = new GroupID(new Description(owner));

        // Getting all TransactionsJPA related to that LedgerJPA
        ArrayList<TransactionJpa> transactionsJpa = new ArrayList<>(); // TODO change later to DB Repository method

        ArrayList<Transaction> transactions = new ArrayList<>();

        for(TransactionJpa transactionJpa : transactionsJpa) {
            transactions.add(TransactionDomainDataAssembler.toDomain(transactionJpa));
        }

        DateAndTime creationDate = StringUtils.toDateAndTime(ledgerJpa.getCreationDate());

        return new Ledger(ownerId, transactions, creationDate);
    }

    public static LedgerJpa toData(Ledger ledger) {

        return new LedgerJpa(ledger.getID().getOwnerID().toString(), ledger.getCreationDateToString());
    }
}
