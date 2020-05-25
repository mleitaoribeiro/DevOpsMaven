package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


@SpringBootTest
class US008CreateTransactionServiceTest {

    @Autowired
    private US008CreateTransactionService service;

    /**
     * US008.1 - Test if Group Transaction is created
     */

    @Test
    @DisplayName("Test if Group Transaction is created - Happy Case")
    void testIfGroupAccountWasCreatedHappyCase() {

        //Arrange
        String groupDescription = "Smith Family";
        String personEmail = "rick@gmail.com";
        double amount1 = 20;
        String currency = "EUR";
        String amount2 = Double.toString(amount1) + currency + "";
        String date = "2019-05-25 13:12";
        String description = "Payment";
        String category = "General";
        String accountFrom = "WALLET";
        String accountTo = "TRANSPORTACCOUNT";
        String type = "DEBIT";


        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount1, currency, date, description, category, accountFrom, accountTo, type);

        TransactionShortDTO expectedTransaction = new TransactionShortDTO(amount2, accountFrom, accountTo, type);

        //Act
        TransactionShortDTO transactionCreated = service.addGroupTransaction(createGroupTransactionDTO);

        //Assert
        //assertEquals(expectedTransaction, transactionCreated);
    }

    @Test
    @DisplayName("Test if Group Transaction is created - monetary value is negative")
    void testIfGroupAccountWasCreatedMonetaryValueNegative() {

        //Arrange
        String groupDescription = "Smith Family";
        String personEmail = "rick@gmail.com";
        double amount1 = -20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Payment";
        String category = "General";
        String accountFrom = "WALLET";
        String accountTo = "TRANSPORTACCOUNT";
        String type = "DEBIT";


        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount1, currency, date, description, category, accountFrom, accountTo, type);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The monetary value cannot be negative.");
    }

    @Test
    @DisplayName("Test if Group Transaction is created - person is not a member of the group")
    void testIfGroupAccountWasCreatedPersonNotMember() {

        //Arrange
        String groupDescription = "Smith Family";
        String personEmail = "1110120@isep.ipp.pt";
        double amount1 = -20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Payment";
        String category = "General";
        String accountFrom = "WALLET";
        String accountTo = "TRANSPORTACCOUNT";
        String type = "DEBIT";


        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount1, currency, date, description, category, accountFrom, accountTo, type);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NoPermissionException.class)
                .hasMessage("This person is not member of this group.");
    }

    @Test
    @DisplayName("Test if Group Transaction is created - group not found")
    void testIfGroupAccountWasCreatedGroupNotFound() {

        //Arrange
        String groupDescription = "Montaditos";
        String personEmail = "1110120@isep.ipp.pt";
        double amount1 = -20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Payment";
        String category = "General";
        String accountFrom = "WALLET";
        String accountTo = "TRANSPORTACCOUNT";
        String type = "DEBIT";


        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount1, currency, date, description, category, accountFrom, accountTo, type);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");
    }

    @Test
    @DisplayName("Test if Group Transaction is created - person not found")
    void testIfGroupAccountWasCreatedPersonNotFound() {

        //Arrange
        String groupDescription = "Montaditos";
        String personEmail = "rosa@sapo.pt";
        double amount1 = -20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Payment";
        String category = "General";
        String accountFrom = "WALLET";
        String accountTo = "TRANSPORTACCOUNT";
        String type = "DEBIT";


        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount1, currency, date, description, category, accountFrom, accountTo, type);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if Group Transaction is created - one transaction parameter is null")
    void testIfGroupAccountWasCreatedNullTransactionParameter() {

        //Arrange
        String groupDescription = "Smith Family";
        String personEmail = "rick@gmail.com";
        double amount1 = -20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = null;
        String category = "General";
        String accountFrom = "WALLET";
        String accountTo = "TRANSPORTACCOUNT";
        String type = "DEBIT";


        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount1, currency, date, description, category, accountFrom, accountTo, type);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }


    /**
     * Test to get Transactions by LedgerId
     */

    @Test
    @DisplayName("Get Transactions By ledgerID - happy case")
    void getTrasactionsByLedgerIdHappyCase() {

        String email = "marge@hotmail.com";

        List<TransactionDTO> result = service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        TransactionDTO transactionDTO = new TransactionDTO("100.0 EUR", "Bought a cheap sofa".toUpperCase(),
                "2020-02-14 11:24", "HOUSE, marge@hotmail.com", "GOLD CARD, marge@hotmail.com",
                "IKEA, marge@hotmail.com", "DEBIT");

        TransactionDTO transactionDTO1 = new TransactionDTO("50.0 EUR", "Grocery for baking cookies".toUpperCase(),
                "2020-03-20 13:04", "HOUSE, marge@hotmail.com", "MASTERCARD, marge@hotmail.com",
                "KWIK E MART, marge@hotmail.com", "DEBIT");

        List<TransactionDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);


        assertEquals(expected, result);
    }
/*
    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getTrasactionsByLedgerIdEmptyLedger() {

        String email = "bart.simpson@hotmail.com";

        List<TransactionDTO> result = service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        List<TransactionDTO> expected = Collections.emptyList();

        assertEquals(expected, result);
    }
    
 */




    @Test
    @DisplayName("Get Transactions By ledgerID - not found")
    void getTransactionsByLedgerIdException() {

        String email = "pikachu@hotmail.com";

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }
}