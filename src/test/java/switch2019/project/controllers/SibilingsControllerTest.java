package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Person;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SibilingsControllerTest {

    /**
     * Test if two persons are siblings
     */

    @Test
    @DisplayName("Test if two individuals are siblings - same mother")
    void isSiblingsSameMother() {
        //Arrange
        Person mae =new Person("Maria",1965,3,4);
        Person antonio=new Person("António",1987,12,9);
        Person manuel=new Person("Manuel",1986,9,12);

        //Act
        antonio.setMother(mae);
        manuel.setMother(mae);
        boolean resultado=antonio.isSibling(manuel);

        //Assert
        assertEquals(true,resultado);
    }
    @Test
    @DisplayName("Test if two individuals are siblings -not related")
    void isSiblingsFalse() {
        //Arrange
        Person mae =new Person("Maria",1965,3,4);
        Person mama=new Person("Amália",1962,9,14);
        Person senhor=new Person("Ricardo",1964,6,9);
        Person pai=new Person("José",1963,3,9);
        Person antonio=new Person("António",1987,12,9);
        Person manuel=new Person("Manuel",1986,9,12);

        //Act
        antonio.setMother(mae);
        antonio.setFather(senhor);
        manuel.setMother(mama);
        manuel.setFather(pai);
        boolean resultado=antonio.isSibling(manuel);

        //Assert
        assertFalse(resultado);
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void isSiblingsSameFather() {
        //Arrange
        Person pai=new Person("José",1963,3,9);
        Person antonio=new Person("António",1987,12,9);
        Person manuel=new Person("Manuel",1986,9,12);

        //Act
        antonio.setFather(pai);
        manuel.setFather(pai);
        boolean resultado=antonio.isSibling(manuel);

        //Assert
        assertEquals(true,resultado);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - in each other list")
    void isSiblingsInTheSiblingsList() {
        //Arrange
        Person antonio=new Person("António",1987,12,9);
        Person manuel=new Person("Manuel",1986,9,12);
        Person ricardo=new Person("Roberto",1992,8,10);


        HashSet<Person> siblings=new HashSet<Person>(Arrays.asList(manuel,ricardo));
        HashSet<Person>siblings2=new HashSet<Person>(Arrays.asList(antonio,ricardo));

        //Act
        antonio.addMultipleSiblings(siblings);
        ricardo.addMultipleSiblings(siblings2);

        boolean resultado=antonio.isSibling(manuel);

        //Assert
        assertEquals(true,resultado);
    }

    /**
     *  Test if Person exists on the other Person siblings list (USER STORIES)
     * @return boolean
     */

    @Test
    @DisplayName("Test if person exists on the other Person siblings list | True")
    public void personExistsOnTheOtherPersonSiblingsList_1() {
        //Arrange

        //One Person
        String name = "João Cardoso";
        //One Person BirthDate
        int year  = 1993;
        int month = 9;
        int day = 1;

        //Other Person
        String otherPersonName = "Marta";
        //Other Person BirthDate
        int otherPersonYear  = 1996;
        int otherPersonMonth = 3;
        int otherPersonDay = 4;

        //One Brother
        String bortherName = "Paulo";
        //One brother BirthDate
        int brotherYear  = 1993;
        int brotherMonth = 9;
        int brotherDay = 1;

        //one Sister
        String sisterName = "Diana";
        //One Sister BirthDate
        int sisterYear  = 2000;
        int sisterMonth = 9;
        int sisterDay = 1;

        Person onePerson = new Person(name, year, month, day);
        Person otherPerson = new Person(otherPersonName, otherPersonYear, otherPersonMonth, otherPersonDay);
        Person brother = new Person (bortherName,brotherYear,brotherMonth,brotherDay);
        Person sister = new Person(sisterName,sisterYear,sisterMonth,sisterDay);

        //Act
        onePerson.addSibling(otherPerson);
        onePerson.addSibling(brother);
        onePerson.addSibling(sister);

        boolean personExistsOtherPersonSiblingsList = onePerson.personExistsOnSiblingsList(otherPerson);

        //Assert
        assertTrue(personExistsOtherPersonSiblingsList);
    }

    @Test
    @DisplayName("Test if person exists on the other Person siblings list | False")
    public void personDoNotExistsOnTheOtherPersonSiblingsList_1() {
        //Arrange

        //One Person
        String name = "João Cardoso";
        //One Person BirthDate
        int year  = 1993;
        int month = 9;
        int day = 1;

        //Other Person
        String otherPersonName = "Marta";
        //Other Person BirthDate
        int otherPersonYear  = 1996;
        int otherPersonMonth = 3;
        int otherPersonDay = 4;

        //One Brother
        String bortherName = "Paulo";
        //One brother BirthDate
        int brotherYear  = 1993;
        int brotherMonth = 9;
        int brotherDay = 1;

        //one Sister
        String sisterName = "Diana";
        //One Sister BirthDate
        int sisterYear  = 2000;
        int sisterMonth = 9;
        int sisterDay = 1;

        Person onePerson = new Person(name, year, month, day);
        Person otherPerson = new Person(otherPersonName, otherPersonYear, otherPersonMonth, otherPersonDay);
        Person brother = new Person (bortherName,brotherYear,brotherMonth,brotherDay);
        Person sister = new Person(sisterName,sisterYear,sisterMonth,sisterDay);

        //Act
        onePerson.addSibling(brother);
        onePerson.addSibling(sister);

        otherPerson.addSibling(brother);
        otherPerson.addSibling(sister);

        boolean personExistsOtherPersonSiblingsList = onePerson.personExistsOnSiblingsList(otherPerson);

        //Assert
        assertFalse(personExistsOtherPersonSiblingsList);
    }

}