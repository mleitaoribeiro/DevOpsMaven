package switch2019.project.controllerLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.controllerLayer.controllersCli.US002_1CreateGroupAndBecomeAdminController;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;
import switch2019.project.applicationLayer.US002_1CreateGroupAndBecomeAdminService;

import static org.junit.jupiter.api.Assertions.*;

class US002_1CreateGroupAndBecomeAdminControllerTest {

    private static GroupsRepository groupsRepository;
    private static PersonRepository personRepository;
    private static US002_1CreateGroupAndBecomeAdminService service;
    private static US002_1CreateGroupAndBecomeAdminController controller;


    /**
     * US002.1
     * Universe setup for US tests
     */
    @BeforeEach
    void setUpUniverse() {
        groupsRepository = new GroupsRepository();
        personRepository = new PersonRepository();

        personRepository.createPerson("Alexandre", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto",
                        "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));

        service = new US002_1CreateGroupAndBecomeAdminService(groupsRepository, personRepository);
        controller = new US002_1CreateGroupAndBecomeAdminController(service);
    }

    /**
     * US002.1
     * Test if a group was created and person is admin
     */
    @Test
    @DisplayName("Main scenario - Existing person crkeates a Group and becomes Admin")
    void createGroupAndBecomeAdmin() {

        //Arrange
        String groupDescription = "Bashtards";
        String personEmail = "1234@isep.pt";

        GroupDTO isGroupCreatedAndAdminSetExpected = new GroupDTO(groupDescription);

        //Act
        GroupDTO isGroupCreatedAndAdminSet = controller.createGroupAndBecomeAdmin(groupDescription, personEmail);

        boolean isAdminSet = groupsRepository.findGroupByDescription(new Description(groupDescription)).
                isGroupAdmin(personRepository.findPersonByEmail(new Email(personEmail)));

        //Assert
        assertEquals(isGroupCreatedAndAdminSetExpected, isGroupCreatedAndAdminSet);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(isGroupCreatedAndAdminSetExpected, isGroupCreatedAndAdminSet),
                () -> assertTrue(isAdminSet)
        );
    }


    @Test
    @DisplayName("Email non existing")
    void createGroupAndBecomeAdminNoEmail() {

        //Arrange
        String groupDescription = "Bashtards";
        String personEmail = "12345@isep.pt";

        //Act
        try {
            controller.createGroupAndBecomeAdmin(groupDescription, personEmail);
        }

        //Assert
        catch (IllegalArgumentException e) {
            assertEquals("No person found with that email.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Group was already created")
    void createGroupAndBecomeAdminGroupAlreadyExists() {

        //Arrange
        String groupDescription = "Bashtards";
        String personEmail = "1234@isep.pt";
        controller.createGroupAndBecomeAdmin(groupDescription, personEmail);

        //Act
        try {
            controller.createGroupAndBecomeAdmin(groupDescription, personEmail);
        }

        //Assert
        catch (IllegalArgumentException ex) {
            assertEquals("This Group Description already exists.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("non valid email")
    void createGroupAndBecomeAdminIndalidEmail() {

        //Arrange
        String groupDescription = "Bashtards";
        String personEmail = "12345isep.pt";

        //Act
        try {
            controller.createGroupAndBecomeAdmin(groupDescription, personEmail);
        }

        //Assert
        catch (IllegalArgumentException e) {
            assertEquals("The email it´s not valid", e.getMessage());
        }
    }

    @Test
    @DisplayName("personEmail null")
    void createGroupAndGroupNullPersonEmail() {

        //Assert
        String groupDescription = "Bashtards";

        try {
            controller.createGroupAndBecomeAdmin(groupDescription, null);
        }

        //Assert
        catch (IllegalArgumentException ex) {
            assertEquals("The email can´t be null!", ex.getMessage());
        }
    }

    @Test
    @DisplayName("group description is null")
    void createGroupAndGroupNullGroupDescription() {

        //Assert
        String personEmail = "1234@isep.pt";

        //Act
        try {
            controller.createGroupAndBecomeAdmin(null, personEmail);
        }

        //Assert
        catch (IllegalArgumentException ex) {
            assertEquals("The description can't be null or empty.", ex.getMessage());
        }
    }
}