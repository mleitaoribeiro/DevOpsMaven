package switch2019.project.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddMemberInfoDTOTest {

    @Test
    void testEqualsSameExactObject() {
        //Arrange:
        AddMemberInfoDTO addMemberDTO = new AddMemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");
        addMemberDTO.setGroupDescription("bashtards");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsNullObject() {
        //Arrange:
        AddMemberInfoDTO addMemberDTO = new AddMemberInfoDTO();
        AddMemberInfoDTO addMemberDTONull = null;
        addMemberDTO.setPersonEmail("marta@isep.pt");
        addMemberDTO.setGroupDescription("bashtards");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTONull);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentClassObject() {
        //Arrange:
        AddMemberInfoDTO addMemberDTO = new AddMemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");
        addMemberDTO.setGroupDescription("bashtards");

        AccountDTO accountDTO = new AccountDTO("marta@isep.pt", "savings", "2020 savings");

        //Act:
        boolean result = addMemberDTO.equals(accountDTO);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsSameParameters() {
        //Arrange:
        AddMemberInfoDTO addMemberDTO = new AddMemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");
        addMemberDTO.setGroupDescription("bashtards");

        AddMemberInfoDTO addMemberDTO2 = new AddMemberInfoDTO();
        addMemberDTO2.setPersonEmail("marta@isep.pt");
        addMemberDTO2.setGroupDescription("bashtards");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentParameters() {
        //Arrange:
        AddMemberInfoDTO addMemberDTO = new AddMemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");
        addMemberDTO.setGroupDescription("bashtards");

        AddMemberInfoDTO addMemberDTO2 = new AddMemberInfoDTO();
        addMemberDTO2.setPersonEmail("pinheiro@isep.pt");
        addMemberDTO2.setGroupDescription("bashtards");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testHashCodeTRUE() {
        //Arrange:
        AddMemberInfoDTO addMemberDTO = new AddMemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");
        addMemberDTO.setGroupDescription("bashtards");

        AddMemberInfoDTO addMemberDTO2 = new AddMemberInfoDTO();
        addMemberDTO2.setPersonEmail("marta@isep.pt");
        addMemberDTO2.setGroupDescription("bashtards");

        //Act:
        boolean result = addMemberDTO.hashCode() == addMemberDTO2.hashCode();

        //Assert:
        assertTrue(result);
    }

    @Test
    void testHashCodeFALSE() {
        //Arrange:
        AddMemberInfoDTO addMemberDTO = new AddMemberInfoDTO();
        addMemberDTO.setPersonEmail("marta@isep.pt");
        addMemberDTO.setGroupDescription("bashtards");

        AddMemberInfoDTO addMemberDTO2 = new AddMemberInfoDTO();
        addMemberDTO2.setPersonEmail("pinheiro@isep.pt");
        addMemberDTO2.setGroupDescription("bashtards");

        //Act:
        boolean result = addMemberDTO.hashCode() == addMemberDTO2.hashCode();

        //Assert:
        assertFalse(result);
    }

    @Test
    void getPersonEmail() {

    }

    @Test
    void getGroupDescription() {

    }
}