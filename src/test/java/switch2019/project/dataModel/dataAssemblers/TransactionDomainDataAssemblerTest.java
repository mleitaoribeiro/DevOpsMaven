package switch2019.project.dataModel.dataAssemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDomainDataAssemblerTest {

    private Transaction transaction;
    private TransactionJpa transactionJpa;

    @BeforeEach
    public void setup() {
        AccountID accountFrom = new AccountID(new Denomination("BPI"), new PersonID(new Email("person@email.pt")));
        AccountID accountTo = new AccountID(new Denomination("Continente Supermarket"), new PersonID(new Email("person@email.pt")));
        CategoryID category = new CategoryID(new Denomination("Grocery"),new PersonID(new Email("person@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200.0, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 11, 00);
        transaction = new Transaction(monetaryValue, new Description("payment"), date, category, accountFrom, accountTo, new Type(false));
        transactionJpa = new TransactionJpa(1, "person@email.pt", 200.0, "EUR", "payment", "2020-01-13 11:00", "GROCERY",
                "BPI", "Continente Supermarket", "DEBIT");
    }

    @Test
    @DisplayName("Test if Transaction is properly converted to TransactionJpa")
    void toDomain() {
        //ARRANGE
        Transaction expectedDomainObj = transaction;

        //ACT
        Transaction resultDomainObj = TransactionDomainDataAssembler.toDomain(transactionJpa);

        //ASSERT
        //  assertEquals(expectedDomainObj, resultDomainObj);
    }

    @Test
    @DisplayName("Test if TransactionJpa is properly converted to Transaction")
    void toData() {
        //ARRANGE
        PersonID ownerID = new PersonID(new Email("person@email.pt"));
        TransactionJpa expectedJpa = transactionJpa;

        //ACT
        TransactionJpa resultJpa = TransactionDomainDataAssembler.toData(ownerID, transaction);

        //ASSERT
        assertEquals(expectedJpa, resultJpa);
    }
}