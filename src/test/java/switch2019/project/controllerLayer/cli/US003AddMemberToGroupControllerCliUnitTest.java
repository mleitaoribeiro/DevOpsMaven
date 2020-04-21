package switch2019.project.controllerLayer.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.SerializationDTO.AddedMemberDTO;
import switch2019.project.DTO.ServiceDTO.AddMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

public class US003AddMemberToGroupControllerCliUnitTest {

    @Mock
    private US003AddMemberToGroupService service;

    @Autowired
    private US003AddMemberToGroupController controller;

    @Test
    @DisplayName("Test if a member was added to group - Success Case")
    public void addMemberSuccessCase() {

        //Arrange
        String groupDescription = "SWitCH";
        String personEmail = "summer@gmail.com";
        AddedMemberDTO wasMemberAddedExpected = new AddedMemberDTO(true, personEmail, groupDescription);

        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail,groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberDTO)).thenReturn(wasMemberAddedExpected);

        //Act
        AddedMemberDTO wasMemberAddedResult = controller.addMemberToGroup(personEmail, groupDescription);

        //Assert
        assertEquals(wasMemberAddedExpected, wasMemberAddedResult);

    }

    @Test
    @DisplayName("Test if a member was added to group - Person was already in the group")
    public void addMemberPersonWasAlreadyAMember() {

        //Arrange
        String groupDescription = "SWitCH";
        String personEmail = "1191778@isep.ipp.pt";
        AddedMemberDTO wasMemberAddedExpected = new AddedMemberDTO(false, personEmail, groupDescription);

        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail,groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberDTO)).thenReturn(wasMemberAddedExpected);

        //Act
        AddedMemberDTO wasMemberAddedResult = controller.addMemberToGroup(personEmail, groupDescription);

        //Assert
        assertEquals(wasMemberAddedExpected, wasMemberAddedResult);

    }

    @Test
    @DisplayName("Test if a member was added to group - Person Email doesn't exist on Person Repository")
    public void addMemberPersonEmailNotOnPersonRepository() {

        //Arrange
        String groupDescription = "SWitCH";
        String personEmail = "marta@gmail.com";
        AddedMemberDTO wasMemberAddedExpected = new AddedMemberDTO(true, personEmail, groupDescription);

        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail,groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberDTO)).thenReturn(wasMemberAddedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addMemberToGroup(personEmail, groupDescription);
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
        AddedMemberDTO wasMemberAddedExpected = new AddedMemberDTO(true, personEmail, groupDescription);

        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail,groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberDTO)).thenReturn(wasMemberAddedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addMemberToGroup(personEmail, groupDescription);
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
        AddedMemberDTO wasMemberAddedExpected = new AddedMemberDTO(true, personEmail, groupDescription);

        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail,groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberDTO)).thenReturn(wasMemberAddedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addMemberToGroup(personEmail, groupDescription);
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
        AddedMemberDTO wasMemberAddedExpected = new AddedMemberDTO(true, personEmail, groupDescription);

        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail,groupDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addMemberToGroup(addMemberDTO)).thenReturn(wasMemberAddedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addMemberToGroup(personEmail, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }
}