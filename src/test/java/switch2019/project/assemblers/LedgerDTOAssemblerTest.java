package switch2019.project.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.deserializationDTO.CreateTransactionInfoDTO;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
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

    @Test
    @DisplayName("Validate if CreatePersonalTransactionInfoDTO")
    public void transformToCreatePersonalTransactionDTO() {
        //Arrange
        CreateTransactionInfoDTO infoDTO = new CreateTransactionInfoDTO();
        infoDTO.setCategory("Work");
        infoDTO.setDescription("BONUS");
        infoDTO.setAmount(1000.00);
        infoDTO.setCurrency("EUR");
        infoDTO.setAccountFrom("Porto Tech Hub");
        infoDTO.setAccountTo("MBCP");
        infoDTO.setType("CREDIT");
        infoDTO.setDate("2020-01-08");

        CreatePersonalTransactionDTO expectedDTO = new CreatePersonalTransactionDTO("sousa@gmail.com",
                1000.00, "EUR", "BONUS", "2020-01-08", "Work", "Porto Tech Hub",
                "MBCP", "CREDIT");

        //Act
        CreatePersonalTransactionDTO dto = LedgerDTOAssembler.transformToCreatePersonalTransactionDTO("sousa@gmail.com", infoDTO);

        //Assert
        assertEquals(expectedDTO, dto);
    }

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