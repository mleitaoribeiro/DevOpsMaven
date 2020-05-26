package switch2019.project.applicationLayer.unit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.dataModel.dataAssemblers.TransactionDomainDataAssembler;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.*;
import switch2019.project.infrastructure.dataBaseRepositories.AccountDbRepository;
import switch2019.project.utils.StringUtils;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@SpringBootTest
public class US008CreateTransactionServiceUnitTest {

    @Mock
    private LedgerRepository ledgerRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private US008CreateTransactionService service;

    private String groupDescription;
    private String personEmail;
    private double amount;
    private String currency;
    private String date;
    private String description;
    private String category;
    private String accountFrom;
    private String accountTo;
    private String type;

    private MonetaryValue realAmount;
    private Description realDescription;
    private DateAndTime realDate;
    private Type realType;

    private PersonID personID;
    private GroupID groupID;
    private CategoryID categoryID;
    private AccountID accountFromID;
    private AccountID accountToID;

    private Ledger ledger;
    private Transaction transaction;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        //DTO information
        groupDescription = "Switch";
        personEmail = "1191755@isep.ipp.pt";
        amount = 20;
        currency = "EUR";
        date = "2019-05-25 13:12";
        description = "Pizza";
        category = "ISEP";
        accountFrom = "POCKET MONEY";
        accountTo = "AE ISEP";
        type = "DEBIT";

        //Variables needed
        realAmount = new MonetaryValue(amount, Currency.getInstance(currency));
        realDescription = new Description(description);
        realDate = StringUtils.toDateHourMinute(date);
        realType = new Type(false);

        personID = new PersonID (new Email(personEmail));
        groupID = new GroupID (new Description(groupDescription));
        categoryID = new CategoryID (new Denomination(category), groupID);
        accountFromID = new AccountID (new Denomination(accountFrom), groupID);
        accountToID = new AccountID (new Denomination(accountFrom), groupID);

        ledger = new Ledger(groupID);

        transaction = new Transaction(realAmount, realDescription, realDate, categoryID, accountFromID,
                accountToID, realType);
    }


    /**
     * US008.1 - Test if Group Transaction is created
     */

    @Test
    @DisplayName("Test if Group Transaction is created - Happy Case")
    void testIfGroupAccountWasCreatedHappyCase() {

        //Arrange
        //Service DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount, currency, date, description, category, accountFrom, accountTo, type);

        //Mocked repositories
        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)).getID())
                .thenReturn(personID);

        Mockito.when(groupRepository.findGroupByDescription(new Description(groupDescription)).getID())
                .thenReturn(groupID);

        Mockito.when(categoryRepository.getByID(new CategoryID(new Denomination(category), groupID)).getID())
                .thenReturn(categoryID);

        Mockito.when(accountRepository.getByID(new AccountID(new Denomination(accountFrom), groupID)).getID())
                .thenReturn(accountFromID);

        Mockito.when(accountRepository.getByID(new AccountID(new Denomination(accountFrom), groupID)).getID())
                .thenReturn(accountFromID);

        Mockito.when(accountRepository.getByID(new AccountID(new Denomination(accountTo), groupID)).getID())
                .thenReturn(accountToID);

        Mockito.when(ledgerRepository.getByID(groupID))
                .thenReturn(ledger);

        Mockito.when(ledgerRepository.addTransactionToLedger(ledger.getID(), realAmount,
                realDescription, realDate, categoryID, accountFromID, accountToID, realType)).
                thenReturn(transaction);

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
        double amount2 = -20;

        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount2, currency, date, description, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)).getID())
                .thenReturn(personID);

        Mockito.when(groupRepository.findGroupByDescription(new Description(groupDescription)).getID())
                .thenReturn(groupID);

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
        String personEmail2 = "rosa@sapo.pt";

        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail2,
                amount, currency, date, description, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail2)).getID())
                .thenThrow(new ArgumentNotFoundException("No person found with that email."));

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

        PersonID personID = new PersonID(new Email(email));

        TransactionShortDTO transactionDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"),
                "GOLD CARD", "IKEA", "CREDIT", 1L);

        TransactionShortDTO transactionDTO1 = new TransactionShortDTO(50.0,  Currency.getInstance("EUR"),
                "MASTERCARD", "KWIK E MART", "CREDIT", 1L);

        List<TransactionShortDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);

        Transaction transaction = new Transaction(new MonetaryValue(100.00,
                Currency.getInstance("EUR")), new Description("Bought a cheap sofa"), new DateAndTime(2020,
                2, 14, 11, 24), new CategoryID(new Denomination("house"), personID),
                new AccountID(new Denomination("gold card"), personID), new AccountID(new Denomination("ikea"),
                personID), new Type(true));

        Transaction transaction1 = new Transaction(new MonetaryValue(50.00, Currency.getInstance("EUR")),
                new Description("Grocery for baking cookies"), new DateAndTime(2020,
                3, 20, 13, 4), new CategoryID(new Denomination("house"), personID),
                new AccountID(new Denomination("mastercard"), personID), new AccountID(new Denomination("kwik e mart"),
                personID), new Type(true));


        List<Transaction> transactions = Arrays.asList(transaction, transaction1);


        Mockito.when(ledgerRepository.findAllTransactionsByLedgerID(email)).
                thenReturn(transactions);

        //Act
        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(email);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getTransactionsByLedgerIdEmptyLedger() {

        //Arrange
        String email = "bart.simpson@gmail.com";

        List <Transaction> transactions = Collections.emptyList();

        List <TransactionShortDTO> expected = Collections.emptyList();

        Mockito.when(ledgerRepository.findAllTransactionsByLedgerID(email)).
                thenReturn(transactions);

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

        Mockito.when(ledgerRepository.findAllTransactionsByLedgerID(email)).
                thenThrow(new ArgumentNotFoundException("No Ledger found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionsByLedgerId(personID.toString());
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }
}
