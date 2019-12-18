package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("Test for validating imput's name")
    public void validateName(){
        //Arrange
        Person A = new Person(null, 1996, 3, 4);

        //Act
        //A.setName("Alex");
        String expected = "Alex";

        //Assert
        //assertEquals(expected, A.getName());
    }
}