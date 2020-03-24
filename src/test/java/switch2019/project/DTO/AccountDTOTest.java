package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDTOTest {

    @Test
    @DisplayName("test for getownerId")
    void getOwnerID() {
        //Arrange
        AccountDTO dto = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");
        String expected = "mailOwner";

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
        String expected = "mesadas";

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
        String expected = "mesada do Alex";

        //Act
        String actual = dto.getDescription();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test for Equals")
    void Equals() {
        //Arrange
        AccountDTO dtoExpected = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");

        //Act
        AccountDTO dtoActual = new AccountDTO(dtoExpected.getOwnerID(), dtoExpected.getDenomination(), dtoExpected.getDescription());

        //Assert
        assertEquals(dtoExpected, dtoActual);
    }

    @Test
    @DisplayName("test for hashcode")
    void Hashcode() {
        //Arrange
        AccountDTO dtoExpected = new AccountDTO("mailOwner", "mesadas", "mesada do Alex");

        //Act
        AccountDTO dtoActual = new AccountDTO(dtoExpected.getOwnerID(), dtoExpected.getDenomination(), dtoExpected.getDescription());

        //Assert
        assertEquals(dtoExpected.hashCode(), dtoActual.hashCode());
    }
}