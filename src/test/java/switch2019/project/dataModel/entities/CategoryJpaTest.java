package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryJpaTest {

        @Test
        void testEquals() {
            //Arrange
            CategoryJpa groupJpa = new CategoryJpa("1191765@isep.ipp.pt","test");

            CategoryJpa groupJpa2 = new CategoryJpa("1191765@isep.ipp.pt","test");

            //Act
            boolean result = groupJpa.equals(groupJpa2);

            //Assert
            assertTrue(result);
        }
        @Test
        void testEqualsNull() {
            //Arrange
            CategoryJpa groupJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

            CategoryJpa groupJpa2 = null;

            //Act
            boolean result = groupJpa.equals(groupJpa2);

            //Assert
            assertFalse(result);
        }

        @Test
        void testEqualsDifferentObjects() {
            //Arrange
            CategoryJpa groupJpa = new CategoryJpa("1191765@isep.ipp.pt","test");

            CategoryJpa groupJpa2 = new CategoryJpa("rick@gmail.com","test");

            //Act
            boolean result = groupJpa.equals(groupJpa2);

            //Assert
            assertFalse(result);
        }

        @Test
        void testHashCode() {
            //Arrange
            CategoryJpa groupJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

            CategoryJpa groupJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "test");

            //Assert
            assertEquals(groupJpa.hashCode(), groupJpa2.hashCode());

        }

    @Test
    void testHashCodeFalse() {
        //Arrange
        CategoryJpa groupJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa groupJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "gg");

        //Assert
        assertNotEquals(groupJpa.hashCode(), groupJpa2.hashCode());
    }

    @Test
    void testToString() {
        //Arrange
        CategoryJpa groupJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa groupJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "test");

        //Assert
        assertEquals(groupJpa.toString(), groupJpa2.toString());
    }

    @Test
    void testToStringFalse() {
        //Arrange
        CategoryJpa groupJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa groupJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "gg");

        //Assert
        assertNotEquals(groupJpa.toString(), groupJpa2.toString());
    }
}
