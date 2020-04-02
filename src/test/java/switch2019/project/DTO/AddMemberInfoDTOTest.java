package switch2019.project.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddMemberInfoDTOTest {

    @Test
    void testEqualsSameExactObject() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsNullObject() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");
        AddMemberDTO addMemberDTONull = null;

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTONull);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentClassObject() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");
        AccountDTO accountDTO = new AccountDTO("marta@isep.pt", "savings", "2020 savings");

        //Act:
        boolean result = addMemberDTO.equals(accountDTO);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsSameParameters() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");
        AddMemberDTO addMemberDTO2 = new AddMemberDTO("marta@isep.pt", "bashtards");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentParameters() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");
        AddMemberDTO addMemberDTO2 = new AddMemberDTO("pinheiro@isep.pt", "bashtards");

        //Act:
        boolean result = addMemberDTO.equals(addMemberDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testHashCodeTRUE() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");
        AddMemberDTO addMemberDTO2 = new AddMemberDTO("marta@isep.pt", "bashtards");

        //Act:
        boolean result = addMemberDTO.hashCode() == addMemberDTO2.hashCode();

        //Assert:
        assertTrue(result);
    }

    @Test
    void testHashCodeFALSE() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");
        AddMemberDTO addMemberDTO2 = new AddMemberDTO("pinheiro@isep.pt", "bashtards");

        //Act:
        boolean result = addMemberDTO.hashCode() == addMemberDTO2.hashCode();

        //Assert:
        assertFalse(result);
    }

    @Test
    void getPersonEmail() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");
        String expected = "marta@isep.pt";

        //Act:
        String description = addMemberDTO.getPersonEmail();

        //Assert:
        assertEquals(expected, description);
    }

    @Test
    void getGroupDescription() {
        //Arrange:
        AddMemberDTO addMemberDTO = new AddMemberDTO("marta@isep.pt", "bashtards");
        String expected = "bashtards";

        //Act:
        String description = addMemberDTO.getGroupDescription();

        //Assert:
        assertEquals(expected, description);
    }
}