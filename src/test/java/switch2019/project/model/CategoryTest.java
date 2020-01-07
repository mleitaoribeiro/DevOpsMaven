package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("Update of Category description - Main Scenario")
    void setCategory_updateDescription() {
        //Arrange
        Category categoryDescription = new Category("Health");
        String expected = "FOOD EXPENSES";
        //Act
        categoryDescription.setCategory("Food Expenses");
        //Assert
        assertEquals(expected, categoryDescription.getCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description - Main Scenario")
    void setCategory_validateInputSuccessfullScenario() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        String expected = "EDUCATION";
        //Act
        categoryDescription.setCategory("Education");
        //Assert
        assertEquals(expected, categoryDescription.getCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description- Numeric Descriptions Acceptable")
    void setCategory_validateNumericInput() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        String expected = "123";
        //Act
        categoryDescription.setCategory("123");
        //Assert
        assertEquals(expected, categoryDescription.getCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description - Remove Special Characters")
    void setCategory_validateSpecialCharacterInput() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        String expected = "SAUDE";
        //Act
        categoryDescription.setCategory("Saúde");
        //Assert
        assertEquals(expected, categoryDescription.getCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description - null case")
    void setCategory_nullCase() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        try {
            //Act
            categoryDescription.setCategory(null);
            fail();
        }
        //Assert
        catch (IllegalArgumentException setterEx) {
            assertEquals("The category description is not valid or it's missing. Please try again.", setterEx.getMessage());
        }
    }
}