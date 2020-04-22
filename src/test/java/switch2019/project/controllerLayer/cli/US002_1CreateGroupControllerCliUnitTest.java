package switch2019.project.controllerLayer.cli;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

public class US002_1CreateGroupControllerCliUnitTest {

    @Mock
    private US002_1CreateGroupService service;

    @InjectMocks
    private US002_1CreateGroupController controller;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * US002.1
     * As a user, I want to create a Group
     */

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - Main Scenario")
    void createGroupAndBecomeAdminHappyCase() throws Exception {

        //ARRANGE:
        String groupDescription = "Adventures of Rick and Morty";
        String personEmail = "rick@gmail.com";

        //arrange the GroupDTO:
        GroupDTO groupCreatedExpected = new GroupDTO(groupDescription);

        //arrange the CreateGroupDTO:
        CreateGroupDTO groupCreation = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(service.createGroup(groupCreation)).thenReturn(groupCreatedExpected);

        //ACT:
        GroupDTO groupCreatedActual = controller.createGroup(groupDescription, personEmail);

        //ASSERT:
        assertEquals(groupCreatedExpected, groupCreatedActual);
    }

    @Test
    @DisplayName("Test if a non-existing person can create a Group and becomes its Admin")
    void createGroupAndBecomeAdminPersonDoesNotExists() throws Exception {

        //ARRANGE:
        String groupDescription = "new Group";
        String personEmail = "nonexistant@hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO groupCreation = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(service.createGroup(groupCreation)).
                thenThrow(new IllegalArgumentException("No person found with that email."));

        //ACT:
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(groupDescription, personEmail);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if an existing person cannot create a Group with an already used description")
    void createGroupAndBecomeAdminGroupExists() throws Exception {

        //ARRANGE:
        String groupDescription = "Family Simpson";
        String personEmail = "homer@hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO groupCreation = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(service.createGroup(groupCreation)).
                thenThrow(new IllegalArgumentException("This group description already exists."));

        //ACT:
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(groupDescription, personEmail);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This group description already exists.");
    }

    @Test
    @DisplayName("Test if a person with a null email cannot create a Group")
    void createGroupAndBecomeAdminEmailNull() throws Exception {

        //ARRANGE:
        String groupDescription = "New Group";
        String personEmail = null;

        //arrange the CreateGroupDTO:
        CreateGroupDTO groupCreation = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(service.createGroup(groupCreation)).
                thenThrow(new IllegalArgumentException("The email can't be null."));

        //ACT:
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(groupDescription, personEmail);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }

    @Test
    @DisplayName("Test if a person with a empty email cannot create a Group")
    void createGroupAndBecomeAdminEmptyEmail() throws Exception {

        //ARRANGE:
        String groupDescription = "New Group";
        String personEmail = "";

        //arrange the CreateGroupDTO:
        CreateGroupDTO groupCreation = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(service.createGroup(groupCreation)).
                thenThrow(new IllegalArgumentException("The email is not valid."));

        //ACT:
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(groupDescription, personEmail);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test if a person with an invalid email cannot create a Group")
    void createGroupAndBecomeAdminInvalidEmail() throws Exception {

        //ARRANGE:
        String groupDescription = "Family Simpson";
        String personEmail = "homer.hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO groupCreation = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(service.createGroup(groupCreation)).
                thenThrow(new IllegalArgumentException("The email is not valid."));

        //ACT:
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(groupDescription, personEmail);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test if a person can create a Group with a null description")
    void createGroupAndBecomeAdminNullGroupDescription() throws Exception {

        //ARRANGE:
        String groupDescription = null;
        String personEmail = "homer@hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO groupCreation = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(service.createGroup(groupCreation)).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //ACT:
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(groupDescription, personEmail);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test if a person can create a Group with an empty description")
    void createGroupAndBecomeAdminEmptyGroupDescription() throws Exception {

        //ARRANGE:
        String groupDescription = "";
        String personEmail = "homer@hotmail.com";

        //arrange the CreateGroupDTO:
        CreateGroupDTO groupCreation = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arranging Mockito:
        Mockito.when(service.createGroup(groupCreation)).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //ACT:
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(groupDescription, personEmail);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }
}
