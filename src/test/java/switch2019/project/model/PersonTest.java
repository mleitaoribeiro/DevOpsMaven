package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.ledger.Ledger;
import switch2019.project.model.ledger.Transaction;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;
import switch2019.project.model.valueObject.Address;

import java.time.DateTimeException;
import java.time.LocalDate;
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
        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        String expected = "Person: Alex, currently lives in RUA X, PORTO, 4520-266, was born in Lisboa, on 1995-12-04.";

        //Act:
        String result = person1.toString();

        //Assert:
        assertEquals(expected, result);
    }
    /**
     * Validate Input for Name
     */

    @Test
    @DisplayName("Test for validating input's name, name is null before")
    public void validateNameNullBefore() {
        //Arrange

        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        person1.setName("Mario");
        String expected = "Mario";

        //Assert
        assertEquals(expected, person1.getName());
    }

    @Test
    @DisplayName("Test for validating input's name, name is not null before")
    public void validateNameNotNullBefore() {
        //Arrange
        Person person1 = new Person("João", LocalDate.of(1990, 12, 04), new Address(("Porto")), new Address("Rua X", "Porto", "4520-266"));

        //Act
        person1.setName("Alex");
        String expected = "Alex";

        //Assert
        assertEquals(expected, person1.getName());
    }

    @Test
    @DisplayName("Test for validating imput's name, name is empty")
    public void validateNameEmpty() {
        //Arrange
        Person person1 = new Person("João", LocalDate.of(1990, 12, 04), new Address("Feira"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        person1.setName("");
        String expected = "";

        //Assert
        assertEquals(expected, person1.getName());
    }


    /**
     * Validate Input for Birthday Date
     */

    @Test
    @DisplayName("Test for validating birth date input => success case")
    public void validateBirthDate() {
        //Arrange
        Person person1 = new Person("Mary", LocalDate.of(1990, 12, 05), new Address("Maia"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        person1.setBirthDate(LocalDate.of(1995, 04, 04));

        LocalDate expected = LocalDate.parse("1995-04-04");

        //Assert
        assertEquals(expected, person1.getBirthDate());
    }

    @Test
    @DisplayName("Test for validating birth date input => error case ")
    public void validateBirthDateWhenMonthisInvalid() {
        //Arrange
        Person person1 = new Person("Pedro", LocalDate.of(1990, 12, 04), new Address("SaoJoao"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        try {
            person1.setBirthDate(LocalDate.of(1990, 13, 04));
            fail();
        }
        //Assert
        catch (DateTimeException ex) {
            assertEquals("Invalid value for MonthOfYear (valid values 1 - 12): 13", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating birth date input => error case ")
    public void validateBirthDateWhenBirthDateIsAfterCurrentDate() {
        //Arrange
        Person person1 = new Person("Rui", LocalDate.of(1990, 12, 04), new Address("Lousada"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        try {
            person1.setBirthDate(LocalDate.of(2050, 12, 04));
            fail();
        }
        //Assert
        catch (IllegalArgumentException ex) {
            assertEquals("Birth Date Not Supported.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating birth date input => error case")
    public void validateBirthDateWhenDayIsInvalid() {
        //Arrange
        Person person1 = new Person("Rui", LocalDate.of(1990, 12, 04), new Address("lamas"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        try {
            person1.setBirthDate(LocalDate.of(1990, 12, 50));
            fail();
        }
        //Assert
        catch (DateTimeException ex) {
            assertEquals("Invalid value for DayOfMonth (valid values 1 - 28/31): 50", ex.getMessage());
        }
    }

    /**
     * Test if two individuals are the same
     */

    @Test
    @DisplayName("Test if two people are the same | True")
    public void individualsAreTheSame() {
        //Arrange
        Person onePersonMother = new Person("Maria", LocalDate.of(1990, 12, 04), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person onePersonFather = new Person("Artur", LocalDate.of(1990, 11, 04), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        Person onePerson = new Person("João", LocalDate.of(1990, 12, 04), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather);
        Person samePerson = new Person("João", LocalDate.of(1990, 12, 04), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather);

        //Act & Assert
        assertEquals(onePerson, samePerson);
    }

    @Test
    @DisplayName("Test if two people are the same | True")
    public void individualsAreTheSame2() {
        //Arrange
        Person onePersonMother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person onePersonFather = new Person("Artur", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person otherPersonMother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person otherPersonFather = new Person("Artur", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        Person onePerson = new Person("João Cardoso", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather);
        Person otherPerson = new Person("João Cardoso", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), otherPersonMother, otherPersonFather);

        //Act & Assert
        assertEquals(onePerson, otherPerson);
    }

    @Test
    @DisplayName("Test if two people are the same | False")
    public void notTheSamePerson() {
        //Arrange
        Person onePersonMother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"));
        Person onePersonFather = new Person("Artur", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"));
        Person otherPersonMother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"));
        Person otherPersonFather = new Person("Raul", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"));

        Person onePerson = new Person("João Cardoso", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather);
        Person otherPerson = new Person("João Cardoso", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), otherPersonMother, otherPersonFather);


        //Act & Assert
        assertNotEquals(onePerson, otherPerson);
    }


    @Test
    @DisplayName("Test if two people are the same - False - Different Class")
    public void individualsAreTheSameDifferentClass() {
        //Arrange
        Person person1Mother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person1Father = new Person("Artur", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        Address address1 = new Address("Rua da Alegria", "Porto", "4430-654");
        Person person1 = new Person("Miguel", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), person1Mother, person1Father);

        //Act & Assert
        assertNotEquals(address1, person1);
    }

    @Test
    @DisplayName("Test if two people are the same - False - Different BirthDate")
    public void individualsAreNotTheSameDifferenteBirthDate() {
        //Arrange
        Person onePersonMother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"));
        Person onePersonFather = new Person("Artur", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"));
        Person person1 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather);
        Person person2 = new Person("Maria", LocalDate.of(1996, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather);
        //Act
        boolean validation = person1.equals(person2);

        //Assert
        assertFalse(validation);
    }

    @Test
    @DisplayName("Test if two people are the same - False - Different birthDate")
    public void individualsAreDifferent_BirthDate() {
        //Arrange
        Person onePersonMother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"));
        Person onePersonFather = new Person("Artur", LocalDate.of(1995, 12, 13), new Address("Portugal"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        try {
            Person person1Mother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather);
            Person person2Mother = new Person("Maria", null, new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), onePersonMother, onePersonFather);

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
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Lyon"), new Address("Rua X", "Porto", "4520-266"));

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
        Person person1 = new Person("Teresa", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Paulo", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Paulo", LocalDate.of(1995, 12, 13), new Address("Coimbra"), new Address("Rua X", "Porto", "4520-266"));
        Person person4 = new Person("Luis", LocalDate.of(1995, 12, 13), new Address("Mozelos"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("FozCoa"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("António", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));

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
        Person person1 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("António", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Manuel", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Anna", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Susan", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person4 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Anna", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Susan", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person4 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person5 = new Person("Jessica", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person6 = new Person("Jack", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Anna", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Susan", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person4 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person5 = new Person("Jessica", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person mother1 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person mother2 = new Person("Josefa", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person1 = new Person("Ricardo", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person mother1 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person1 = new Person("Ricardo", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person mother1 = new Person("Teresa", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person1 = new Person("Ricardo", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person mother1 = new Person("Teresa", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person mother2 = null;
        Person person1 = new Person("Ricardo", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("Miguel", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Anna", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        //siblings:
        Person sibling1 = new Person("Susan", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person sibling2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person sibling3 = new Person("Jessica", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person sibling4 = new Person("Jack", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Anna", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        //siblings:
        Person sibling1 = new Person("Susan", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person sibling2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person sibling3 = new Person("Jessica", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person sibling4 = new Person("Jack", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
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
        int year = 1993;
        int month = 9;
        int day = 1;
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

        Person onePerson = new Person(name, LocalDate.of(1995, 12, 13), birthPlaceJoao, new Address("Rua X", "Porto", "4520-266"));
        Person otherPerson = new Person(otherPersonName, LocalDate.of(1995, 12, 13), birthPlaceMarta, new Address("Rua X", "Porto", "4520-266"));
        Person brother = new Person(brotherName, LocalDate.of(1995, 12, 13), birthPlacePaulo, new Address("Rua X", "Porto", "4520-266"));
        Person sister = new Person(sisterName, LocalDate.of(1995, 12, 13), birthPlaceDiana, new Address("Rua X", "Porto", "4520-266"));

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

        Person onePerson = new Person(name, LocalDate.of(1995, 12, 13), birthPlaceJoao, new Address("Rua X", "Porto", "4520-266"));
        Person otherPerson = new Person(otherPersonName, LocalDate.of(1995, 12, 13), birthPlaceMarta, new Address("Rua X", "Porto", "4520-266"));
        Person brother = new Person(brotherName, LocalDate.of(1995, 12, 13), birthPlacePaulo, new Address("Rua X", "Porto", "4520-266"));
        Person sister = new Person(sisterName, LocalDate.of(1995, 12, 13), birthPlaceDiana, new Address("Rua X", "Porto", "4520-266"));

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
        Person person1 = new Person("João Cardoso", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        Person brother = new Person("Paulo", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person sister = new Person("Diana", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

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
        Person person1 = new Person("João Cardoso", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        Person brother = new Person("Paulo", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person sister = new Person("Diana", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

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
        Person person1 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Filipa", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person father1 = new Person("Antonio", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));

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
        Person person1 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Filipa", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person father1 = new Person("josé", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));
        Person father2 = new Person("Afonso", LocalDate.of(1995, 12, 13), new Address("Gaia"), new Address("Rua X", "Porto", "4520-266"));

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
        Person person1 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Filipa", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person father1 = new Person("jose", LocalDate.of(1995, 12, 13), new Address("Matosinhos"), new Address("Rua X", "Porto", "4520-266"));
        Person father2 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Filipa", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person father1 = new Person("jose", LocalDate.of(1995, 12, 13), new Address("Matosinhos"), new Address("Rua X", "Porto", "4520-266"));
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
        Person mother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person1 = new Person("António", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Manuel", LocalDate.of(1995, 12, 13), new Address("Matosinhos"), new Address("Rua X", "Porto", "4520-266"));

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
        Person father = new Person("José", LocalDate.of(1995, 12, 13), new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person1 = new Person("António", LocalDate.of(1995, 12, 13), new Address("Matosinhos"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Manuel", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

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
        Person person1 = new Person("António", LocalDate.of(1995, 12, 13), new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Manuel", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Roberto", LocalDate.of(1995, 12, 13), new Address("Matosinhos"), new Address("Rua X", "Porto", "4520-266"));

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
        Person mae = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Penacova"), new Address("Rua X", "Porto", "4520-266"));
        Person mama = new Person("Amália", LocalDate.of(1995, 12, 13), new Address("Penacova"), new Address("Rua X", "Porto", "4520-266"));
        Person senhor = new Person("Ricardo", LocalDate.of(1995, 12, 13), new Address("Penacova"), new Address("Rua X", "Porto", "4520-266"));
        Person pai = new Person("José", LocalDate.of(1995, 12, 13), new Address("Penacova"), new Address("Rua X", "Porto", "4520-266"));
        Person antonio = new Person("António", LocalDate.of(1995, 12, 13), new Address("Penacova"), new Address("Rua X", "Porto", "4520-266"));
        Person manuel = new Person("Manuel", LocalDate.of(1995, 12, 13), new Address("Penacova"), new Address("Rua X", "Porto", "4520-266"));

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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";

        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        boolean type = false; //debit

        //Act
        boolean result = person.createTransaction(amount, description, null, category, from, to, type);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a transaction was created - monetary value is negative")
    void createTransactionAccountNegativeMonetaryValue() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        MonetaryValue amountPositive = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue amountNegative = new MonetaryValue(-50, Currency.getInstance("EUR"));
        String description1 = "payment";

        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");

        Account accountWallet = new Account("Wallet", "General expenses");
        Account accountTransport = new Account("Transport", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("Transport", "Transport expenses");

        boolean type = false; //debit

        //Act
        try {
            person.createTransaction(amountNegative, description1, null, category, accountWallet, accountTransport, type);
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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        MonetaryValue amount = new MonetaryValue(50, Currency.getInstance("EUR"));
        String description = "payment";

        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");

        Account accountWallet = new Account("Wallet", "General expenses");
        person.createAccount("Wallet", "General expenses");

        boolean type = false; //debit

        //Act
        boolean result = person.createTransaction(amount, description, null, category, accountWallet, accountWallet, type);

        //Assert
        assertFalse(result);
    }


    /**
     * Test if an Account was created
     */

    @Test
    @DisplayName("Test if an Account was created - success case")
    void createAccountSuccessCase() {
        // Arrange

        Person onePerson = new Person("João", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        String accountDenomination = "Wallet";
        String accountDescription = "General expenses";

        //Act
        boolean real = onePerson.createAccount(accountDenomination, accountDescription);

        // assert
        assertTrue(real);
    }

    /**
     * * Tests to validate if a category was added to Category List
     */
    @Test
    @DisplayName("Check if a category was added to Category List - Main Scenario")
    void createCategoryAndAddToCategoryListMainScenario() {
        //Arrange
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Category to be included in Category List
        String category1 = "School expenses";

        //Act
        boolean realResult = person1.createCategoryAndAddToCategoryList(category1);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Check if null category is not added")
    void createCategoryAndAddToCategoryListWithANullCase() {
        //Arrange
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Category to be included in Category List
        String category1 = null;
        //Act
        try {
            person1.createCategoryAndAddToCategoryList(category1);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category description is not valid or it's missing. Please try again.", description.getMessage());
        }
    }


    @Test
    @DisplayName("Check if the same Category is not added simultaneously")
    void createAndAddTwoCategoriesToListWithTwoCategoriesThatAreTheSame() {
        //Arrange
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Categories to be included in Category List
        String category1 = "School expenses";
        String category2 = "School expenses";

        //Act
        boolean realResult = person1.createCategoryAndAddToCategoryList(category1) && !person1.createCategoryAndAddToCategoryList(category2);

        //Assert
        assertTrue(realResult);

    }

    @Test
    @DisplayName("Check if the same Category is not added simultaneously - Ignore letter capitalization and special characters ")
    void createAndAddTwoCategoriesToListWithTwoCategoriesCaseInsensitive() {
        //Arrange
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Categories to be included in Category List
        String category1 = "School expenses";
        String category2 = "SCHOóL expenses";

        //Act
        boolean realResult = person1.createCategoryAndAddToCategoryList(category1) && !person1.createCategoryAndAddToCategoryList(category2);

        //Assert
        assertTrue(realResult);
    }

    /**
     * * Tests to validate if two persons are equal with only the birthdate different
     */
    @Test
    @DisplayName("Check if two persons are the same if they have different ages")
    void checkEqualsSameAttributesButDifferentAge() {
        //Arrange
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Alexandre", LocalDate.of(1995, 12, 14), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        boolean realResult = person1.equals(person2);

        //Assert
        assertFalse(realResult);
    }

    @Test
    @DisplayName("Check if two persons have the same birthdate - 2nd constructor")
    void checkIfTwoPeopleHaveTheSameBirthdate() {
        //Arrange
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Pai do Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Act
        boolean realResult = person3.getBirthDate().equals(person1.getBirthDate());

        //Assert
        assertTrue(realResult);
    }

    /**
     * * Tests to validate if a person is another person's mother
     */
    @Test
    @DisplayName("Check if a given person anothers' mother - true")
    void checkIsMotherTrue() {
        //Arrange
        Person person1 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Diana", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
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
        Person person1 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
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

        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
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

        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
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

        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
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

        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Group group1 = new Group("Test Group");

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
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Assert
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    @DisplayName("Test if two persons have the same Hashcode - False")
    public void testIfTwoPersonsHaveTheSameHashCodeFalse() {

        //Arrange & Act
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Assert
        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    /**
     * * Tests to validate if a category was added to Category List
     */
    @Test
    @DisplayName("Check if a category was added to Category List - Main Scenario")
    void removeMultipleCategoriesToListMainScenario() {
        //Arrange
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        person1.createCategoryAndAddToCategoryList("food");
        person1.createCategoryAndAddToCategoryList("car");
        person1.createCategoryAndAddToCategoryList("dog");
        person1.createCategoryAndAddToCategoryList("school");
        person1.createCategoryAndAddToCategoryList("drinks");

        //Act
        boolean result = person1.removeMultipleCategoriesToList(new HashSet<>(Arrays.asList("dog", "school", "drinks")));

        //Assert
        assertTrue(result);
    }

    /**
     * Test to check the number os categories in the CategoryList
     */

    @Test
    @DisplayName("Check the number of categories in the CategoryList")
    void numberOfCategoryInTheCategoryList() {
        //Arrange
        Person person1 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        String categoryDescriptionOne = "Health";
        String categoryDescriptionTwo = "Saude";
        String categoryDescriptionThree = "paz";
        HashSet<String> categoriesList = new HashSet<>(Arrays.asList(categoryDescriptionOne, categoryDescriptionTwo, categoryDescriptionThree));
        person1.createAndAddMultipleCategoriesToList(categoriesList);

        //Act
        int result = person1.numberOfCategoryInTheCategoryList();

        //Assert
        assertEquals(3, result);
    }


    @Test
    @DisplayName("Test if a person get their transactions in a given period - success case - one transaction -  US011")
    void returnPersonLedgerInDateRangeSuccessCaseOneTransaction() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit
        person.createTransaction(amount, "payment", dateTransaction1, category, from, to, type);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 23, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 20, 23, 00);

        Transaction transaction1 = new Transaction(amount, "payment", dateTransaction1, category, from, to, type);
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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 10, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - Transaction2//
        LocalDateTime dateTransaction2 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount2 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category2 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount2, "payment", dateTransaction2, category2, from, to, false);
        Transaction transaction2 = new Transaction(amount2, "payment", dateTransaction2, category2, from, to, false);

        //Arrange - Transaction3//
        LocalDateTime dateTransaction3 = LocalDateTime.of(2020, 1, 16, 13, 00);
        MonetaryValue amount3 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category3 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount3, "payment", dateTransaction3, category3, from, to, false);
        Transaction transaction3 = new Transaction(amount3, "payment", dateTransaction3, category3, from, to, false);

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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

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
        Person person = new Person("Miguel", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Account1", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Account1", "General expenses");
        person.createAccount("Account2", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - Transaction2//
        LocalDateTime dateTransaction2 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount2 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category2 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount2, "payment", dateTransaction2, category2, from, to, false);
        Transaction transaction2 = new Transaction(amount2, "payment", dateTransaction2, category2, from, to, false);

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
        Person person = new Person("Miguel", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Account1", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Account1", "General expenses");
        person.createAccount("Account2", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - ExpectedResult//
        List<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction1));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);

        //Act
        try {
            List<Transaction> personLedgerTransactions = person.returnPersonLedgerInDateRange(initialDate, null);
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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - ExpectedResult//
        List<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction1));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2030, 1, 9, 00, 00);

        //Act
        try {
            List<Transaction> personLedgerTransactions = person.returnPersonLedgerInDateRange(initialDate, finalDate);
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
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));

        //Init Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "car gas",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 1, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 6, 00, 00);

        double expectedPersonalBalanceFromDateRange = -95.4;

        //Act
        double personalBalanceInDateRange = person1.getPersonalBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Get the balance of my own transactions for one day - valid day")
    void getPersonalBalanceForJustOneDay() {
        //Arrange
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        //Init Transactions
        person1.createTransaction(new MonetaryValue(250, Currency.getInstance("EUR")), "Hostel Barcelona",
                LocalDateTime.of(2020, 1, 13, 13, 05),
                new Category("grocery"), new Account("Revolut", "For trips expenses"),
                new Account("Friends & Company", "Holidays"),
                true);
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "Pack of Super Bock",
                LocalDateTime.of(2020, 1, 13, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(60, Currency.getInstance("EUR")), "Car Gas",
                LocalDateTime.of(2020, 1, 18, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 13, 23, 59);

        double expectedPersonalBalanceFromDateRange = 230;

        //Act
        double personalBalanceInDateRange = person1.getPersonalBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }


    @Test
    @DisplayName("Get the balance of my own transactions over a valid date range but initial date and final date not in order")
    void getPersonalBalanceInDateRangeWithDatesNotInOrder() {
        //Arrange
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        //Init Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 6, 00, 00);
        LocalDateTime initialDate = LocalDateTime.of(2019, 12, 31, 00, 00);

        double expectedPersonalBalanceFromDateRange = -95.4;

        //Act
        double personalBalanceInDateRange = person1.getPersonalBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Get the balance of my own transactions over invalid date range - final date higher than today!")
    void getPersonalBalanceInDateRangeWithInvalidDate() {
        //Arrange
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        //Init Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 27, 00, 00);
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
    @DisplayName("Get the balance of my own transactions of an invalid date range - final date higher than today!")
    void getPersonalBalanceInDateRangeWithNullDate() {
        //Arrange
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        //Init Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

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
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

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
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        //Init Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

        //Act
        double personalBalanceInDateRange = person1.getPersonalBalanceInDateRange(initialDate, finalDate);


        assertEquals(0, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Schedule a personal transaction")
    void scheduleNewTransactionDaily() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        //Act
        boolean result = person.scheduleNewTransaction("daily", amount, description, null, category, from, to, type);

        Thread.sleep(2400); // 250 x 10 = 2500

        //Assert
        assertTrue(result && person.ledgerSize() == 10);
    }


    @Test
    void scheduleNewTransactionWorkingDays() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        //Act
        boolean result = person.scheduleNewTransaction("working days", amount, description, null, category, from, to, type);

        Thread.sleep(1900); // 500 x 4 = 2000

        //Assert
        assertTrue(result && person.ledgerSize() == 4);
    }

    @Test
    void scheduleNewTransactionWeekly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        //Act
        boolean result = person.scheduleNewTransaction("weekly", amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 750 x 4 = 3000

        //Assert
        assertTrue(result && person.ledgerSize() == 4);
    }


    @Test
    void scheduleNewTransactionMonthly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        //Act
        boolean result = person.scheduleNewTransaction("monthly", amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 1000 x 3 = 3000

        //Assert
        assertTrue(result && person.ledgerSize() == 3);
    }

    @Test
    void scheduleNewTransactionNoMatch() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        try {
            //Act
            person.scheduleNewTransaction("tomorrow", amount, description, null, category, from, to, type);

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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        person.createAccount("mercearia", "mercearia Continente");
        person.createAccount("transporte", "transporte Metro");
        person.createCategoryAndAddToCategoryList("grocery");
        Category category = new Category("grocery");
        boolean type = true;
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();

        //Act
        int sizeBefore = ledger.getLedgerSize();
        ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, account2, type);
        ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, account2, type);
        ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, account2, type);
        int sizeAfter = ledger.getLedgerSize();

        //Assert
        assertEquals(sizeBefore + 3, sizeAfter);
    }

    /**
     * Test to verify if multiple categories were removed to list
     */

    @Test
    @DisplayName("Test if a category was removed from the Category List - Main Scenario")
    void removeCategoryFromListMainScenario() {

        //Arrange
        String oneCategory = "Saude";
        String otherCategory = "Cinema";
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));


        //Act
        person.createCategoryAndAddToCategoryList(oneCategory);
        person.createCategoryAndAddToCategoryList(otherCategory);

        //Remove one Category

        boolean realResult = person.removeCategoryFromList(otherCategory);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("check if category is not in list and threfore cant be removed")
    void removeCategoryFromListThatIsNotInTheList() {

        //Arrange:
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        //Act:
        boolean isACategoryNotInListRemoved = person.removeCategoryFromList("Cinema");

        //Assert:
        assertFalse(isACategoryNotInListRemoved);
    }

    @Test
    @DisplayName("Add a Set of Categories to Category List - Main Scenario")
    void addMultipleCategoriesToListMainScenario() {
        // Arrange
        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryUniversity = "University";

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryUniversity));
        CategoryList newCategoryList = new CategoryList();

        //Act

        boolean validateIfTheSetOfCategoriesWasAdded = person1.createAndAddMultipleCategoriesToList(setOfCategories);

        //Assert
        assertTrue(validateIfTheSetOfCategoriesWasAdded);
    }

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Main Scenario")
    void removeMultipleCategoriesToList() {
        // Arrange
        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryBeauty = "Beauty";

        CategoryList newCategoryList = new CategoryList();

        //set of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryBeauty, categoryGym));

        //Act
        //Remove the set of categories with the method under test
        boolean realResult = person1.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        assertTrue(realResult);
    }

    /**
     * US010 Como utilizador, quero obter os movimentos de determinada conta num dado período.
     */

    @Test
    @DisplayName("Obtain transactions from an account - case of success")
    void obtainTransactionsFromAnAccount() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = person.getOneAccountTransactionsFromUser(account5, date1, date2);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - same day")
    void obtainTransactionsFromAnAccountSameDay() {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 00, 00);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 23, 59);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = person.getOneAccountTransactionsFromUser(account5, date1, date2);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - date null")
    void obtaintTransactionsDateNull() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        LocalDateTime date1 = null;
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 23, 59);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

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
    @DisplayName("Test Constructor - BirthDate")
    void descriptionConstructor() {

        //Arrange
        Person person1Mother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2Father= new Person("Mario", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person1 = new Person("Marilia", LocalDate.of(1995,12,3), new Address("Porto"), new Address("Rua Requeixos","Vizela", "4620-747" ), person1Mother, person2Father);
        LocalDate expected = LocalDate.of(1995, 12,3);

        //Act
        LocalDate real = person1.getBirthDate();

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test Constructor - BirthDate Null")
    void descriptionConstructorNull() {
        //Arrange
        Person person1Mother = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person2Father= new Person("Mario", LocalDate.of(1995, 12, 13), new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));
        Person person1 = new Person("Marilia", LocalDate.of(1995,12,3), new Address("Porto"), new Address("Rua Requeixos","Vizela", "4620-747"), person1Mother, person2Father);

        try {
            //Act
            person1.setBirthDate(null);

        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("Birth Date Can't be Null.", description.getMessage());
        }
    }
}
