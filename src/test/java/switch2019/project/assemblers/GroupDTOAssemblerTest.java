package switch2019.project.assemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.AddMemberDTO;
import switch2019.project.DTO.createGroupDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;

import static org.junit.jupiter.api.Assertions.*;

class GroupDTOAssemblerTest {

    /**
     * Test to createGroupDTO method
     */
    @Test
    @DisplayName("Test to createGroupDTO")
    void createGroupDTO() {
        //Arrange
        GroupID groupID = new GroupID(new Description("familia"));
        GroupDTO expected = new GroupDTO(groupID.getDescription());

        GroupID groupID2 = new GroupID(new Description("bikinis"));
        GroupDTO unexpected = new GroupDTO(groupID2.getDescription());


        //Arrange
        GroupDTO actual = GroupDTOAssembler.createGroupDTO(groupID);


        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, actual),
                () -> assertNotEquals(unexpected, actual)
        );
    }

    /**
     * Test to createAdmimCreateGroup DTO method
     */
    @Test
    @DisplayName("Test to createAdmimCreateGroupDTO")
    void createAdminCreateGroupDTO() {
        //Arrange
        String groupDescription = "Grupo";
        String personemail = "j@ip.pt";

        createGroupDTO expected = new createGroupDTO(groupDescription, personemail);
        createGroupDTO unexpected = new createGroupDTO("familia", "js.p@.pt");

        //Act
        createGroupDTO actual = GroupDTOAssembler.creationOfGroupDTO(groupDescription, personemail);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, actual),
                () -> assertNotEquals(unexpected, actual)
        );
    }

    /**
     * Test to createAddMemberDTO method
     */

    @Test
    @DisplayName("Test to createaAddMemberDTO")
    void createAddMemberDTO() {
        //Arrange
        String personemail = "j@ip.pt";
        String groupDescription = "Grupo";

        AddMemberDTO expected = new AddMemberDTO(personemail, groupDescription);
        AddMemberDTO unexpected = new AddMemberDTO("fjf@jd.pt", "piropos");

        //Act
        AddMemberDTO actual = GroupDTOAssembler.createAddMemberDTO(personemail, groupDescription);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, actual),
                () -> assertNotEquals(unexpected, actual)
        );
    }
}