package switch2019.project.domain.domainEntities.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    /**
     * Test Equals - Different Objects
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
     * Test Equals - Same Object
     */

    @Test
    @DisplayName("Test Same Email - Same object")
    public void sameObject() {

        //Arrange:
        Email oneEmail = new Email("email@gmail.com");

        //Act:
        boolean result = oneEmail.equals(oneEmail);

        //Assert:
        assertTrue(result);
    }

    /**
     * Test Equals - Different Email
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
     * Test Equals - Different Class object
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

    /**
     * Test Equals - Null
     */

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
    @DisplayName("Test Same Hashcode")
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
    @DisplayName("Test Different Hashcode")
    public void differentHashCode () {

        //Arrange & Act:
        Email oneEmail = new Email("email@gmail.com");
        Email otherEmail = new Email("email.s@gmail.com");

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
     * Test valid email input - only numbers
     */

    @Test
    @DisplayName("Test Valid email input - numbers")
    public void validEmailInput_numbers() {

        //Arrange:
        Email oneEmail = new Email("1110203@gmail.com");
        String expected = "1110203@gmail.com";

        //Act:
        String real = oneEmail.getEmailAddress();

        //Assert:
        assertEquals(expected, real);

    }

    /**
     * Test valid email input
     */

    @Test
    @DisplayName("Test Valid email input ")
    public void validEmailInput_1() {

        //Arrange:
        Email oneEmail = new Email("12345678@isep.ipp.pt");
        String expected = "12345678@isep.ipp.pt";

        //Act:
        String real = oneEmail.getEmailAddress();

        //Assert:
        assertEquals(expected, real);

    }

    @Test
    @DisplayName("Test Valid email input ")
    public void validEmailInput_2() {

        //Arrange:
        Email oneEmail = new Email("joo_a3sd.12@live.com.au");
        String expected = "joo_a3sd.12@live.com.au";

        //Act:
        String real = oneEmail.getEmailAddress();

        //Assert:
        assertEquals(expected, real);

    }


    /**
     * Tests  invalid email input - No @
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
                assertEquals("The email is not valid.", invalidEmail.getMessage());
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
            assertEquals("The email is not valid.", invalidEmail.getMessage());
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
            assertEquals("The email can't be null.", invalidEmail.getMessage());
        }

    }

    /**
     * Tests  invalid email input - two dots
     */

    @Test
    @DisplayName("Test Invalid email input - Two Dots")
    public void invalidEmailInput_TwoDots() {

        //Arrange & Act:
        try {
            Email oneEmail = new Email("email@gmail..com");
        }

        //Assert
        catch (IllegalArgumentException invalidEmail) {
            assertEquals("The email is not valid.", invalidEmail.getMessage());
        }

    }

    /**
     * Tests  invalid email input - two "at sign"
     */

    @Test
    @DisplayName("Test Invalid email input - Two at sign")
    public void invalidEmailInput_TwoAtSign() {

        //Arrange & Act:
        try {
            Email oneEmail = new Email("email@@gmail.pt");
        }

        //Assert
        catch (IllegalArgumentException invalidEmail) {
            assertEquals("The email is not valid.", invalidEmail.getMessage());
        }

    }

    /**
     * Tests  invalid email input - two "at sign"
     */

    @Test
    @DisplayName("Test Invalid email input - Two at sign - Different Places")
    public void invalidEmailInput_TwoAtSign_differentPlaces() {

        //Arrange & Act:
        try {
            Email oneEmail = new Email("email@123@gmail.pt");
        }

        //Assert
        catch (IllegalArgumentException invalidEmail) {
            assertEquals("The email is not valid.", invalidEmail.getMessage());
        }

    }


    /**
     * Tests  invalid email input - No "@" | No "." "
     */

    @Test
    @DisplayName("Test Invalid email input - Two at sign")
    public void invalidEmailInput_NoAtSign_NoDot() {

        //Arrange & Act:
        try {
            Email oneEmail = new Email("emailgmailcom");
        }

        //Assert
        catch (IllegalArgumentException invalidEmail) {
            assertEquals("The email is not valid.", invalidEmail.getMessage());
        }

    }


}