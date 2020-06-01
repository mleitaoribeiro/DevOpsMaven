package switch2019.project.domain.domainEntities.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {


    /**
     * Tests for the Person method toString
     */
    @Test
    @DisplayName("Test if method toString returns the name and home adress")
    public void validateToString() {
        //Arrange:
        Person person1 = new Person("Alex", new DateAndTime(1995, 12, 04), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"),new Email("1234@isep.pt"));

        String expected = "Email: 1234@isep.pt, Person Alex, currently lives in RUA X, PORTO, 4520-266, was born in LISBOA, on 1995-12-04.";

        //Act:
        String result = person1.toString();

        //Assert:
        assertEquals(expected, result);
    }

    /**
     * Test if two individuals are the same
     */

    @Test
    @DisplayName("Test if two people are the same | True")
    public void individualsAreTheSame() {
        //Arrange
        Person onePersonMother = new Person("Maria", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person onePersonFather = new Person("Artur", new DateAndTime(1990, 11, 4), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Person onePerson = new Person("João", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), onePersonMother.getID(), onePersonFather.getID(), new Email("1234@isep.pt"));
        Person samePerson = new Person("João", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), onePersonMother.getID(), onePersonFather.getID(), new Email("1234@isep.pt"));

        //Act & Assert
        assertEquals(onePerson, samePerson);
    }

    @Test
    @DisplayName("Test if two people are the same | True")
    public void individualsAreTheSame2() {
        //Arrange
        Person onePersonMother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person onePersonFather = new Person("Artur", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person otherPersonMother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person otherPersonFather = new Person("Artur", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Person onePerson = new Person("João Cardoso", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), onePersonMother.getID(), onePersonFather.getID(), new Email("1234@isep.pt"));
        Person otherPerson = new Person("João Cardoso", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), otherPersonMother.getID(), otherPersonFather.getID(), new Email("1234@isep.pt"));

        //Act & Assert
        assertEquals(onePerson, otherPerson);
    }

    @Test
    @DisplayName("Test if two people are the same | False")
    public void notTheSamePerson() {
        //Arrange
        Person onePersonMother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person onePersonFather = new Person("Artur", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person otherPersonMother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person otherPersonFather = new Person("Raul", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));

        Person onePerson = new Person("João Cardoso", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), onePersonMother.getID(), onePersonFather.getID(), new Email("12345@isep.pt"));
        Person otherPerson = new Person("João Cardoso", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), otherPersonMother.getID(), otherPersonFather.getID(), new Email("123456@isep.pt"));


        //Act & Assert
        assertNotEquals(onePerson, otherPerson);
    }


    @Test
    @DisplayName("Test if two people are the same - False - Different Class")
    public void individualsAreTheSameDifferentClass() {
        //Arrange
        Person person1Mother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person1Father = new Person("Artur", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));

        Address address1 = new Address("Rua da Alegria", "Porto", "4430-654");
        Person person1 = new Person("Miguel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person1Mother.getID(), person1Father.getID(), new Email("12@isep.pt"));

        //Act & Assert
        assertNotEquals(address1, person1);
    }

    @Test
    @DisplayName("Test if two people are the same - False - Different BirthDate")
    public void individualsAreNotTheSameDifferenteBirthDate() {
        //Arrange
        Person onePersonMother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person onePersonFather = new Person("Artur", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), onePersonMother.getID(), onePersonFather.getID(), new Email("12@isep.pt"));
        Person person2 = new Person("Maria", new DateAndTime(1996, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), onePersonMother.getID(), onePersonFather.getID(), new Email("1@isep.pt"));
        //Act
        boolean validation = person1.equals(person2);

        //Assert
        assertFalse(validation);
    }

    @Test
    @DisplayName("Test if two people are the same - False - Different birthDate")
    public void individualsAreDifferent_BirthDate() {
        //Arrange
        Person onePersonMother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person onePersonFather = new Person("Artur", new DateAndTime(1995, 12, 13), new Address("Portugal"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Act
        try {
            new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                    new Address("Rua X", "Porto", "4520-266"), onePersonMother.getID(), onePersonFather.getID(), new Email("1234@isep.pt"));
            new Person("Maria", null, new Address("Porto"),
                    new Address("Rua X", "Porto", "4520-266"), onePersonMother.getID(), onePersonFather.getID(), new Email("1234@isep.pt"));

        } catch (IllegalArgumentException description) {
            assertEquals("Birth Date Can't be Null.", description.getMessage());
        }
    }


    /**
     * Validate if a sibling was added to siblings list
     */

    @Test
    @DisplayName("Test for validating add a new sibling")
    public void validateAddSibling() {
        //Arrange
        Person person1 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Lyon"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Act
        person1.addSibling(person2);

        //Assert
        assertTrue(person1.getSiblingList().contains(person2) && person2.getSiblingList().contains(person1));
    }

    /**
     * Test if multiple siblings were added to siblings list
     */

    @Test
    void addMultipleSiblings() {
        //Arrange
        Person person1 = new Person("Teresa", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Paulo", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person3 = new Person("Paulo", new DateAndTime(1995, 12, 13), new Address("Coimbra"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person4 = new Person("Luis", new DateAndTime(1995, 12, 13), new Address("Mozelos"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        HashSet<Person> newSiblings = new HashSet<>(Arrays.asList(person2, person4, person3));

        //Act
        person1.addMultipleSiblings(newSiblings);

        //Assert
        assertTrue(person1.getSiblingList().containsAll(newSiblings));
    }

    /**
     * Validate if a sibling was removed from to siblings list
     */
    @Test
    @DisplayName("Validate if a sibling was removed from to siblings list")
    void validateRemoveSibling() {
        //Arrange
        Person person1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("FozCoa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("António", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Act
        person1.addSibling(person2);
        person1.removeSibling(person2);

        //Assert
        assertTrue(person1.getSiblingList().size() == 0);
    }

    @Test
    @DisplayName("Validate if a sibling was removed from to siblings list - more than one sibling")
    void validateRemoveSiblingMoreThanOne() {
        //Arrange
        Person person1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("António", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Manuel", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        HashSet<Person> threeSiblings = new HashSet<>(Arrays.asList(person2, person3));

        //Act
        person1.addMultipleSiblings(threeSiblings);
        person1.removeSibling(person2);

        //Assert
        assertTrue(person1.getSiblingList().size() == 1);
    }

    /**
     * Test if multiple siblings were removed to siblings list
     */
    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - 1 remaining")
    void validateMultipleSiblingRemoval() {
        //Arrange
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Anna", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Susan", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person person4 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));
        HashSet<Person> threeSiblings = new HashSet<>(Arrays.asList(person2, person3, person4));
        HashSet<Person> twoSiblings = new HashSet<>(Arrays.asList(person3, person4));

        //Act
        person1.addMultipleSiblings(threeSiblings);
        person1.removeMultipleSibling(twoSiblings);

        //Assert
        assertTrue(person1.getSiblingList().size() == 1);
    }

    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - multiple remaining")
    void validateMultipleSiblingRemovalMultipleRemaining() {
        //Arrange
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Anna", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Susan", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person person4 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));
        Person person5 = new Person("Jessica", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12345@isep.pt"));
        Person person6 = new Person("Jack", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123456@isep.pt"));
        HashSet<Person> totalSib = new HashSet<>(Arrays.asList(person2, person3, person4, person5, person6));
        HashSet<Person> removeSib = new HashSet<>(Arrays.asList(person3, person4));

        //Act
        person1.addMultipleSiblings(totalSib);
        person1.removeMultipleSibling(removeSib);

        //Assert
        assertTrue(person1.getSiblingList().size() == 3);
    }

    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - not contains exact ones")
    void validateMultipleSiblingRemovalNotContainsExactOnes() {
        //Arrange
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Anna", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Susan", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person person4 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));
        Person person5 = new Person("Jessica", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12345@isep.pt"));
        HashSet<Person> totalSib = new HashSet<>(Arrays.asList(person2, person3, person4, person5));
        HashSet<Person> removeSib = new HashSet<>(Arrays.asList(person3, person4));
        HashSet<Person> expectedSib = new HashSet<>(Arrays.asList(person2, person5));

        //Act
        person1.addMultipleSiblings(totalSib);
        person1.removeMultipleSibling(removeSib);

        //Assert
        assertTrue(person1.getSiblingList().containsAll(expectedSib));
    }

    /**
     * Test if two people have the same mother
     */
    @Test
    @DisplayName("Validate if two people have the same mother - False")
    void checkSameMotherFalse() {
        //Arrange
        Person mother1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person mother2 = new Person("Josefa", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person1 = new Person("Ricardo", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person person2 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));
        person1.setMother(mother1.getID());
        person2.setMother(mother2.getID());

        //Act
        boolean result = person1.checkSameMother(person2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - True")
    void checkSameMotherTrue() {
        //Arrange
        Person mother1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person1 = new Person("Ricardo", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person2 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        person1.setMother(mother1.getID());
        person2.setMother(mother1.getID());

        //Act
        boolean result = person1.checkSameMother(person2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - no mother")
    void checkSameMotherNoMother() {
        //Arrange
        Person mother1 = new Person("Teresa", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person1 = new Person("Ricardo", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setMother(mother1.getID());

        //Act
        boolean result = person1.checkSameMother(person2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - Null mother")
    void checkSameMotherNullMother() {
        //Arrage
        Person mother1 = new Person("Teresa", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person1 = new Person("Ricardo", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setMother(mother1.getID());
        person2.setMother(null);

        //Act
        boolean result = person1.checkSameMother(person2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - Null mothers")
    void checkSameMotherNullMothers() {
        //Arrage
        Person person1 = new Person("Miguel", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setMother(null);
        person2.setMother(null);

        //Act
        boolean result = person1.checkSameMother(person2);

        //Assert
        assertFalse(result);
    }

    /**
     * Test if two people have the same siblings
     */
    @Test
    @DisplayName("Validate if two people have the same siblings")
    void compareSameSiblings() {
        //Arrange
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Anna", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //siblings:
        Person sibling1 = new Person("Susan", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person sibling2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person sibling3 = new Person("Jessica", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person sibling4 = new Person("Jack", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //siblingList arrangement
        HashSet<Person> person1siblings = new HashSet<>(Arrays.asList(person2, sibling1, sibling2, sibling3, sibling4));
        HashSet<Person> person2siblings = new HashSet<>(Arrays.asList(person1, sibling1, sibling2, sibling3, sibling4));

        //Act
        person1.addMultipleSiblings(person1siblings);
        person2.addMultipleSiblings(person2siblings);

        //Assert
        assertTrue(person1.checkSameSiblings(person2));
    }

    @Test
    @DisplayName("Validate if two people have the same siblings - False")
    void compareSameSiblings2() {
        //Arrange
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person2 = new Person("Anna", new DateAndTime(1995, 12, 13),
                new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //siblings:
        Person sibling1 = new Person("Susan", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person sibling2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));
        Person sibling3 = new Person("Jessica", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12345@isep.pt"));
        Person sibling4 = new Person("Jack", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123456@isep.pt"));
        //siblingList arrangement
        HashSet<Person> person1siblings = new HashSet<>(Arrays.asList(sibling1, sibling2));
        HashSet<Person> person2siblings = new HashSet<>(Arrays.asList(sibling3, sibling4));

        //Act
        person1.addMultipleSiblings(person1siblings);
        person2.addMultipleSiblings(person2siblings);

        //Assert
        assertFalse(person1.checkSameSiblings(person2));
    }


    /**
     * Test if Person exists on the other Person siblings list
     *
     * @return boolean
     */

    @Test
    @DisplayName("Test if person exists on the other Person siblings list | True")
    public void personExistsOnTheOtherPersonSiblingsList_1() {
        //Arrange

        //One Person
        String name = "João Cardoso";
        //One Person BirthDate
        Address birthPlaceJoao = new Address("Porto");

        //Other Person
        String otherPersonName = "Marta";
        //Other Person BirthDate
        Address birthPlaceMarta = new Address("Porto");

        //One Brother
        String brotherName = "Paulo";
        //One brother BirthDate
        Address birthPlacePaulo = new Address("Porto");

        //one Sister
         String sisterName = "Diana";
        //One Sister BirthDate
        Address birthPlaceDiana = new Address("Porto");

        Person onePerson = new Person(name, new DateAndTime (1995, 12, 13), birthPlaceJoao,
                new Address("Rua X", "Porto", "4520-266"),new Email("1234@isep.pt"));
        Person otherPerson = new Person(otherPersonName, new DateAndTime(1995, 12, 13), birthPlaceMarta,
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person brother = new Person(brotherName, new DateAndTime(1995, 12, 13), birthPlacePaulo,
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person sister = new Person(sisterName, new DateAndTime(1995, 12, 13), birthPlaceDiana,
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

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
        Address birthPlaceJoao = new Address("Porto");

        //Other Person
        String otherPersonName = "Marta";
        //Other Person BirthDate
        Address birthPlaceMarta = new Address("Porto");

        //One Brother
        String brotherName = "Paulo";
        //One brother BirthDate
        Address birthPlacePaulo = new Address("Porto");

        //one Sister
        String sisterName = "Diana";
        //One Sister BirthDate
        Address birthPlaceDiana = new Address("Porto");

        Person onePerson = new Person(name, new DateAndTime(1995, 12, 13), birthPlaceJoao,
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person otherPerson = new Person(otherPersonName, new DateAndTime(1995, 12, 13), birthPlaceMarta,
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person brother = new Person(brotherName, new DateAndTime(1995, 12, 13), birthPlacePaulo,
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person sister = new Person(sisterName, new DateAndTime(1995, 12, 13), birthPlaceDiana,
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));

        //Act
        onePerson.addSibling(brother);
        onePerson.addSibling(sister);

        otherPerson.addSibling(brother);
        otherPerson.addSibling(sister);

        boolean personExistsOtherPersonSiblingsList = onePerson.personExistsOnSiblingsList(otherPerson);

        //Assert
        assertFalse(personExistsOtherPersonSiblingsList);
    }


    @Test
    @DisplayName("Test if person exists on the other Person siblings list | True")
    public void personExistsOnTheOtherPersonSiblingsList() {
        //Arrange
        Person person1 = new Person("João Cardoso", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Person brother = new Person("Paulo",new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person sister = new Person("Diana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Act
        person1.addSibling(person2);
        person1.addSibling(brother);
        person1.addSibling(sister);

        boolean personExistsOtherPersonSiblingsList = person1.personExistsOnSiblingsList(person2);

        //Assert
        assertTrue(personExistsOtherPersonSiblingsList);
    }

    @Test
    @DisplayName("Test if person exists on the other Person siblings list | False")
    public void personDoNotExistsOnTheOtherPersonSiblingsList() {
        //Arrange
        Person person1 = new Person("João Cardoso", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));

        Person brother = new Person("Paulo", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person sister = new Person("Diana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));

        //Act
        person1.addSibling(brother);
        person1.addSibling(sister);

        person2.addSibling(brother);
        person2.addSibling(sister);

        boolean personExistsOtherPersonSiblingsList = person1.personExistsOnSiblingsList(person2);

        //Assert
        assertFalse(personExistsOtherPersonSiblingsList);
    }


    /**
     * Test if two people have the same father
     */
    @Test
    @DisplayName("Test if two persons have the same father - True")
    void checkSameFatherTrue() {
        //Arrange
        Person person1 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Filipa", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person father1 = new Person("Antonio", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Act
        person1.setFather(father1.getID());
        person2.setFather(father1.getID());

        boolean result = person1.checkSameFather(person2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two persons have the same father - False")
    void checkSameFatherFalse() {
        //Arrange
        Person person1 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Filipa", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person father1 = new Person("josé", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person father2 = new Person("Afonso", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));

        //Act
        person1.setFather(father1.getID());
        person2.setFather(father2.getID());

        boolean result = person1.checkSameFather(person2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two persons have the same father - False Version 2")
    void checkSameFatherFalse2() {
        //Arrange
        Person person1 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Filipa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person father1 = new Person("jose", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person father2 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));
        //Act
        person1.setFather(father1.getID());
        person2.setFather(father2.getID());

        boolean result = person1.checkSameFather(person2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two persons have the same father - False with Null")
    void checkSameFatherFalseNull() {
        //Arrange
        Person person1 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Filipa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person father1 = new Person("jose", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //Act
        person1.setFather(father1.getID());
        person2.setFather(null);

        boolean result = person1.checkSameFather(person2);

        //Assert
        assertFalse(result);
    }

    /**
     * Test if two persons are siblings(USER STORIE 1)
     */

    @Test
    @DisplayName("Test if two individuals are siblings - same mother")
    void isSiblingsSameMother() {
        //Arrange
        Person mother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person1 = new Person("António", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Manuel", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Act
        person1.setMother(mother.getID());
        person2.setMother(mother.getID());
        boolean resultado = person1.isSibling(person2);

        //Assert
        assertEquals(true, resultado);
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void isSiblingsSameFather() {
        //Arrange
        Person father = new Person("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person1 = new Person("António",new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Manuel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Act
        person1.setFather(father.getID());
        person2.setFather(father.getID());
        boolean resultado = person1.isSibling(person2);

        //Assert
        assertEquals(true, resultado);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - in each other list")
    void isSiblingsInTheSiblingsList() {
        //Arrange
        Person person1 = new Person("António", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Manuel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person3 = new Person("Roberto", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        HashSet<Person> siblings1 = new HashSet<>(Arrays.asList(person2, person3));
        HashSet<Person> siblings2 = new HashSet<>(Arrays.asList(person1, person3));

        //Act
        person1.addMultipleSiblings(siblings1);
        person3.addMultipleSiblings(siblings2);

        boolean resultado = person1.isSibling(person2);

        //Assert
        assertEquals(true, resultado);
    }

    @Test
    @DisplayName("Test if two individuals are siblings -not related")
    void isSiblingsFalse() {
        //Arrange
        Person mae = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person mama = new Person("Amália", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person senhor = new Person("Ricardo", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        Person pai = new Person("José", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1@isep.pt"));
        Person antonio = new Person("António", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12345@isep.pt"));
        Person manuel = new Person("Manuel", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123456@isep.pt"));

        //Act
        antonio.setMother(mae.getID());
        antonio.setFather(senhor.getID());
        manuel.setMother(mama.getID());
        manuel.setFather(pai.getID());
        boolean resultado = antonio.isSibling(manuel);

        //Assert
        assertFalse(resultado);
    }



    /**
     * * Tests to validate if a person is another person's mother
     */
    @Test
    @DisplayName("Check if a given person anothers' mother - true")
    void checkIsMotherTrue() {
        //Arrange
        Person person1 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setMother(person2.getID());

        //Act
        boolean realResult = person1.isMother(person2.getID());

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Check if a given person anothers' mother - false")
    void checkIsMotherFalse() {
        //Arrange
        Person person1 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Diana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        person1.setMother(person2.getID());

        //Act
        boolean realResult = person1.isMother(person3.getID());

        //Assert
        assertFalse(realResult);
    }

    @Test
    @DisplayName("Check if a given person anothers' mother - false null case")
    void checkIsMotherFalseNull() {
        //Arrange
        Person person1 = new Person("Elsa",new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta",new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setMother(null);

        //Act
        boolean realResult = person1.isMother(person2.getID());

        //Assert
        assertFalse(realResult);
    }

    /**
     * Test to validate is the person is Father
     */
    @Test
    @DisplayName("Check if a given person is father- Main scenario")
    void isFather() {
        //Arrange

        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setFather(person2.getID());

        //Act

        boolean result = person1.isFather(person2.getID());
        //
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if a given person is father- False scenario")
    void isFather_manyPerson() {
        //Arrange

        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("12@isep.pt"));
        person1.setFather(person2.getID());

        //Act

        boolean result = person1.isFather(person3.getID());
        //
        assertFalse(result);
    }

    @Test
    @DisplayName("Check if a given person is father- Null Scenario")
    void isFatherNullCase() {
        //Arrange

        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setFather(null);

        //Act

        boolean result = person1.isFather(person2.getID());
        //Assert
        assertFalse(result);
    }

    /**
     * Test to check if a person and group are the same
     */
    @Test
    @DisplayName("Check if a person and group are the same-False")
    void testEquals() {
        //Arrange

        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Test Group"),person1.getID());

        //Act
        boolean result = person1.equals(group1);

        //Assert
        assertFalse(result);
    }

    /**
     * Test if two person have the same Hashcode
     */

    @Test
    @DisplayName("Test if two persons have the same Hashcode - True")
    public void testIfTwoPersonsHaveTheSameHashCodeTrue() {

        //Arrange & Act
        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Assert
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    @DisplayName("Test if two persons have the same Hashcode - False")
    public void testIfTwoPersonsHaveTheSameHashCodeFalse() {

        //Arrange & Act
        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));

        //Assert
        assertNotEquals(person1.hashCode(), person2.hashCode());
    }


    @Test
    void getPersonID() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));

        PersonID expected = new PersonID(new Email("1234@isep.pt"));

        //Act:
        PersonID result = person.getID();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get siblings ID List")
    public void getSiblingsIDs() {
        //Arrange
        Person person1 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("marta@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1990, 10, 20), new Address("Lyon"),
                new Address("Rua X", "Porto", "4520-266"), new Email("elsa@isep.pt"));
        Person person3 = new Person("Diana", new DateAndTime(1994, 11, 5), new Address("Lisboa"),
                new Address("Rua X", "Lisboa", "4520-266"), new Email("diana@isep.pt"));
        person1.addSibling(person2);
        person1.addSibling(person3);

        Set<PersonID> expectedSiblingsID = new HashSet<>(Arrays.asList(person2.getID(),person3.getID()));
        //Act
        Set<PersonID> realSiblingsID = person1.getSiblingsIDList();

        //Assert
        assertEquals(expectedSiblingsID, realSiblingsID);
    }

    @Test
    @DisplayName("Get siblings ID Lis")
    public void getSiblingsIDsNull() {
        //Arrange
        Person person1 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("marta@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1990, 10, 20), new Address("Lyon"),
                new Address("Rua X", "Porto", "4520-266"), new Email("elsa@isep.pt"));
        Person person3 = new Person("Diana", new DateAndTime(1994, 11, 5), new Address("Lisboa"),
                new Address("Rua X", "Lisboa", "4520-266"), new Email("diana@isep.pt"));
        person1.addSibling(person2);
        person1.addSibling(person3);

        Set<PersonID> expectedSiblingsID = new HashSet<>(Arrays.asList(person2.getID()));
        //Act
        Set<PersonID> realSiblingsID = person1.getSiblingsIDList();

        //Assert
        assertNotEquals( expectedSiblingsID, realSiblingsID);
    }
}

