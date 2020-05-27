package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
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
     * Tests for addPersonalTransaction
     */

    @Test
    @DisplayName("addPersonalTransaction - Happy Case")
    void addPersonalTransactionHappyCase() {

        // Arrange
        String personEmail = "marge@hotmail.com";
        Double amount = 10.50;
        Currency currency = Currency.getInstance("EUR");
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        TransactionShortDTO transactionShortDTOExpected =
                new TransactionShortDTO(amount, currency, accountFrom.toUpperCase(),
                        accountTo.toUpperCase(), type.toUpperCase(), 1L);

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency.toString(), "beers", date, category, accountFrom, accountTo, type);

        // Act
        TransactionShortDTO transactionShortDTO = service.addPersonalTransaction(createPersonalTransactionDTO);

        // Assert
        assertEquals(transactionShortDTOExpected, transactionShortDTO);
    }

    @Test
    @DisplayName("addPersonalTransaction - Person not found")
    void addPersonalTransactionPersonNotFound() {

        // Arrange
        String personEmail = "pinheiro@hotmail.com";
        Double amount = 10.50;
        String currency = "EUR";
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency, "beers", date, category, accountFrom, accountTo, type);

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("addPersonalTransaction - Negative monetary value")
    void addPersonalTransactionNegativeMonetaryValue() {

        // Arrange
        String personEmail = "marge@hotmail.com";
        Double amount = -10.50;
        String currency = "EUR";
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency, "beers", date, category, accountFrom, accountTo, type);

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The monetary value cannot be negative.");
    }

    @Test
    @DisplayName("addPersonalTransaction - Null Description")
    void addPersonalTransactionNullDescription() {

        // Arrange
        String personEmail = "marge@hotmail.com";
        Double amount = 10.50;
        String currency = "EUR";
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency, null, date, category, accountFrom, accountTo, type);

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("addPersonalTransaction - Category doesn't exist")
    void addPersonalTransactionCategoryDoesntExist() {

        // Arrange
        String personEmail = "marge@hotmail.com";
        Double amount = 10.50;
        String currency = "EUR";
        String date = "2020-05-25 15:50";
        String category = "SPORTS";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency, "beers", date, category, accountFrom, accountTo, type);

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No category found with that ID.");
    }

    @Test
    @DisplayName("addPersonalTransaction - Account doesn't exist")
    void addPersonalTransactionAccountDoesntExist() {

        // Arrange
        String personEmail = "marge@hotmail.com";
        Double amount = 10.50;
        String currency = "EUR";
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "Decatlhon";
        String accountTo = "Homer Snacks";
        String type = "debit";

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency, "beers", date, category, accountFrom, accountTo, type);

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }

    /**
     * US008.1 - Test if Group Transaction is created
     */

    @Test
    @DisplayName("Test if Group Transaction is created - Happy Case")
    void testIfGroupAccountWasCreatedHappyCase() {

        //Arrange
        double amount = 20;
        String groupDescription = "Switch";
        String personEmail = "1191755@isep.ipp.pt";
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Pizza";
        String category = "ISEP";
        String accountFrom = "POCKET MONEY";
        String accountTo = "AE ISEP";
        String type = "DEBIT";

        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount, currency, date, description, category, accountFrom, accountTo, type);

        TransactionShortDTO expectedTransaction = new TransactionShortDTO
                (amount, Currency.getInstance("EUR"), accountFrom, accountTo, type, 1L);

        //Act
        TransactionShortDTO transactionCreated = service.addGroupTransaction(createGroupTransactionDTO);

        //Assert
        assertEquals(expectedTransaction, transactionCreated);
    }

    @Test
    @DisplayName("Test if Group Transaction is created - monetary value is negative")
    void testIfGroupAccountWasCreatedMonetaryValueNegative() {

        //Arrange
        String groupDescription = "SWITCH";
        String personEmail = "1191755@isep.ipp.pt";
        double amount1 = -20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Pizza";
        String category = "ISEP";
        String accountFrom = "POCKET MONEY";
        String accountTo = "AE ISEP";
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
        String groupDescription = "Switch";
        String personEmail = "leonard.smith@gmail.com";
        double amount1 = 20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Pizza";
        String category = "ISEP";
        String accountFrom = "POCKET MONEY";
        String accountTo = "AE ISEP";
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
        String personEmail = "1191755@isep.ipp.pt";
        double amount1 = 20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Pizza";
        String category = "ISEP";
        String accountFrom = "POCKET MONEY";
        String accountTo = "AE ISEP";
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
        String groupDescription = "Switch";
        String personEmail = "rosa@sapo.pt";
        double amount1 = 20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Pizza";
        String category = "ISEP";
        String accountFrom = "POCKET MONEY";
        String accountTo = "AE ISEP";
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
    @DisplayName("Test if Group Transaction is created - category not found")
    void testIfGroupAccountWasCreatedCategoryNotFound() {

        //Arrange
        String groupDescription = "Switch";
        String personEmail = "1191755@isep.ipp.pt";
        double amount1 = 20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Pizza";
        String category = "Compras";
        String accountFrom = "POCKET MONEY";
        String accountTo = "AE ISEP";
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
                .hasMessage("No category found with that ID.");
    }

    @Test
    @DisplayName("Test if Group Transaction is created - accountFrom not found")
    void testIfGroupAccountWasCreatedAccountFromNotFound() {

        //Arrange
        String groupDescription = "Switch";
        String personEmail = "1191755@isep.ipp.pt";
        double amount1 = 20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Pizza";
        String category = "ISEP";
        String accountFrom = "REVOLUT";
        String accountTo = "AE ISEP";
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
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Test if Group Transaction is created - accountTo not found")
    void testIfGroupAccountWasCreatedAccountNotFound() {

        //Arrange
        String groupDescription = "Switch";
        String personEmail = "1191755@isep.ipp.pt";
        double amount1 = 20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = "Pizza";
        String category = "ISEP";
        String accountFrom = "AE ISEP";
        String accountTo = "REVOLUT";
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
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Test if Group Transaction is created - one transaction parameter is null")
    void testIfGroupAccountWasCreatedNullTransactionParameter() {

        //Arrange
        String groupDescription = "Switch";
        String personEmail = "1191755@isep.ipp.pt";
        double amount1 = 20;
        String currency = "EUR";
        String date = "2019-05-25 13:12";
        String description = null;
        String category = "ISEP";
        String accountFrom = "POCKET MONEY";
        String accountTo = "AE ISEP";
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
    void getTransactionsByLedgerIdHappyCase() {

        //Arrange
        String email = "marge@hotmail.com";

        TransactionShortDTO transactionDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"),
                "GOLD CARD", "IKEA", "DEBIT", 1L);

        TransactionShortDTO transactionDTO1 = new TransactionShortDTO(50.0, Currency.getInstance("EUR"),
                "MASTERCARD", "KWIK E MART", "DEBIT", 2L);

        List<TransactionShortDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);

        //Act
        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(email);

        //Assert
        // assertEquals(expected, result);
    }


    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getTransactionsByLedgerIdEmptyLedger() {

        //Arrange
        String email = "bart.simpson@gmail.com";

        List<TransactionShortDTO> expected = Collections.emptyList();

        //Act
        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(email);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Get Transactions By ledgerID - not found")
    void getTransactionsByLedgerIdException() {

        //Arrange
        String email = "pikachu@hotmail.com";

        PersonID personID = new PersonID(new Email(email));

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionsByLedgerId(personID.toString());

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }

    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getGroupTransactionsByLedgerEmptyLedger() {

        //Arrange
        String groupDescription = "Friends";

        GroupID groupID = new GroupID(new Description(groupDescription));

        List<TransactionShortDTO> expected = Collections.emptyList();

        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(groupID.toString());

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get Transactions By ledgerID - not found")
    void getGroupTransactionsByLedgerIdException() {

        //Arrange
        String groupDescription = "Rick & Rock";

        GroupID groupID = new GroupID(new Description(groupDescription));

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionsByLedgerId(groupID.toString());

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }

    /**
     * Tests for the method: getTransactionByID
     */
/*
    @Test
    @DisplayName("Get Transaction By ID - PersonLedger - happy case")
    void getTransactionByIDPersonLedgerHappyCase() {

        //Arrange
        String email = "marge@hotmail.com";
        Long id = 2L;

        TransactionDTO transactionDTOexpected = new TransactionDTO
                (50.0, Currency.getInstance("EUR"), "GROCERY FOR BAKING COOKIES",
                        "2020-03-20 13:04", "HOUSE",
                        "MASTERCARD", "KWIK E MART", "DEBIT");

        //Act
        TransactionDTO result = service.getTransactionByID(email, id);

        //Assert
        assertEquals(transactionDTOexpected, result);
    }

 */
/*
    @Test
    @DisplayName("Get Transaction By ID - PersonLedger - No Permission")
    void getTransactionByIDPersonLedgerNoPermission() {

        //Arrange
        String email = "rick@gmail.com";
        Long id = 2L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionByID(email, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NoPermissionException.class)
                .hasMessage("No permission.");
    }

 */

    @Test
    @DisplayName("Get Transaction By ID - PersonLedger - No Person Found")
    void getTransactionByIDPersonLedgerNoPersonFound() {

        //Arrange
        String email = "raquel@hotmail.com";
        Long id = 2L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionByID(email, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Get Transaction By ID - PersonLedger - No Transaction Found")
    void getTransactionByIDPersonLedgerNoTransactionFound() {

        //Arrange
        String email = "marge@hotmail.com";
        Long id = 20L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionByID(email, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No transaction found with that ID.");

    }
/*

    @Test
    @DisplayName("Get Transaction By ID - GroupLedger - happy case")
    void getTransactionByIDGroupLedgerHappyCase() {

        //Arrange
        String groupDescription = "SWITCH";
        Long id = 7L;

        TransactionDTO transactionDTOexpected = new TransactionDTO
                (20.0, Currency.getInstance("EUR"), "SUPERBOCK ROUND 2",
                        "2020-03-04 17:00", "ISEP",
                        "POCKET MONEY", "AE ISEP", "DEBIT");

        //Act
        TransactionDTO result = service.getTransactionByID(groupDescription, id);

        //Assert
        assertEquals(transactionDTOexpected, result);
    }

 */

    /*
    @Test
    @DisplayName("Get Transaction By ID - GroupLedger - No Permission")
    void getTransactionByIDGroupLedgerNoPermission() {

        //Arrange
        String groupDescription = "SWITCH";
        Long id = 2L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionByID(groupDescription, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NoPermissionException.class)
                .hasMessage("No permission.");
    }

     */

    @Test
    @DisplayName("Get Transaction By ID - GroupLedger - No Group Found")
    void getTransactionByIDGroupLedgerNoGroupFound() {

        //Arrange
        String groupDescription = "GYM FRIENDS";
        Long id = 2L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionByID(groupDescription, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");
    }

    @Test
    @DisplayName("Get Transaction By ID - GroupLedger - No Transaction Found")
    void getTransactionByIDGroupLedgerNoTransactionFound() {

        //Arrange
        String groupDescription = "SWITCH";
        Long id = 20L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionByID(groupDescription, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No transaction found with that ID.");

    }
}
