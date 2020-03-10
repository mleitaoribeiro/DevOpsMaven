package switch2019.project.model.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    @DisplayName("Test for description - happy case")
    public void setDescription(){
        //Arrange
        Description description=new Description("Mercearia");

        //Act
        description.getDescription();

        //Assert
        assertEquals("MERCEARIA",description.getDescription());
    }


    @Test
    @DisplayName("Testing for null")
    public void descriptionIsNull(){
        try{
            new Description(null);
        }
        catch (IllegalArgumentException description){
            assertEquals("The description can't be null or empty. ",description.getMessage());
        }
    }

    @Test
    @DisplayName("Testing for empty")
    public void descriptionIsEmpty(){
        try{
            new Description("");
        }
        catch (IllegalArgumentException description){
            assertEquals("The description can't be null or empty. ",description.getMessage());
        }
    }
}