package switch2019.project.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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
        Person person1 = new Person("Alex", 1996,04,02,new Address("Lisboa"));

        //Act
        person1.setName("Mario");
        String expected = "Mario";

        //Assert
        assertEquals(expected, person1.getName());
    }

    @Test
    @DisplayName("Test for validating imput's name, name is not null before")
    public void validateNameNotNullBefore() {
        //Arrange
        Person person1 = new Person("João",1996,04,05,new Address(("Porto")));

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
        Person person1 = new Person("João",1996,04,03, new Address("Feira"));

        //Act
        person1.setName("");
        String expected = "";

        //Assert
        assertEquals(expected, person1.getName());
    }
    

    /**
     *Validate Input for Birthday Date
     */

    @Test
    @DisplayName("Test for validating birth date input => success case")
    public void validateBirthDate() {
        //Arrange
        Person person1 = new Person("Mary", 1996,03,07,new Address("Maia") );

        //Act
        person1.setBirthDate(1995,4, 4);

        LocalDate expected = LocalDate.parse("1995-04-04");

        //Assert
        assertEquals(expected, person1.getBirthDate());
    }
    @Test
    @DisplayName("Test for validating birth date input => error case ")
    public void validateBirthDate_invalidMonth() {
        //Arrange
        Person person1 = new Person("Pedro", 1995,04,15, new Address("SaoJoao"));

        //Act
        try {
            person1.setBirthDate(1995, 13, 15);
            fail();
        }
        //Assert
        catch (DateTimeException ex) {
            assertEquals("Invalid value for MonthOfYear (valid values 1 - 12): 13",ex.getMessage());
        }
    }
    @Test
    @DisplayName("Test for validating birth date input => error case ")
    public void validateBirthDate_birthDateAfterCurrentDate() {
        //Arrange
        Person person1 = new Person("Rui", 1995,8,15, new Address("Lousada"));

        //Act
        try {
            person1.setBirthDate(2021, 12, 15);
            fail();
        }
        //Assert
        catch (IllegalArgumentException ex) {
            assertEquals("Birth Date Not Supported.",ex.getMessage());
        }
    }
    @Test
    @DisplayName("Test for validating birth date input => error case")
    public void validateBirthDate_invalidDay() {
        //Arrange
        Person person1 = new Person("Rui", 1995,8,16, new Address("lamas"));

        //Act
        try {
            person1.setBirthDate(2000, 12, 50);
            fail();
        }
        //Assert
        catch (DateTimeException ex) {
            assertEquals("Invalid value for DayOfMonth (valid values 1 - 28/31): 50",ex.getMessage());
        }
    }

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
        //One Person BirthPlace
        Address onePersonBirthdayPlace = new Address( "Porto");
        //One Person Mother
        String onepersonMotherName = "Maria";
        //Mother BirthDate
        int onePersonMotherYear  = 1960;
        int onePersonMotherMonth = 3;
        int onePersonMotherDay = 4;

        //One Person Mother BirthPlace
        Address onePersonMotherBirthdayPlace = new Address( "Porto");

        // One Person Father
        String onepersonFatherName = "Artur";
        //Father BirthDate
        int onePersonFatherYear  = 1960;
        int onePersonFatherMonth = 3;
        int onePersonFatherDay = 4;

       //One Person FatherBirthPlace
        Address onePersonFatherBirthdayPlace = new Address( "Porto");


        Person onePersonMother = new Person (onepersonMotherName,onePersonMotherYear,onePersonMotherMonth,onePersonMotherDay,onePersonMotherBirthdayPlace);
        Person onePersonFather = new Person (onepersonFatherName,onePersonFatherYear,onePersonFatherMonth,onePersonFatherDay,onePersonFatherBirthdayPlace);

        Person onePerson = new Person(name, year, month, day, onePersonBirthdayPlace, onePersonMother, onePersonFather);
        Person samePerson = new Person(name, year, month, day, onePersonBirthdayPlace, onePersonMother, onePersonFather);

        //Act & Assert
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
        //One Person BirthPlace
        String onePersonBirthPlace = "Lisboa";
        Address onePersonAddress = new Address(onePersonBirthPlace);

        //One Person Mother
        String onepersonMotherName = "Maria";
        //Mother BirthDate
        int onePersonMotherYear  = 1960;
        int onePersonMotherMonth = 3;
        int onePersonMotherDay = 4;
        //One Person Mother BirthPlace
        Address onePersonMotherBirthdayPlace = new Address( "Porto");

        // One Person Father
        String onepersonFatherName = "Artur";
        //Father BirthDate
        int onePersonFatherYear  = 1960;
        int onePersonFatherMonth = 3;
        int onePersonFatherDay = 4;
        //One Person Father BirthPlace
        Address onePersonFatherBirthdayPlace = new Address( "Porto");

        //Other Person
        String otherPersonName = "João Cardoso";
        //Other Person BirthDate
        int otherPersonYear  = 1996;
        int otherPersonMonth = 3;
        int otherPersonDay = 4;
        //Other Person BirthPlace
        String otherPersonBirthPlace = "Porto";
        Address otherPersonAddress = new Address(otherPersonBirthPlace);

        //One Person Mother
        String otherPersonMotherName = "Maria";
        //Mother BirthDate
        int otherPersonMotherYear  = 1960;
        int otherPersonMotherMonth = 3;
        int otherPersonMotherDay = 4;
        //Other Person Mother BirthPlace
        Address otherPersonMotherBirthdayPlace = new Address( "Porto");

        // Other Person Father
        String otherpersonFatherName = "Artur";
        //Father BirthDate
        int otherPersonFatherYear  = 1960;
        int otherPersonFatherMonth = 3;
        int otherPersonFatherDay = 4;
        //Other Person Father BirthPlace
        Address otherPersonFatherBirthdayPlace = new Address( "Porto");


        Person onePersonMother = new Person (onepersonMotherName,onePersonMotherYear,onePersonMotherMonth,onePersonMotherDay,onePersonMotherBirthdayPlace);
        Person onePersonFather = new Person (onepersonFatherName,onePersonFatherYear,onePersonFatherMonth,onePersonFatherDay,onePersonFatherBirthdayPlace);
        Person otherPersonMother = new Person (otherPersonMotherName,otherPersonMotherYear,otherPersonMotherMonth,otherPersonMotherDay,otherPersonMotherBirthdayPlace);
        Person otherPersonFather = new Person (otherpersonFatherName,otherPersonFatherYear,otherPersonFatherMonth,otherPersonFatherDay,otherPersonFatherBirthdayPlace);

        Person onePerson = new Person(name, year, month, day, onePersonAddress, onePersonMother, onePersonFather);
        Person otherPerson = new Person(otherPersonName, otherPersonYear, otherPersonMonth, otherPersonDay, otherPersonAddress, otherPersonMother, otherPersonFather);

        //Act & Assert
        assertEquals(onePerson, otherPerson);
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
        //One Person BirthPlace
        String onePersonBirthPlace = "Porto";
        Address onePersonAddress = new Address(onePersonBirthPlace);

        //One Person Mother
        String onepersonMotherName = "Maria";
        //Mother BirthDate
        int onePersonMotherYear  = 1960;
        int onePersonMotherMonth = 3;
        int onePersonMotherDay = 4;
        //Mother BirthPlace
        String motherPersonBirthPlace = "Porto";
        Address motherPersonAddress = new Address(motherPersonBirthPlace);

        // One Person Father
        String onepersonFatherName = "Artur";
        //Father BirthDate
        int onePersonFatherYear  = 1960;
        int onePersonFatherMonth = 3;
        int onePersonFatherDay = 4;
        //Father BirthPlace
        Address fatherPersonAddress = new Address("Porto");

        //Other Person
        String otherPersonName = "João Cardoso";
        //Other Person BirthDate
        int otherPersonYear  = 1996;
        int otherPersonMonth = 3;
        int otherPersonDay = 4;
        //Other Person BirthPlace
        String otherPersonBirthPlace = "Lisboa";
        Address otherPersonAddress = new Address(otherPersonBirthPlace);

        //One Person Mother
        String otherPersonMotherName = "Maria";
        //Mother BirthDate
        int otherPersonMotherYear  = 1960;
        int otherPersonMotherMonth = 3;
        int otherPersonMotherDay = 4;
        //Other Person Mother BirthPlace
        Address otherPersonMotherAddress = new Address("Lisboa");
        // OtherPerson Father
        String otherpersonFatherName = "Raul";
        //Father BirthDate
        int otherPersonFatherYear  = 1960;
        int otherPersonFatherMonth = 3;
        int otherPersonFatherDay = 4;
        //Other Person Father BirthPlace
        Address otherPersonFatherBirthdayPlace = new Address( "Porto");


        Person onePersonMother = new Person (onepersonMotherName,onePersonMotherYear,onePersonMotherMonth,onePersonMotherDay,motherPersonAddress);
        Person onePersonFather = new Person (onepersonFatherName,onePersonFatherYear,onePersonFatherMonth,onePersonFatherDay,fatherPersonAddress);
        Person otherPersonMother = new Person (otherPersonMotherName,otherPersonMotherYear,otherPersonMotherMonth,otherPersonMotherDay,otherPersonMotherAddress);
        Person otherPersonFather = new Person (otherpersonFatherName,otherPersonFatherYear,otherPersonFatherMonth,otherPersonFatherDay,otherPersonFatherBirthdayPlace);

        Person onePerson = new Person(name, year, month, day, onePersonAddress, onePersonMother, onePersonFather);
        Person otherPerson = new Person(otherPersonName, otherPersonYear, otherPersonMonth, otherPersonDay, otherPersonAddress, otherPersonMother, otherPersonFather);


        //Act & Assert
        assertNotEquals(onePerson, otherPerson);
    }

    /**
     *Validate if a sibling was added to siblings list
     */

    @Test
    @DisplayName("Test for validating add a new sibling")
    public void validateAddSibling() {
        //Arrange
        Person person1 = new Person("Marta",1996,05,12,new Address("Porto"));
        Person person2 = new Person("Elsa",1987,01,16, new Address("Lyon"));

        //Act
        person1.addSibling(person2);

        //Assert
        assertTrue(person1.getSiblingList().contains(person2) && person2.getSiblingList().contains(person1));
    }

    /**
     *Test if multiple siblings were added to siblings list
     */

    @Test
    void addMultipleSiblings() {
        //Arrange
        Person person1 = new Person("Teresa",1987,4,5,new Address("Lisboa"));
        Person person2 = new Person("Paulo", 1994,5,18,new Address("Porto"));
        Person person3 = new Person("Paulo",1985,5,17, new Address("Coimbra"));
        Person person4 = new Person("Luis",2000,10,4,new Address("Mozelos"));
        HashSet<Person>newSiblings= new HashSet<>(Arrays.asList(person2,person4,person3));
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
    void validateRemoveSibling (){
        //Arrange
        Person person1=new Person("Maria",1956,05,17, new Address("FozCoa"));
        Person person2=new Person("António",1985,10,12, new Address("Gaia"));

        //Act
        person1.addSibling(person2);
        person1.removeSibling(person2);

        //Assert
        assertTrue(person1.getSiblingList().size()==0);
    }

    @Test
    @DisplayName("Validate if a sibling was removed from to siblings list - more than one sibling")
    void validateRemoveSiblingMoreThanOne (){
        //Arrange
        Person person1=new Person("Maria",1954,10,14,new Address("Porto"));
        Person person2=new Person("António",1985,10,2, new Address("Porto"));
        Person person3=new Person("Manuel",1993,10,4, new Address("Gaia"));
        HashSet<Person>threeSiblings=new HashSet<>(Arrays.asList(person2,person3));

        //Act
        person1.addMultipleSiblings(threeSiblings);
        person1.removeSibling(person2);

        //Assert
        assertTrue(person1.getSiblingList().size()==1);
    }

    /**
     * Test if multiple siblings were removed to siblings list
     */
    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - 1 remaining")
    void validateMultipleSiblingRemoval (){
        //Arrange
        Person person1=new Person("John",1996,12,9, new Address("gaia"));
        Person person2=new Person("Anna",1993,2,23, new Address("Gaia"));
        Person person3=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person person4=new Person("Frank",1996,12,5, new Address("Gaia"));
        HashSet<Person>threeSiblings=new HashSet<>(Arrays.asList(person2,person3,person4));
        HashSet<Person>twoSiblings= new HashSet<>(Arrays.asList(person3,person4));

        //Act
        person1.addMultipleSiblings(threeSiblings);
        person1.removeMultipleSibling(twoSiblings);

        //Assert
        assertTrue(person1.getSiblingList().size()==1);
    }

    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - multiple remaining")
    void validateMultipleSiblingRemoval2 (){
        //Arrange
        Person person1=new Person("John",1996,12,9, new Address("Gaia"));
        Person person2=new Person("Anna",1993,2,23,new Address("Gaia"));
        Person person3=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person person4=new Person("Frank",1996,12,5,new Address("Gaia"));
        Person person5=new Person("Jessica",2002,12,3,new Address("Gaia"));
        Person person6= new Person("Jack", 1990,1,3,new Address("Gaia"));
        HashSet<Person>totalSib=new HashSet<>(Arrays.asList(person2,person3,person4,person5,person6));
        HashSet<Person>removeSib= new HashSet<>(Arrays.asList(person3,person4));

        //Act
        person1.addMultipleSiblings(totalSib);
        person1.removeMultipleSibling(removeSib);

        //Assert
        assertTrue(person1.getSiblingList().size()==3);
    }

    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - not contains exact ones")
    void validateMultipleSiblingRemoval_not_contains_exact_ones (){
        //Arrange
        Person person1=new Person("John",1996,12,9,new Address("Gaia"));
        Person person2=new Person("Anna",1993,2,23,new Address("Gaia"));
        Person person3=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person person4=new Person("Frank",1996,12,5,new Address("Gaia"));
        Person person5=new Person("Jessica",2002,12,3,new Address("Gaia"));
        HashSet<Person>totalSib=new HashSet<>(Arrays.asList(person2,person3,person4,person5));
        HashSet<Person>removeSib= new HashSet<>(Arrays.asList(person3,person4));
        HashSet<Person>expectedSib=new HashSet<>(Arrays.asList(person2,person5));

        //Act
        person1.addMultipleSiblings(totalSib);
        person1.removeMultipleSibling(removeSib);

        //Assert
        assertTrue(person1.getSiblingList().containsAll(expectedSib));
    }

    /**
     *  Test if two people have the same mother
     */
    @Test
    @DisplayName("Validate if two people have the same mother - False")
    void checkSameMother_false(){
        Person mother1 = new Person("Maria",1988,3,23,new Address("Gaia"));
        Person mother2 = new Person("Josefa",1987,4,23,new Address("Gaia"));
        Person person1 = new Person("Ricardo",2005,4,20, new Address("Gaia"));
        Person person2 = new Person("Pedro",2006,4,21,new Address("Gaia"));
        person1.setMother(mother1);
        person2.setMother(mother2);
        //Act
        boolean result = person1.checkSameMother(person2);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - True")
    void checkSameMother_true(){
        Person mother1 = new Person("Maria",1988,3,23,new Address("Gaia"));
        Person person1 = new Person("Ricardo",2005,4,20,new Address("Gaia"));
        Person person2 = new Person("Pedro",2006,4,21,new Address("Gaia"));
        person1.setMother(mother1);
        person2.setMother(mother1);
        //Act
        boolean result = person1.checkSameMother(person2);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - no mother")
    void checkSameMother_no_mother(){
        Person mother1 = new Person("Teresa",1980,1,23,new Address("Gaia"));
        Person person1 = new Person("Ricardo",2005,4,20,new Address("Gaia"));
        Person person2 = new Person("Pedro",2006,4,21,new Address("Gaia"));
        person1.setMother(mother1);
        //Act
        boolean result = person1.checkSameMother(person2);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - Null mother")
    void checkSameMother_null_mother(){
        Person mother1 = new Person("Teresa",1980,1,23,new Address("Gaia"));
        Person mother2 = null;
        Person person1 = new Person("Ricardo",2005,4,20,new Address("Gaia"));
        Person person2 = new Person("Pedro",2006,4,21,new Address("Gaia"));
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
    void compareSameSiblings(){
        //Arrange
        Person person1=new Person("John",1996,12,9,new Address("Gaia"));
        Person person2=new Person("Anna",1993,2,23,new Address("Gaia"));
        //siblings:
        Person sibling1=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person sibling2=new Person("Frank",1996,12,5,new Address("Gaia"));
        Person sibling3=new Person("Jessica",2002,12,3,new Address("Gaia"));
        Person sibling4= new Person("Jack", 1990,1,3,new Address("Gaia"));
        //siblingList arrangement
        HashSet<Person> person1siblings = new HashSet<>(Arrays.asList(person2,sibling1,sibling2,sibling3,sibling4));
        HashSet<Person> person2siblings = new HashSet<>(Arrays.asList(person1,sibling1,sibling2,sibling3,sibling4));

        //Act
        person1.addMultipleSiblings(person1siblings);
        person2.addMultipleSiblings(person2siblings);

        //Assert
        assertTrue(person1.checkSameSiblings(person2));
    }

    @Test
    @DisplayName("Validate if two people have the same siblings - False")
    void compareSameSiblings2(){
        //Arrange
        Person person1=new Person("John",1996,12,9,new Address("Gaia"));
        Person person2=new Person("Anna",1993,2,23,new Address("Gaia"));
        //siblings:
        Person sibling1=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person sibling2=new Person("Frank",1996,12,5,new Address("Gaia"));
        Person sibling3=new Person("Jessica",2002,12,3,new Address("Gaia"));
        Person sibling4= new Person("Jack", 1990,1,3,new Address("Gaia"));
        //siblingList arrangement
        HashSet<Person> person1siblings = new HashSet<>(Arrays.asList(sibling1,sibling2));
        HashSet<Person> person2siblings = new HashSet<>(Arrays.asList(sibling3,sibling4));

        //Act
        person1.addMultipleSiblings(person1siblings);
        person2.addMultipleSiblings(person2siblings);

        //Assert
        assertFalse(person1.checkSameSiblings(person2));
    }

    /**
     *  Test if person exists on the other siblings list
     */

    @Test
    @DisplayName("Test if person exists on the other siblings list | True")
    public void personExistsOtherSiblingsList_1() {
        //Arrange
        Person Person1 = new Person("João Cardoso", 1993, 9, 1, new Address("Porto"));
        Person Person2 = new Person("Marta", 1996, 3, 4, new Address("Porto"));

        Person brother = new Person ("Paulo",1993,9,1,new Address("Porto"));
        Person sister = new Person("Diana",2000,9,1,new Address("Porto"));

        //Act
        Person1.addSibling(brother);
        Person1.addSibling(sister);
        Person1.addSibling(Person2);

        Person2.addSibling(brother);
        Person2.addSibling(sister);
        Person2.addSibling(Person1);

        brother.addSibling(Person1);
        sister.addSibling(Person1);

        boolean personExistsOtherSiblingsList= Person1.personExistsOtherSiblingsList();

        //Assert
        assertTrue(personExistsOtherSiblingsList);
    }

    @Test
    @DisplayName("Test if person exists on the other siblings list | True")
    public void personExistsOnOtherSiblingsList_2() {
        //Arrange
        Person Person1 = new Person("João Cardoso", 1993, 9, 1, new Address("Porto"));
        Person Person2 = new Person("Marta", 1996, 3, 4, new Address("Porto"));

        Person brother = new Person ("Paulo",1993,9,1,new Address("Porto"));
        Person sister = new Person("Diana",2000,9,1,new Address("Porto"));

        HashSet<Person> Person1Siblings = new HashSet<>(Arrays.asList(brother,sister,Person2));
        HashSet<Person> Person2Siblings = new HashSet<>(Arrays.asList(Person1,sister));
        HashSet<Person> brotherSiblings = new HashSet<>(Collections.singletonList(Person1));
        HashSet<Person> sisterSiblings = new HashSet<>(Collections.singletonList(Person2));

        //Act
        Person1.addMultipleSiblings(Person1Siblings);
        Person2.addMultipleSiblings(Person2Siblings);
        brother.addMultipleSiblings(brotherSiblings);
        sister.addMultipleSiblings(sisterSiblings);

        boolean personExistsOtherSiblingsList = Person1.personExistsOtherSiblingsList();

        //Assert
        assertTrue(personExistsOtherSiblingsList);
    }

    /**
     *  Test if Person exists on the other Person siblings list (USER STORIES)
     * @return boolean
     */

    @Test
    @DisplayName("Test if person exists on the other Person siblings list | True")
    public void personExistsOnTheOtherPersonSiblingsList_01() {
        //Arrange
        Person Person1 = new Person("João Cardoso", 1993, 9, 1, new Address("Porto"));
        Person Person2 = new Person("Marta", 1996, 3, 4, new Address("Porto"));

        Person brother = new Person ("Paulo",1993,9,1,new Address("Porto"));
        Person sister = new Person("Diana",2000,9,1,new Address("Porto"));

        //Act
        Person1.addSibling(Person2);
        Person1.addSibling(brother);
        Person1.addSibling(sister);

        boolean personExistsOtherPersonSiblingsList = Person1.personExistsOnSiblingsList(Person2);

        //Assert
        assertTrue(personExistsOtherPersonSiblingsList);
    }

    @Test
    @DisplayName("Test if person exists on the other Person siblings list | False")
    public void personDoNotExistsOnTheOtherPersonSiblingsList_01() {
        //Arrange
        Person Person1 = new Person("João Cardoso", 1993, 9, 1, new Address("Porto"));
        Person Person2 = new Person("Marta", 1996, 3, 4, new Address("Porto"));

        Person brother = new Person ("Paulo",1993,9,1,new Address("Porto"));
        Person sister = new Person("Diana",2000,9,1,new Address("Porto"));

        //Act
        Person1.addSibling(brother);
        Person1.addSibling(sister);

        Person2.addSibling(brother);
        Person2.addSibling(sister);

        boolean personExistsOtherPersonSiblingsList = Person1.personExistsOnSiblingsList(Person2);

        //Assert
        assertFalse(personExistsOtherPersonSiblingsList);
    }


/**
 *Test if two people have the same father
 */
    @Test
    @DisplayName("Two Equals father_true")
    void checkSameFather_True() {
    //Arrange
    Person person1=new Person("Elsa",2000,02,24,new Address("Gaia"));
    Person person2=new Person("Filipa",1990,01,05,new Address("Gaia"));
    Person father1=new Person("Antonio",1970,02,15,new Address("Gaia"));

    //Act
    person1.setFather(father1);
    person2.setFather(father1);

    boolean result= person1.checkSameFather(person2);

    //Assert
    assertTrue(result);
    }
    @Test
    @DisplayName("Two Equals father_null")
    void checkSameFather() {
        //Arrange
        Person person1=new Person("Elsa",2000,02,24,new Address("Gaia"));
        Person person2=new Person("Filipa",1990,01,05,new Address("Gaia"));
        Person father1=new Person(null,1990,1,12,new Address("Gaia"));
        Person father2=new Person("Afonso",1950,8,07,new Address("Gaia"));

        //Act
        person1.setFather(father1);
        person2.setFather(father2);

        boolean result= person1.checkSameFather(person2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Two Equals father_false")
    void checkSameFather_FalseFather() {
        //Arrange
        Person person1=new Person("Elsa",2000,02,24, new Address("Miragaia"));
        Person person2=new Person("Filipa",1990,01,05, new Address("Porto"));
        Person father1=new Person("jose",1980,05,04, new Address("Matosinhos"));
        Person father2=new Person("Pedro",1970,05,04, new Address("Miragaia"));
        //Act
        person1.setFather(father1);
        person2.setFather(father2);

        boolean result= person1.checkSameFather(person2);

        //Assert
        assertFalse(result);
    }

    /**
     * Test if two persons are siblings
     */

    @Test
    @DisplayName("Test if two individuals are siblings - same mother")
    void isSiblingsSameMother() {
        //Arrange
        Person mother =new Person("Maria",1965,3,4, new Address("Miragaia"));
        Person person1=new Person("António",1987,12,9, new Address("Porto"));
        Person person2=new Person("Manuel",1986,9,12, new Address("Matosinhos"));

        //Act
        person1.setMother(mother);
        person2.setMother(mother);
        boolean resultado=person1.isSibling(person2);

        //Assert
        assertEquals(true,resultado);
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void isSiblingsSameFather() {
        //Arrange
        Person father=new Person("José",1963,3,9, new Address("Miragaia"));
        Person person1=new Person("António",1987,12,9, new Address("Matosinhos"));
        Person person2=new Person("Manuel",1986,9,12, new Address("Porto"));

        //Act
        person1.setFather(father);
        person2.setFather(father);
        boolean resultado=person1.isSibling(person2);

        //Assert
        assertEquals(true,resultado);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - in each other list")
    void isSiblingsInTheSiblingsList() {
        //Arrange
        Person person1=new Person("António",1987,12,9, new Address("Miragaia"));
        Person person2=new Person("Manuel",1986,9,12, new Address("Porto"));
        Person person3=new Person("Roberto",1992,8,10, new Address("Matosinhos"));


        HashSet<Person> siblings=new HashSet<Person>(Arrays.asList(person2,person3));
        HashSet<Person>siblings2=new HashSet<Person>(Arrays.asList(person1,person3));

        //Act
        person1.addMultipleSiblings(siblings);
        person3.addMultipleSiblings(siblings2);

        boolean resultado=person1.isSibling(person2);

        //Assert
        assertEquals(true,resultado);
    }
}