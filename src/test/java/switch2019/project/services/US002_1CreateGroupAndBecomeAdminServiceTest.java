package switch2019.project.services;

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

import static org.junit.jupiter.api.Assertions.*;

class US002_1CreateGroupAndBecomeAdminServiceTest {

    private static US002_1CreateGroupAndBecomeAdminService service;

    /**
     * US002.1
     * Universe setup for US tests
     */

    @BeforeEach
    void setUpUniverse() {
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();

        personRepository.createPerson("Alexandre", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto",
                        "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));

        service = new US002_1CreateGroupAndBecomeAdminService(groupsRepository, personRepository);

    }

    /**
     * US001
     * Test if a group was created and person is admin
     */

    @Test
    @DisplayName("Main scenario")
    void createGroupAndBecomeAdmin() {
        //Arrange
        Description groupDescription = new Description("Bashtards");
        PersonID personID = new PersonID(new Email("1234@isep.pt"));

        //Act
        boolean result = service.createGroupAndBecomeAdmin(groupDescription, personID);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("No personID matching")
    void createGroupAndBecomeAdminNoPersonID() {

        //Arrange
        Description groupDescription = new Description("Bashtards");
        PersonID personID = new PersonID(new Email("12345@isep.pt"));

        //Act
        try {
            service.createGroupAndBecomeAdmin(groupDescription, personID);
        }

        //Assert
        catch (IllegalArgumentException e) {
            assertEquals("No person found with that ID.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Group was already created")
    void createGroupAndGroupAlreadyExists() {
        //Arrange
        Description groupDescription = new Description("Bashtards");
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        service.createGroupAndBecomeAdmin(groupDescription, personID);

        //Act
        try {
            service.createGroupAndBecomeAdmin(groupDescription, personID);
        }
        catch (IllegalArgumentException ex) {
            assertEquals("This Group Description already exists.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Group was already created")
    void createGroupAndGroupNullFields() {

        //Act
        boolean result = service.createGroupAndBecomeAdmin(null, null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Group was already created")
    void createGroupAndGroupNullPersonID() {

        //Assert
        Description groupDescription = new Description("Bashtards");

        //Act
        boolean result = service.createGroupAndBecomeAdmin(groupDescription, null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Group was already created")
    void createGroupAndGroupNullGroupDescription() {

        //Assert
        PersonID personID = new PersonID(new Email("1234@isep.pt"));

        //Act
        boolean result = service.createGroupAndBecomeAdmin(null, personID);

        //Assert
        assertFalse(result);
    }

}