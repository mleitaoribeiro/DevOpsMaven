package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.LedgerJpa;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.utils.StringUtils;

import java.util.Currency;

public class TransactionDomainDataAssembler {

 private TransactionDomainDataAssembler() {};

    public static Transaction toDomain(TransactionJpa transactionJpa) {

        //Assembling OwnerID
        String owner = transactionJpa.getLedger().getOwner();
        OwnerID ownerId;

        //Checking if owner is a Group or Person:
        if (StringUtils.isEmail(owner))
            ownerId = new PersonID(new Email(owner));
        else
            ownerId = new GroupID(new Description(owner));

        MonetaryValue amount = new MonetaryValue(transactionJpa.getAmount(), Currency.getInstance(transactionJpa.getCurrency()));
        Description description = new Description(transactionJpa.getDescription());
        CategoryID category = new CategoryID(new Denomination(transactionJpa.getCategory()), ownerId);
        AccountID accountFrom = new AccountID(new Denomination(transactionJpa.getAccountFrom()), ownerId) ;
        AccountID accountTo = new AccountID(new Denomination(transactionJpa.getAccountTo()),ownerId);
        Long id = transactionJpa.getId();

        Type type;
        if(transactionJpa.getType().toUpperCase().equals("CREDIT"))
            type = new Type(true);
        else type = new Type(false);

        //Date
        DateAndTime date = StringUtils.toDateHourMinute(transactionJpa.getDate());

        return new Transaction(amount, description, date , category, accountFrom, accountTo, type, id);
    }

    public static TransactionJpa toData(Ledger ledger, Transaction transaction) {

        return new TransactionJpa(LedgerDomainDataAssembler.toData(ledger), transaction.getAmount(), transaction.getCurrency().toString(),
                transaction.getDescription().toString(), transaction.getDate().yearMonthDayHourMinuteToString(), transaction.getCategoryID().getDenominationString(),
                transaction.getAccountFrom().getDenominationToString(), transaction.getAccountTo().getDenominationToString(), transaction.typeToString());
    }
}
