package switch2019.project.DTO.SerializationDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddedMemberDTOTest {

    @Test
    void testEqualsSameExactObject() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");

        //Act:
        boolean result = addedMemberDTO.equals(addedMemberDTO);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsNullObject() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");
        AddedMemberDTO addedMemberDTONull = null;

        //Act:
        boolean result = addedMemberDTO.equals(addedMemberDTONull);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentClassObject() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");
        AccountDTO accountDTO = new AccountDTO("marta@isep.pt", "savings", "2020 savings");

        //Act:
        boolean result = addedMemberDTO.equals(accountDTO);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsSameParameters() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");
        AddedMemberDTO addedMemberDTO2 = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");

        //Act:
        boolean result = addedMemberDTO.equals(addedMemberDTO2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentParameters() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");
        AddedMemberDTO addedMemberDTO2 = new AddedMemberDTO
                (false, "marta@isep.pt", "bashtards");

        //Act:
        boolean result = addedMemberDTO.equals(addedMemberDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testHashCodeTRUE() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");
        AddedMemberDTO addedMemberDTO2 = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");

        //Act:
        boolean result = addedMemberDTO.hashCode() == addedMemberDTO2.hashCode();

        //Assert:
        assertTrue(result);
    }

    @Test
    void testHashCodeFALSE() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");
        AddedMemberDTO addedMemberDTO2 = new AddedMemberDTO
                (true, "pinheiro@isep.pt", "bashtards");

        //Act:
        boolean result = addedMemberDTO.hashCode() == addedMemberDTO2.hashCode();

        //Assert:
        assertFalse(result);
    }

    @Test
    void getMemberAddedTRUE() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (true, "marta@isep.pt", "bashtards");
        String expected = "marta@isep.pt was added to group bashtards";

        //Act:
        String description = addedMemberDTO.getMemberAdded();

        //Assert:
        assertEquals(expected, description);
    }

    @Test
    void getMemberAddedFALSE() {
        //Arrange:
        AddedMemberDTO addedMemberDTO = new AddedMemberDTO
                (false, "marta@isep.pt", "bashtards");
        String expected = "marta@isep.pt is already on group bashtards";

        //Act:
        String description = addedMemberDTO.getMemberAdded();

        //Assert:
        assertEquals(expected, description);
    }
}