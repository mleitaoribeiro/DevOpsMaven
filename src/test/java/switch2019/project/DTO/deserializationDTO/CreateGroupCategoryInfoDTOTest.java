package switch2019.project.DTO.deserializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;

import static org.junit.jupiter.api.Assertions.*;

public class CreateGroupCategoryInfoDTOTest {

    @DisplayName("Test getGroupDescription Getter")
    @Test
    public void getGroupDescriptionTest() {
        //Arrange
        CreateGroupCategoryInfoDTO dto = new CreateGroupCategoryInfoDTO();

        dto.setGroupDescription("Friends");
        dto.setPersonEmail("maria@gmail.com");
        dto.setCategoryDenomination("Netflix");

        String expectedDescription = "Friends";

        //Act
        String realGroupDescription = dto.getGroupDescription();

        //Assert
        assertEquals(expectedDescription, realGroupDescription);
    }

    @DisplayName("Test getPersonEmail")
    @Test
    public void getPersonEmailTest() {
        //Arrange
        CreateGroupCategoryInfoDTO dto = new CreateGroupCategoryInfoDTO();

        dto.setGroupDescription("Footbal Team");
        dto.setPersonEmail("jorge@gmail.com");
        dto.setCategoryDenomination("Sport");

        String expectedPersonEmail = "jorge@gmail.com";

        //Act
        String realPersonEmail = dto.getPersonEmail();

        //Assert
        assertEquals(expectedPersonEmail, realPersonEmail);
    }

    @DisplayName("Test getCategoryDenomination")
    @Test
    public void getCategoryDenominationTest() {
        //Arrange
        CreateGroupCategoryInfoDTO dto = new CreateGroupCategoryInfoDTO();

        dto.setGroupDescription("Sport");
        dto.setPersonEmail("10203@isep.ipp.pt");
        dto.setCategoryDenomination("Gasoline");

        String expectedCategoryDenomination = "Gasoline";

        //Act
        String realCategoryDenomination = dto.getCategoryDenomination();

        //Assert
        assertEquals(expectedCategoryDenomination, realCategoryDenomination);
    }

    /**
     * Tests for the equals method
     */

    @Test
    @DisplayName("Test to equals - Same Object")
    void equalsSameObject() {
        //Arrange
        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("gym");

        //Act
        boolean result = groupCategoryInfoDTO.equals(groupCategoryInfoDTO);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to equals - Null Object")
    void equalsNullObject() {
        //Arrange
        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        CreateGroupCategoryDTO groupCategoryinfoDTOnull = null;
        groupCategoryInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("gym");

        //Act
        boolean result = groupCategoryInfoDTO.equals(null);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - DifferentClass Object")
    void equalsDifferentObject() {
        //Arrange
        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("gym");

        AccountDTO accountDTO = new AccountDTO("marta@isep.pt", "savings", "2020 savings");
        //Act
        boolean result = groupCategoryInfoDTO.equals(accountDTO);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Same Parameters")
    void testEqualsSameParameters() {
        //Arrange:
        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("gym");

        CreateGroupCategoryInfoDTO groupCategoryInfoDTO2 = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO2.setPersonEmail("1191762@isep.ipp.pt");
        groupCategoryInfoDTO2.setCategoryDenomination("compras");
        groupCategoryInfoDTO2.setGroupDescription("gym");

        //Act
        boolean result = groupCategoryInfoDTO.equals(groupCategoryInfoDTO2);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to equals - Different Parameters")
    void testEqualsDifferentParameters() {
        //Arrange:
        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("gym");

        CreateGroupCategoryInfoDTO groupCategoryInfoDTO2 = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO2.setCategoryDenomination("compras");
        groupCategoryInfoDTO2.setGroupDescription("gym");

        //Act
        boolean result = groupCategoryInfoDTO.equals(groupCategoryInfoDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Different Parameters-Denomintion")
    void testEqualsDifferentParametersDenomination() {
        //Arrange:
        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("gym");

        CreateGroupCategoryInfoDTO groupCategoryInfoDTO2 = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO2.setCategoryDenomination("shop");
        groupCategoryInfoDTO2.setGroupDescription("gym");

        //Act
        boolean result = groupCategoryInfoDTO.equals(groupCategoryInfoDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Different Parameters-Description")
    void testEqualsDifferentParametersDescription() {
        //Arrange:
        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("ginásio");

        CreateGroupCategoryInfoDTO groupCategoryInfoDTO2 = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO2.setCategoryDenomination("compras");
        groupCategoryInfoDTO2.setGroupDescription("gym");

        //Act
        boolean result = groupCategoryInfoDTO.equals(groupCategoryInfoDTO2);

        //Assert:
        assertFalse(result);
    }


    @Test
    void testHashCodeTrue() {
        //Arrange:

        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("gym");

        CreateGroupCategoryInfoDTO groupCategoryInfoDTO2 = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO2.setCategoryDenomination("compras");
        groupCategoryInfoDTO2.setGroupDescription("gym");

        //Act:
        boolean result = groupCategoryInfoDTO.hashCode() == groupCategoryInfoDTO2.hashCode();

        //Assert:
        assertTrue(result);
    }

    @Test
    void testHashCodeFalse() {
        //Arrange:

        CreateGroupCategoryInfoDTO groupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO.setCategoryDenomination("compras");
        groupCategoryInfoDTO.setGroupDescription("ginásio");

        CreateGroupCategoryInfoDTO groupCategoryInfoDTO2 = new CreateGroupCategoryInfoDTO();
        groupCategoryInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupCategoryInfoDTO2.setCategoryDenomination("compras");
        groupCategoryInfoDTO2.setGroupDescription("gym");

        //Act:
        boolean result = groupCategoryInfoDTO.hashCode() == groupCategoryInfoDTO2.hashCode();

        //Assert:
        assertFalse(result);
    }
}