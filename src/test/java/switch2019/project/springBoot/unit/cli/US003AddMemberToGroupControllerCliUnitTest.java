package switch2019.project.springBoot.unit.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.SerializationDTO.AddedMemberDTO;
import switch2019.project.DTO.ServiceDTO.AddMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.controllerLayer.controllersCli.US003AddMemberToGroupController;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class US003AddMemberToGroupControllerCliUnitTest {
    @Mock @Autowired private US003AddMemberToGroupService service;
    @Autowired private US003AddMemberToGroupController controller;

    @Test
    @DisplayName("Test if a member was added to group - Success Case")
    public void addMemberSuccessCase() {

        //Arrange
        String groupDescription = "SWitCH";
        String personEmail = "morty@gmail.com";
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
                .hasMessage("No group was found with the given description.");
    }
}
