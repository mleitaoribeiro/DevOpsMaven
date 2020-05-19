package switch2019.project.assemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
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
     * Validate method that creates the TransactionShortDTO from Domain Object
     */

}