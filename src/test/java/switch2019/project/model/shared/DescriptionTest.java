package switch2019.project.model.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.category.Category;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    @DisplayName("Test for description - happy case")
    public void setDescription() {
        //Arrange
        Description description = new Description("Mercearia");

        //Act
        description.getDescriptionValue();

        //Assert
        assertEquals("MERCEARIA", description.getDescriptionValue());
    }


    @Test
    @DisplayName("Testing for null")
    public void descriptionIsNull() {
        try {
            new Description(null);
        } catch (IllegalArgumentException description) {
            assertEquals("The description can't be null or empty. ", description.getMessage());
        }
    }

    @Test
    @DisplayName("Testing for empty")
    public void descriptionIsEmpty() {
        try {
            new Description("");
        } catch (IllegalArgumentException description) {
            assertEquals("The description can't be null or empty. ", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if two descriptions are equal")
    public void equalDescriptions() {
        Description firstDescription = new Description("Mercearia");
        Description secondDescription = new Description("Mercearia");

        boolean result= firstDescription.equals(secondDescription);

        assertEquals(true,result);
    }
    @Test
    @DisplayName("Test if two descriptions are equal - with upper case")
    public void equalDescriptionsUpperCase() {
        Description firstDescription = new Description("Mercearia");
        Description secondDescription = new Description("MERCEARIA");

        boolean result= firstDescription.equals(secondDescription);

        assertEquals(true,result);
    }


    @Test
    @DisplayName("Test if two descriptions are equal - false")
    public void differentDescriptions() {
        Description firstDescription = new Description("Mercearia");
        Description secondDescription = new Description("FARMACIA");

        boolean result= firstDescription.equals(secondDescription);

        assertEquals(false,result);
    }

    @Test
    @DisplayName("Test same description")
    public void sameDescription() {
        Description firstDescription = new Description("Mercearia");


        boolean result= firstDescription.equals(firstDescription);

        assertTrue(result);
    }

    @Test
    @DisplayName("Test if is the same object - false")
    public void sameObjectFalse() {
        Description firstDescription = new Description("Mercearia");
        Category category = new Category("Talho");


        boolean result= firstDescription.equals(category);

        assertFalse(result);
    }

    @Test
    @DisplayName("Test Hashcode")
    public void testIfIsTheSameHashcode() {
        Description firstDescription = new Description("Mercearia");
        Description secondDescription = new Description("Mercearia");


        assertEquals(firstDescription.hashCode(),secondDescription.hashCode());
    }

}