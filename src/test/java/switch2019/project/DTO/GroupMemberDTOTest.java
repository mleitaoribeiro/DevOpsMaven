package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupMemberDTOTest {

    /**
     * Tests to validate GroupMemberDTO
     */
    @Test
    @DisplayName("Test if GroupMemberDTO can get the person name")
    void getPersonName() {
        //Arrange
        GroupMemberDTO dto = new GroupMemberDTO("Maria", "maria@gmail.com");
        String expected = "Maria";

        //Act
        String actual = dto.getPersonName();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if GroupMemberDTO can get the member email")
    void getMemberEmail() {
        //Arrange
        GroupMemberDTO dto = new GroupMemberDTO("Maria", "maria@gmail.com");
        String expected = "maria@gmail.com";

        //Act
        String actual = dto.getMemberEmail();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if two GroupMemberDTO are equal and have the same hashcode")
    void equalsHashCodeTrue() {
        //Arrange
        GroupMemberDTO dto1 = new GroupMemberDTO("Maria", "maria@gmail.com");
        GroupMemberDTO dto2 = new GroupMemberDTO("Maria", "maria@gmail.com");

        //Act && Assert
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(),dto2.hashCode());
    }

    @Test
    @DisplayName("Test if two GroupMemberDTO aren't equal and don't have the same hashcode")
    void equalsHashCodeFalse() {
        //Arrange
        GroupMemberDTO dto1 = new GroupMemberDTO("Helena", "helena@gmail.com");
        GroupMemberDTO dto2 = new GroupMemberDTO("Maria", "maria@gmail.com");

        //Act && Assert
        assertNotEquals(dto1, dto2);
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    @DisplayName("Test if two GroupMemberDTO are equal and have the same hashcode - same object")
    void equalsHashCodeSameObject() {
        //Arrange
        GroupMemberDTO dto1 = new GroupMemberDTO("Maria", "maria@gmail.com");

        //Act && Assert
        assertEquals(dto1, dto1);
        assertEquals(dto1.hashCode(),dto1.hashCode());
    }

    @Test
    @DisplayName("Test if two GroupMemberDTO aren't equal and don't have the same hashcode - different class")
    void equalsHashCodeFalseDifClass() {
        //Arrange
        GroupMemberDTO dto1 = new GroupMemberDTO("Helena", "helena@gmail.com");
        AreSiblingsDTO dto2 = new AreSiblingsDTO("helenaa@gmail.com", "maria@gmail.com");

        //Act && Assert
        assertNotEquals(dto1, dto2);
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    @DisplayName("Test if two GroupMemberDTO aren't equal and don't have the same hashcode - null")
    void equalsHashCodeFalseNull() {
        //Arrange
        GroupMemberDTO dto1 = new GroupMemberDTO("Helena", "helena@gmail.com");
        GroupMemberDTO dto2 = null;

        //Act && Assert
        assertNotEquals(dto1, dto2);
    }
}