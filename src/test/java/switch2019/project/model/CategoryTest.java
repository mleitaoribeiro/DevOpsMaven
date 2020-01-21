package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    /**
     * Check if category's description was updated
     */
    @Test
    @DisplayName("Update of Category description - Main Scenario")
    void setCategoryUpdateDescription() {
        //Arrange
        Category categoryDescription = new Category("Health");
        String expected = "FOOD EXPENSES";

        //Act
        categoryDescription.setNameOfCategory("Food Expenses");

        //Assert
        assertEquals(expected, categoryDescription.getNameOfCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description")
    void setCategoryValidateInputSuccessfullScenario() {
        //Arrange
        Category categoryDescription = new Category("Not Defined");
        String expected = "EDUCATION";

        //Act
        categoryDescription.setNameOfCategory("Education");

        //Assert
        assertEquals(expected, categoryDescription.getNameOfCategory());
    }

    @Test
    @DisplayName("Validate user's input of Category description - Numeric Descriptions Acceptable")
    void setCategoryValidateNumericInput() {
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
    void setCategoryValidateSpecialCharacterInput() {
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
    void setCategoryNullCase() {
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

    /**
     * Check if two categories are the same
     */
    @Test
    @DisplayName("Verfiy if two categories are the same - ignore case - Main Scenario")
    void twoCategoriesAreEqualsIgnoreCase() {
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
    void twoCategoriesAreEqualsIgnoreSpecialCharacter() {
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
    void twoCategoriesAreEqualsNumericCategories() {
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

    @Test
    @DisplayName("Verfiy if two categories are the same - same object")
    void twoCategoriesAreEqualsSame () {
        //Arrange
        Category categoryDescription = new Category("Health");

        //Assert
        assertEquals(categoryDescription, categoryDescription);
    }

    @Test
    @DisplayName("Verify if equals method verifies if object is not of the same type")
    public void testIfACategoryIsNotEqualToAnotherObject() {
        //Arrange:
        Category categoryPlaceholder = new Category("Dinner");
        Group groupPlaceholder = new Group("Friends");

        //Act:
        boolean result = categoryPlaceholder.equals(groupPlaceholder);

        //Assert:
        assertEquals(false, result);
    }

    /**
     * Test if two categories have the same Hashcode
     */

    @Test
    @DisplayName("Test if two categories are the same - same HashCode")
    public void testIfTwoCategoriesHaveTheSameHashCodeSame() {

        //Arrange & Act
        Category category1 = new Category("Health");
        Category category2 = new Category("Health");

        //Assert
        assertEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    @DisplayName("Test if two categories are the same - not the same HashCode")
    public void testIfTwoCategoriesHaveTheSameHashCodeNotSame() {

        //Arrange & Act
        Category category1 = new Category("Health");
        Category category2 = new Category("Education");

        //Assert
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }
}
