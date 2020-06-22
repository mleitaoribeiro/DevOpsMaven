package switch2019.project.dataModel.dataAssemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.dataModel.entities.LedgerJpa;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionDomainDataAssemblerTest {

    private Transaction debitPersonalTransaction;
    private TransactionJpa debitPersonalTransactionJpa;
    private Transaction debitGroupTransaction;
    private TransactionJpa debitGroupTransactionJpa;

    private Transaction creditTransaction;
    private TransactionJpa creditTransactionJpa;

    @BeforeEach
    public void setup() {

        //Personal Account
        AccountID personAccountFrom = new AccountID(new Denomination("BPI"), new PersonID(new Email("person@email.pt")));
        AccountID personAccountTo = new AccountID(new Denomination("Continente Supermarket"), new PersonID(new Email("person@email.pt")));
        CategoryID personCategory = new CategoryID(new Denomination("Grocery"),new PersonID(new Email("person@email.pt")));
        MonetaryValue monetaryValue = new MonetaryValue(200.0, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 11, 00);
        debitPersonalTransaction = new Transaction( monetaryValue, new Description("payment"), date, personCategory, personAccountFrom, personAccountTo, new Type(false));
        debitPersonalTransactionJpa = new TransactionJpa(new LedgerJpa("person@email.pt","2020-05-26"), 200.0, "EUR", "payment", "2020-01-13 11:00", "GROCERY",
                "BPI", "CONTINENTE SUPERMARKET", "DEBIT");

        //Group Account
        AccountID groupAccountFrom = new AccountID(new Denomination("BPI"), new GroupID(new Description("SWITCH")));
        AccountID groupAccountTo = new AccountID(new Denomination("Continente Supermarket"), new GroupID(new Description("SWITCH")));
        CategoryID groupCategory = new CategoryID(new Denomination("Grocery"), new GroupID(new Description("SWITCH")));
        debitGroupTransaction = new Transaction( monetaryValue, new Description("payment"), date, groupCategory, groupAccountFrom, groupAccountTo, new Type(false));
        debitGroupTransactionJpa = new TransactionJpa(new LedgerJpa("SWITCH", "2020-05-26"), 200.0, "EUR", "payment", "2020-01-13 11:00", "GROCERY",
                "BPI", "CONTINENTE SUPERMARKET", "DEBIT");

        //Credit transaciton
        creditTransaction = new Transaction(monetaryValue, new Description("payment"), date, personCategory, personAccountFrom, personAccountTo, new Type(true));
        creditTransactionJpa = new TransactionJpa(new LedgerJpa("person@email.pt","2020-05-26"), 200.0, "EUR", "payment", "2020-01-13 11:00", "GROCERY",
                "BPI", "CONTINENTE SUPERMARKET", "CREDIT");


    }

    @Test
    @DisplayName("Test if Transaction is properly converted to TransactionJpa - Personal  - DEBIT")
    void toDomainPersonID() {
        //ARRANGE
        Transaction expectedDomainObj = debitPersonalTransaction;

        //ACT
        Transaction resultDomainObj = TransactionDomainDataAssembler.toDomain(debitPersonalTransactionJpa);

        //ASSERT
        assertEquals(expectedDomainObj, resultDomainObj);
    }

    @Test
    @DisplayName("Test if Transaction is properly converted to TransactionJpa - Group  - DEBIT")
    void toDomainOwnerID() {
        //ARRANGE
        Transaction expectedDomainObj = debitGroupTransaction;

        //ACT
        Transaction resultDomainObj = TransactionDomainDataAssembler.toDomain(debitGroupTransactionJpa);

        //ASSERT
        assertEquals(expectedDomainObj, resultDomainObj);
    }

    @Test
    @DisplayName("Test if TransactionJpa is properly converted to Transaction - Person - DEBIT")
    void toDataPersonID() {
        //ARRANGE
        PersonID ownerID = new PersonID(new Email("person@email.pt"));
        TransactionJpa expectedJpa = debitPersonalTransactionJpa;
        Ledger personLedger = new Ledger(ownerID);

        //ACT
        TransactionJpa resultJpa = TransactionDomainDataAssembler.toData(personLedger, debitPersonalTransaction);

        //ASSERT
        assertEquals(expectedJpa, resultJpa);
    }

    @Test
    @DisplayName("Test if Transaction is properly converted to TransactionJPA - GROUP - DEBIT")
    void toDataGroupID() {
        //ARRANGE
        GroupID ownerID = new GroupID(new Description("switch"));
        TransactionJpa expectedJpa = debitGroupTransactionJpa;
        Ledger groupLedger = new Ledger (ownerID);

        //ACT
        TransactionJpa resultJpa = TransactionDomainDataAssembler.toData(groupLedger, debitGroupTransaction);

        //ASSERT
        assertEquals(expectedJpa, resultJpa);
    }

    @Test
    @DisplayName("Test if TransactionJpa is properly converted to Transaction - CREDIT")
    void toDomainPersonCREDIT() {
        //ARRANGE
        PersonID ownerID = new PersonID(new Email("person@email.pt"));
        TransactionJpa expectedJpa = creditTransactionJpa;
        Ledger personLedger = new Ledger(ownerID);

        //ACT
        TransactionJpa resultJpa = TransactionDomainDataAssembler.toData(personLedger, creditTransaction);

        //ASSERT
        assertEquals(expectedJpa, resultJpa);
    }

    @Test
    @DisplayName("Test if Transaction is properly converted to TransactionJPA - CREDIT")
    void toDataPersonCREDIT() {
        //ARRANGE
        PersonID ownerID = new PersonID(new Email("person@email.pt"));
        TransactionJpa expectedJpa = creditTransactionJpa;
        Ledger personLedger = new Ledger(ownerID);

        //ACT
        TransactionJpa resultJpa = TransactionDomainDataAssembler.toData(personLedger, creditTransaction);

        //ASSERT
        assertEquals(expectedJpa, resultJpa);
    }
}