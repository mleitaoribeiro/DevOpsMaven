package switch2019.project.applicationLayer.integration;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.ServiceDTO.AddMemberDTO;
import switch2019.project.DTO.SerializationDTO.AddedMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;

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
        catch (IllegalArgumentException email) {
            assertEquals("No person found with that email.", email.getMessage());
        }
    }
}
