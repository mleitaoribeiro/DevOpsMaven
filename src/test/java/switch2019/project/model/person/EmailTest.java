package switch2019.project.model.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    /**
     * Test Same Email
     */

    @Test
    @DisplayName("Test Same Email - Different Objects")
    public void sameEmail() {

        //Arrange:
        Email oneEmail = new Email("email@gmail.com");
        Email otherEmail = new Email("email@gmail.com");

        //Act:
        boolean result = oneEmail.equals(otherEmail);

        //Assert:
        assertTrue(result);

    }

    /**
     * Test Same Email
     */

    @Test
    @DisplayName("Test Same Email - Different Objects")
    public void sameObject() {

        //Arrange:
        Email oneEmail = new Email("email@gmail.com");

        //Act:
        boolean result = oneEmail.equals(oneEmail);

        //Assert:
        assertTrue(result);
    }

    /**
     * Test Same Email
     */

    @Test
    @DisplayName("Test Same Email - Different email")
    public void differentEmail() {

        //Arrange:
        Email oneEmail = new Email("email@gmail.com");
        Email otherEmail = new Email("email1@gmail.com");

        //Act:
        boolean result = oneEmail.equals(otherEmail);

        //Assert:
        assertFalse(result);
    }

    /**
     * Test Same Email
     */

    @Test
    @DisplayName("Test Same Email - Different Class")
    public void otherObjectOfDifferentClass() {

        //Arrange:
        Email oneEmail = new Email("email@gmail.com");
        PersonName onePerson = new PersonName("João Cardoso");

        //Act:
        boolean result = oneEmail.equals(onePerson);

        //Assert:
        assertFalse(result);

    }

    @Test
    @DisplayName("Test Same Email - Null")
    public void emailNull() {

        //Arrange
        Email oneEmail = new Email("email@gmail.com");
        Email otherEmail = null;

        //Act:
        boolean result = oneEmail.equals(otherEmail);

        //Assert:
        assertFalse(result);
    }

    /**
     * Test if two emails have the same Hashcode
     */

    @Test
    @DisplayName("Test Same Email - Null")
    public void sameHashCode () {

        //Arrange & Act:
        Email oneEmail = new Email("email@gmail.com");
        Email otherEmail = new Email("email@gmail.com");

        //Assert:
        assertEquals(oneEmail.hashCode(), otherEmail.hashCode());
    }

    /**
     * Test if two emails have the same Hashcode
     */

    @Test
    @DisplayName("Test Same Email - Null")
    public void differentHashCode () {

        //Arrange & Act:
        Email oneEmail = new Email("email@gmail.com");
        Email otherEmail = new Email("email1@gmail.com");

        //Assert:
        assertNotEquals(oneEmail.hashCode(), otherEmail.hashCode());
    }


    /**
     * Test valid email input
     */

    @Test
    @DisplayName("Test Valid email input")
    public void validEmailInput() {

        //Arrange:
        Email oneEmail = new Email("email@gmail.com");
        String expected = "email@gmail.com";

        //Act:
        String real = oneEmail.getEmailAddress();

        //Assert:
        assertEquals(expected, real);

    }

    /**
     * Tests  invalid email input - - No @
     */

    @Test
    @DisplayName("Test Invalid email input - No @")
    public void invalidEmailInput() {

            //Arrange & Act:
            try {
                Email oneEmail = new Email("emailgmail.com");
            }

            //Assert
            catch (IllegalArgumentException invalidEmail) {
                assertEquals("The email it´s not valid", invalidEmail.getMessage());
            }

    }

    /**
     * Tests  invalid email input - no dot
     */

    @Test
    @DisplayName("Test Invalid email input - No Dot")
    public void invalidEmailInput_noDot() {

        //Arrange & Act:
        try {
            Email oneEmail = new Email("email@gmailcom");
        }

        //Assert
        catch (IllegalArgumentException invalidEmail) {
            assertEquals("The email it´s not valid", invalidEmail.getMessage());
        }

    }


    /**
     * Tests  invalid email input - Null
     */

    @Test
    @DisplayName("Test Invalid email input")
    public void invalidEmailInput_null() {

        //Arrange & Act:
        try {
            Email oneEmail = new Email(null);
        }

        //Assert
        catch (IllegalArgumentException invalidEmail) {
            assertEquals("The email can´t be null!", invalidEmail.getMessage());
        }

    }

}