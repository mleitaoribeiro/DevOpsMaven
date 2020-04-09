package switch2019.project.applicationLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.ServiceDTO.CreateGroupDTO;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

class US002_1CreateGroupServiceTest {

    private static US002_1CreateGroupService service;

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

        service = new US002_1CreateGroupService(groupsRepository, personRepository);

    }

    /**
     * US002.1
     * Test if a group was created and person is admin
     */

    @Test
    @DisplayName("Main scenario - Existing person creates group and becomes admin")
    void createGroupAndBecomeAdmin() {
        //Arrange
        String groupDescription = "Bashtards";
        String personEmail = "1234@isep.pt";

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(groupDescription, personEmail);

        GroupDTO expected = new GroupDTO(groupDescription);

        //Act
        GroupDTO result = service.createGroup(createGroupDTO);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("No personID matching")
    void createGroupAndBecomeAdminNoPersonID() {

        //Arrange
        String groupDescription = "Bashtards";
        String personEmail = "12345@isep.pt";

        //Act
        try {
            service.createGroup(new CreateGroupDTO(groupDescription, personEmail));
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
        service.createGroup(new CreateGroupDTO(groupDescription, personID));

        //Act
        try {
            service.createGroup(new CreateGroupDTO(groupDescription, personID));
        }
        catch (IllegalArgumentException ex) {
            assertEquals("This group description already exists.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Null group Description")
    void createGroupNullDescription() {
        //Arrange
        String personEmail = "1234@isep.pt";

        //Act
        try {
            service.createGroup(new CreateGroupDTO(null, personEmail));
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
           service.createGroup(new CreateGroupDTO(groupDescription, null));
       }

       //Assert
       catch (IllegalArgumentException ex) {
           assertEquals("The email can´t be null!", ex.getMessage());
       }
    }
}
