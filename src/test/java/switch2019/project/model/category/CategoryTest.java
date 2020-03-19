package switch2019.project.model.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.*;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    /**
     * Check if two categories are the same
     */
    @Test
    @DisplayName("Verfiy if two categories are the same - ignore case - Main Scenario")
    void twoCategoriesAreEqualsIgnoreCase() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        Category categoryDescription = new Category(new Denomination("Health"), person1.getID());
        Category otherCategoryDescription = new Category(new Denomination("HEALTH"), person1.getID());

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
        Category categoryDescription = new Category(new Denomination("saúde"), person1.getID());
        Category otherCategoryDescription = new Category(new Denomination("saude"), person1.getID());

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
        Category categoryDescription = new Category(new Denomination("123"), person1.getID());
        Category otherCategoryDescription = new Category(new Denomination("123"), person1.getID());

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
        Category categoryDescription = new Category(new Denomination("Health"),person1.getID());
        Category otherCategoryDescription = new Category(new Denomination("Education"),person1.getID());

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
        Group groupPlaceholder = new Group(new Description("Friends"));

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
        Category category1 = new Category(new Denomination("Health"),person1.getID());
        Category category2 = new Category(new Denomination("Education"),person1.getID());

        //Assert
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }

    /**
     * Test for toString method
     */

    @Test
    @DisplayName("Test toString method- true ")
    void testToStringMethod() {
        //Arrange
        Category category = new Category(new Denomination("FOOD"),
                new PersonID(new Email("marta@gmail.com")));
        String expectedResult = "FOOD, marta@gmail.com";
        //Act
        String realResult = category.toString();
        //Assert
        assertEquals(expectedResult, realResult);
    }

    @Test
    @DisplayName("Test toString method - false")
    void testToStringMethodFalse() {
        //Arrange
        Category category = new Category(new Denomination("FOOD"),
                new PersonID(new Email("marta@gmail.com")));
        String unExpectedResult = "marta@gmail.com, FOOD";
        //Act
        String realResult = category.toString();
        //Assert
        assertNotEquals(unExpectedResult, realResult);
    }

    @Test
    void getID() {
        //Arrange
        Category category = new Category(new Denomination("FOOD"),
                new PersonID(new Email("marta@gmail.com")));
        CategoryID expected = new CategoryID(new Denomination("FOOD"),
                new PersonID(new Email("marta@gmail.com")));

        //Act
        CategoryID actual = category.getID();

        //Assert
        assertEquals(expected, actual);
    }
}
