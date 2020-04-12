package switch2019.project.springBoot.unit.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.DeserializationDTO.CreateGroupInfoDTO;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.controllerLayer.controllersRest.US002_1CreateGroupControllerRest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class US002_1CreateGroupControllerRestUnitTest {
    @Mock
    @Autowired
    private US002_1CreateGroupService service;
    @Autowired
    private US002_1CreateGroupControllerRest controller;

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
        MockitoAnnotations.initMocks(this);
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
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arrange Mockito:
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroup(groupCreationInput)).thenReturn(groupCreationOutput);

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
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
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arrange Mockito:
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroup(groupCreationInput)).thenReturn(groupCreationOutput);

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
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
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arrange Mockito:
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroup(groupCreationInput)).thenReturn(groupCreationOutput);

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can´t be null!");
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
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arrange Mockito:
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroup(groupCreationInput)).thenReturn(groupCreationOutput);

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it´s not valid");
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
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arrange Mockito:
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroup(groupCreationInput)).thenReturn(groupCreationOutput);

        //ACT
        Throwable exception = catchThrowable(() -> {
            controller.createGroup(info);
        });

        //ASSERT:
        //1.- is the instance of the exception is the same
        //2.- is the contained message the expected:
        assertThat(exception)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it´s not valid");
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
        CreateGroupDTO groupCreationInput = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personEmail);

        //arrange Mockito:
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroup(groupCreationInput)).thenReturn(groupCreationOutput);

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
}
