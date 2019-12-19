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
        Person senhor=new Person("Ricardo",1964,6,9);
        Person pai=new Person("José",1963,3,9);
        Person antonio=new Person("António",1987,12,9);
        Person p1=new Person("Manuel",1986,9,12);

        //Act
        antonio.addSibling(p1);
        boolean resultado=antonio.isSibling(p1);

        //Assert
        assertEquals(true,resultado);
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void isSiblingsSameFather() {
        //Arrange
        Person mae =new Person("Maria",1965,3,4);
        Person mama=new Person("Amália",1962,9,14);
        Person senhor=new Person("Ricardo",1964,6,9);
        Person pai=new Person("José",1963,3,9);
        Person antonio=new Person("António",1987,12,9);
        Person p1=new Person("Manuel",1986,9,12);

        //Act
        antonio.addSibling(p1);
        boolean resultado=antonio.isSibling(p1);

        //Assert
        assertEquals(true,resultado);
    }

    @Test
    @DisplayName("Test if two individuals are brothers - in each other list")
    void isSiblingsInTheSiblingsList() {
        //Arrange
        Person mae =new Person("Maria",1965,3,4);
        Person mama=new Person("Amália",1962,9,14);
        Person senhor=new Person("Ricardo",1964,6,9);
        Person pai=new Person("José",1963,3,9);
        Person antonio=new Person("António",1987,12,9);
        Person p1=new Person("Manuel",1986,9,12);
        Person p2=new Person("Roberto",1992,8,10);


        HashSet<Person> siblings=new HashSet<Person>(Arrays.asList(p1,p2));
        HashSet<Person>siblings2=new HashSet<Person>(Arrays.asList(antonio,p2));

        //Act
        antonio.addMultipleSiblings(siblings);
        p2.addMultipleSiblings(siblings2);

        boolean resultado=antonio.isSibling(p1);

        //Assert
        assertEquals(true,resultado);
    }

}