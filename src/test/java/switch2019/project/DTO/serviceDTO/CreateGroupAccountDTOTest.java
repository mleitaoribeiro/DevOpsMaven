package switch2019.project.DTO.serviceDTO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serializationDTO.AccountDTO;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupAccountDTOTest {
    private static CreateGroupAccountDTO createGroupAccountDTO;

    @BeforeAll
    static void setup () {
        createGroupAccountDTO = new CreateGroupAccountDTO("marta.cardoso@gmail.com", "Family", "Revolut", "Online Expenses");
    }

    @Test
    @DisplayName("Get administrator email")
    void getPersonEmail() {
        //Arrange
        String expected = "marta.cardoso@gmail.com";

        //Act
        String actual = createGroupAccountDTO.getPersonEmail();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get group description")
    void getGroupDescription() {
        //Arrange
        String expected = "Family";

        //Act
        String actual = createGroupAccountDTO.getGroupDescription();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get account denomination")
    void getAccountDenomination() {
        //Arrange
        String expected = "Revolut";

        //Act
        String actual = createGroupAccountDTO.getAccountDenomination();

        //Assert
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Get account description")
    void getAccountDescription() {
        //Assert
        String expected = "Online Expenses";

        //Act
        String actual = createGroupAccountDTO.getAccountDescription();

        //Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests for the equals method
     */

    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same - Equals")
    @Test
    public void equalsTestTrue(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        CreateGroupAccountDTO dto2 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertTrue(areDtosTheSame);
    }


    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same - Same object")
    @Test
    public void equalsTestTrueSameObject(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto1);

        //Assert:
        assertTrue(areDtosTheSame);
    }

    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same object - Not the Same")
    @Test
    public void equalsTestFalseDifferentObject(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");
        CreateGroupAccountDTO dto2 = new CreateGroupAccountDTO("joaquim@gmail.com",
                "qwerty", "Revo", "Revolut");


        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same object - Not the Same")
    @Test
    public void equalsTestFalseDifferentObjectType(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");
        AccountDTO dto2 = new AccountDTO("joaquim@gmail.com",
                "qwerty", "Revolut");


        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same object - Not the Same")
    @Test
    public void equalsTestFalseDifferentServiceObject(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");
        CreateGroupDTO dto2 = new CreateGroupDTO("Games", "marta@isep.ipp.pt");


        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same - Different Email")
    @Test
    public void equalsTestFalseDifferentEmail(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        CreateGroupAccountDTO dto2 = new CreateGroupAccountDTO("joaquim@gmail.com",
                "Milu", "Revolut", "Revolut account");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same - Different Group Description")
    @Test
    public void equalsTestFalseDifferentGroupDescription(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        CreateGroupAccountDTO dto2 = new CreateGroupAccountDTO("milu@gmail.com",
                "Fam", "Revolut", "Revolut account");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }


    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same - Different Account Denomination")
    @Test
    public void equalsTestFalseDifferentAccountDenomination(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        CreateGroupAccountDTO dto2 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revos", "Revolut account");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @DisplayName("Test if Two CreateGroupAccountDTO objects are the same - Different Account Description")
    @Test
    public void equalsTestFalseDifferentAccountDescription(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        CreateGroupAccountDTO dto2 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut ");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    /**
     * Tests for the hashCode method
     */

    @DisplayName("Test if Two CreateGroupAccountDTO  objects are the same - Same Hashcode")
    @Test
    public void equalsHashCodeTrue(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        CreateGroupAccountDTO dto2 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");

        //Act:
        boolean sameHashCode = (dto1.hashCode() == dto2.hashCode());

        //Assert:
        assertTrue(sameHashCode);
    }

    @DisplayName("Test if Two CreateGroupAccountDTO  objects are the same - Different Hashcode")
    @Test
    public void equalsHashCodeFalse(){
        //Arrange:
        CreateGroupAccountDTO dto1 = new CreateGroupAccountDTO("milu@gmail.com",
                "Milu", "Revolut", "Revolut account");
        CreateGroupAccountDTO dto2 = new CreateGroupAccountDTO("joaquim@gmail.com",
                "qwerty", "Revo", "Revolut");

        //Act:
        boolean sameHashCode = (dto1.hashCode() == dto2.hashCode());

        //Assert:
        assertFalse(sameHashCode);
    }


}