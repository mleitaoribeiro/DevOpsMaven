package switch2019.project.DTO.SerializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDTOTest {

    @Test
    @DisplayName("test for getOwnerId")
    void getOwnerID() {

        //Arrange
        AccountDTO dto = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");
        String expected = "MAILOWNER";

        //Act
        String actual = dto.getOwnerID();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test for getDenomination")
    void getDenomination() {

        //Arrange
        AccountDTO dto = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");
        String expected = "MESADAS";

        //Act
        String actual = dto.getDenomination();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test for getDescription")
    void getDescription() {

        //Arrange
        AccountDTO dto = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");
        String expected = "MESADA DO ALEX";

        //Act
        String actual = dto.getDescription();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test for Equals - same attributes")
    void EqualsSameAttributes() {

        //Arrange
        AccountDTO dtoExpected = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");

        //Act
        AccountDTO dtoActual = new AccountDTO(dtoExpected.getOwnerID(), dtoExpected.getDenomination(), dtoExpected.getDescription());

        //Assert
        assertEquals(dtoExpected, dtoActual);
    }

    @Test
    @DisplayName("test for Equals - different ownerID")
    void EqualsDifferentOwnerID() {

        //Arrange
        AccountDTO dtoExpected = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");

        //Act
        AccountDTO dtoActual = new AccountDTO("marta", dtoExpected.getDenomination(), dtoExpected.getDescription());

        //Assert
        assertNotEquals(dtoExpected, dtoActual);
    }

    @Test
    @DisplayName("test for Equals - different denomination")
    void EqualsDifferentDenomination() {

        //Arrange
        AccountDTO dtoExpected = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");

        //Act
        AccountDTO dtoActual = new AccountDTO(dtoExpected.getOwnerID(), "poupanças", dtoExpected.getDescription());

        //Assert
        assertNotEquals(dtoExpected, dtoActual);
    }

    @Test
    @DisplayName("test for Equals - different description")
    void EqualsDifferentDescription() {

        //Arrange
        AccountDTO dtoExpected = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");

        //Act
        AccountDTO dtoActual = new AccountDTO(dtoExpected.getOwnerID(), dtoExpected.getDenomination(), "mesada da Marta");

        //Assert
        assertNotEquals(dtoExpected, dtoActual);
    }

    @Test
    @DisplayName("test for Equals - same object")
    void EqualsSameObject() {

        //Arrange
        AccountDTO dto = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");

        //Act
        boolean result = dto.equals(dto);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("test for Equals - different object class")
    void EqualsSameObjectClass() {

        //Arrange
        AccountDTO accountDTO = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");
        CategoryDTO categoryDTO = new CategoryDTO("games", "mailOwner");

        //Act
        boolean result = accountDTO.equals(categoryDTO);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("test for hashcode - equal")
    void HashcodeSame() {

        //Arrange
        AccountDTO dtoExpected = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");

        //Act
        AccountDTO dtoActual = new AccountDTO(dtoExpected.getOwnerID(), dtoExpected.getDenomination(), dtoExpected.getDescription());

        //Assert
        assertEquals(dtoExpected.hashCode(), dtoActual.hashCode());
    }

    @Test
    @DisplayName("test for hashcode - not equal")
    void HashcodeDifferent() {

        //Arrange
        AccountDTO accountDTOAlex = new AccountDTO("alex", "mesadas", "mesada do Alex");
        AccountDTO accountDTOMarta = new AccountDTO("marta", "mesadas", "mesada da Marta");

        //Act & Assert
        assertNotEquals(accountDTOAlex.hashCode(), accountDTOMarta.hashCode());
    }
}