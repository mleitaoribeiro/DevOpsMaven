package switch2019.project.domain.domainEntities.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.ledger.Periodicity;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.shared.*;

import java.time.LocalDateTime;
import java.util.*;

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
                new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather, new Email("1234@isep.pt"));
        Person samePerson = new Person("João", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather, new Email("1234@isep.pt"));

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
                new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather, new Email("1234@isep.pt"));
        Person otherPerson = new Person("João Cardoso", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), otherPersonMother, otherPersonFather, new Email("1234@isep.pt"));

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
                new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather, new Email("12345@isep.pt"));
        Person otherPerson = new Person("João Cardoso", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), otherPersonMother, otherPersonFather, new Email("123456@isep.pt"));


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
                new Address("Rua X", "Porto", "4520-266"), person1Mother, person1Father, new Email("12@isep.pt"));

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
                new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather, new Email("12@isep.pt"));
        Person person2 = new Person("Maria", new DateAndTime(1996, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather, new Email("1@isep.pt"));
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
            Person person1Mother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                    new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather, new Email("1234@isep.pt"));
            Person person2Mother = new Person("Maria", null, new Address("Porto"),
                    new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather, new Email("1234@isep.pt"));

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
        person1.setMother(mother1);
        person2.setMother(mother2);

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
        person1.setMother(mother1);
        person2.setMother(mother1);

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
        person1.setMother(mother1);

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
        Person mother2 = null;
        Person person1 = new Person("Ricardo", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setMother(mother1);
        person2.setMother(mother2);

        //Act
        boolean result = person1.checkSameMother(person2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - Null mothers")
    void checkSameMotherNullMothers() {
        //Arrage
        Person mother1 = null;
        Person mother2 = null;
        Person person1 = new Person("Miguel", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Gaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        person1.setMother(mother1);
        person2.setMother(mother2);

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
        person1.setFather(father1);
        person2.setFather(father1);

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
        person1.setFather(father1);
        person2.setFather(father2);

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
        person1.setFather(father1);
        person2.setFather(father2);

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
        person1.setFather(father1);
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
        person1.setMother(mother);
        person2.setMother(mother);
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
        person1.setFather(father);
        person2.setFather(father);
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
        antonio.setMother(mae);
        antonio.setFather(senhor);
        manuel.setMother(mama);
        manuel.setFather(pai);
        boolean resultado = antonio.isSibling(manuel);

        //Assert
        assertFalse(resultado);
    }

    /**
     * Test if a transaction was created
     */

    @Test
    @DisplayName("Test if a transaction was created - success case")
    void createTransactionSuccessCase() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";

        Category category = new Category(new Denomination("General"), person.getID());

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), person.getID());
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), person.getID());

        //Act
        boolean result = person.createTransaction(amount, description, null, category, from, to, new Type(false));

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a transaction was created - monetary value is negative")
    void createTransactionAccountNegativeMonetaryValue() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        MonetaryValue amountPositive = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue amountNegative = new MonetaryValue(-50, Currency.getInstance("EUR"));
        String description1 = "payment";

        Category category = new Category("General");

        Account accountWallet = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account accountTransport = new Account(new Denomination("Transport"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        try {
            person.createTransaction(amountNegative, description1, null, category, accountWallet, accountTransport, new Type(false));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value can´t be null or negative. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if a transaction was created - Same account")
    void createTransactionSameAccount() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        MonetaryValue amount = new MonetaryValue(50, Currency.getInstance("EUR"));
        String description = "payment";

        Category category = new Category("General");

        Account accountWallet = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        boolean result = person.createTransaction(amount, description, null, category, accountWallet, accountWallet, new Type(false));

        //Assert
        assertFalse(result);
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
        person1.setMother(person2);

        //Act
        boolean realResult = person1.isMother(person2);

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
        person1.setMother(person2);

        //Act
        boolean realResult = person1.isMother(person3);

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
        boolean realResult = person1.isMother(person2);

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
        person1.setFather(person2);

        //Act

        boolean result = person1.isFather(person2);
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
        person1.setFather(person2);

        //Act

        boolean result = person1.isFather(person3);
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

        boolean result = person1.isFather(person2);
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
        Group group1 = new Group(new Description("Test Group"));

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

    /**
     * US011
     * returnPersonLedgerInDateRangeSuccessCaseOneTransaction
     */
    @Test
    @DisplayName("Test if a person get their transactions in a given period - success case - one transaction -  US011")
    void returnPersonLedgerInDateRangeSuccessCaseOneTransaction() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category = new Category(new Denomination("General"), person.getID());

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), person.getID());
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), person.getID());

        person.createTransaction(amount, "payment", dateTransaction1, category, from, to, new Type(false));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 23, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 20, 23, 00);

        Transaction transaction1 = new Transaction(amount, "payment", dateTransaction1, category, from, to, new Type(false));
        List<Transaction> expectedResult = new ArrayList<>();
        expectedResult.add(transaction1);

        //Act
        List<Transaction> personLedgerTransactions = person.returnPersonLedgerInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(personLedgerTransactions, expectedResult);
    }


    @Test
    @DisplayName("Test if a person get their transactions in a given period - success case - several transactions -  US011")
    void returnPersonLedgerInDateRangeSuccessCaseSeveralTransactions() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), person.getID());
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), person.getID());

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 10, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category(new Denomination("General"), person.getID());

        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));

        //Arrange - Transaction2//
        LocalDateTime dateTransaction2 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount2 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category2 = new Category(new Denomination("General"), person.getID());

        person.createTransaction(amount2, "payment", dateTransaction2, category2, from, to, new Type(false));
        Transaction transaction2 = new Transaction(amount2, "payment", dateTransaction2, category2, from, to, new Type(false));

        //Arrange - Transaction3//
        LocalDateTime dateTransaction3 = LocalDateTime.of(2020, 1, 16, 13, 00);
        MonetaryValue amount3 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category3 = new Category(new Denomination("General"), person.getID());

        person.createTransaction(amount3, "payment", dateTransaction3, category3, from, to, new Type(false));
        Transaction transaction3 = new Transaction(amount3, "payment", dateTransaction3, category3, from, to, new Type(false));

        //Arrange - ExpectedResult//
        List<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction3, transaction2, transaction1));


        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 17, 00, 00);

        //Act
        List<Transaction> personLedgerTransactions = person.returnPersonLedgerInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(personLedgerTransactions, expectedResult);
    }

    @Test
    @DisplayName("Test if a person get their transactions in a given period - no transactions in that period -  US011")
    void returnPersonLedgerInDateRangeNoTransactions() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");

        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));

        //Arrange - ExpectedResult//
        ArrayList<Transaction> expectedResult = new ArrayList<>();

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 23, 0);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 10, 23, 0);

        //Act
        List<Transaction> personLedgerTransactions = person.returnPersonLedgerInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(personLedgerTransactions, expectedResult);
    }

    @Test
    @DisplayName("Test if a person get their transactions in a given period - exchanged dates  -  US011")
    void returnPersonLedgerFromPeriodExchangedDates() {
        //Arrange
        Person person = new Person("Miguel", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Account from = new Account(new Denomination("Account1"),
                new Description("General expenses"), person.getID());
        Account to = new Account(new Denomination("Account2"),
                new Description("Transport expenses"), person.getID());


        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category(new Denomination("General"), person.getID());

        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));

        //Arrange - Transaction2//
        LocalDateTime dateTransaction2 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount2 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category2 = new Category(new Denomination("General"), person.getID());

        person.createTransaction(amount2, "payment", dateTransaction2, category2, from, to, new Type(false));
        Transaction transaction2 = new Transaction(amount2, "payment", dateTransaction2, category2, from, to, new Type(false));

        //Arrange - ExpectedResult//
        ArrayList<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction1, transaction2));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 17, 00, 00);

        //Act
        List<Transaction> personLedgerTransactions = person.returnPersonLedgerInDateRange(finalDate, initialDate);

        //Assert
        assertEquals(personLedgerTransactions, expectedResult);
    }

    @Test
    @DisplayName("Test if a person get their transactions in a given period - same day  -  US011")
    void returnPersonLedgerFromPeriodSameDay() {
        //Arrange
        Person person = new Person("Miguel", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Account from = new Account(new Denomination("Account1"),
                new Description("General expenses"), person.getID());
        Account to = new Account(new Denomination("Account2"),
                new Description("Transport expenses"), person.getID());

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category(new Denomination("General"), person.getID());

        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));

        //Arrange - ExpectedResult//
        ArrayList<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction1));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 14, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 14, 23, 59);

        //Act
        List<Transaction> personLedgerTransactions = person.returnPersonLedgerInDateRange(finalDate, initialDate);

        //Assert
        assertEquals(personLedgerTransactions, expectedResult);
    }


    @Test
    @DisplayName("Test if a person get their transactions in a given period - null date -  US011")
    void returnPersonLedgerInDateRangeiodNullDate() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("Account2"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));


        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");

        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));

        //Arrange

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);

        //Act
        try {
            person.returnPersonLedgerInDateRange(initialDate, null);
        }

        //Assert
        catch (IllegalArgumentException returnPersonLedgerInDateRange) {
            assertEquals("The dates can´t be null", returnPersonLedgerInDateRange.getMessage());
        }

    }

    @Test
    @DisplayName("Test if a person get their transactions in a given period - invalid date -  US011")
    void returnPersonLedgerInDateRangeInvalidDate() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("Account2"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");

        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, new Type(false));

        //Arrange
        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2030, 1, 9, 00, 00);

        //Act
        try {
            person.returnPersonLedgerInDateRange(initialDate, finalDate);
        }

        //Assert
        catch (IllegalArgumentException returnPersonLedgerInDateRange) {
            assertEquals("One of the submitted dates is not valid.", returnPersonLedgerInDateRange.getMessage());
        }
    }

    /**
     * User Story 17:
     */

    @Test
    @DisplayName("Get the balance of my own transactions over a valid date range - Main Scenario of US17")
    void getPersonalBalanceInDateRange() {
        //Arrange
        //Init Person:
        Person person = new Person("Marta", new DateAndTime(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Init Transactions
        person.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "car gas",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("CGD"),
                        new Description("Only Gas Expenses"), person.getID()),
                new Account(new Denomination("BP"),
                        new Description("Gas"), person.getID()),
                new Type(false));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 1, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 6, 00, 00);

        double expectedPersonalBalanceFromDateRange = -95.4;

        //Act
        double personalBalanceInDateRange = person.getPersonalBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Get the balance of my own transactions for one day - valid day")
    void getPersonalBalanceForJustOneDay() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //Init Transactions
        person.createTransaction(new MonetaryValue(250, Currency.getInstance("EUR")), "Hostel Barcelona",
                LocalDateTime.of(2020, 1, 13, 13, 05),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Revolut"),
                        new Description("For trips expenses"), person.getID()),
                new Account(new Denomination("Friends & Company"),
                        new Description("Holidays"), person.getID()),
                new Type(true));
        person.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "Pack of Super Bock",
                LocalDateTime.of(2020, 1, 13, 14, 11),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(60, Currency.getInstance("EUR")), "Car Gas",
                LocalDateTime.of(2020, 1, 18, 17, 23),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("CGD"),
                        new Description("Only Gas Expenses"), person.getID()),
                new Account(new Denomination("BP"),
                        new Description("Gas"), person.getID()),
                new Type(false));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 13, 23, 59);

        double expectedPersonalBalanceFromDateRange = 230;

        //Act
        double personalBalanceInDateRange = person.getPersonalBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }


    @Test
    @DisplayName("Get the balance of my own transactions over a valid date range but initial date and final date not in order")
    void getPersonalBalanceInDateRangeWithDatesNotInOrder() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //Init Transactions
        person.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("CGD"),
                        new Description("Only Gas Expenses"), person.getID()),
                new Account(new Denomination("BP"),
                        new Description("Gas"), person.getID()),
                new Type(false));

        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 6, 00, 00);
        LocalDateTime initialDate = LocalDateTime.of(2019, 12, 31, 00, 00);

        double expectedPersonalBalanceFromDateRange = -95.4;

        //Act
        double personalBalanceInDateRange = person.getPersonalBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Get the balance of my own transactions over invalid date range - final date higher than today!")
    void getPersonalBalanceInDateRangeWithInvalidDate() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1995, 12, 4), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //Init Transactions
        person.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 5),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("CGD"),
                        new Description("Only Gas Expenses"), person.getID()),
                new Account(new Denomination("BP"),
                        new Description("Gas"), person.getID()),
                new Type(false));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);

        try {
            //Act
            double personalBalanceInDateRange = person.getPersonalBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the submitted dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my own transactions of an invalid date range - final date higher than today!")
    void getPersonalBalanceInDateRangeWithNullDate() {
        //Arrange
        Person person1 = new Person("Marta", new DateAndTime(1995, 12, 4), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //Init Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 5),
                new Category("grocery"), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), new PersonID(new Email("personEmail@email.pt"))),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), new PersonID(new Email("personEmail@email.pt"))),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account(new Denomination("CGD"),
                        new Description("Only Gas Expenses"), new PersonID(new Email("personEmail@email.pt"))),
                new Account(new Denomination("BP"),
                        new Description("Gas"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

        LocalDateTime initialDate = null;
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);

        try {
            //Act
            double personalBalanceInDateRange = person1.getPersonalBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the submitted dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my ledger that has any transactions")
    void getPersonalBalanceInDateRangeEmptyBalance() {
        //Arrange
        Person person1 = new Person("Marta", new DateAndTime(1995, 12, 4), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"), new Email("1234@isep.pt"));

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 0, 0);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 0, 0);

        try {
            //Act
            person1.getPersonalBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("The ledger is Empty.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my own transactions over a period with zero transactions in date range")
    void getPersonalBalanceInDateRangeEmptyBalanceOverDateRange() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1995, 12, 4), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"), new Email("1234@isep.pt"));
        //Init Transactions
        person.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 5),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("Millenium"),
                        new Description("Only for Groceries"), person.getID()),
                new Account(new Denomination("Continente"),
                        new Description("Food Expenses"), person.getID()),
                new Type(false));
        person.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category(new Denomination("grocery"), person.getID()), new Account(new Denomination("CGD"),
                        new Description("Only Gas Expenses"), person.getID()),
                new Account(new Denomination("BP"),
                        new Description("Gas"), person.getID()),
                new Type(false));

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 0, 0);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 0, 0);

        //Act
        double personalBalanceInDateRange = person.getPersonalBalanceInDateRange(initialDate, finalDate);


        assertEquals(0, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Schedule a personal transaction")
    void scheduleNewTransactionDaily() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        boolean result = person.scheduleNewTransaction(new Periodicity("daily"), amount, description,
                null, category, from, to, new Type(false));

        Thread.sleep(2400); // 250 x 10 = 2500

        //Assert
        assertTrue(result && person.ledgerSize() == 10);
    }


    @Test
    void scheduleNewTransactionWorkingDays() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        boolean result = person.scheduleNewTransaction(new Periodicity("working days"), amount, description,
                null, category, from, to, new Type(false));

        Thread.sleep(1900); // 500 x 4 = 2000

        //Assert
        assertTrue(result && person.ledgerSize() == 4);
    }

    @Test
    void scheduleNewTransactionWeekly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        boolean result = person.scheduleNewTransaction(new Periodicity("weekly"), amount, description,
                null, category, from, to, new Type(false));

        Thread.sleep(2900); // 750 x 4 = 3000

        //Assert
        assertTrue(result && person.ledgerSize() == 4);
    }


    @Test
    void scheduleNewTransactionMonthly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        boolean result = person.scheduleNewTransaction(new Periodicity("monthly"), amount, description,
                null, category, from, to, new Type(false));

        Thread.sleep(2900); // 1000 x 3 = 3000

        //Assert
        assertTrue(result && person.ledgerSize() == 3);
    }

    @Test
    void scheduleNewTransactionNoMatch() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        try {
            //Act
            person.scheduleNewTransaction(new Periodicity("tomorrow"), amount, description,
                    null, category, from, to, new Type(false));

            Thread.sleep(1600);
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }

    }

    /**
     * test for creating transaction with size of Ledger
     */

    @Test
    @DisplayName("Test for validating add a new transaction")
    void addTransactionToLedgerChangeSize() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));


        Category category = new Category("grocery");

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();

        //Act
        int sizeBefore = ledger.getLedgerSize();
        ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, account2, new Type(true));
        ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, account2, new Type(true));
        ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, account2, new Type(true));
        int sizeAfter = ledger.getLedgerSize();

        //Assert
        assertEquals(sizeBefore + 3, sizeAfter);
    }

    /**
     * US010 Como utilizador, quero obter os movimentos de determinada conta num dado período.
     */

    @Test
    @DisplayName("Obtain transactions from an account - case of success")
    void obtainTransactionsFromAnAccount() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), person.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), person.getID());
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"), person.getID());

        Category category1 = new Category(new Denomination("grocery"), person.getID());
        Category category2 = new Category(new Denomination("friends"), person.getID());

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        //Act
        List<Transaction> listOfTransactions = person.getOneAccountTransactionsFromUser(account5, date1, date2);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - same day")
    void obtainTransactionsFromAnAccountSameDay() {

        //Arrange
        Person person = new Person("Jose",new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 00, 00);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 23, 59);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), person.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), person.getID());
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"), person.getID());

        Category category1 = new Category(new Denomination("grocery"), person.getID());
        Category category2 = new Category(new Denomination("friends"), person.getID());

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        //Act
        List<Transaction> listOfTransactions = person.getOneAccountTransactionsFromUser(account5, date1, date2);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - date null")
    void obtaintTransactionsDateNull() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        LocalDateTime date1 = null;
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 23, 59);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"), new PersonID(new Email("personEmail@email.pt")));

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        //Act
        try {
            person.getOneAccountTransactionsFromUser(account5, date1, date2);
        }

        //Assert
        catch (IllegalArgumentException initialDate) {
            assertEquals("The dates can´t be null", initialDate.getMessage());
        }
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
}
