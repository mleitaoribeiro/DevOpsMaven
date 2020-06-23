package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class US006CreatePersonAccountServiceUnitTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AccountRepository accountRepository;


    @InjectMocks
    private US006CreatePersonAccountService service;


    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test If Person Account is created - Happy Case - Main Scenario")
    void testIfPersonAccountWasCreatedHappyCase() {

        //Arrange
        String creatorEmail = "marge@hotmail.com";
        String accountDenomination = "Homer Snacks";
        String accountDescription = "Money spent on snacks for homer";

        Person creator = new Person("Marge", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("marge@hotmail.com"));

        Account account = new Account(new Denomination(accountDenomination), new Description(accountDescription), new PersonID(new Email("marge@hotmail.com")));

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(creatorEmail, accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new PersonID(new Email(creatorEmail)))).thenReturn(account);

        AccountDTO expected = new AccountDTO(creatorEmail, accountDenomination, accountDescription);

        //Act
        AccountDTO accountCreated = service.createPersonAccount(createPersonAccountDTO);

        //Assert
        assertEquals(expected, accountCreated);
    }

    @Test
    @DisplayName("Test If Person Account is created - Exception - Person doesn't exist on Person Repository")
    void testIfPersonAccountWasCreatedPersonDoesntExistsOnRepo() {

        //Arrange
        String creatorEmail = "fake@gmail.com";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";


        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(creatorEmail, accountDenomination, accountDescription);


        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenThrow(new ArgumentNotFoundException("No person found with that email."));


        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new PersonID(new Email(creatorEmail)))).thenThrow(new ArgumentNotFoundException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createPersonAccount(createPersonAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }


    /**
     * Test If person Account is created - Several accounts added - Null & Empty Values
     */

    @Test
    @DisplayName("Test If person Account is created - Exception - Person email is null")
    void testIfPersonAccountWasCreatedPersonIDNull() {

        //Arrange

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(null, accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(null))
                .thenThrow(new IllegalArgumentException("The email can't be null."));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                null)).thenThrow(new IllegalArgumentException("The email can't be null."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createPersonAccount(createPersonAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }

    @Test
    @DisplayName("Test If person Account is created - Exception - Account denomination is null")
    void testIfPersonAccountWasCreatedAccountDenominationNull() {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(creatorEmail, null, accountDescription);
        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(accountRepository.createAccount(null, new Description(accountDescription),
                new PersonID(new Email(creatorEmail)))).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createPersonAccount(createPersonAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test If person Account is created - Exception - Account description is null")
    void testIfPersonAccountWasCreatedAccountDescriptionNull() {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String accountDenomination = "Online";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(creatorEmail, "online", null);
        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(accountRepository.createAccount(new Denomination("online"), null,
                new PersonID(new Email(creatorEmail)))).thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createPersonAccount(createPersonAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test If person Account is created - Exception - Account already exists")
    void testIfPersonAccountWasCreatedAccountAlreadyExists() {

        //Arrange
        String creatorEmail = "1191778@isep.ipp.pt";
        String accountDenomination = "Mbway";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(creatorEmail, accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new PersonID(new Email(creatorEmail)))).
                thenThrow(new ResourceAlreadyExistsException("This account already exists."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createPersonAccount(createPersonAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("This account already exists.");
    }

    /**
     * Test if an Account can be found by the ID
     */

    @Test
    @DisplayName("Test if an Account can be found by the ID - Happy Case")
    void getAccountyByAccountID() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));
        PersonID personID = person.getID();

        Denomination accountDenomination = new Denomination("Revolut");
        Description accountDescription = new Description("Revolut Account");
        Account account = new Account(accountDenomination, accountDescription, personID);

        //arranging mockitos
        Mockito.when(personRepository.findPersonByEmail(new Email("rick@gmail.com"))).thenReturn(person);

        Mockito.when(accountRepository.getByID(account.getID())).thenReturn(account);

        //DTO expected
        AccountDTO accountDTOExpected = new AccountDTO("rick@gmail.com", "Revolut", "Revolut Account");

        //Act
        AccountDTO accountDTOResult = service.getAccountByAccountID("Revolut", "rick@gmail.com");

        //Assert
        assertEquals(accountDTOExpected, accountDTOResult);

    }


    @Test
    @DisplayName("Test if an Account can be found by the ID - Person Does Not Exists")
    void getAccountByAccountIDPersonDoesNotExists() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));
        PersonID personID = person.getID();

        Denomination accountDenomination = new Denomination("Revolut");
        Description accountDescription = new Description("Revolut Account");
        Account account = new Account(accountDenomination, accountDescription, personID);

        //arranging mockitos
        Mockito.when(personRepository.findPersonByEmail(new Email("NOT_EXISTING_PERSON@GMAIL.COM"))).thenThrow(new ArgumentNotFoundException("No person found with that email."));

        Mockito.when(accountRepository.getByID(account.getID())).thenReturn(account);

        //Act

        Throwable thrown = catchThrowable(() -> {
            service.getAccountByAccountID("Revolut", "NOT_EXISTING_PERSON@GMAIL.COM");
        });


        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");

    }


    @Test
    @DisplayName("Test if an Account can be found by the ID - Account Does Not Exists")
    void getAccountByAccountIDAccountDoesNotExists() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));
        PersonID personID = person.getID();

        AccountID accountID = new AccountID(new Denomination("notExistingAccount"), personID);

        //arranging mockitos
        Mockito.when(personRepository.findPersonByEmail(new Email("rick@gmail.com"))).thenReturn(person);

        Mockito.when(accountRepository.getByID(accountID)).thenThrow(new ArgumentNotFoundException("No account found with that ID."));

        //Act

        Throwable thrown = catchThrowable(() -> {
            service.getAccountByAccountID("notExistingAccount", "rick@gmail.com");
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");

    }


    @Test
    @DisplayName("Test if an Account can be found by the ID - Invalid Email")
    void getAccountByAccountIDInvalidEmail() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));
        PersonID personID = person.getID();

        Denomination accountDenomination = new Denomination("Revolut");
        Description accountDescription = new Description("Revolut Account");
        Account account = new Account(accountDenomination, accountDescription, personID);

        //Act

        Throwable thrown = catchThrowable(() -> {
            Mockito.when(personRepository.findPersonByEmail(new Email("invalid_email@@GMAIL.COM"))).thenThrow(new IllegalArgumentException("The email is not valid."));
            Mockito.when(accountRepository.getByID(account.getID())).thenReturn(account);
            service.getAccountByAccountID("Revolut", "invalid_email@@GMAIL.COM");
        });


        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");

    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Email Null")
    void getAccountByAccountIDEmailNull() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));
        PersonID personID = person.getID();

        Denomination accountDenomination = new Denomination("Revolut");
        Description accountDescription = new Description("Revolut Account");
        Account account = new Account(accountDenomination, accountDescription, personID);

        Mockito.when(personRepository.findPersonByEmail(null)).thenThrow(new IllegalArgumentException("The email can't be null."));

        Mockito.when(accountRepository.getByID(account.getID())).thenReturn(account);

        //Act
        Throwable thrown = catchThrowable(() -> {

            service.getAccountByAccountID("Revolut", null);
        });


        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");

    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Email Empty")
    void getAccountByAccountIDEmailEmpty() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));
        PersonID personID = person.getID();

        Denomination accountDenomination = new Denomination("Revolut");
        Description accountDescription = new Description("Revolut Account");
        Account account = new Account(accountDenomination, accountDescription, personID);

        //Act
        Throwable thrown = catchThrowable(() -> {
            Mockito.when(personRepository.findPersonByEmail(new Email(""))).thenThrow(new IllegalArgumentException("The email is not valid."));
            Mockito.when(accountRepository.getByID(account.getID())).thenReturn(account);
            service.getAccountByAccountID("Revolut", "invalid_email@@GMAIL.COM");
            service.getAccountByAccountID("Revolut", null);
        });


        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");

    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Denomination Null")
    void getAccountByAccountIDDenominationNull() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));

        Mockito.when(personRepository.findPersonByEmail(new Email("rick@gmail.com"))).thenReturn(person);

        //Act
        Throwable thrown = catchThrowable(() -> {

            service.getAccountByAccountID(null, "rick@gmail.com");
        });


        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");

    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Denomination Empty")
    void getAccountByAccountIDDenominationEmpty() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));
        PersonID personID = person.getID();

        Mockito.when(personRepository.findPersonByEmail(new Email("rick@gmail.com"))).thenReturn(person);

        //Act
        Throwable thrown = catchThrowable(() -> {
            Mockito.when(accountRepository.getByID(new AccountID(new Denomination(""), personID))).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));
            service.getAccountByAccountID("", "rick@gmail.com");
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");

    }

    /**
     * Test to get all accounts from personID
     */

    @Test
    @DisplayName("Test to get all accounts by personID - Happy Case")
    void getAccountsByPersonID() {

        //Arrange
        String personEmail = "1191782@isep.ipp.pt";
        Person person = new Person("Raquel Santos", new DateAndTime(1993, 9, 1),
                new Address("Porto"), new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191782@isep.ipp.pt"));
        PersonID personID = person.getID();

        //Arrange - Accounts
        Account account1 = new Account(new Denomination("Mbway"), new Description("Friends"), personID);
        Account account2 = new Account(new Denomination("CTT"), new Description("Work"), personID);
        Account account3 = new Account(new Denomination("Home"), new Description("Home Expenses"), personID);

        Set<Account> expectedAccounts = new LinkedHashSet<>();
        expectedAccounts.add(account1);
        expectedAccounts.add(account2);
        expectedAccounts.add(account3);

        //Arrange - AccountsDTO
        AccountDTO accountDTO1 = new AccountDTO(personEmail, "Mbway", "Friends");
        AccountDTO accountDTO2 = new AccountDTO(personEmail, "CTT", "Work");
        AccountDTO accountDTO3 = new AccountDTO(personEmail, "Home", "Home Expenses");

        Set<AccountDTO> expectedDTOAccounts = new LinkedHashSet<>();
        expectedDTOAccounts.add(accountDTO1);
        expectedDTOAccounts.add(accountDTO2);
        expectedDTOAccounts.add(accountDTO3);

        //Arrange - Mockitos
        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail))).thenReturn(person);
        Mockito.when(accountRepository.returnAccountsByOwnerID(personID)).thenReturn(expectedAccounts);

        //Act
        Set<AccountDTO> accountsDTOResult = service.getAccountsByPersonID(personEmail);

        //Assert
        assertEquals(expectedDTOAccounts, accountsDTOResult);

    }

    @Test
    @DisplayName("Test to get all accounts by personID - Person Does Not Exists")
    void getAccountsByPersonIDPersonDoesNotExists() {

        //Arrange
        String notExistingPersonEmail = "not_existing_person@gmail.com";

        //Arrange - Mockitos
        Mockito.when(personRepository.findPersonByEmail(new Email(notExistingPersonEmail)))
                .thenThrow(new ArgumentNotFoundException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getAccountsByPersonID(notExistingPersonEmail);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");

    }

    @Test
    @DisplayName("Test to get all accounts by personID - Accounts Not Found")
    void getAccountsByPersonIDAccountsNotFound() {

        //Arrange
        String personEmail = "1191755@isep.ipp.pt";
        Person person = new Person("Diana Dias", new DateAndTime(1993, 9, 1),
                new Address("Porto"), new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191755@isep.ipp.pt"));
        PersonID personID = person.getID();

        Set<Account> expectedAccounts = new LinkedHashSet<>();


        //Arrange - Mockitos
        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)))
                .thenThrow(new ArgumentNotFoundException("No accounts found with that ID."));
        Mockito.when(accountRepository.returnAccountsByOwnerID(personID)).thenReturn(expectedAccounts);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getAccountsByPersonID(personEmail);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No accounts found with that ID.");
    }

    @Test
    @DisplayName("Test to get all accounts by personID - Invalid Email")
    void getAccountsByPersonIDInvalidEmail() {

        //Arrange
        String personEmail = "1191755isep.ipp.pt";

        //Act
        Throwable thrown = catchThrowable(() -> {
            Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)))
                    .thenThrow(new IllegalArgumentException("The email is not valid."));
            service.getAccountsByPersonID(personEmail);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");

    }

    @Test
    @DisplayName("Test to get all accounts by personID - Empty Email")
    void getAccountsByPersonIDEmptyEmail() {

        //Arrange
        String personEmail = "";

        //Act
        Throwable thrown = catchThrowable(() -> {
            Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)))
                    .thenThrow(new IllegalArgumentException("The email is not valid."));
            service.getAccountsByPersonID(personEmail);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");

    }

    @Test
    @DisplayName("Test to get all accounts by personID - Null Email")
    void getAccountsByPersonIDNullEmail() {

        //Arrange
        Mockito.when(personRepository.findPersonByEmail(null))
                .thenThrow(new IllegalArgumentException("The email can't be null."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getAccountsByPersonID(null);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }

}