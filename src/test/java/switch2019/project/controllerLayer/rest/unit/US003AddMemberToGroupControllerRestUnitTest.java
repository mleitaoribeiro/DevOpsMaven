package switch2019.project.controllerLayer.rest.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.DeserializationDTO.AddMemberInfoDTO;
import switch2019.project.DTO.SerializationDTO.AddedMemberDTO;
import switch2019.project.DTO.ServiceDTO.AddMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.controllerLayer.rest.US003AddMemberToGroupControllerRest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

public class US003AddMemberToGroupControllerRestUnitTest {

    @Mock
    private US003AddMemberToGroupService service;

    @Autowired
    private US003AddMemberToGroupControllerRest controller;

    @Test
    @DisplayName("Test if a member was added to group - Success Case")
    public void addMemberSuccessCase() {

        //Arrange
        String groupDescription = "SWitCH";
        String personEmail = "morty@gmail.com";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);
        addMemberInfoDTO.setGroupDescription(groupDescription);

        AddMemberDTO addMemberControllerDTO = GroupDTOAssembler.transformIntoAddMemberDTO(addMemberInfoDTO);
        AddedMemberDTO addedMemberExpectedDTO = new AddedMemberDTO(true, personEmail, groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberControllerDTO)).thenReturn(addedMemberExpectedDTO);

        ResponseEntity responseEntityExpected = new ResponseEntity<>(addedMemberExpectedDTO, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> responseEntity = controller.addMemberToGroup(addMemberInfoDTO);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);

    }

    @Test
    @DisplayName("Test if a member was added to group - Person was already in the group")
    public void addMemberPersonWasAlreadyAMember() {

        //Arrange
        String groupDescription = "SWitCH";
        String personEmail = "1191778@isep.ipp.pt";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);
        addMemberInfoDTO.setGroupDescription(groupDescription);

        AddMemberDTO addMemberControllerDTO = GroupDTOAssembler.transformIntoAddMemberDTO(addMemberInfoDTO);
        AddedMemberDTO addedMemberExpectedDTO = new AddedMemberDTO(false, personEmail, groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberControllerDTO)).thenReturn(addedMemberExpectedDTO);

        ResponseEntity responseEntityExpected = new ResponseEntity<>(addedMemberExpectedDTO, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> responseEntity = controller.addMemberToGroup(addMemberInfoDTO);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);

    }

    @Test
    @DisplayName("Test if a member was added to group - Person Email doesn't exist on Person Repository")
    public void addMemberPersonEmailNotOnPersonRepository() {

        //Arrange
        String groupDescription = "SWitCH";
        String personEmail = "marta@gmail.com";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);
        addMemberInfoDTO.setGroupDescription(groupDescription);

        AddMemberDTO addMemberControllerDTO = GroupDTOAssembler.transformIntoAddMemberDTO(addMemberInfoDTO);
        AddedMemberDTO addedMemberExpectedDTO = new AddedMemberDTO(true, personEmail, groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberControllerDTO)).thenReturn(addedMemberExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addMemberToGroup(addMemberInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");

    }

    @Test
    @DisplayName("Test if a member was added to group - Group with the given description doesn't exist on Group Repository")
    public void addMemberGroupDoesntExist() {

        //Arrange
        String groupDescription = "games";
        String personEmail = "morty@gmail.com";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);
        addMemberInfoDTO.setGroupDescription(groupDescription);

        AddMemberDTO addMemberControllerDTO = GroupDTOAssembler.transformIntoAddMemberDTO(addMemberInfoDTO);
        AddedMemberDTO addedMemberExpectedDTO = new AddedMemberDTO(true, personEmail, groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberControllerDTO)).thenReturn(addedMemberExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addMemberToGroup(addMemberInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No group found with that description.");

    }

    @Test
    @DisplayName("Test if a member was added to group - Null Person Email")
    public void addMemberNullPersonEmail() {

        //Arrange
        String groupDescription = "games";
        String personEmail = null;

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);
        addMemberInfoDTO.setGroupDescription(groupDescription);

        AddMemberDTO addMemberControllerDTO = GroupDTOAssembler.transformIntoAddMemberDTO(addMemberInfoDTO);
        AddedMemberDTO addedMemberExpectedDTO = new AddedMemberDTO(true, personEmail, groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberControllerDTO)).thenReturn(addedMemberExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addMemberToGroup(addMemberInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");

    }

    @Test
    @DisplayName("Test if a member was added to group - Null Group Description")
    public void addMemberNullGroupDescription() {

        //Arrange
        String groupDescription = null;
        String personEmail = "morty@gmail.com";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);
        addMemberInfoDTO.setGroupDescription(groupDescription);

        AddMemberDTO addMemberControllerDTO = GroupDTOAssembler.transformIntoAddMemberDTO(addMemberInfoDTO);
        AddedMemberDTO addedMemberExpectedDTO = new AddedMemberDTO(true, personEmail, groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberControllerDTO)).thenReturn(addedMemberExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addMemberToGroup(addMemberInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");

    }

}