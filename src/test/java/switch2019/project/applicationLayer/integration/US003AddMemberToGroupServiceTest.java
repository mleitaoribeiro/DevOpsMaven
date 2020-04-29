package switch2019.project.applicationLayer.integration;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.PersonIDDTO;
import switch2019.project.DTO.serviceDTO.AddMemberDTO;
import switch2019.project.DTO.serializationDTO.AddedMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.customExceptions.ArgumentNotFoundException;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class US003AddMemberToGroupServiceTest {

    @Autowired
    private US003AddMemberToGroupService service;


    /**
     * Test to add member to group
     */

    @Test
    @DisplayName("Test if a member was added to group")
    void addMemberToGroup() {
        //Arrange
        String personEmail = "beatriz.azevedo@gmail.com";
        String groupDescription = "Split Expenses";
        AddMemberDTO addMemberDTO = new AddMemberDTO(personEmail, groupDescription);
        String expected = "beatriz.azevedo@gmail.com was added to group split expenses";

        //Act
        AddedMemberDTO addedMemberDTO = service.addMemberToGroup(addMemberDTO);

        //Assert
        assertEquals(expected, addedMemberDTO.getMemberAdded());
    }

    @Test
    @DisplayName("Test if a member was added to group - Same Person")
    void addMemberToGroupAlreadyIn() {
        //Arrange
        String personEmail = "1110120@isep.ipp.pt";
        String groupDescription = "Friends";
        AddMemberDTO addMemberDTO = new AddMemberDTO(personEmail, groupDescription);
        String expected = "1110120@isep.ipp.pt is already on group friends";

        //Act
        AddedMemberDTO addedMemberDTO = service.addMemberToGroup(addMemberDTO);

        //Assert
        assertEquals(expected, addedMemberDTO.getMemberAdded());
    }

    @Test
    @DisplayName("Test if a member was added to group-Invalid Person ID")
    void addMemberToGroupInvalidPersonID() {
        //Arrange
        String personEmail = "jp@ip.pt";
        String groupDescription = "familia";
        AddMemberDTO addMemberDTO = new AddMemberDTO(personEmail, groupDescription);

        //Act
        try {
            service.addMemberToGroup(addMemberDTO);
        }

        //Assert
        catch (ArgumentNotFoundException email) {
            assertEquals("No person found with that email.", email.getMessage());
        }
    }

    @Test
    @DisplayName("Test to getPersonByEmail not valid")
    void getPersonByEmail(){
        String personEmail = "1191743@isep.ipp.pt";
        String groupDescription = "friends";

        try {
            service.getPersonByEmail(personEmail, groupDescription);
        }
        catch (IllegalArgumentException e)  {
            assertEquals("That person is not a member of this group.", e.getMessage());
        };
    }

    @Test
    @DisplayName("Test to getPersonByEmail")
    void getPersonByEmailEquals(){
        String personEmail = "1191743@isep.ipp.pt";
        String groupDescription = "switch";

        PersonIDDTO personIDDTOactual = service.getPersonByEmail(personEmail, groupDescription);

        PersonIDDTO personIDDTOexpected = new PersonIDDTO(new PersonID(new Email(personEmail)));

            assertEquals(personIDDTOexpected, personIDDTOactual);
    }

    @Test
    @DisplayName("Test for getMembersByGroupDescription - Main Scenario")
    void getMembersByGroupDescription(){

        // Arrange
        String groupDescription = "Rick and Morty";

        Set<PersonIDDTO> membersExpected = new LinkedHashSet<>();
        membersExpected.add(PersonDTOAssembler.createPersonIDDTO(new PersonID(new Email("rick@gmail.com"))));
        membersExpected.add(PersonDTOAssembler.createPersonIDDTO(new PersonID(new Email("morty@gmail.com"))));

        // Act
        Set<PersonIDDTO> members = service.getMembersByGroupDescription(groupDescription);

        // Assert
        assertEquals(membersExpected, members);
    }

    @Test
    @DisplayName("Test for getMembersByGroupDescription - Exception - No group found with that description")
    void getMembersByGroupDescriptionException() throws ArgumentNotFoundException{

        // Arrange
        String groupDescription = "High School buddies";

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getMembersByGroupDescription(groupDescription);;
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");

    }

    @Test
    @DisplayName("Test for getAdminssByGroupDescription - Main Scenario")
    void getAdminsByGroupDescription(){

        // Arrange
        String groupDescription = "Rick and Morty";

        Set<PersonIDDTO> membersExpected = new LinkedHashSet<>();
        membersExpected.add(PersonDTOAssembler.createPersonIDDTO(new PersonID(new Email("rick@gmail.com"))));

        // Act
        Set<PersonIDDTO> members = service.getAdminsByGroupDescription(groupDescription);

        // Assert
        assertEquals(membersExpected, members);
    }

    @Test
    @DisplayName("Test for getAdminsByGroupDescription - Exception - No group found with that description")
    void getAdminsByGroupDescriptionException() throws ArgumentNotFoundException{

        // Arrange
        String groupDescription = "High School buddies";

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getAdminsByGroupDescription(groupDescription);;
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");

    }
}
