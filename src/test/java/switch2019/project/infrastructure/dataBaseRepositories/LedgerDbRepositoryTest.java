package switch2019.project.infrastructure.dataBaseRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class LedgerDbRepositoryTest {

    @Autowired
    LedgerDbRepository ledgerDbRepository;

    @Autowired
    GroupDbRepository groupDbRepository;

    @Autowired
    PersonDbRepository personDbRepository;

    @Autowired
    AccountDbRepository accountDbRepository;

    @Autowired
    CategoryDbRepository categoryDbRepository;


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
        someGroup = groupDbRepository.createGroup(new Description("Switch Group"), groupCreator.getID());
            someGroup.addMember(groupMember.getID());

        //Create Accounts
        Account someoneAccount1 = accountDbRepository.createAccount(new Denomination("Account1"), new Description("Account 1"), someone.getID());
        Account someoneAccount2 = accountDbRepository.createAccount(new Denomination("Account2"), new Description("Account 2"), someone.getID());

        Account groupCreatorAccount1 = accountDbRepository.createAccount(new Denomination("Account1"), new Description("Account 1"), groupCreator.getID());
        Account groupCreatorAccount2 = accountDbRepository.createAccount(new Denomination("Account2"), new Description("Account 2"), groupCreator.getID());

        //Create Category

        Category someoneCategory = categoryDbRepository.createCategory(new Denomination ("Someone Category"), someone.getID());

        //Create Ledger

        Ledger someoneLedger = ledgerDbRepository.createLedger(someone.getID());

        //Add Transaction To Ledger

        ledgerDbRepository.addTransactionToLedger(someoneLedger, 123456L, new MonetaryValue(2, Currency.getInstance("EUR")),
                new Description("XPTO"), date, someoneCategory.getID(), someoneAccount1.getID(), someoneAccount2.getID(), new Type(true));

    }


    /**
     * Test if ledger is created
     */

    @Test
    @DisplayName("Test if ledger is created - True")
    void createLedger() {

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
    @DisplayName("Test add Transaction to Ledger - True - ledger is also created")
    void addTransactionToLedgerTrue() {

        //Arrange

        OwnerID ledgerID = someGroup.getID();

        Ledger expectedLedger = new Ledger (ledgerID);

        Category someGroupCategory= categoryDbRepository.createCategory(new Denomination ("Some Group Category"), someGroup.getID());
        Account groupAccount1 = accountDbRepository.createAccount(new Denomination("Account1"), new Description("Account 1"), someGroup.getID());
        Account groupAccount2 = accountDbRepository.createAccount(new Denomination("Account2"), new Description("Account 2"), someGroup.getID());

        int expectedNumberOfTransactionsBefore = 9;
        int expectedNumberOfTransactionsAfter = 10;

        //Act
        List<TransactionJpa> realTransactionsBefore = ledgerDbRepository.findAllTransactions();

        int realNumberOfTransactionsBefore = realTransactionsBefore.size();

        boolean transactionAdded = ledgerDbRepository.addTransactionToLedger(expectedLedger, 987654321L,
                new MonetaryValue(5, Currency.getInstance("EUR")), new Description("XPTO"),
                date, someGroupCategory.getID(), groupAccount1.getID(), groupAccount2.getID(), new Type(true));

        Ledger realLedger = ledgerDbRepository.getByID(ledgerID);

        List<TransactionJpa> realTransactionsAfter = ledgerDbRepository.findAllTransactions();

        int realNumberOfTransactionsAfter = realTransactionsAfter.size();

        //Assert
        Assertions.assertAll(
                () -> assertTrue(transactionAdded),
                () -> assertEquals(expectedLedger, realLedger),
                () -> assertEquals(expectedNumberOfTransactionsBefore, realNumberOfTransactionsBefore),
                () -> assertEquals(expectedNumberOfTransactionsAfter, realNumberOfTransactionsAfter)
        );
    }

    /**
     * Test if all transactions can be found by Ledger ID
     */

    @Test
    @DisplayName("Test if all transactions can be found by Ledger ID - Yes")
    void findAllTransactionsByLedgerID() {

        //Arrange

        TransactionJpa expectedTransactionJpa = new TransactionJpa(123456L,"someone_email@gmail.pt",
                2D, "EUR", "XPTO", date.yearMonthDayHourMinuteToString(), "SOMEONE CATEGORY",
                "ACCOUNT1","ACCOUNT2","DEBIT");

        List<TransactionJpa> expectedTransactions = new ArrayList<>(Collections.singletonList(expectedTransactionJpa));

        //Act
        List<TransactionJpa> realTransactions = ledgerDbRepository.findAllTransactionsByLedgerID("someone_email@gmail.pt");

        //Assert

        //assertEquals(expectedTransactions, realTransactions);

    }


    /**
     * Test if all transactions can be found
     */

    @Test
    @DisplayName("Test if all transactions can be found  - Yes")
    void findAllTransactions() {

        //Arrange

        int expectedSize = 9;

        //Act
        List<TransactionJpa> realTransactions = ledgerDbRepository.findAllTransactions();

        int realSize = realTransactions.size();

        //Assert

        assertEquals(expectedSize, realSize);

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
}