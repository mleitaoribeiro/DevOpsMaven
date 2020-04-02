package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.ServiceDTO.AddMemberDTO;

import static org.junit.jupiter.api.Assertions.*;

class AddMemberDTOTest {
    /**
     * Tests to Get Method on AddMemberDTOtests
     */

    @Test
    @DisplayName("Test to get method personEmail")
    void getPersonEmail() {
        //Arrange
        AddMemberDTO dto=new AddMemberDTO("email@ip.pt","familia");
        String expected="email@ip.pt";

        //Act
        String actual= dto.getPersonEmail();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test to get method groupDescription")
    void getGroupDescription() {
        //Arrange
        AddMemberDTO dto=new AddMemberDTO("email@ip.pt","familia");
        String expected="familia";

        //Act
        String actual=dto.getGroupDescription();

        //Assert
        assertEquals(expected,actual);
    }
}