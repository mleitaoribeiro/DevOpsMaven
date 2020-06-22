package switch2019.project.DTO.serviceDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetGroupTransactionInDateRangeDTOTest {

    /**
     * Tests for equals method
     */

    @Test
    @DisplayName("Test for method equals - exactly the same object")
    void testEqualsSameObject() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        // Act
        boolean result = dto.equals(dto);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method equals - two objects with the same parameters")
    void testEqualsTwoObjectSameParameters() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        GetGroupTransactionInDateRangeDTO dto2 = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        // Act
        boolean result = dto.equals(dto2);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method equals - different object")
    void testEqualsDifferentObject() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        GetGroupTransactionInDateRangeDTO dto2 = new GetGroupTransactionInDateRangeDTO("Buddies",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        // Act
        boolean result = dto.equals(dto2);

        // Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Test for method equals - object from different class")
    void testEqualsObjectFromDifferentClass() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        GetPersonalTransactionInDateRangeDTO dtoFromDifferentClass = new GetPersonalTransactionInDateRangeDTO("raquel@isep.ipp.pt",
                "19-01-2002", "25-01-2002");

        // Act
        boolean result = dto.equals(dtoFromDifferentClass);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test for method equals - null object")
    void testEqualsNullObject() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        // Act
        boolean result = dto.equals(null);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests for hashcode method
     */

    @Test
    @DisplayName("Test for method hashcode - same hashcode")
    void testHashCodeSameHashCode() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        GetGroupTransactionInDateRangeDTO dto2 = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        // Act
        boolean result = dto.hashCode() == dto2.hashCode();

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method hashcode - different hashcode")
    void testHashCodeDifferentHashCode() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        GetGroupTransactionInDateRangeDTO dto2 = new GetGroupTransactionInDateRangeDTO("Gym",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        // Act
        boolean result = dto.hashCode() == dto2.hashCode();

        // Assert
        assertFalse(result);
    }


    /**
     * Tests for the different getter methods
     */

    @Test
    @DisplayName("Test for method getGroupDescription")
    void getGroupDescription() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        String groupDescriptionExpected = "Friends";

        // Act
        String groupDescription = dto.getGroupDescription();

        // Assert
        assertEquals(groupDescriptionExpected, groupDescription);
    }

    @Test
    @DisplayName("Test for method getPersonEmail")
    void getPersonEmail() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        String personEmailExpected = "raquel@isep.ipp.pt";

        // Act
        String personEmail = dto.getPersonEmail();

        // Assert
        assertEquals(personEmailExpected, personEmail);
    }

    @Test
    @DisplayName("Test for method getInitialDate")
    void getInitialDate() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        String initialDateExpected = "19-01-2002";

        // Act
        String initialDate = dto.getInitialDate();

        // Assert
        assertEquals(initialDateExpected, initialDate);
    }

    @Test
    @DisplayName("Test for method getFinalDate")
    void getFinalDate() {

        // Arrange
        GetGroupTransactionInDateRangeDTO dto = new GetGroupTransactionInDateRangeDTO("Friends",
                "raquel@isep.ipp.pt", "19-01-2002", "25-01-2002");

        String finalDateExpected = "25-01-2002";

        // Act
        String finalDate = dto.getFinalDate();

        // Assert
        assertEquals(finalDateExpected, finalDate);
    }
}
