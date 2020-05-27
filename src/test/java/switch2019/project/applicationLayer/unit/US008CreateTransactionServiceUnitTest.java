package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.*;
import switch2019.project.utils.StringUtils;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.domain.repositories.CategoryRepository;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.domain.repositories.PersonRepository;
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

    private Person person;
    private PersonID personID;

    private Group group;
    private GroupID groupID;

    private Category newCategory;
    private CategoryID categoryID;

    private Account newAccountFrom;
    private AccountID accountFromID;

    private Account newAccountTo;
    private AccountID accountToID;

    private Ledger ledger;
    private LedgerID ledgerID;

    private Transaction transaction;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        //GROUP INFORMATION
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

        person = new Person(
                "Diana Dias",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191755@isep.ipp.pt"));
        personID = new PersonID (new Email(personEmail));

        group = new Group (new Description("SWitCH"), personID);
        groupID = new GroupID (new Description(groupDescription));

        newCategory = new Category (new Denomination("ISEP"), groupID);
        categoryID = new CategoryID (new Denomination(category), groupID);

        newAccountFrom = new Account(new Denomination("POCKET MONEY"),
                new Description("POCKET MONEY"), groupID);
        accountFromID = new AccountID (new Denomination(accountFrom), groupID);

        newAccountTo = new Account(new Denomination("AE ISEP"),
                new Description("AE ISEP"), groupID);
        accountToID = new AccountID (new Denomination(accountFrom), groupID);

        ledger = new Ledger(groupID);
        ledgerID = new LedgerID(groupID);

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
        Mockito.when(personRepository
                .findPersonByEmail(new Email(createGroupTransactionDTO.getPersonEmail())))
                .thenReturn(person);

        Mockito.when(groupRepository
                .findGroupByDescription(new Description(createGroupTransactionDTO.getGroupDescription())))
                .thenReturn(group);

        Mockito.when(categoryRepository
                .getByID(new CategoryID(new Denomination(createGroupTransactionDTO.getCategory()), groupID)))
                .thenReturn(newCategory);

        Mockito.when(accountRepository.
                getByID(new AccountID(new Denomination(createGroupTransactionDTO.getAccountFrom()), groupID)))
                .thenReturn(newAccountFrom);

        Mockito.when(accountRepository.
                getByID(new AccountID(new Denomination(createGroupTransactionDTO.getAccountTo()), groupID)))
                .thenReturn(newAccountTo);

        Mockito.when(ledgerRepository.getByID(groupID))
                .thenReturn(ledger);

        Mockito.when(ledgerRepository.addTransactionToLedger(ledgerID, realAmount, realDescription, realDate,
                categoryID, accountFromID, accountToID, realType))
                .thenReturn(transaction);

        TransactionShortDTO expectedTransaction = new TransactionShortDTO
                (amount, Currency.getInstance("EUR"), accountFrom, accountTo, type, 1L);

        //Act
        //TransactionShortDTO transactionCreated = service.addGroupTransaction(createGroupTransactionDTO);

        //Assert
        //assertEquals(expectedTransaction, transactionCreated);
    }

    @Test
    @DisplayName("Test if Group Transaction is created - monetary value is negative")
    void testIfGroupAccountWasCreatedMonetaryValueNegative() {

        //Arrange
        double amount2 = -20;
        MonetaryValue realAmount2 = new MonetaryValue(amount, Currency.getInstance(currency));

        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO(groupDescription, personEmail,
                amount2, currency, date, description, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)))
                .thenReturn(person);

        Mockito.when(groupRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(categoryRepository
                .getByID(new CategoryID(new Denomination(createGroupTransactionDTO.getCategory()), groupID)))
                .thenReturn(newCategory);

        Mockito.when(accountRepository.
                getByID(new AccountID(new Denomination(createGroupTransactionDTO.getAccountFrom()), groupID)))
                .thenReturn(newAccountFrom);

        Mockito.when(accountRepository.
                getByID(new AccountID(new Denomination(createGroupTransactionDTO.getAccountTo()), groupID)))
                .thenReturn(newAccountTo);

        Mockito.when(ledgerRepository.getByID(groupID))
                .thenReturn(ledger);

        Mockito.when(ledgerRepository.addTransactionToLedger(ledgerID, realAmount2, realDescription, realDate,
                categoryID, accountFromID, accountToID, realType))
                .thenReturn(transaction);

        //Act
        /*Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The monetary value cannot be negative.");*/
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
        /*Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NoPermissionException.class)
                .hasMessage("This person is not member of this group.");*/
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
        /*Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");*/
    }

    @Test
    @DisplayName("Test if Group Transaction is created - person not found")
    void testIfGroupAccountWasCreatedPersonNotFound() {

        //Arrange
        /*String personEmail2 = "rosa@sapo.pt";

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
                .hasMessage("No person found with that email.");*/
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
        /*Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No category found with that ID.");*/
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
        /*Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");*/
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
        /*Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");*/
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

        /*//Act
        Throwable thrown = catchThrowable(() -> {
            service.addGroupTransaction(createGroupTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");*/
    }

    /**
     * US008 - Test if Personal Transaction is created
     */

    @Test
    @DisplayName("addPersonalTransaction - Happy Case")
    void addPersonalTransactionHappyCase() {

        // Arrange
        String personEmail = "marge@hotmail.com";
        Double amount = 10.50;
        Currency currency = Currency.getInstance("EUR");
        String description = "beers";
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        Person person = new Person("Marjorie Bouvier Simpson",
                new DateAndTime(1956, 5, 12), new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                new Email("marge@hotmail.com"));

        PersonID personID = new PersonID(new Email(personEmail));
        Ledger ledger = new Ledger(personID);
        LedgerID ledgerID = new LedgerID(personID);

        MonetaryValue monetaryValue = new MonetaryValue(amount, currency);
        Description description1Object = new Description(description);
        DateAndTime dateAndTime = StringUtils.toDateHourMinute(date);
        CategoryID categoryID = new CategoryID(new Denomination(category), personID);
        AccountID accountFromID = new AccountID(new Denomination(accountFrom), personID);
        AccountID accountToID = new AccountID(new Denomination(accountTo), personID);
        Type typeObject = new Type(false);

        Category categoryObject = new Category(new Denomination(category), personID);
        Account accountFromObject = new Account(new Denomination(accountFrom),
                new Description("For daily expenses"), personID);
        Account accountToObject = new Account(new Denomination(accountTo),
                new Description("Money spent on snacks for homer"), personID);

        Transaction transaction = new Transaction(monetaryValue, description1Object, dateAndTime,
                categoryID, accountFromID, accountToID, typeObject);

        TransactionShortDTO transactionShortDTOExpected =
                new TransactionShortDTO(amount, currency, accountFrom.toUpperCase(),
                        accountTo.toUpperCase(), type.toUpperCase(), 1L);

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency.toString(), description, date, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail))).thenReturn(person);
        Mockito.when(ledgerRepository.getByID(personID)).thenReturn(ledger);
        Mockito.when(categoryRepository.getByID(categoryID)).thenReturn(categoryObject);
        Mockito.when(accountRepository.getByID(accountFromID)).thenReturn(accountFromObject);
        Mockito.when(accountRepository.getByID(accountToID)).thenReturn(accountToObject);
        Mockito.when(ledgerRepository.addTransactionToLedger(ledgerID, monetaryValue, description1Object,
                dateAndTime, categoryID, accountFromID, accountToID, typeObject)).thenReturn(transaction);

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
        Currency currency = Currency.getInstance("EUR");
        String description = "beers";
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency.toString(), description, date, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)))
                .thenThrow(new IllegalArgumentException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("addPersonalTransaction - Negative monetary value")
    void addPersonalTransactionNegativeMonetaryValue() {

        // Arrange
        String personEmail = "marge@hotmail.com";
        double amount = -10.50;
        Currency currency = Currency.getInstance("EUR");
        String description = "beers";
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        Person person = new Person("Marjorie Bouvier Simpson",
                new DateAndTime(1956, 5, 12), new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                new Email("marge@hotmail.com"));

        PersonID personID = new PersonID(new Email(personEmail));
        Ledger ledger = new Ledger(personID);
        LedgerID ledgerID = new LedgerID(personID);

        MonetaryValue monetaryValue = new MonetaryValue(amount, currency);
        Description description1Object = new Description(description);
        DateAndTime dateAndTime = StringUtils.toDateHourMinute(date);
        CategoryID categoryID = new CategoryID(new Denomination(category), personID);
        AccountID accountFromID = new AccountID(new Denomination(accountFrom), personID);
        AccountID accountToID = new AccountID(new Denomination(accountTo), personID);
        Type typeObject = new Type(false);

        Category categoryObject = new Category(new Denomination(category), personID);
        Account accountFromObject = new Account(new Denomination(accountFrom),
                new Description("For daily expenses"), personID);
        Account accountToObject = new Account(new Denomination(accountTo),
                new Description("Money spent on snacks for homer"), personID);

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency.toString(), description, date, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail))).thenReturn(person);
        Mockito.when(ledgerRepository.getByID(personID)).thenReturn(ledger);
        Mockito.when(categoryRepository.getByID(categoryID)).thenReturn(categoryObject);
        Mockito.when(accountRepository.getByID(accountFromID)).thenReturn(accountFromObject);
        Mockito.when(accountRepository.getByID(accountToID)).thenReturn(accountToObject);
        Mockito.when(ledgerRepository.addTransactionToLedger(ledgerID, monetaryValue, description1Object,
                dateAndTime, categoryID, accountFromID, accountToID, typeObject))
                .thenThrow(new IllegalArgumentException("The monetary value cannot be negative."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        //Assert
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
        Currency currency = Currency.getInstance("EUR");
        String description = null;
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        Person person = new Person("Marjorie Bouvier Simpson",
                new DateAndTime(1956, 5, 12), new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                new Email("marge@hotmail.com"));

        PersonID personID = new PersonID(new Email(personEmail));
        Ledger ledger = new Ledger(personID);
        LedgerID ledgerID = new LedgerID(personID);

        MonetaryValue monetaryValue = new MonetaryValue(amount, currency);
        DateAndTime dateAndTime = StringUtils.toDateHourMinute(date);
        CategoryID categoryID = new CategoryID(new Denomination(category), personID);
        AccountID accountFromID = new AccountID(new Denomination(accountFrom), personID);
        AccountID accountToID = new AccountID(new Denomination(accountTo), personID);
        Type typeObject = new Type(false);

        Category categoryObject = new Category(new Denomination(category), personID);
        Account accountFromObject = new Account(new Denomination(accountFrom),
                new Description("For daily expenses"), personID);
        Account accountToObject = new Account(new Denomination(accountTo),
                new Description("Money spent on snacks for homer"), personID);

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency.toString(), description, date, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail))).thenReturn(person);
        Mockito.when(ledgerRepository.getByID(personID)).thenReturn(ledger);
        Mockito.when(categoryRepository.getByID(categoryID)).thenReturn(categoryObject);
        Mockito.when(accountRepository.getByID(accountFromID)).thenReturn(accountFromObject);
        Mockito.when(accountRepository.getByID(accountToID)).thenReturn(accountToObject);
        Mockito.when(ledgerRepository.addTransactionToLedger(ledgerID, monetaryValue,
                null, dateAndTime, categoryID, accountFromID, accountToID, typeObject))
                .thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        //Assert
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
        Currency currency = Currency.getInstance("EUR");
        String description = "beers";
        String date = "2020-05-25 15:50";
        String category = "WORK STUFF";
        String accountFrom = "MasterCard";
        String accountTo = "Homer Snacks";
        String type = "debit";

        Person person = new Person("Marjorie Bouvier Simpson",
                new DateAndTime(1956, 5, 12), new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                new Email("marge@hotmail.com"));

        PersonID personID = new PersonID(new Email(personEmail));
        Ledger ledger = new Ledger(personID);
        CategoryID categoryID = new CategoryID(new Denomination(category), personID);

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency.toString(), description, date, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail))).thenReturn(person);
        Mockito.when(ledgerRepository.getByID(personID)).thenReturn(ledger);
        Mockito.when(categoryRepository.getByID(categoryID))
                .thenThrow(new ArgumentNotFoundException("No category found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        //Assert
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
        Currency currency = Currency.getInstance("EUR");
        String description = "beers";
        String date = "2020-05-25 15:50";
        String category = "HOUSE";
        String accountFrom = "MasterCard";
        String accountTo = "Game Shop";
        String type = "debit";

        Person person = new Person("Marjorie Bouvier Simpson",
                new DateAndTime(1956, 5, 12), new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                new Email("marge@hotmail.com"));

        PersonID personID = new PersonID(new Email(personEmail));
        Ledger ledger = new Ledger(personID);

        CategoryID categoryID = new CategoryID(new Denomination(category), personID);
        AccountID accountFromID = new AccountID(new Denomination(accountFrom), personID);
        AccountID accountToID = new AccountID(new Denomination(accountTo), personID);

        Category categoryObject = new Category(new Denomination(category), personID);
        Account accountFromObject = new Account(new Denomination(accountFrom),
                new Description("For daily expenses"), personID);
        Account accountToObject = new Account(new Denomination(accountTo),
                new Description("Money spent on snacks for homer"), personID);

        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO
                (personEmail, amount, currency.toString(), description, date, category, accountFrom, accountTo, type);

        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail))).thenReturn(person);
        Mockito.when(ledgerRepository.getByID(personID)).thenReturn(ledger);
        Mockito.when(categoryRepository.getByID(categoryID)).thenReturn(categoryObject);
        Mockito.when(accountRepository.getByID(accountFromID)).thenReturn(accountFromObject);
        Mockito.when(accountRepository.getByID(accountToID))
                .thenThrow(new ArgumentNotFoundException("No account found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addPersonalTransaction(createPersonalTransactionDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
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


        Mockito.when(ledgerRepository.getTransactionByID(email,id)).
                thenReturn(expectedTransaction);


        TransactionDTO transactionDTOexpected = new TransactionDTO
                (50.0, Currency.getInstance("EUR"), "GROCERY FOR BAKING COOKIES",
                        "2020-03-20 13:04", "HOUSE",
                        "MASTERCARD", "KWIK E MART", "DEBIT");

        //Act
        TransactionDTO result = service.getTransactionByID(email, id);

        //Assert
        assertEquals(transactionDTOexpected, result);
        }*/



}

