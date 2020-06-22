package switch2019.project.DTO.deserializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.Denomination;

import static org.junit.jupiter.api.Assertions.*;

class createGroupInfoDTOTest {


    @Test
    @DisplayName("Test to getPersonEmail")
    void getPersonEmail() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setPersonEmail("raquel@isep.pt");
        String expected = "raquel@isep.pt";

        //Act
        String real = dto.getPersonEmail();

        //Assert
        assertEquals(expected, real);
    }


    @Test
    @DisplayName("Test to getGroupDescription")
    void getGroupDescription() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        String expected = "Gym Buddies";

        //Act
        String real = dto.getGroupDescription();

        //Assert
        assertEquals(expected, real);
    }


    /**
     * Tests for the equals method
     */

    @Test
    @DisplayName("Test to equals - Same Object")
    void equalsSameObject() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        dto.setPersonEmail("raquel@isep.pt");

        //Act
        boolean actual = dto.equals(dto);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Test to equals - Null Object")
    void equalsNullObject() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        dto.setPersonEmail("raquel@isep.pt");

        CreateGroupInfoDTO dto2 = null;

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Test to equals - Object from different class")
    void equalsDifferentObjectClass() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        dto.setPersonEmail("raquel@isep.pt");

        Denomination denomination = new Denomination("Gym");

        //Act
        boolean actual = dto.equals(denomination);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Test to equals - Same attributes")
    void equalsSameAttributes() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        dto.setPersonEmail("raquel@isep.pt");

        CreateGroupInfoDTO dto2 = new CreateGroupInfoDTO();
        dto2.setGroupDescription("Gym Buddies");
        dto2.setPersonEmail("raquel@isep.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Test to equals - Different Group Description")
    void equalsDifferentGroupDescription() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        dto.setPersonEmail("raquel@isep.pt");

        CreateGroupInfoDTO dto2 = new CreateGroupInfoDTO();
        dto2.setGroupDescription("Gym Team");
        dto2.setPersonEmail("raquel@isep.pt");

        //Act
        boolean actual = dto.equals(dto2);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Test to equals - Different Person Email")
    void equalsDifferentPersonEmail() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        dto.setPersonEmail("raquel@isep.pt");

        CreateGroupInfoDTO dto2 = new CreateGroupInfoDTO();
        dto2.setGroupDescription("Gym Buddies");
        dto2.setPersonEmail("kelle@isep.pt");

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
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        dto.setPersonEmail("raquel@isep.pt");

        CreateGroupInfoDTO dto2 = new CreateGroupInfoDTO();
        dto2.setGroupDescription("Gym Buddies");
        dto2.setPersonEmail("raquel@isep.pt");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Test to HashCode - Different Hashcode")
    void differentHashcode() {
        //Arrange
        CreateGroupInfoDTO dto = new CreateGroupInfoDTO();
        dto.setGroupDescription("Gym Buddies");
        dto.setPersonEmail("raquel@isep.pt");

        CreateGroupInfoDTO dto2 = new CreateGroupInfoDTO();
        dto2.setGroupDescription("Gym Team");
        dto2.setPersonEmail("raquel@isep.pt");

        //Act
        boolean actual = dto.hashCode() == dto2.hashCode();

        //Assert
        assertFalse(actual);
    }

}