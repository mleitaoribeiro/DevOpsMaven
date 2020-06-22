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
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class US002_1CreateGroupServiceUnitTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private GroupRepository groupsRepository;

    @Mock
    private LedgerRepository ledgerRepository;

    @InjectMocks
    private US002_1CreateGroupService service;

    private Person admin;
    private Description groupDescriptionToMock;
    private Group groupToMock;

    @BeforeEach
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        admin = new Person("Elizabeth Marie Bouvier Simpson",
                new DateAndTime(2002, 9, 10),
                new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                new Email("liza.simpson@hotmail.com"));

        groupDescriptionToMock = new Description("TEAM KIM");

        groupToMock = new Group (groupDescriptionToMock, admin.getID());
    }


    /**
     * US002.1
     * Test if a group was created and person is admin
     */
    @Test
    @DisplayName("Test if group was created - Happy Case")
    void createGroupAndBecomeAdmin() {
        //Arrange
        String groupDescription = "TEAM KIM";
        String personEmail = "liza.simpson@hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO createGroupDTO = new CreateGroupDTO(groupDescription, personEmail);

        //arrange the GroupDTO:
        GroupDTO expected = new GroupDTO(groupDescription);

        //ledger created
        Ledger ledger = new Ledger(new GroupID(new Description(groupDescription)));

        //arranging Mockito:
        Mockito.when(personRepository.findPersonByEmail(new Email(createGroupDTO.getPersonEmail()))).thenReturn(admin);
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin.getID())).thenReturn(groupToMock);

        Mockito.when(ledgerRepository.createLedger(new GroupID(new Description(groupDescription)))).thenReturn(ledger);

        //Act
        GroupDTO groupCreated = service.createGroup(createGroupDTO);

        //Assert
        assertEquals(expected, groupCreated);
    }

    @Test
    @DisplayName("Test if group was created - Person doesn't exist in the Person Repository")
    void createGroupAndBecomeAdminPersonDoesntExistsInThePersonRepository() {

        //Arrange
        String groupDescription = "TEAM KIM";
        String personEmail = "notFound@isep.pt";

        //arrange the CreateGroupDTO:
        CreateGroupDTO createGroupDTO = new CreateGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(personRepository.findPersonByEmail(new Email(createGroupDTO.getPersonEmail()))).
                thenThrow(new ArgumentNotFoundException("No person found with that email."));
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin.getID())).thenReturn(groupToMock);

        //Act
        Throwable exception = catchThrowable(() -> {
            service.createGroup(createGroupDTO);
        });

        //Assert
        assertThat(exception)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if group was created - Group already exists")
    void createGroupAndGroupAlreadyExists() {

        //Arrange
        String groupDescription = "TEAM KIM";
        String personEmail = "liza.simpson@hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO createGroupDTO = new CreateGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(personRepository.findPersonByEmail(new Email(createGroupDTO.getPersonEmail()))).thenReturn(admin);
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin.getID())).
                thenThrow(new ResourceAlreadyExistsException("This group description already exists."));

        //Act
        Throwable exception = catchThrowable(() -> {
            service.createGroup(createGroupDTO);
        });

        //Assert
        assertThat(exception)
                .isExactlyInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("This group description already exists.");
    }

    @Test
    @DisplayName("Test if group was created - email null")
    void createGroupAndGroupNullPersonEmail() {

        //Arrange
        String groupDescription = "TEAM KIM";
        String personEmail = null;

        //arrange the CreateGroupDTO:
        CreateGroupDTO createGroupDTO = new CreateGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(personRepository.findPersonByEmail(null)).
                thenThrow(new IllegalArgumentException("The email can't be null."));
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin.getID())).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable exception = catchThrowable(() -> {
            service.createGroup(createGroupDTO);
        });

        //Assert
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }

    @Test
    @DisplayName("Test if group was created - group description null")
    void createGroupNullDescription() {

        //Arrange
        String groupDescription = null;
        String personEmail = "liza.simpson@hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO createGroupDTO = new CreateGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(personRepository.findPersonByEmail(new Email(createGroupDTO.getPersonEmail()))).thenReturn(admin);
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin.getID())).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable exception = catchThrowable(() -> {
            service.createGroup(createGroupDTO);
        });

        //Assert
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");

    }

    @Test
    @DisplayName("Test if group was created - group description empty")
    void createGroupEmptyDescription() {

        //Arrange
        String groupDescription = "";
        String personEmail = "liza.simpson@hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO createGroupDTO = new CreateGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(personRepository.findPersonByEmail(new Email(createGroupDTO.getPersonEmail()))).thenReturn(admin);
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin.getID())).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable exception = catchThrowable(() -> {
            service.createGroup(createGroupDTO);
        });

        //Assert
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");

    }

    /**
     * US002.1
     * Test if a groupDTO is returned given its description
     */

    @Test
    @DisplayName("Test if a groupDTO is returned given its description  Hapyy Case")
    public void getGroupByDescription() {
        //Arrange
        String groupDescription = "TEAM AWESOME";
        Group group = new Group(new Description(groupDescription), admin.getID());
        GroupDTO outputExpected = new GroupDTO(group.getID().toString());

        //Act
        Mockito.when(groupsRepository.findGroupByDescription( new Description(groupDescription))).thenReturn(group);
        GroupDTO outputActual = service.getGroupByDescription(groupDescription);

        //Assert
        assertEquals(outputExpected, outputActual);
    }

    @Test
    @DisplayName("Test if a groupDTO is returned given its description - Not Found")
    public void getGroupByDescriptionNotFound() {
        //Arrange
        String groupDescription = "DZRT";

        //Act
        Mockito.when(groupsRepository.findGroupByDescription( new Description(groupDescription))).
                thenThrow(new ArgumentNotFoundException("No group found with that description."));

        Throwable thrown = catchThrowable(() -> {
            service.getGroupByDescription(groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");
    }

    @Test
    @DisplayName("Test if a groupDTO is returned given its description - group null")
    void getGroupByDescriptionNull() {
        //Arrange:
        String groupDescription = null;

        //Act
        Mockito.when(groupsRepository.findGroupByDescription( null)).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        Throwable thrown = catchThrowable(() -> {
            service.getGroupByDescription(groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }
}
