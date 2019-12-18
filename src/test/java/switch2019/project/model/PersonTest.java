package switch2019.project.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    /**
     * Validate Input for Name
     */

    @Test
    @DisplayName("Test for validating imput's name, name is null before")
    public void validateNameNullBefore() {
        //Arrange
        Person A = new Person(null, 1996, 3, 4);

        //Act
        A.setName("Alex");
        String expected = "Alex";

        //Assert
        assertEquals(expected, A.getName());
    }

    @Test
    @DisplayName("Test for validating imput's name, name is not null before")
    public void validateNameNotNullBefore() {
        //Arrange
        Person A = new Person("João", 1996, 3, 4);

        //Act
        A.setName("Alex");
        String expected = "Alex";

        //Assert
        assertEquals(expected, A.getName());
    }

    @Test
    @DisplayName("Test for validating imput's name, name is empty")
    public void validateNameEmpty() {
        //Arrange
        Person A = new Person("João", 1996, 3, 4);

        //Act
        A.setName("");
        String expected = "";

        //Assert
        assertEquals(expected, A.getName());
    }
    

    /**
     *Validate Input for Birthday Date
     */

    /**
     *Test if two individuals are the same
     */

    @Test
    @DisplayName("Test if two people are the same | True")
    public void individualsAreTheSame() {
        //Arrange

        //One Person
        String name = "João";
        //One Person BirthDate
        int year  = 1996;
        int month = 3;
        int day = 4;

        //Act
        Person onePerson = new Person(name, year, month, day);
        Person samePerson = new Person(name, year, month, day);

        //Assert
        assertEquals(onePerson, samePerson);
    }

    @Test
    @DisplayName("Test if two people are the same | True")
    public void individualsAreTheSame_2() {
        //Arrange

        //One Person
        String name = "João Cardoso";
        //One Person BirthDate
        int year  = 1996;
        int month = 3;
        int day = 4;

        //Other Person
        String otherPersonName = "João Cardoso";
        //Other Person BirthDate
        int otherPersonYear  = 1996;
        int otherPersonMonth = 3;
        int otherPersonDay = 4;

        //Act
        Person onePerson = new Person(name, year, month, day);
        Person samePerson = new Person(otherPersonName, otherPersonYear, otherPersonMonth, otherPersonDay);

        //Assert
        assertEquals(onePerson, samePerson);
    }

    @Test
    @DisplayName("Test if two people are the same | False")
    public void notTheSamePerson() {
        //Arrange

        //One Person
        String name = "João Cardoso";
        //One Person BirthDate
        int year  = 1996;
        int month = 3;
        int day = 4;

        //Other Person
        String otherPersonName = "Marta";
        //Other Person BirthDate
        int otherPersonYear  = 1996;
        int otherPersonMonth = 3;
        int otherPersonDay = 4;

        //Act
        Person onePerson = new Person(name, year, month, day);
        Person otherPerson = new Person(otherPersonName, otherPersonYear, otherPersonMonth, otherPersonDay);

        //Assert
        assertNotEquals(onePerson, otherPerson);
    }

    /**
     *Validate if a sibling was added to siblings list
     */

    @Test
    @DisplayName("Test for validating add a new sibling")
    public void validateAddSibling() {
        //Arrange
        Person A = new Person("Marta", 1996, 4, 27);
        Person B = new Person("Elsa", 1996, 1, 16);

        //Act
        A.addSibling(B);

        //Assert
        assertTrue(A.getSiblingList().contains(B) && B.getSiblingList().contains(A));
    }

    /**
     *Test if multiple siblings were added to siblings list
     */

    @Test
    void addMultipleSiblings() {
        //Arrange
        Person p1 = new Person("Teresa", 1987, 01, 16);
        Person p2 = new Person("Paulo", 1994, 04, 27);
        Person p3 = new Person("Paulo",1985,05,27);
        Person p4 = new Person("Luis",2000,8,15);
        HashSet<Person>newSiblings= new HashSet<>(Arrays.asList(p2,p4,p3));
        //Act
        p1.addMultipleSiblings(newSiblings);

        //Assert
        assertTrue(p1.getSiblingList().containsAll(newSiblings));
    }

    /**
     * Validate if a sibling was removed from to siblings list
     */
    @Test
    @DisplayName("Validate if a sibling was removed from to siblings list")
    void validateRemoveSibling (){
        //Arrange
        Person p1=new Person("Maria",1996,12,9);
        Person p2=new Person("António",1993,2,23);

        //Act
        p1.addSibling(p2);
        p1.removeSibling(p2);

        //Assert
        assertTrue(p1.getSiblingList().size()==0);
    }

    @Test
    @DisplayName("Validate if a sibling was removed from to siblings list - more than one sibling")
    void validateRemoveSiblingMoreThanOne (){
        //Arrange
        Person p1=new Person("Maria",1996,12,9);
        Person p2=new Person("António",1993,2,23);
        Person p3=new Person("Manuel",1993,3,9);
        HashSet<Person>threeSiblings=new HashSet<>(Arrays.asList(p2,p3));

        //Act
        p1.addMultipleSiblings(threeSiblings);
        p1.removeSibling(p2);

        //Assert
        assertTrue(p1.getSiblingList().size()==1);
    }

    /**
     * Test if multiple siblings were removed to siblings list
     */

    /**
     * Test if two people have the same siblings
     */

}