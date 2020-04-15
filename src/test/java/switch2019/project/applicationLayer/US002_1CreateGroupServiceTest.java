package switch2019.project.applicationLayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.ServiceDTO.CreateGroupDTO;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class US002_1CreateGroupServiceTest {

    @Autowired
    private US002_1CreateGroupService service;


    /**
     * US002.1
     * Test if a group was created and person is admin
     */

    @Test
    @DisplayName("Main scenario - Existing person creates group and becomes admin")
    void createGroupAndBecomeAdmin() {
        //Arrange
        String groupDescription = "TEAM KIM";
        String personEmail = "1191743@isep.ipp.pt";

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
        String personEmail = "notFound@isep.pt";

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
        String groupDescription = "GYM TEAM";
        String personID = "1191762@isep.ipp.pt";
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
        String personEmail = "1191762@isep.ipp.pt";

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
            assertEquals("The email can't be null.", ex.getMessage());
        }
    }
}
