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
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.domain.repositories.CategoryRepository;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.utils.StringUtils;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

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
    private CategoryRepository categoryRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private US008CreateTransactionService service;


    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

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

