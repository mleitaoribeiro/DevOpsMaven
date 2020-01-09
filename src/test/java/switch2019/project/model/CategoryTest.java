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
        categoryDescription.setNameOfCategory("Food Expenses");
        //Assert
        assertEquals(expected, categoryDescription.getNameOfCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description - Main Scenario")
    void setCategory_validateInputSuccessfullScenario() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        String expected = "EDUCATION";
        //Act
        categoryDescription.setNameOfCategory("Education");
        //Assert
        assertEquals(expected, categoryDescription.getNameOfCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description- Numeric Descriptions Acceptable")
    void setCategory_validateNumericInput() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        String expected = "123";
        //Act
        categoryDescription.setNameOfCategory("123");
        //Assert
        assertEquals(expected, categoryDescription.getNameOfCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description - Remove Special Characters")
    void setCategory_validateSpecialCharacterInput() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        String expected = "SAUDE";
        //Act
        categoryDescription.setNameOfCategory("Saúde");
        //Assert
        assertEquals(expected, categoryDescription.getNameOfCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description - null case")
    void setCategory_nullCase() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        try {
            //Act
            categoryDescription.setNameOfCategory(null);
            fail();
        }
        //Assert
        catch (IllegalArgumentException setterEx) {
            assertEquals("The category description is not valid or it's missing. Please try again.", setterEx.getMessage());
        }
    }

    @Test
    @DisplayName("Verfiy if two categories are the same - ignore case - Main Scenario")
    void twoCategoriesAreEquals_ignoreCase() {
        //Arrange
        Category categoryDescription = new Category("Health");
        Category otherCategoryDescription = new Category("HEALTH");

        //Act
        boolean realResult = categoryDescription.equals(otherCategoryDescription);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verfiy if two categories are the same - ignore special Character - Main Scenario")
    void twoCategoriesAreEquals_ignoreSpecialCharacter() {
        //Arrange
        Category categoryDescription = new Category("saúde");
        Category otherCategoryDescription = new Category("saude");

        //Act
        boolean realResult = categoryDescription.equals(otherCategoryDescription);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verfiy if two categories are the same - numeric characters - Main Scenario")
    void twoCategoriesAreEquals_numericCategories() {
        //Arrange
        Category categoryDescription = new Category("123");
        Category otherCategoryDescription = new Category("123");

        //Act
        boolean realResult = categoryDescription.equals(otherCategoryDescription);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verfiy if two categories are the same - Not the same")
    void twoCategoriesAreNotTheSame() {
        //Arrange
        Category categoryDescription = new Category("Health");
        Category otherCategoryDescription = new Category("Education");

        //Act
        boolean realResult = categoryDescription.equals(otherCategoryDescription);

        //Assert
        assertEquals(false, realResult);
    }
}