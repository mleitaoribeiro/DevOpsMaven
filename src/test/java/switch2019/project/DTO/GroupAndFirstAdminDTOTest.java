package switch2019.project.DTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.Denomination;

import static org.junit.jupiter.api.Assertions.*;

class GroupAndFirstAdminDTOTest {

    /**
     * Tests for the equals override
     */

    @Test
    void equalsSameObject() {
        //Arrange
        GroupAndFirstAdminDTO dto = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");

        //Act
        boolean actual = dto.equals(dto);

        //Assert
        assertTrue(actual);
    }

    @Test
    void equalsNullObject() {
        //Arrange
        GroupAndFirstAdminDTO dto = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");
        GroupAndFirstAdminDTO dto2 = null;

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsDifferentObjectClass() {
        //Arrange
        GroupAndFirstAdminDTO dto = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");
        Denomination denomination = new Denomination("School");

        //Act
        boolean actual = dto.equals(denomination);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsSameAttributes() {
        //Arrange
        GroupAndFirstAdminDTO dto = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");
        GroupAndFirstAdminDTO dto2 = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertTrue(actual);
    }

    @Test
    void equalsDifferentGroupDescription() {
        //Arrange
        GroupAndFirstAdminDTO dto = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");
        GroupAndFirstAdminDTO dto2 = new GroupAndFirstAdminDTO("School", "marta@isep.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    void equalsDifferentAdmin() {
        //Arrange
        GroupAndFirstAdminDTO dto = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");
        GroupAndFirstAdminDTO dto2 = new GroupAndFirstAdminDTO("Games", "alex@isep.pt");

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
        GroupAndFirstAdminDTO dto = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");
        GroupAndFirstAdminDTO dto2 = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    void differentHashcode() {
        //Arrange
        GroupAndFirstAdminDTO dto = new GroupAndFirstAdminDTO("Games", "marta@isep.pt");
        GroupAndFirstAdminDTO dto2 = new GroupAndFirstAdminDTO("School", "marta@isep.pt");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertFalse(actual);
    }
}