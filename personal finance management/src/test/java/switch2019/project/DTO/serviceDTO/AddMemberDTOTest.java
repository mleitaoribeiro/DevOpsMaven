package switch2019.project.DTO.serviceDTO;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serializationDTO.AccountDTO;

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

    @Test
    void sameHashCode (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191755@isep.ipp.pt", "switch");
        AddMemberDTO dto2 = new AddMemberDTO("1191755@isep.ipp.pt", "switch");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    void sameHashCodeFalse (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191755@isep.ipp.pt", "switch");
        AddMemberDTO dto2 = new AddMemberDTO("1191755@isep.ipp.pt", "friends");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void sameObject (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191755@isep.ipp.pt", "switch");

        //Act
        boolean result = dto.equals(dto);

        //Assert
        assertTrue(result);
    }

    @Test
    void differentObject (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191755@isep.ipp.pt", "switch");
        AddMemberDTO dto2 = new AddMemberDTO("1191755@isep.ipp.pt", "friends");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    void compareNull (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191755@isep.ipp.pt", "switch");
        AddMemberDTO dto2 = null;

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    void differentClassObject (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191755@isep.ipp.pt", "switch");
        AccountDTO accountDTO = new AccountDTO("1191755@isep.ipp.pt", "house", "cleaning");

        //Act
        boolean result = dto.equals(accountDTO);

        //Assert
        assertFalse(result);
    }

    @Test
    void sameParameters (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191755@isep.ipp.pt", "switch");
        AddMemberDTO dto2 = new AddMemberDTO("1191755@isep.ipp.pt", "switch");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertTrue(result);
    }

    @Test
    void sameParametersDifferentEmail (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191756@isep.ipp.pt", "switch");
        AddMemberDTO dto2 = new AddMemberDTO("1191755@isep.ipp.pt", "switch");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    void sameParametersDifferentDescription (){

        //Arrange
        AddMemberDTO dto = new AddMemberDTO("1191755@isep.ipp.pt", "friends");
        AddMemberDTO dto2 = new AddMemberDTO("1191755@isep.ipp.pt", "switch");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }
}