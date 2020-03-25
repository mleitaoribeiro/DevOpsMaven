package switch2019.project.applicationLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.AdminCreateGroupDTO;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

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
     * US002.1
     * Test if a group was created and person is admin
     */

    @Test
    @DisplayName("Main scenario")
    void createGroupAndBecomeAdmin() {
        //Arrange
        Person person = new Person("Alexandre", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto",
                "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));
        String groupDescription = "Bashtards";
        String personEmail = "1234@isep.pt";
        AdminCreateGroupDTO adminCreateGroupDTO = new AdminCreateGroupDTO(groupDescription, personEmail);
        Group expected = new Group(new Description(groupDescription), person);

        //Act
        Group result = service.createGroupAndBecomeAdmin(adminCreateGroupDTO).get();

        //Assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("No personID matching")
    void createGroupAndBecomeAdminNoPersonID() {

        //Arrange
        String groupDescription = "Bashtards";
        String personID = "12345@isep.pt";

        //Act
        try {
            service.createGroupAndBecomeAdmin(new AdminCreateGroupDTO(groupDescription, personID));
        }

        //Assert
        catch (IllegalArgumentException e) {
            assertEquals("No person found with that email.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Group was already created")
    void createGroupAndGroupAlreadyExists() {
        //Arrange
        String groupDescription = "Bashtards";
        String personID = "1234@isep.pt";
        service.createGroupAndBecomeAdmin(new AdminCreateGroupDTO(groupDescription, personID));

        //Act
        try {
            service.createGroupAndBecomeAdmin(new AdminCreateGroupDTO(groupDescription, personID));
        }
        catch (IllegalArgumentException ex) {
            assertEquals("This Group Description already exists.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Both null fields")
    void createGroupAndGroupNullFields() {

        //Act
        try {
            service.createGroupAndBecomeAdmin(new AdminCreateGroupDTO(null, null));
        }

        //Assert
        catch (IllegalArgumentException ex) {
            assertEquals("The description can't be null or empty.", ex.getMessage());
        }

    }

    @Test
    @DisplayName("personEmail null")
    void createGroupAndGroupNullPersonEmail() {

        //Arrange
        String groupDescription = "Bashtards";

       try {
           service.createGroupAndBecomeAdmin(new AdminCreateGroupDTO(groupDescription, null));
       }

       //Assert
       catch (IllegalArgumentException ex) {
           assertEquals("The email can´t be null!", ex.getMessage());
       }
    }

    @Test
    @DisplayName("group description is null")
    void createGroupAndGroupNullGroupDescription() {

        //Arrange
        String personID = "1234@isep.pt";

        //Act
        try {
            service.createGroupAndBecomeAdmin(new AdminCreateGroupDTO(null, personID));
        }

        //Assert
        catch (IllegalArgumentException ex) {
            assertEquals("The description can't be null or empty.", ex.getMessage());
        }
    }

}
