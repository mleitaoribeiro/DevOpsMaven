package switch2019.project.DTO.deserializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupAccountInfoDTOTest {

    @Test
    @DisplayName("Test do getPersonEmail method")
    void getPersonEmail() {
        //Assert
        CreateGroupAccountInfoDTO dto = new CreateGroupAccountInfoDTO();
        String personEmailExpected = "raquel@xpto.pt";

        //Act:
        dto.setPersonEmail("raquel@xpto.pt");

        //Assert:
        assertEquals(personEmailExpected, dto.getPersonEmail());
    }



    @Test
    @DisplayName("Test do getAccountDenomination method")
    void getAccountDenomination() {
        //Assert
        CreateGroupAccountInfoDTO dto = new CreateGroupAccountInfoDTO();
        String accountDenominationExpected = "Fitness";

        //Act:
        dto.setAccountDenomination("Fitness");

        //Assert:
        assertEquals(accountDenominationExpected, dto.getAccountDenomination());
    }

    @Test
    @DisplayName("Test do getAccountDescription method")
    void getAccountDescription() {
        //Assert
        CreateGroupAccountInfoDTO dto = new CreateGroupAccountInfoDTO();
        String accountDescriptionExpected = "Gym Expenses";

        //Act:
        dto.setAccountDescription("Gym Expenses");

        //Assert:
        assertEquals(accountDescriptionExpected, dto.getAccountDescription());
    }

    /**
     * Tests for the equals method
     */


    @Test
    @DisplayName("Test to equals - Same Object")
    void equalsSameObject() {
        //Arrange
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("compras");
        groupAccountInfoDTO.setAccountDescription("balada");

        //Act
        boolean result = groupAccountInfoDTO.equals(groupAccountInfoDTO);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to equals - Null Object")
    void equalsNullObject() {
        //Arrange
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        CreateGroupAccountDTO groupAccountinfoDTOnull = null;
        groupAccountInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("compras");
        groupAccountInfoDTO.setAccountDescription("balada");

        //Act
        boolean result = groupAccountInfoDTO.equals(groupAccountinfoDTOnull);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - DifferentClass Object")
    void equalsDifferentObject() {
        //Arrange
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("compras");
        groupAccountInfoDTO.setAccountDescription("balada");


        AccountDTO accountDTO = new AccountDTO("marta@isep.pt", "savings", "2020 savings");
        //Act
        boolean result = groupAccountInfoDTO.equals(accountDTO);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Same Parameters")
    void testEqualsSameParameters() {
        //Arrange:

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("compras");
        groupAccountInfoDTO.setAccountDescription("balada");

        CreateGroupAccountInfoDTO groupAccountInfoDTO2 = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO2.setPersonEmail("1191762@isep.ipp.pt");
        groupAccountInfoDTO2.setAccountDenomination("compras");
        groupAccountInfoDTO2.setAccountDescription("balada");


        //Act
        boolean result = groupAccountInfoDTO.equals(groupAccountInfoDTO2);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to equals - Different Parameters")
    void testEqualsDifferentParameters() {
        //Arrange:

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("compras");
        groupAccountInfoDTO.setAccountDescription("balada");

        CreateGroupAccountInfoDTO groupAccountInfoDTO2 = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO2.setAccountDenomination("compras");
        groupAccountInfoDTO2.setAccountDescription("balada");


        //Act
        boolean result = groupAccountInfoDTO.equals(groupAccountInfoDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Different Parameters-Denomination")
    void testEqualsDifferentParametersDenomination() {
        //Arrange:

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("compras");
        groupAccountInfoDTO.setAccountDescription("balada");

        CreateGroupAccountInfoDTO groupAccountInfoDTO2 = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO2.setAccountDenomination("bolas");
        groupAccountInfoDTO2.setAccountDescription("balada");


        //Act
        boolean result = groupAccountInfoDTO.equals(groupAccountInfoDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Different Parameters-Description")
    void testEqualsDifferentParametersDescription() {
        //Arrange:

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("bolas");
        groupAccountInfoDTO.setAccountDescription("balada");

        CreateGroupAccountInfoDTO groupAccountInfoDTO2 = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO2.setAccountDenomination("bolas");
        groupAccountInfoDTO2.setAccountDescription("noite");


        //Act
        boolean result = groupAccountInfoDTO.equals(groupAccountInfoDTO2);

        //Assert:
        assertFalse(result);
    }
    @Test
    @DisplayName("Test to equals - Different Parameters-GroupDescription")
    void testEqualsDifferentParametersGDescription() {
        //Arrange:

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("bolas");
        groupAccountInfoDTO.setAccountDescription("balada");

        CreateGroupAccountInfoDTO groupAccountInfoDTO2 = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO2.setAccountDenomination("bolas");
        groupAccountInfoDTO2.setAccountDescription("noite");


        //Act
        boolean result = groupAccountInfoDTO.equals(groupAccountInfoDTO2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testHashCodeTRUE() {
        //Arrange:

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("bolas");
        groupAccountInfoDTO.setAccountDescription("noite");

        CreateGroupAccountInfoDTO groupAccountInfoDTO2 = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO2.setAccountDenomination("bolas");
        groupAccountInfoDTO2.setAccountDescription("noite");

        //Act:
        boolean result = groupAccountInfoDTO.hashCode() == groupAccountInfoDTO2.hashCode();

        //Assert:
        assertTrue(result);
    }

    @Test
    void testHashCodeFalse() {
        //Arrange:

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO.setAccountDenomination("bolas");
        groupAccountInfoDTO.setAccountDescription("noite");

        CreateGroupAccountInfoDTO groupAccountInfoDTO2 = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO2.setPersonEmail("marge@isep.ipp.pt");
        groupAccountInfoDTO2.setAccountDenomination("bolas");
        groupAccountInfoDTO2.setAccountDescription("noitadas");

        //Act:
        boolean result = groupAccountInfoDTO.hashCode() == groupAccountInfoDTO2.hashCode();

        //Assert:
        assertFalse(result);
    }
}