package switch2019.project.assemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class LedgerDTOAssemblerTest {

    /**
     * Validate method that transforms a CreatePersonalTransactionInfoDTO into a CreatePersonalTransactionDTO
     */

    /**
     * Validate method that transforms a CreateGroupTransactionInfoDTO into a CreateGroupTransactionDTO
     */

    /**
     * Validate method that creates the TransactionDTO from Domain Object
     */

    /**
     * Tests for validate creation of DTO from Domain Object
     *
     */
    @Test
    @DisplayName("Test method createTransactionDTOFromDomain")
    void createTransactionDTOFromDomain() {

        //Arrange
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);
        Description description = new Description("Payment");

        Transaction transaction = new Transaction(monetaryValue, description, date, category, account1, account2,
                new Type(false));
        TransactionDTO expected = new TransactionDTO(monetaryValue.toString(), description.toString(),
                date.yearMonthDayHourMinuteToString(), category.toString(), account1.toString(), account2.toString(),
                "DEBIT");

        //Act
        TransactionDTO result = LedgerDTOAssembler.createTransactionDTOFromDomain(transaction);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests for the createTransactionShortDTOFromDomain method
     *
     */
    @Test
    @DisplayName("Test method createTransactionDTOFromDomain")
    void createTransactionShortDTOFromDomain() {

        //ARRANGE:
        //Arrange Accounts:
        Account accountFrom = new Account(new Denomination("groceries"),
                new Description("food"), new PersonID(new Email("test@gmail.com")));
        Account accountTo = new Account(new Denomination("meals"),
                new Description("lunch and dinner expenses"), new PersonID(new Email("personEmail@email.pt")));

        //Arrange Category:
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));

        //Arrange MonetaryValue:
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        //Arrange DateAndTime:
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);

        //Arrange Description:
        Description description = new Description("Payment");

        //Arrange Transaction that will go into the Ledger:
        Transaction transaction = new Transaction(monetaryValue, description, date, category, accountFrom, accountTo,
                new Type(false));

        //Arrange the expected TransactionShortDTO
        TransactionShortDTO expected = new TransactionShortDTO (monetaryValue.toString(), accountFrom.toString(),
                accountTo.toString(), "DEBIT");

        //Act
        //Create the actual TransactionShortDTO
        TransactionShortDTO result = LedgerDTOAssembler.createTransactionShortDTOFromDomain(transaction);

        // Assert
        assertEquals(expected, result);
    }

}