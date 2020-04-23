package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                .thenThrow(new IllegalArgumentException("No person found with that email."));


        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new PersonID(new Email(creatorEmail)))).thenThrow(new IllegalArgumentException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createPersonAccount(createPersonAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
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
                thenThrow(new IllegalArgumentException("This account already exists."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createPersonAccount(createPersonAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This account already exists.");
    }
}