package switch2019.project.infrastructure.dataBaseRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.*;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LedgerDbRepositoryTest {

    @Autowired
    LedgerRepository ledgerDbRepository;

    @Autowired
    GroupRepository groupDbRepository;

    @Autowired
    PersonRepository personDbRepository;

    @Autowired
    AccountRepository accountDbRepository;

    @Autowired
    CategoryRepository categoryDbRepository;


    private Person someone;
    private Person groupCreator;
    private Person groupMember;
    private Group someGroup;
    private final DateAndTime date = new DateAndTime(2020, 2, 4, 10, 1);


    @BeforeEach
    public void setup() {

        //Create Persons
        someone = personDbRepository.createPerson("Someone", new DateAndTime(1993, 3, 1),
                new Address("Porto"),new Address("Rua 1", "Porto", "4620-580"), new Email("someone_email@gmail.pt"));

        groupCreator = personDbRepository.createPerson("Someone", new DateAndTime(1993, 3, 1),
                new Address("Porto"),new Address("Rua 2", "Porto", "4620-580"), new Email("groupCreator@gmail.pt"));

        groupMember = personDbRepository.createPerson("Someone", new DateAndTime(1993, 3, 1),
                new Address("Porto"),new Address("Rua 3", "Porto", "4620-580"), new Email("groupMember@gmail.pt"));

        //Create Group
        someGroup = groupDbRepository.createGroup(new Description("Some Group"), groupCreator.getID());
            someGroup.addMember(groupMember.getID());

        //Create Accounts
        Account someoneAccount1 = accountDbRepository.createAccount(new Denomination("Account1"), new Description("Account 1"), someone.getID());
        Account someoneAccount2 = accountDbRepository.createAccount(new Denomination("Account2"), new Description("Account 2"), someone.getID());

        accountDbRepository.createAccount(new Denomination("Account1"), new Description("Account 1"), groupCreator.getID());
        accountDbRepository.createAccount(new Denomination("Account2"), new Description("Account 2"), groupCreator.getID());

        //Create Category

        Category someoneCategory = categoryDbRepository.createCategory(new Denomination ("Someone Category"), someone.getID());

        //Create Ledger

        Ledger someoneLedger = ledgerDbRepository.createLedger(someone.getID());

        //Add Transaction To Ledger

        ledgerDbRepository.addTransactionToLedger(someoneLedger.getID(), new MonetaryValue(2, Currency.getInstance("EUR")),
                new Description("XPTO"), date, someoneCategory.getID(), someoneAccount1.getID(), someoneAccount2.getID(), new Type(true));
    }


    /**
     * Test if ledger is created
     */

    @Test
    @DisplayName("Test if ledger is created - True")
    void createLedgerTrue() {

        //Arrange

        Ledger expectedLedger = new Ledger (groupCreator.getID());

        //Act
        Ledger realLedger = ledgerDbRepository.createLedger(groupCreator.getID());

        //Assert
        assertEquals(expectedLedger, realLedger);

    }

    /**
     * Test if Transaction is added to Ledger
     */

    @Test
    @DisplayName("Test add Transaction to Ledger - True - No ledger in Repo")
    void addTransactionToLedgerTrue() {

        //Arrange

        OwnerID ledgerID = someGroup.getID();

        Ledger expectedLedger = new Ledger (ledgerID);

        Category someGroupCategory= categoryDbRepository.createCategory(new Denomination ("Some Group Category"), someGroup.getID());
        Account groupAccount1 = accountDbRepository.createAccount(new Denomination("Account1"), new Description("Account 1"), someGroup.getID());
        Account groupAccount2 = accountDbRepository.createAccount(new Denomination("Account2"), new Description("Account 2"), someGroup.getID());

        Transaction expectedTransaction = new Transaction(new MonetaryValue(5, Currency.getInstance("EUR")), new Description("XPTO"),
                date, someGroupCategory.getID(), groupAccount1.getID(), groupAccount2.getID(), new Type(true));

        //Act
                Transaction transactionAdded = ledgerDbRepository.addTransactionToLedger(expectedLedger.getID(),
                new MonetaryValue(5, Currency.getInstance("EUR")), new Description("XPTO"),
                date, someGroupCategory.getID(), groupAccount1.getID(), groupAccount2.getID(), new Type(true));

        Ledger realLedger = ledgerDbRepository.getByID(ledgerID);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedTransaction,transactionAdded),
                () -> assertEquals(expectedLedger, realLedger)
        );
    }


    @Test
    @DisplayName("Test add Transaction to Ledger - True - Ledger in Repo")
    void addTransactionToLedgerLedgerIsInRepo() {

        //Arrange

        OwnerID ledgerID = someone.getID();

        Ledger expectedLedger = new Ledger (ledgerID);

        Category category= categoryDbRepository.createCategory(new Denomination ("Some Category"), someone.getID());
        Account account1 = accountDbRepository.createAccount(new Denomination("SomeAccount1"), new Description("Some Account 1"), someone.getID());
        Account account2 = accountDbRepository.createAccount(new Denomination("SomeAccount2"), new Description("Some Account 2"), someone.getID());

        Transaction expectedTransaction = new Transaction(new MonetaryValue(5, Currency.getInstance("EUR")), new Description("XPTO"),
                date, category.getID(), account1.getID(), account2.getID(), new Type(true));

        //Act
        Transaction transactionAdded = ledgerDbRepository.addTransactionToLedger(expectedLedger.getID(),
                new MonetaryValue(5, Currency.getInstance("EUR")), new Description("XPTO"),
                        date, category.getID(), account1.getID(), account2.getID(), new Type(true));

        Ledger realLedger = ledgerDbRepository.getByID(ledgerID);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedTransaction,transactionAdded),
                () -> assertEquals(expectedLedger, realLedger)
        );
    }

    /**
     * Test if all transactions can be found by Ledger ID
     */

    @Test
    @DisplayName("Test if all transactions can be found by Ledger ID - Yes")
    void findAllTransactionsByLedgerID() {

        //Arrange

        Category someoneCategory = new Category (new Denomination ("Someone Category"), someone.getID());
        Account someoneAccount1 = new Account(new Denomination("Account1"), new Description("Account 1"), someone.getID());
        Account someoneAccount2 = new Account(new Denomination("Account2"), new Description("Account 2"), someone.getID());


        Transaction expectedTransaction = new Transaction(new MonetaryValue(2, Currency.getInstance("EUR")),
                new Description("XPTO"), date, someoneCategory.getID(), someoneAccount1.getID(), someoneAccount2.getID(), new Type(true));

        List <Transaction> expectedTransactionsList = new ArrayList<>(Collections.singletonList(expectedTransaction));

        int expectedNumberOfTransactions = 1;

        //Act
        List<Transaction> realTransactionsList = ledgerDbRepository.findAllTransactionsByLedgerID("someone_email@gmail.pt");

        int realNumberOfTransactions = realTransactionsList.size();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedTransactionsList,realTransactionsList),
                () -> assertEquals(expectedNumberOfTransactions,realNumberOfTransactions)
        );

    }




    @Test
    @DisplayName("Test if all transactions can be found by Ledger ID - NO - GroupID")
    void findAllTransactionsByLedgerID_False() {

        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.findAllTransactionsByLedgerID("Some Group Description");;
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");

    }

    /**
     * Test if a Ledger can be found by it´s ID
     */

    @Test
    @DisplayName("Test if a Ledger can be found by it´s ID - Ledger found")
    void getByIDYes() {

        //Arrange

        OwnerID ledgerID = someone.getID();

        Ledger expectedLedger = new Ledger (someone.getID());

        //Act
        Ledger realLedger = ledgerDbRepository.getByID(ledgerID);

        //Assert
        assertEquals(expectedLedger, realLedger);

    }


    @Test
    @DisplayName("Test if a Ledger can be found by it´s ID - Ledger not found")
    void getByIDNo() {

        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getByID(groupMember.getID());
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");

    }

    /**
     * Tests to get the transaction by it´s ID
     */

    @Test
    @DisplayName("Test to get Transaction by it's ID - Personal Transaction - Happy Case")
    void getTransactionByIDPersonalTransactionHappyCase() {

        //Arrange
        String email = "marge@hotmail.com";
        Long id = 2L;

        PersonID personID = new PersonID(new Email(email));

        Category category= new Category (new Denomination ("HOUSE"), personID );

        Account accountFrom = new Account (new Denomination("MASTERCARD"),
                new Description("For daily expenses"), personID);
        Account accountTo = new Account(new Denomination("Kwik-E-Mart"),
                new Description("Food and Grocery") , personID);

        Transaction expectedTransaction = new Transaction(new MonetaryValue(50.0, Currency.getInstance("EUR")),
                new Description("GROCERY FOR BAKING COOKIES"),
                new DateAndTime(2020,03,20,13,04),
                category.getID(), accountFrom.getID(), accountTo.getID(), new Type(false));

        //Act
        Transaction transaction = ledgerDbRepository.getTransactionByID(email,id);

        //Assert
        assertEquals(expectedTransaction, transaction);

    }


    @Test
    @DisplayName("Test to get Transaction by it's ID - Personal Transaction - No Permission")
    void getTransactionByIDPersonalTransactionNoPermission() {

        //Arrange
        String email = "summer@gmail.com";
        Long id = 2L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getTransactionByID(email, id);
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NoPermissionException.class)
                .hasMessage("No permission.");

    }



    @Test
    @DisplayName("Get Transaction By ID - PersonLedger - No Transaction Found")
    void getTransactionByIDPersonLedgerNoTransactionFound() {

        //Arrange
        String email = "marge@hotmail.com";
        Long id = 20L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getTransactionByID(email, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No transaction found with that ID.");

    }

    @Test
    @DisplayName("Get Transaction By ID - GroupLedger - happy case")
    void getTransactionByIDGroupLedgerHappyCase() {

        //Arrange
        String groupDescription = "SWITCH";
        Long id = 7L;

        GroupID groupID = new GroupID(new Description(groupDescription));

        Category category= new Category (new Denomination ("ISEP"), groupID);

        Account accountFrom = new Account (new Denomination("Pocket Money"),
                new Description("Pocket Money for Superbock"), groupID);
        Account accountTo = new Account(new Denomination("AE ISEP"),
                new Description("AE BAR ISEP") , groupID);

        Transaction expectedTransaction = new Transaction(new MonetaryValue(20.0, Currency.getInstance("EUR")),
                new Description("SUPERBOCK ROUND 2"),
                new DateAndTime(2020,03,04,17,00),
                category.getID(), accountFrom.getID(), accountTo.getID(), new Type(false));

        //Act
        Transaction result = ledgerDbRepository.getTransactionByID(groupDescription, id);

        //Assert
        assertEquals(expectedTransaction, result);
    }

    @Test
    @DisplayName("Get Transaction By ID - GroupLedger - No Permission")
    void getTransactionByIDGroupLedgerNoPermission() {

        //Arrange
        String groupDescription = "SWITCH";
        Long id = 2L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getTransactionByID(groupDescription, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NoPermissionException.class)
                .hasMessage("No permission.");
    }


    @Test
    @DisplayName("Get Transaction By ID - GroupLedger - No Transaction Found")
    void getTransactionByIDGroupLedgerNoTransactionFound() {

        //Arrange
        String groupDescription = "SWITCH";
        Long id = 20L;

        // Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getTransactionByID(groupDescription, id);

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No transaction found with that ID.");

    }




    /**
     * Test if the ledger is in the ledger Repository
     */

    @Test
    @DisplayName("Test if the ledger is in the ledger Repository - Yes")
    void isIDOnRepositoryTrue() {

        //Arrange

        OwnerID ledgerID = someone.getID();

        //Act
        boolean ledgerIsInTheRepository = ledgerDbRepository.isIDOnRepository(ledgerID);

        //Assert
        assertTrue(ledgerIsInTheRepository);

    }


    @Test
    @DisplayName("Test if the ledger is in the ledger Repository - No")
    void isIDOnRepositoryFalse() {

        //Arrange

        OwnerID ledgerID = groupMember.getID();

        //Act
        boolean ledgerIsInTheRepository = ledgerDbRepository.isIDOnRepository(ledgerID);

        //Assert
        assertFalse(ledgerIsInTheRepository);

    }


    /**
     * Test if the number of Ledgers in the Repository is as expected
     */

    @Test
    @DisplayName("Test if the number of Ledgers in the Repository is as expected")
    void repositorySize() {

        //Arrange
        long expected = 49L;

        //Act
        long real = ledgerDbRepository.repositorySize();

        //Assert
        assertEquals(expected, real);
    }


    /**
     * Test to get the Transactions of a Ledger in a given Date Range
     */

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeHappyCaseAll() {

        //Arrange
        String groupDescription = "SWITCH";

        GroupID groupID = new GroupID(new Description(groupDescription));

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

        String initialDate = "2020-03-04 16:00";
        String finalDate = "2020-03-05 16:00";

        List<Transaction> expected = Arrays.asList(transaction1, transaction2, transaction3);

        //Act
        List<Transaction> result = ledgerDbRepository.getTransactionsInDateRange(groupID, initialDate, finalDate);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeHappyCaseNotAll() {

        //Arrange
        String groupDescription = "SWITCH";

        GroupID groupID = new GroupID(new Description(groupDescription));

        Category category= new Category (new Denomination ("ISEP"), groupID);

        Account accountFrom = new Account (new Denomination("Pocket Money"),
                new Description("Pocket Money for Superbock"), groupID);
        Account accountTo = new Account(new Denomination("AE ISEP"),
                new Description("AE BAR ISEP") , groupID);


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

        List<Transaction> expected = Arrays.asList(transaction2, transaction3);

        //Act
        List<Transaction> result = ledgerDbRepository.getTransactionsInDateRange(groupID, initialDate, finalDate);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeEmptyLedger() {

        //Arrange
        String personDescription = "bart.simpson@gmail.com";

        PersonID personID = new PersonID(new Email(personDescription));


        String initialDate = "2020-03-04 16:00";
        String finalDate = "2020-03-04 17:30";

        List<Transaction> expected = Collections.emptyList();

        //Act
        List<Transaction> result = ledgerDbRepository.getTransactionsInDateRange(personID, initialDate, finalDate);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Transactions In Date Range - happy case")
    void getTransactionsInDateRangeInvalidDate() {

        //Arrange
        String groupDescription = "SWITCH";

        GroupID groupID = new GroupID(new Description(groupDescription));

        String initialDate = "2023-04 16:00";
        String finalDate = "2020-03-04 17:30";


        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getTransactionsInDateRange(groupID, initialDate, finalDate);
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
        String groupDescription = "SWITCH";

        GroupID groupID = new GroupID(new Description(groupDescription));

        String finalDate = "2020-03-04 17:30";


        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getTransactionsInDateRange(groupID, null, finalDate);
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

        String initialDate = "2020-03-04 16:30";
        String finalDate = "2020-03-04 17:30";


        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getTransactionsInDateRange(null, initialDate, finalDate);
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

        GroupID groupID = new GroupID(new Description("Grupinho dos amigos"));

        String initialDate = "2020-03-04 16:30";
        String finalDate = "2020-03-04 17:30";


        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerDbRepository.getTransactionsInDateRange(groupID, initialDate, finalDate);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");

    }

}