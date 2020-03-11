package switch2019.project.model.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.shared.Description;

import static org.junit.jupiter.api.Assertions.*;

class TypeTest {

    @Test
    @DisplayName("Validate toString method - CREDIT")
    void typeToStringCREDIT() {
        //Arrange
        Type credit = new Type(true);
        String expected = "CREDIT";

        //Assert
        assertEquals(expected, credit.toString());
    }

    @Test
    @DisplayName("Validate toString method - DEBIT")
    void typeToString() {
        //Arrange
        Type debit = new Type(false);
        String expected = "DEBIT";

        //Assert
        assertEquals(expected, debit.toString());
    }

    @Test
    @DisplayName("Get debit type")
    void getTypeDebit() {
        //Arrange
        Type debit = new Type(false);
        String expected = "DEBIT";
        debit.getType();

        //Assert
        assertEquals(expected, debit.toString());
    }

    @Test
    @DisplayName("Get credit type")
    void getTypeCredit() {
        //Arrange
        Type credit = new Type(false);
        String expected = "DEBIT";
        credit.getType();

        //Assert
        assertEquals(expected, credit.toString());
    }

    @Test
    @DisplayName("Test if two Types equals - false case")
    void testEquals() {
        //Arrange
        Type credit = new Type(true);
        Type debit = new Type(false);

        //Act
        boolean result = credit.equals(debit);
        //Assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Test if two Types equals - true case")
    void testEqualsTrue() {
        //Arrange
        Type type = new Type(true);
        Type otherType = new Type(true);

        //Act
        boolean result = type.equals(otherType);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two Types equals - null case")
    void testEqualsNull() {
        //Arrange
        Type type = new Type(true);
        Type nullType = null;

        //Act
        boolean result = type.equals(nullType);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two Types equals - different class case")
    void testEqualsDifferentClass() {
        //Arrange
        Type type = new Type(true);
        Description otherClass = null;

        //Act
        boolean result = type.equals(otherClass);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same hashcode")
    void testHashCodeTrue() {
        //Arrange
        Type type = new Type(true);
        Type otherType = new Type(true);

        //Assert
        assertEquals(type.hashCode(), otherType.hashCode());
    }

    @Test
    @DisplayName("Different hashcode")
    void testHashCodeFalse() {
        //Arrange
        Type type = new Type(true);
        Type otherType = new Type(false);

        //Assert
        assertNotEquals(type.hashCode(), otherType.hashCode());


    }
}