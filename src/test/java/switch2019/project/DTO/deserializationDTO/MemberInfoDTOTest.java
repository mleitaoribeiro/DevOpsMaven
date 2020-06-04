package switch2019.project.DTO.deserializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serializationDTO.AccountDTO;

import static org.junit.jupiter.api.Assertions.*;

class MemberInfoDTOTest {

    @Test
    void testEqualsSameExactObject() {
        //Arrange:
        MemberInfoDTO addMemberDTO = new MemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsNullObject() {
        //Arrange:
        MemberInfoDTO addMemberDTO = new MemberInfoDTO();
        MemberInfoDTO addMemberDTONull = null;
        addMemberDTO.setPersonEmail("marta@isep.pt");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTONull);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentClassObject() {
        //Arrange:
        MemberInfoDTO addMemberDTO = new MemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");

        AccountDTO accountDTO = new AccountDTO("marta@isep.pt", "savings", "2020 savings");

        //Act:
        boolean result = addMemberDTO.equals(accountDTO);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsSameParameters() {
        //Arrange:
        MemberInfoDTO addMemberDTO = new MemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");


        MemberInfoDTO addMemberDTO2 = new MemberInfoDTO();
        addMemberDTO2.setPersonEmail("marta@isep.pt");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentParameters() {
        //Arrange:
        MemberInfoDTO addMemberDTO = new MemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");


        MemberInfoDTO addMemberDTO2 = new MemberInfoDTO();
        addMemberDTO2.setPersonEmail("pinheiro@isep.pt");


        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testHashCodeTRUE() {
        //Arrange:
        MemberInfoDTO addMemberDTO = new MemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");


        MemberInfoDTO addMemberDTO2 = new MemberInfoDTO();
        addMemberDTO2.setPersonEmail("marta@isep.pt");


        //Act:
        boolean result = addMemberDTO.hashCode() == addMemberDTO2.hashCode();

        //Assert:
        assertTrue(result);
    }

    @Test
    void testHashCodeFALSE() {
        //Arrange:
        MemberInfoDTO addMemberDTO = new MemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");

        MemberInfoDTO addMemberDTO2 = new MemberInfoDTO();
        addMemberDTO2.setPersonEmail("pinheiro@isep.pt");

        //Act:
        boolean result = addMemberDTO.hashCode() == addMemberDTO2.hashCode();

        //Assert:
        assertFalse(result);
    }


    /**
     * Tests to Get Methods
     */

    @Test
    @DisplayName("Test to get method personEmail")
    void getPersonEmail() {
        //Arrange
        MemberInfoDTO addMemberDTO = new MemberInfoDTO();
        String expected = "raquel@xpto.pt";
        addMemberDTO.setPersonEmail(expected);

        //Act
        String actual = addMemberDTO.getPersonEmail();

        //Assert
        assertEquals(expected, actual);
    }



}