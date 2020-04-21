package switch2019.project.DTO.ServiceDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.SerializationDTO.CategoryDTO;
import switch2019.project.DTO.ServiceDTO.AreSiblingsDTO;

import static org.junit.jupiter.api.Assertions.*;

class AreSiblingsDTOTest {

    @Test
    @DisplayName("Test getEmailPersonOne")
    void getEmailPersonOne() {

        //Arrange
        AreSiblingsDTO dto = new AreSiblingsDTO("email1", "email2");
        String expected = "email1";

        //Act
        String actual = dto.getEmailPersonOne();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test getEmailPersonTwo")
    void getEmailPersonTwo() {

        //Arrange
        AreSiblingsDTO dto = new AreSiblingsDTO("email1", "email2");
        String expected = "email2";

        //Act
        String actual = dto.getEmailPersonTwo();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashCode() {

        // Arrange
        AreSiblingsDTO dto1 = new AreSiblingsDTO("email1@hola.com", "email2@hola.com");
        AreSiblingsDTO dto2 = new AreSiblingsDTO("email1@hola.com", "email2@hola.com");

        // Act
        boolean result = dto1.hashCode() == dto2.hashCode();

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test hashcode - Same Hashcode")
    void testHashCodeSame() {

        // Arrange
        AreSiblingsDTO dto1 = new AreSiblingsDTO("email1@hola.com", "email2@hola.com");
        AreSiblingsDTO dto2 = new AreSiblingsDTO("email1@hola.com", "email2@hola.com");


        // Act & Assert
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }


    @Test
    @DisplayName("Test hashcode - Different Hashcode")
    void testHashCodeDifferent() {

        // Arrange
        AreSiblingsDTO dto1 = new AreSiblingsDTO("email1@hola.com", "email2@hola.com");
        AreSiblingsDTO dto2 = new AreSiblingsDTO("qwerty@hola.com", "ssss@hola.com");


        // Act & Assert
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }




    @Test
    @DisplayName("Test Equals - Same object")
    void testEqualsSameObject() {

        // Arrange
        AreSiblingsDTO dto = new AreSiblingsDTO("email1", "email2");

        // Act
        boolean result = dto.equals(dto);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals - Different object class")
    void testEqualsObjectDifferentClass() {

        // Arrange
        AreSiblingsDTO areSiblingsDTO = new AreSiblingsDTO("email1", "email2");
        CategoryDTO categoryDTO = new CategoryDTO("games", "marta");

        // Act
        boolean result = areSiblingsDTO.equals(categoryDTO);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals - Equals Same Attributes")
    void testEqualsSameAttributes() {

        // Arrange
        AreSiblingsDTO areSiblingsDTO1 = new AreSiblingsDTO("email1", "email2");
        AreSiblingsDTO areSiblingsDTO2 = new AreSiblingsDTO("email1", "email2");

        // Act
        boolean result = areSiblingsDTO1.equals(areSiblingsDTO2);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals - Equals Different Email1")
    void testEqualsDifferentEmail1() {

        // Arrange
        AreSiblingsDTO areSiblingsDTO1 = new AreSiblingsDTO("email1", "email2");
        AreSiblingsDTO areSiblingsDTO2 = new AreSiblingsDTO("email1", "email3");

        // Act
        boolean result = areSiblingsDTO1.equals(areSiblingsDTO2);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals - Equals Different Email2")
    void testEqualsDifferentEmail2() {

        // Arrange
        AreSiblingsDTO areSiblingsDTO1 = new AreSiblingsDTO("email1", "email2");
        AreSiblingsDTO areSiblingsDTO2 = new AreSiblingsDTO("email3", "email2");

        // Act
        boolean result = areSiblingsDTO1.equals(areSiblingsDTO2);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals - Equals Different Email1 & Email2")
    void testEqualsDifferentEmails() {

        // Arrange
        AreSiblingsDTO areSiblingsDTO1 = new AreSiblingsDTO("email1", "email2");
        AreSiblingsDTO areSiblingsDTO2 = new AreSiblingsDTO("email3", "email4");

        // Act
        boolean result = areSiblingsDTO1.equals(areSiblingsDTO2);

        // Assert
        assertFalse(result);
    }
}