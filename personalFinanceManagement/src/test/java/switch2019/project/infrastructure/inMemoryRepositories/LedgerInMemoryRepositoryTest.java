package switch2019.project.infrastructure.inMemoryRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class LedgerInMemoryRepositoryTest {

    @Test
    @DisplayName("Test if Ledger was created - Happy Case")
    public void createLedger() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledgerExpected = new Ledger(ownerID);

        // Act
        Ledger ledger = ledgerInMemoryRepository.createLedger(ownerID);
        boolean result = ledger.equals(ledgerExpected);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if Ledger was created - Exception")
    public void createLedgerException() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));

        ledgerInMemoryRepository.createLedger(ownerID);

        // Act
        try {
            ledgerInMemoryRepository.createLedger(ownerID);
        }

        // Assert
        catch (ResourceAlreadyExistsException exception) {
            assertEquals("This ledger already exists.", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger by ID")
    public void getByID() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));
        OwnerID ownerID2 = new PersonID(new Email("mm@gmail.com"));
        ledgerInMemoryRepository.createLedger(ownerID);
        ledgerInMemoryRepository.createLedger(ownerID2);

        Ledger ledgerExpected = new Ledger(ownerID);

        // Act
        Ledger ledger = ledgerInMemoryRepository.getByID(new LedgerID(ownerID));

        //Assert
        assertEquals(ledgerExpected, ledger);

    }

    @Test
    @DisplayName("Get Ledger by ID - Exception")
    public void getByIDException() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));
        ledgerInMemoryRepository.createLedger(ownerID);

        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.getByID(new LedgerID(new GroupID(new Description("NOT FOUND"))));
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No ledger found with that ID.");

    }

    @Test
    @DisplayName("Get Transaction by ID")
    public void getTransactionByID() {

        // Arrange
        String email = "marge@hotmail.com";
        Long id = 2L;

        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        // Act
        Transaction transaction = ledgerInMemoryRepository.getTransactionByID(email, id);

        //Assert
        assertEquals(null, transaction);

    }


    @Test
    @DisplayName("Check if a LedgerID is in the Repository - True")
    public void isIDOnRepositoryTrue() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));
        ledgerInMemoryRepository.createLedger(ownerID);

        // Act
        boolean ledger = ledgerInMemoryRepository.isIDOnRepository(new LedgerID(ownerID));

        // Assert
        assertTrue(ledger);
    }

    @Test
    @DisplayName("Check if a LedgerID is in the Repository - False")
    public void isIDOnRepositoryFalse() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));

        // Act
        boolean ledger = ledgerInMemoryRepository.isIDOnRepository(new LedgerID(ownerID));

        // Assert
        assertFalse(ledger);
    }

    @Test
    @DisplayName("Get LedgerRepository size")
    public void repositorySize() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        OwnerID ownerID1 = new GroupID(new Description("isep"));
        ledgerInMemoryRepository.createLedger(ownerID1);

        OwnerID ownerID2 = new GroupID(new Description("switch"));
        ledgerInMemoryRepository.createLedger(ownerID2);

        OwnerID ownerID3 = new GroupID(new Description("bashtards"));
        ledgerInMemoryRepository.createLedger(ownerID3);

        // Act
        long ledgerSize = ledgerInMemoryRepository.repositorySize();

        // Assert
        assertEquals(3, ledgerSize);
    }

    @Test
    @DisplayName("Get LedgerRepository size - Empty")
    public void repositorySizeEmpty() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        // Act
        long ledgerSize = ledgerInMemoryRepository.repositorySize();

        // Assert
        assertEquals(0, ledgerSize);
    }

    @Test
    @DisplayName("Test add Transaction To Ledger - True")
    public void addTransactionToLedgerTrue() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        OwnerID ownerID = new GroupID(new Description("switch"));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Description description = new Description("xpto");
        DateAndTime dateAndTime = new DateAndTime();
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        AccountID account1 = new AccountID(new Denomination("mercearia"),
                new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        Type type = new Type(true);

        Transaction expectedTransaction = new Transaction(monetaryValue, description, dateAndTime, category, account1, account2, type);

        Ledger ledgerExpected = new Ledger(ownerID);

        int sizeExpected = 1;

        // Act
        Ledger ledgerReal = ledgerInMemoryRepository.createLedger(ownerID);

        Transaction transactionAdded = ledgerInMemoryRepository.addTransactionToLedger(ledgerReal.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);

        int sizeReal = ledgerReal.getLedgerSize();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(expectedTransaction, transactionAdded),
                () -> assertEquals(ledgerExpected, ledgerReal),
                () -> assertEquals(sizeExpected, sizeReal),
                () -> assertNotNull(transactionAdded)

        );
    }

    @Test
    @DisplayName("Test add Transaction To Ledger - False - No ledger in Repository")
    public void addTransactionToLedgerFalseNoLedgerInRepo() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        OwnerID ownerID = new GroupID(new Description("switch"));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Description description = new Description("xpto");
        DateAndTime dateAndTime = new DateAndTime();
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        AccountID account1 = new AccountID(new Denomination("mercearia"),
                new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        Type type = new Type(true);

        Ledger ledger = new Ledger(ownerID);

        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                    monetaryValue, description, dateAndTime, category, account1, account2, type);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");

    }

    @Test
    @DisplayName("Test add Transaction To Ledger - False - Null Account")
    public void addTransactionToLedgerFalseNullAccount() {

        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        OwnerID ownerID = new GroupID(new Description("switch"));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Description description = new Description("xpto");
        DateAndTime dateAndTime = new DateAndTime();
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        AccountID account1 = new AccountID(new Denomination("mercearia"),
                new PersonID(new Email("personEmail@email.pt")));
        Type type = new Type(true);


        Ledger ledger = new Ledger(ownerID);

        ledgerInMemoryRepository.createLedger(ownerID);

        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                    monetaryValue, description, dateAndTime, category, account1, null, type);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The accounts cannot be null.");
    }

    @Test
    @DisplayName("Test add Transaction To Ledger - True")
    public void addGroupTransactionToLedger() {
        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        GroupID ownerID = new GroupID(new Description("switch"));

        Ledger ledger = new Ledger(ownerID);

        ledgerInMemoryRepository.createLedger(ownerID);

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Description description = new Description("xpto");
        DateAndTime dateAndTime = new DateAndTime();
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        AccountID account1 = new AccountID(new Denomination("mercearia"),
                new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        Type type = new Type(true);

        ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);

        ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);

        Transaction expectedTransaction = new Transaction(monetaryValue, description, dateAndTime, category, account1, account2, type);

        Transaction expectedTransaction2 = new Transaction(monetaryValue, description, dateAndTime, category, account1, account2, type);


        List<Transaction> expected = Arrays.asList(expectedTransaction, expectedTransaction2);

        //Act
        List<Transaction> result = ledgerInMemoryRepository.findAllTransactionsByLedgerID(ownerID.getDescription());

        //Assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Test add Transaction To Ledger - True")
    public void addPersonTransactionToLedger() {
        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        PersonID ownerID = new PersonID(new Email("morty@gmail.com"));

        Ledger ledger = new Ledger(ownerID);

        ledgerInMemoryRepository.createLedger(ownerID);

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Description description = new Description("xpto");
        DateAndTime dateAndTime = new DateAndTime();
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        AccountID account1 = new AccountID(new Denomination("mercearia"),
                new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        Type type = new Type(true);

        ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);

        ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);

        Transaction expectedTransaction = new Transaction(monetaryValue, description, dateAndTime, category, account1, account2, type);

        Transaction expectedTransaction2 = new Transaction(monetaryValue, description, dateAndTime, category, account1, account2, type);


        List<Transaction> expected = Arrays.asList(expectedTransaction, expectedTransaction2);

        //Act
        List<Transaction> result = ledgerInMemoryRepository.findAllTransactionsByLedgerID(ownerID.getEmail());

        //Assert
        assertEquals(expected, result);
        assertNotNull(result);

    }

    @Test
    @DisplayName("Test add Transaction To Ledger - Exception")
    public void addGroupTransactionToLedgerIDNotFound() {
        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        GroupID ownerID = new GroupID(new Description("switch"));

        Ledger ledger = new Ledger(ownerID);

        ledgerInMemoryRepository.createLedger(ownerID);

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Description description = new Description("xpto");
        DateAndTime dateAndTime = new DateAndTime();
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        AccountID account1 = new AccountID(new Denomination("mercearia"),
                new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        Type type = new Type(true);

        ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);

        ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);


        // Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.findAllTransactionsByLedgerID("switchFake");
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No ledger found with that ID.");
    }

    @Test
    @DisplayName("Test add Transaction To Ledger - Exception")
    public void addPersonTransactionToLedgerIDNotFound() {
        // Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        PersonID ownerID = new PersonID(new Email("morty@email.com"));

        Ledger ledger = new Ledger(ownerID);

        ledgerInMemoryRepository.createLedger(ownerID);

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Description description = new Description("xpto");
        DateAndTime dateAndTime = new DateAndTime();
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        AccountID account1 = new AccountID(new Denomination("mercearia"),
                new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        Type type = new Type(true);

        ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);

        ledgerInMemoryRepository.addTransactionToLedger(ledger.getID(),
                monetaryValue, description, dateAndTime, category, account1, account2, type);


        // Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.findAllTransactionsByLedgerID("emailfalso@fake.pt");

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No ledger found with that ID.");
    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeHappyCaseAll() {

        //Arrange
        String groupDescription = "SWITCH";

        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        GroupID groupID = new GroupID(new Description(groupDescription));

        ledgerInMemoryRepository.createLedger(groupID);

        Category category= new Category (new Denomination ("ISEP"), groupID);

        Account accountFrom = new Account (new Denomination("Pocket Money"),
                new Description("Pocket Money for Superbock"), groupID);
        Account accountTo = new Account(new Denomination("AE ISEP"),
                new Description("AE BAR ISEP") , groupID);

        Transaction transaction1 = new Transaction(new MonetaryValue(10.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 1"),
                new DateAndTime(2020, 3, 4,18, 0),
                category.getID(), accountFrom.getID(), accountTo.getID(), new Type(false));

        Transaction transaction2 = new Transaction(new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 2"),
                new DateAndTime(2020, 3, 4,17, 0),
                category.getID(), accountFrom.getID(), accountTo.getID(), new Type(false));

        Transaction transaction3 = new Transaction(new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 3"),
                new DateAndTime(2020, 3, 4,17, 0),
                category.getID(), accountFrom.getID(), accountTo.getID(), new Type(false));

        ledgerInMemoryRepository.addTransactionToLedger(new LedgerID(groupID),
                new MonetaryValue(10.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 1"),
                new DateAndTime(2020, 3, 4,18, 0), category.getID(),
                accountFrom.getID(), accountTo.getID(), new Type(false));

        ledgerInMemoryRepository.addTransactionToLedger(new LedgerID(groupID),
                new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 2"),
                new DateAndTime(2020, 3, 4,17, 0), category.getID(),
                accountFrom.getID(), accountTo.getID(), new Type(false));

        ledgerInMemoryRepository.addTransactionToLedger(new LedgerID(groupID),
                new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 3"),
                new DateAndTime(2020, 3, 4,17, 0), category.getID(),
                accountFrom.getID(), accountTo.getID(), new Type(false));


        String initialDate = "2020-03-04 16:00";
        String finalDate = "2020-03-05 16:00";

        List<Transaction> expected = Arrays.asList(transaction1, transaction2, transaction3);

        //Act
        List<Transaction> result = ledgerInMemoryRepository.getTransactionsInDateRange(groupID, initialDate, finalDate);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeHappyCaseNotAll() {

        //Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        String groupDescription = "SWITCH";

        GroupID groupID = new GroupID(new Description(groupDescription));

        ledgerInMemoryRepository.createLedger(groupID);

        Category category= new Category (new Denomination ("ISEP"), groupID);

        Account accountFrom = new Account (new Denomination("Pocket Money"),
                new Description("Pocket Money for Superbock"), groupID);
        Account accountTo = new Account(new Denomination("AE ISEP"),
                new Description("AE BAR ISEP") , groupID);

        ledgerInMemoryRepository.addTransactionToLedger(new LedgerID(groupID),
                new MonetaryValue(10.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 1"),
                new DateAndTime(2020, 3, 4,18, 0), category.getID(),
                accountFrom.getID(), accountTo.getID(), new Type(false));

        Transaction transaction2 = new Transaction(new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 2"),
                new DateAndTime(2020, 3, 4,17, 0),
                category.getID(), accountFrom.getID(), accountTo.getID(), new Type(false));

        Transaction transaction3 = new Transaction(new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 3"),
                new DateAndTime(2020, 3, 4,17, 0),
                category.getID(), accountFrom.getID(), accountTo.getID(), new Type(false));

        String initialDate = "2020-03-04 16:00";
        String finalDate = "2020-03-04 17:30";

        ledgerInMemoryRepository.addTransactionToLedger(new LedgerID(groupID),
                new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 2"),
                new DateAndTime(2020, 3, 4,17, 0), category.getID(),
                accountFrom.getID(), accountTo.getID(), new Type(false));

        ledgerInMemoryRepository.addTransactionToLedger(new LedgerID(groupID),
                new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 3"),
                new DateAndTime(2020, 3, 4,17, 0), category.getID(),
                accountFrom.getID(), accountTo.getID(), new Type(false));

        List<Transaction> expected = Arrays.asList(transaction2, transaction3);

        //Act
        List<Transaction> result = ledgerInMemoryRepository.getTransactionsInDateRange(groupID, initialDate, finalDate);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeEmptyLedger() {

        //Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        String personDescription = "bart.simpson@gmail.com";

        PersonID personID = new PersonID(new Email(personDescription));

        ledgerInMemoryRepository.createLedger(personID);


        String initialDate = "2020-03-04 16:00";
        String finalDate = "2020-03-04 17:30";

        List<Transaction> expected = Collections.emptyList();

        //Act
        List<Transaction> result = ledgerInMemoryRepository.getTransactionsInDateRange(personID, initialDate, finalDate);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeInvalidDate() {

        //Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        String groupDescription = "SWITCH";

        GroupID groupID = new GroupID(new Description(groupDescription));

        ledgerInMemoryRepository.createLedger(groupID);

        String initialDate = "2023-04 16:00";
        String finalDate = "2020-03-04 17:30";


        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.getTransactionsInDateRange(groupID, initialDate, finalDate);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The date is not valid.");

    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeNullDate() {

        //Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        String groupDescription = "SWITCH";

        GroupID groupID = new GroupID(new Description(groupDescription));

        ledgerInMemoryRepository.createLedger(groupID);

        String finalDate = "2020-03-04 17:30";


        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.getTransactionsInDateRange(groupID, null, finalDate);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The date is not valid.");

    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeNullID() {

        //Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        String initialDate = "2020-03-04 16:30";
        String finalDate = "2020-03-04 17:30";


        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.getTransactionsInDateRange(null, initialDate, finalDate);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Owner ID can't be null.");

    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeNoIDFound() {

        //Arrange
        LedgerRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        GroupID groupID = new GroupID(new Description("Grupinho dos amigos"));

        String initialDate = "2020-03-04 16:30";
        String finalDate = "2020-03-04 17:30";


        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.getTransactionsInDateRange(groupID, initialDate, finalDate);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                    .hasMessage("No ledger found with that ID.");
        

    }

}