package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsInMemoryRepository;
import switch2019.project.infrastructure.repositories.PersonInMemoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class US002_1CreateGroupServiceUnitTest {

    @Mock
    private PersonInMemoryRepository personRepository;
    @Mock
    private GroupsInMemoryRepository groupsRepository;

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

        groupToMock = new Group (groupDescriptionToMock, admin);
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

        //arranging Mockito:
        Mockito.when(personRepository.findPersonByEmail(new Email(createGroupDTO.getPersonEmail()))).thenReturn(admin);
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin)).thenReturn(groupToMock);

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
                thenThrow(new IllegalArgumentException("No person found with that email."));
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin)).thenReturn(groupToMock);

        //Act
        Throwable exception = catchThrowable(() -> {
            service.createGroup(createGroupDTO);
        });

        //Assert
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
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
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin)).
                thenThrow(new IllegalArgumentException("This group description already exists."));

        //Act
        Throwable exception = catchThrowable(() -> {
            service.createGroup(createGroupDTO);
        });

        //Assert
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
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
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin)).
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
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin)).
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
        Mockito.when(groupsRepository.createGroup(groupDescriptionToMock, admin)).
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
}
