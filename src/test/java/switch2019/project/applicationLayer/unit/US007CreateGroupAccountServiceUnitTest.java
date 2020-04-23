package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonInMemoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class US007CreateGroupAccountServiceUnitTest {

    @Mock
    private PersonInMemoryRepository personRepository;

    @Mock
    private GroupsRepository groupsRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private US007CreateGroupAccountService service;


    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Test If Group Account is created - Happy Case - Main Scenario")
    void testIfGroupAccountWasCreatedHappyCase() {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String groupDescription = "Smith Family";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"),new Email("rick@gmail.com"));

        Account account = new Account(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)));

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenReturn(new Group(new Description(groupDescription), creator));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)))).thenReturn(account);

        AccountDTO expected = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        //Act
        AccountDTO accountCreated = service.createGroupAccount(createGroupAccountDTO);

        //Assert
        assertEquals(expected, accountCreated);
    }


    /**
     * Test If group Account is created - Failing scenarios
     */

    @Test
    @DisplayName("Test if Group Account is created - Exception - Person doesn't exit on Person Repository")
    void testIfGroupAccountWasCreatedPersonDoesntExist() {

        //Arrange
        String creatorEmail = "veryfake@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenThrow(new IllegalArgumentException("No person found with that email."));

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenThrow(new IllegalArgumentException("No person found with that email."));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription))))
                .thenThrow(new IllegalArgumentException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if Group Account is created - Exception - Group doesn't exist on Group Repository")
    void testIfSeveralGroupAccountsWereCreatedGroupDoesntExist() {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String groupDescription = "Unknown";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenThrow(new IllegalArgumentException("No group found with that description."));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription))))
                .thenThrow(new IllegalArgumentException("No group found with that description."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No group found with that description.");
    }

    /**
     * Test If group Account is created - Several accounts added - Null & Empty Values
     */

    @Test
    @DisplayName("Test If group Account is created - Exception - Person email is null")
    void testIfGroupAccountWasCreatedPersonIDNull() {

        //Arrange
        String groupDescription = "SWitCH";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(null, groupDescription,
                accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(null))
                .thenThrow(new IllegalArgumentException("The email can't be null."));

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenThrow(new IllegalArgumentException("The email can't be null."));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription))))
                .thenThrow(new IllegalArgumentException("The email can't be null."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }

    @Test
    @DisplayName("Test If group Account is created - Exception - Group ID is null")
    void testIfGroupAccountWasCreatedGroupIDNull()  {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, null,
                accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(groupsRepository.findGroupByDescription(null))
                .thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                null)).thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test If group Account is created - Exception - Account denomination is null")
    void testIfGroupAccountWasCreatedAccountDenominationNull() {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String groupDescription = "Smith Family";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                null, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenReturn(new Group(new Description(groupDescription), creator));

        Mockito.when(accountRepository.createAccount(null, new Description(accountDescription),
                new GroupID(new Description(groupDescription)))).
                thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test If group Account is created - Exception - Account description is null")
    void testIfGroupAccountWasCreatedAccountDescriptionNull() {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String groupDescription = "Smith Family";
        String accountDenomination = "Online";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, null);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenReturn(new Group(new Description(groupDescription), creator));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), null,
                new GroupID(new Description(groupDescription)))).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    /**
     * Test If group Account is created -  Failing scenarios - Simple Tests
     */

    @Test
    @DisplayName("Test If group Account is created - Exception - Person is a Member but is not Admin")
    void testIfGroupAccountWasCreatedNotAdmin() {

        //Arrange
        String creatorEmail = "beth.smith@gmail.com";
        String groupDescription = "Smith Family";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenReturn(new Group(new Description(groupDescription), creator));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)))).
                thenThrow(new IllegalArgumentException("This person is not admin of this group."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not admin of this group.");
    }

    @Test
    @DisplayName("Test If group Account is created - Exception - Person is not a Member")
    void testIfGroupAccountWasCreatedNotGroupMember() {

        //Arrange
        String creatorEmail = "1191778@isep.ipp.pt";
        String groupDescription = "Smith Family";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenReturn(new Group(new Description(groupDescription), creator));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)))).
                thenThrow(new IllegalArgumentException("This person is not member of this group."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not member of this group.");
    }


    @Test
    @DisplayName("Test If group Account is created - Exception - Account already exists")
    void testIfGroupAccountWasCreatedAccountAlreadyExists() {

        //Arrange
        String creatorEmail = "1191778@isep.ipp.pt";
        String groupDescription = "Smith Family";
        String accountDenomination = "Mbway";
        String accountDescription = "Online Shopping";

        Person creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email(creatorEmail));

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription())))
                .thenReturn(new Group(new Description(groupDescription), creator));

        Mockito.when(accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)))).
                thenThrow(new IllegalArgumentException("This account already exists."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.createGroupAccount(createGroupAccountDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This account already exists.");
    }
}
