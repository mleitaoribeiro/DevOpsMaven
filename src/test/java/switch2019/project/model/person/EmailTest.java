package switch2019.project.model.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.repository.CategoryRepository;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

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
        String real = oneEmail.getEmail();

        //Assert:
        assertEquals(expected, real);

    }

    /**
     * Tests  invalid email input - - No @
     */

    @Test
    @DisplayName("Test Invalid email input - No @")
    public void invalidEmailInput() {

            ///Arrange & Act:
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

        ///Arrange & Act:
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

        ///Arrange & Act:
        try {
            Email oneEmail = new Email(null);
        }

        //Assert
        catch (IllegalArgumentException invalidEmail) {
            assertEquals("The email it´s not valid", invalidEmail.getMessage());
        }

    }

}