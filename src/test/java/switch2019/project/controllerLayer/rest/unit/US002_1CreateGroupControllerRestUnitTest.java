package switch2019.project.controllerLayer.rest.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.deserializationDTO.CreateGroupInfoDTO;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.controllerLayer.rest.US002_1CreateGroupControllerRest;
import switch2019.project.customExceptions.ArgumentNotFoundException;
import switch2019.project.customExceptions.ResourceAlreadyExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

public class US002_1CreateGroupControllerRestUnitTest {

    @Mock
    private US002_1CreateGroupService service;

    @InjectMocks
    private US002_1CreateGroupControllerRest controller;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * US002.1
     * As a user, I want to create a Group
     */

    @Test
    @DisplayName("Test if the group is created - Success case")
    public void createGroupSuccessCase() {

        //ARRANGE:
        String groupDescription = "Rick and Morty Expenses";
        String personEmail = "rick@gmail.com";

        //arrange the GroupInfoDTO :
        CreateGroupInfoDTO info = new CreateGroupInfoDTO();
        info.setGroupDescription(groupDescription);
        info.setPersonEmail(personEmail);

        //arrange the GroupDTO (output):
        GroupDTO groupCreationOutput = new GroupDTO(groupDescription);

        //arrange the CreateGroupDTO (input):
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arrange Mockito:
        Mockito.when(service.createGroup(groupCreationInput)).thenReturn(groupCreationOutput);

        //arrange Response Entity for the HTTP request (EXPECTED):
        ResponseEntity expectedResponseEntity = new ResponseEntity<>(groupCreationOutput, HttpStatus.CREATED);

        //ACT:
        //obtaining our actual response entity:
        ResponseEntity<Object> actualResponseEntity = controller.createGroup(info);

        //Assert:
        assertEquals(expectedResponseEntity, actualResponseEntity);
    }

    @Test
    @DisplayName("Test if a non-existing person can create a Group and becomes its Admin")
    public void createGroupUnknownPerson() {

        //ARRANGE:
        String groupDescription = "Adventures of Rick and Morty";
        String personEmail = "rick@ghotmail.com";

        //arrange the GroupInfoDTO :
        CreateGroupInfoDTO info = new CreateGroupInfoDTO();
        info.setGroupDescription(groupDescription);
        info.setPersonEmail(personEmail);

        //arrange the GroupDTO (output):
        GroupDTO groupCreationOutput = new GroupDTO(groupDescription);

        //arrange the CreateGroupDTO (input):

        CreateGroupDTO groupCreationInput = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        //arrange Mockito:
        Mockito.when(service.createGroup(groupCreationInput)).
                thenThrow(new ArgumentNotFoundException("No person found with that email."));

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if an existing person cannot create a Group with an already used description")
    public void createGroupExistingDescription() {

        //ARRANGE:
        String groupDescription = "Family Simpson";
        String personEmail = "homer@hotmail.com";

        //arrange the GroupInfoDTO :
        CreateGroupInfoDTO info = new CreateGroupInfoDTO();
        info.setGroupDescription(groupDescription);
        info.setPersonEmail(personEmail);

        //arrange the GroupDTO (output):
        GroupDTO groupCreationOutput = new GroupDTO(groupDescription);

        //arrange the CreateGroupDTO (input):
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        //arrange Mockito:
        Mockito.when(service.createGroup(groupCreationInput)).
                thenThrow(new ResourceAlreadyExistsException("This group description already exists."));

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("This group description already exists.");
    }

    @Test
    @DisplayName("Test if a person with a null email cannot create a Group")
    public void createGroupNullEmail() {

        //ARRANGE:
        String groupDescription = "New Group";
        String personEmail = null;

        //arrange the GroupInfoDTO :
        CreateGroupInfoDTO info = new CreateGroupInfoDTO();
        info.setGroupDescription(groupDescription);
        info.setPersonEmail(personEmail);

        //arrange the GroupDTO (output):
        GroupDTO groupCreationOutput = new GroupDTO(groupDescription);

        //arrange the CreateGroupDTO (input):
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        //arrange Mockito:
        Mockito.when(service.createGroup(groupCreationInput)).
                thenThrow(new IllegalArgumentException("The email can't be null."));

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
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
    public void createGroupEmptyEmail() {

        //ARRANGE:
        String groupDescription = "New Group";
        String personEmail = "";

        //arrange the GroupInfoDTO :
        CreateGroupInfoDTO info = new CreateGroupInfoDTO();
        info.setGroupDescription(groupDescription);
        info.setPersonEmail(personEmail);

        //arrange the GroupDTO (output):
        GroupDTO groupCreationOutput = new GroupDTO(groupDescription);

        //arrange the CreateGroupDTO (input):
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        //arrange Mockito:
        Mockito.when(service.createGroup(groupCreationInput)).
                thenThrow(new IllegalArgumentException("The email is not valid."));

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
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
    public void createGroupInvalidEmail() {

        //ARRANGE:
        String groupDescription = "New Group";
        String personEmail = "homer.hotmail.com";

        //arrange the GroupInfoDTO :
        CreateGroupInfoDTO info = new CreateGroupInfoDTO();
        info.setGroupDescription(groupDescription);
        info.setPersonEmail(personEmail);

        //arrange the GroupDTO (output):
        GroupDTO groupCreationOutput = new GroupDTO(groupDescription);

        //arrange the CreateGroupDTO (input):
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        //arrange Mockito:
        Mockito.when(service.createGroup(groupCreationInput)).
                thenThrow(new IllegalArgumentException("The email is not valid."));

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test if a person can create a Group with an empty description")
    public void createGroupEmptyGroupDescription() {

        //ARRANGE:
        String groupDescription = "";
        String personEmail = "homer@hotmail.com";

        //arrange the GroupInfoDTO :
        CreateGroupInfoDTO info = new CreateGroupInfoDTO();
        info.setGroupDescription(groupDescription);
        info.setPersonEmail(personEmail);

        //arrange the GroupDTO (output):
        GroupDTO groupCreationOutput = new GroupDTO(groupDescription);

        //arrange the CreateGroupDTO (input):
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        //arrange Mockito:
        Mockito.when(service.createGroup(groupCreationInput)).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
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
        String groupDescription = "SWITCH";
        GroupDTO outputExpected = new GroupDTO(groupDescription);
        ResponseEntity expectedResponseEntity = new ResponseEntity<>(outputExpected, HttpStatus.CREATED);

        //Act
        Mockito.when(service.getGroupByDescription(groupDescription)).thenReturn(outputExpected);

        ResponseEntity<Object> actualResponseEntity = controller.getGroupByDescription(outputExpected.getGroupDescription());

        //Assert
        assertEquals(expectedResponseEntity, actualResponseEntity);
    }

    @Test
    @DisplayName("Test if a groupDTO is returned given its description - Not Found")
    public void getGroupByDescriptionNotFound() {
        //Arrange
        String groupDescription = "SWITCH";

        //Act
        Mockito.when(service.getGroupByDescription(groupDescription)).
                thenThrow(new ArgumentNotFoundException("No group found with that description."));

        Throwable thrown = catchThrowable(() -> {
            controller.getGroupByDescription(groupDescription);
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
        Mockito.when(service.getGroupByDescription(groupDescription)).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        Throwable thrown = catchThrowable(() -> {
            service.getGroupByDescription(groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test if a groupDTO is returned given its description - group empty")
    void getGroupByDescriptionEmpty() {
        //Arrange:
        String groupDescription = "";

        //Act
        Mockito.when(service.getGroupByDescription(groupDescription)).
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
