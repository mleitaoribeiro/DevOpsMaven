package switch2019.project.DTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.Denomination;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupDTOTest {

    /**
     * Tests for the different getter methods
     */

    @Test
    void getGroupDescription() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Futebol", "maria@isep.ipp.pt");
        String expected = "Futebol";

        //Act
        String actual = dto.getGroupDescription();

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void getPersonEmail() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Futebol", "maria@isep.ipp.pt");
        String expected = "maria@isep.ipp.pt";

        //Act
        String actual = dto.getPersonEmail();

        //Assert
        assertEquals(expected,actual);
    }

    /**
     * Tests for the equals override
     */

    @Test
    void equalsSameObject() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Games", "marta@isep.ipp.pt");

        //Act
        boolean actual = dto.equals(dto);

        //Assert
        assertTrue(actual);
    }

    @Test
    void equalsNullObject() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Games", "marta@isep.ipp.pt");
        createGroupDTO dto2 = null;

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsDifferentObjectClass() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Games", "marta@isep.ipp.pt");
        Denomination denomination = new Denomination("School");

        //Act
        boolean actual = dto.equals(denomination);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsSameAttributes() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Games", "marta@isep.ipp.pt");
        createGroupDTO dto2 = new createGroupDTO("Games", "marta@isep.ipp.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertTrue(actual);
    }

    @Test
    void equalsDifferentGroupDescription() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Games", "marta@isep.ipp.pt");
        createGroupDTO dto2 = new createGroupDTO("School", "marta@isep.ipp.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsDifferentPersonEmail() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Games", "marta@isep.ipp.pt");
        createGroupDTO dto2 = new createGroupDTO("Games", "diana@isep.ipp.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    /**
     * Tests for the hashcode override
     */

    @Test
    void sameHashcode() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Games", "marta@isep.ipp.pt");
        createGroupDTO dto2 = new createGroupDTO("Games", "marta@isep.ipp.pt");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    void differentHashcode() {
        //Arrange
        createGroupDTO dto = new createGroupDTO("Games", "marta@isep.ipp.pt");
        createGroupDTO dto2 = new createGroupDTO("Games", "diana@isep.ipp.pt");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertFalse(actual);
    }

}