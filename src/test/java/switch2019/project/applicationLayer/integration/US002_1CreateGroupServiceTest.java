
package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serviceDTO.CreateGroupDTO;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class US002_1CreateGroupServiceTest {


    @Autowired
    private US002_1CreateGroupService service;

    /*
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
        catch (ArgumentNotFoundException e) {
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
        catch (ResourceAlreadyExistsException ex) {
            assertEquals("This group already exists.", ex.getMessage());
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

    /*
     * US002.1
     * Test if a groupDTO is returned given its description
     */


    @Test
    @DisplayName("Test if a groupDTO is returned given its description  Hapyy Case")
    public void getGroupByDescription() {
        //Arrange
        String groupDescription = "SWITCH";
        GroupDTO outputExpected = new GroupDTO("SWITCH");

        //Act
        GroupDTO outputActual = service.getGroupByDescription(groupDescription);

        //Assert
        assertEquals(outputExpected, outputActual);
    }

    @Test
    @DisplayName("Test if a groupDTO is returned given its description - Not Found")
    public void getGroupByDescriptionNotFound() {
        //Arrange
        String groupDescription = "Just4Fun";

        //Act
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
        Throwable thrown = catchThrowable(() -> {
            service.getGroupByDescription(groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group empty")
    void getGroupByDescriptionEmpty() {
        //Arrange:
        String groupDescription = "";

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getGroupByDescription(groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }
}

