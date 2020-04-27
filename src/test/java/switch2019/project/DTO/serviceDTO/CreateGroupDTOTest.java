package switch2019.project.DTO.serviceDTO;

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
        CreateGroupDTO dto = new CreateGroupDTO("Futebol", "maria@isep.ipp.pt");
        String expected = "Futebol";

        //Act
        String actual = dto.getGroupDescription();

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void getPersonEmail() {
        //Arrange
        CreateGroupDTO dto = new CreateGroupDTO("Futebol", "maria@isep.ipp.pt");
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
        CreateGroupDTO dto = new CreateGroupDTO("Games", "marta@isep.ipp.pt");

        //Act
        boolean actual = dto.equals(dto);

        //Assert
        assertTrue(actual);
    }

    @Test
    void equalsNullObject() {
        //Arrange
        CreateGroupDTO dto = new CreateGroupDTO("Games", "marta@isep.ipp.pt");
        CreateGroupDTO dto2 = null;

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsDifferentObjectClass() {
        //Arrange
        CreateGroupDTO dto = new CreateGroupDTO("Games", "marta@isep.ipp.pt");
        Denomination denomination = new Denomination("School");

        //Act
        boolean actual = dto.equals(denomination);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsSameAttributes() {
        //Arrange
        CreateGroupDTO dto = new CreateGroupDTO("Games", "marta@isep.ipp.pt");
        CreateGroupDTO dto2 = new CreateGroupDTO("Games", "marta@isep.ipp.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertTrue(actual);
    }

    @Test
    void equalsDifferentGroupDescription() {
        //Arrange
        CreateGroupDTO dto = new CreateGroupDTO("Games", "marta@isep.ipp.pt");
        CreateGroupDTO dto2 = new CreateGroupDTO("School", "marta@isep.ipp.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsDifferentPersonEmail() {
        //Arrange
        CreateGroupDTO dto = new CreateGroupDTO("Games", "marta@isep.ipp.pt");
        CreateGroupDTO dto2 = new CreateGroupDTO("Games", "diana@isep.ipp.pt");

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
        CreateGroupDTO dto = new CreateGroupDTO("Games", "marta@isep.ipp.pt");
        CreateGroupDTO dto2 = new CreateGroupDTO("Games", "marta@isep.ipp.pt");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    void differentHashcode() {
        //Arrange
        CreateGroupDTO dto = new CreateGroupDTO("Games", "marta@isep.ipp.pt");
        CreateGroupDTO dto2 = new CreateGroupDTO("Games", "diana@isep.ipp.pt");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertFalse(actual);
    }

}