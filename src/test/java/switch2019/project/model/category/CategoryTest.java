package switch2019.project.model.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.frameworks.Owner;
import switch2019.project.model.frameworks.OwnerID;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.PersonID;

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
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        Category categoryDescription = new Category("Health", person1.getID());
        Category otherCategoryDescription = new Category("HEALTH", person1.getID());

        //Act
        boolean realResult = categoryDescription.equals(otherCategoryDescription);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verify if two categories are the same - ignore special Character - Main Scenario")
    void twoCategoriesAreEqualsIgnoreSpecialCharacter() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        Category categoryDescription = new Category("saúde", person1.getID());
        Category otherCategoryDescription = new Category("saude", person1.getID());

        //Act
        boolean realResult = categoryDescription.equals(otherCategoryDescription);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verify if two categories are the same - numeric characters - Main Scenario")
    void twoCategoriesAreEqualsNumericCategories() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        Category categoryDescription = new Category("123", person1.getID());
        Category otherCategoryDescription = new Category("123", person1.getID());

        //Act
        boolean realResult = categoryDescription.equals(otherCategoryDescription);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verify if two categories are the same - Not the same")
    void twoCategoriesAreNotTheSame() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        Category categoryDescription = new Category("Health",person1.getID());
        Category otherCategoryDescription = new Category("Education",person1.getID());

        //Act
        boolean realResult = categoryDescription.equals(otherCategoryDescription);

        //Assert
        assertEquals(false, realResult);
    }

    @Test
    @DisplayName("Verify if two categories are the same - same object")
    void twoCategoriesAreEqualsSame() {
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
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Act
        Category category1 = new Category("Health",person1.getID());
        Category category2 = new Category("Education",person1.getID());

        //Assert
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }
}
