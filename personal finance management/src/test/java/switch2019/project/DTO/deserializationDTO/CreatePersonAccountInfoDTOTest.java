package switch2019.project.DTO.deserializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonAccountInfoDTOTest {


    @Test
    @DisplayName("Test accountDenomination getter")
    void getAccountDenominationTest() {

        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Revolut");
        dto.setAccountDescription("Online Shopping");
        //Act:
        String denomination = dto.getAccountDenomination();

        //Assert:
        assertEquals("Revolut", denomination);

    }

    @Test
    @DisplayName("Test accountDescription getter")
    void getAccountDescriptionTest() {

        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Revolut");
        dto.setAccountDescription("Online Shopping");
        //Act:
        String description = dto.getAccountDescription();

        //Assert:
        assertEquals("Online Shopping", description);

    }

    @Test
    @DisplayName("Test accountDenomination setter")
    void setAccountDenomination() {
        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Revolut");
        dto.setAccountDescription("Online Shopping");

        String expected = "Revolut";

        //Act:
        dto.setAccountDenomination("Revolut");
        String result = dto.getAccountDenomination();

        //Assert:
        assertEquals("Revolut", result);

    }

    @Test
    @DisplayName("Test accountDescription setter")
    void setAccountDescription() {
        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Revolut");
        dto.setAccountDescription("Online Shopping");
        String expected = "Online Shopping";

        //Act:
        dto.setAccountDescription("Online Shopping");
        String result = dto.getAccountDescription();

        //Assert:
        assertEquals("Online Shopping", result);

    }

    /**
     * Tests for the equals method
     */

    @Test
    @DisplayName("Test to equals - Same Object")
    void equalsSameObject() {
        //Arrange
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Kelle's Account");
        dto.setAccountDescription("General Expenses");

        //Act
        boolean actual = dto.equals(dto);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Test to equals - Null Object")
    void equalsNullObject() {
        //Arrange
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Kelle's Account");
        dto.setAccountDescription("General Expenses");

        CreatePersonAccountInfoDTO dto2 = null;

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Test to equals - Object from different class")
    void equalsDifferentObjectClass() {
        //Arrange
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Kelle's Account");
        dto.setAccountDescription("General Expenses");

        CreateGroupInfoDTO dtoFromDifClass = new CreateGroupInfoDTO();

        //Act
        boolean actual = dto.equals(dtoFromDifClass);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Test to equals - Same attributes")
    void equalsSameAttributes() {
        //Arrange
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Kelle's Account");
        dto.setAccountDescription("General Expenses");

        CreatePersonAccountInfoDTO dto2 = new CreatePersonAccountInfoDTO();
        dto2.setAccountDenomination("Kelle's Account");
        dto2.setAccountDescription("General Expenses");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Test to equals - Different Account Description")
    void equalsDifferentAccountDescription() {
        //Arrange
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Kelle's Account");
        dto.setAccountDescription("General Expenses");

        CreatePersonAccountInfoDTO dto2 = new CreatePersonAccountInfoDTO();
        dto2.setAccountDenomination("Kelle's Account");
        dto2.setAccountDescription("Gym Expenses");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Test to equals - Different Account Denomination")
    void equalsDifferentAccountDenomination() {
        //Arrange
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Family Account");
        dto.setAccountDescription("General Expenses");

        CreatePersonAccountInfoDTO dto2 = new CreatePersonAccountInfoDTO();
        dto2.setAccountDenomination("Kelle's Account");
        dto2.setAccountDescription("General Expenses");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    /**
     * Tests for the hashcode method
     */

    @Test
    @DisplayName("Test to HashCode - Same Hashcode")
    void sameHashcode() {
        //Arrange
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Family Account");
        dto.setAccountDescription("General Expenses");

        CreatePersonAccountInfoDTO dto2 = new CreatePersonAccountInfoDTO();
        dto2.setAccountDenomination("Family Account");
        dto2.setAccountDescription("General Expenses");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Test to HashCode - Different Hashcode")
    void differentHashcode() {
        //Arrange
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO();
        dto.setAccountDenomination("Family Account");
        dto.setAccountDescription("General Expenses");

        CreatePersonAccountInfoDTO dto2 = new CreatePersonAccountInfoDTO();
        dto2.setAccountDenomination("General Account");
        dto2.setAccountDescription("Gym Expenses");


        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertFalse(actual);
    }


}
