package switch2019.project.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US002_1CreateGroupAndBecomeAdminService;

import static org.junit.jupiter.api.Assertions.*;

class US002_1CreateGroupAndBecomeAdminControllerTest {

    private static GroupsRepository groupsRepository;
    private static US002_1CreateGroupAndBecomeAdminController controller;


    /**
     * US002.1
     * Universe setup for US tests
     */

    @BeforeEach
    void setUpUniverse() {
        groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();

        personRepository.createPerson("Alexandre", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto",
                        "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));

        US002_1CreateGroupAndBecomeAdminService service = new US002_1CreateGroupAndBecomeAdminService(groupsRepository, personRepository);
        controller = new US002_1CreateGroupAndBecomeAdminController(service);
    }


    /**
     * US001
     * Test if a group was created and person is admin
     */


    @Test
    @DisplayName("Main scenario")
    void createGroupAndBecomeAdminDoubleCheck() {

        //Arrange
        String groupDescription = "Bashtards";
        String email = "1234@isep.pt";

        PersonID personID = new PersonID(new Email(email)); //usado para confirmar este ficou admin


        //Act
        boolean result = controller.createGroupAndBecomeAdmin(groupDescription, email);
        boolean isAdmin = groupsRepository.findGroupByDescription(new Description(
                "Bashtards")).isGroupAdmin(personID);

        //Assert
        assertTrue(result && isAdmin);
    }

    @Test
    @DisplayName("PersonID non existing")
    void createGroupAndBecomeAdminNoPersonID() {

        //Arrange
        String groupDescription = "Bashtards";
        String personID = "12345@isep.pt";

        //Act
        try {
            controller.createGroupAndBecomeAdmin(groupDescription, personID);
        }

        //Assert
        catch (IllegalArgumentException e) {
            assertEquals("No person found with that ID.", e.getMessage());
        }

    }

    @Test
    @DisplayName("Groups was already created")
    void createGroupAndBecomeAdminGroupAlreadyExists() {

        //Arrange
        String groupDescription = "Bashtards";
        String personID = "1234@isep.pt";
        controller.createGroupAndBecomeAdmin(groupDescription, personID);
        //Act
        try {
           controller.createGroupAndBecomeAdmin(groupDescription, personID);
        }
        catch (IllegalArgumentException ex) {
            assertEquals("This Group Description already exists.", ex.getMessage());
        }
    }
}