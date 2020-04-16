package switch2019.project.DTO.SerializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SiblingsDTOTest {

    /**
     * Tests for the get method
     */

    @Test
    @DisplayName("Test to get method")
    void getSiblings() {
        //Arrange
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);
        SiblingsDTO siblingsDTO1 = new SiblingsDTO(false);

        String expected = "They are siblings.";
        String expected1 = "They are not siblings.";

        //Act & Assert
        assertEquals(expected, siblingsDTO.getSiblings());
        assertEquals(expected1, siblingsDTO1.getSiblings());
    }

    /**
     * Tests for the equals method
     */

    @Test
    @DisplayName("Test to equals - same attributes")
    void testEqualsSameAttributes() {
        //Arrange
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);
        SiblingsDTO siblingsDTO1 = new SiblingsDTO(true);

        //Act & Assert
        assertEquals(siblingsDTO, siblingsDTO1);
    }

    @Test
    @DisplayName("Test to equals - same object")
    void testEqualsSameObject() {
        //Arrange
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);
        SiblingsDTO siblingsDTO1 = siblingsDTO;

        //Act
        boolean actual = siblingsDTO.equals(siblingsDTO1);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Test to equals - object from different class")
    void testEqualsObjectFromDifferentClass() {
        //Arrange
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);
        GroupDTO groupDTO = new GroupDTO("group");

        //Act
        boolean actual = siblingsDTO.equals(groupDTO);

        //Assert
        assertFalse(actual);
    }

    /**
     * Tests for the hashcode method
     */

    @Test
    @DisplayName("Test to hashcode - Same Hashcode")
    void testHashCode() {
        //Arrange
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);
        SiblingsDTO siblingsDTO1 = new SiblingsDTO(true);

        //Act
        boolean actual = siblingsDTO.hashCode() == siblingsDTO1.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Test to hashcode - Different Hashcode")
    void testHashCodeDifferentHashcode() {
        //Arrange
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);
        SiblingsDTO siblingsDTO1 = new SiblingsDTO(false);

        //Act
        boolean actual = siblingsDTO.hashCode() == siblingsDTO1.hashCode();

        //Assert
        assertFalse(actual);
    }
}