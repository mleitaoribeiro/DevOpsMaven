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
        Person Alex = new Person("Alex", 1996,04,02,new Address("Lisboa"));

        //Act
        Alex.setName("Mario");
        String expected = "Mario";

        //Assert
        assertEquals(expected, Alex.getName());
    }

    @Test
    @DisplayName("Test for validating imput's name, name is not null before")
    public void validateNameNotNullBefore() {
        //Arrange
        Person Joao = new Person("João",1996,04,05,new Address(("Porto"));

        //Act
        Joao.setName("Alex");
        String expected = "Alex";

        //Assert
        assertEquals(expected, Joao.getName());
    }

    @Test
    @DisplayName("Test for validating imput's name, name is empty")
    public void validateNameEmpty() {
        //Arrange
        Person Joao = new Person("João",1996,04,03, new Address("Feira"));

        //Act
        Joao.setName("");
        String expected = "";

        //Assert
        assertEquals(expected, Joao.getName());
    }
    

    /**
     *Validate Input for Birthday Date
     */

    @Test
    @DisplayName("Test for validating birth date input => success case")
    public void validateBirthDate() {
        //Arrange
        Person Mary = new Person("Mary", 1996,03,07,new Address("Maia") );

        //Act
        Mary.setBirthDate(1995,4, 4);

        LocalDate expected = LocalDate.parse("1995-04-04");

        //Assert
        assertEquals(expected, Mary.getBirthDate());
    }
    @Test
    @DisplayName("Test for validating birth date input => error case ")
    public void validateBirthDate_invalidMonth() {
        //Arrange
        Person Pedro = new Person("Pedro", 1995,04,15, new Address("SaoJoao"));

        //Act
        try {
            Pedro.setBirthDate(1995, 13, 15);
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
        Person Rui = new Person("Rui", 1995,08,15, new Address("Lousada"));

        //Act
        try {
            Rui.setBirthDate(2021, 12, 15);
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
        Person Rui = new Person("Rui", 1995,08,16, new Address("lamas"));

        //Act
        try {
            Rui.setBirthDate(2000, 12, 50);
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
        Person personMarta = new Person("Marta",1996,05,12,new Address("Porto"));
        Person personElsa = new Person("Elsa",1987,01,16, new Address("Lyon"));

        //Act
        personMarta.addSibling(personElsa);

        //Assert
        assertTrue(personMarta.getSiblingList().contains(personElsa) && personElsa.getSiblingList().contains(personMarta));
    }

    /**
     *Test if multiple siblings were added to siblings list
     */

    @Test
    void addMultipleSiblings() {
        //Arrange
        Person p1 = new Person("Teresa",1987,04,05,new Address("Lisboa"));
        Person p2 = new Person("Paulo", 1994,05,18,new Address("Porto"));
        Person p3 = new Person("Paulo",1985,05,17, new Address("Coimbra"));
        Person p4 = new Person("Luis",2000,15,04,new Address("Mozelos"));
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
        Person p1=new Person("Maria",1956,05,17, new Address("FozCoa"));
        Person p2=new Person("António",1985,10,12, new Address("Gaia"));

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
        Person p1=new Person("Maria",1954,10,14,new Address("Porto"));
        Person p2=new Person("António",1985,10,02, new Address("Porto"));
        Person p3=new Person("Manuel",1993,10,04, new Address("Gaia");
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
    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - 1 remaining")
    void validateMultipleSiblingRemoval (){
        //Arrange
        Person p1=new Person("John",1996,12,9, new Address("gaia"));
        Person p2=new Person("Anna",1993,2,23, new Address("Gaia"));
        Person p3=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person p4=new Person("Frank",1996,12,5, new Address("Gaia"));
        HashSet<Person>threeSiblings=new HashSet<>(Arrays.asList(p2,p3,p4));
        HashSet<Person>twoSiblings= new HashSet<>(Arrays.asList(p3,p4));

        //Act
        p1.addMultipleSiblings(threeSiblings);
        p1.removeMultipleSibling(twoSiblings);

        //Assert
        assertTrue(p1.getSiblingList().size()==1);
    }

    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - multiple remaining")
    void validateMultipleSiblingRemoval2 (){
        //Arrange
        Person p1=new Person("John",1996,12,9, new Address("Gaia"));
        Person p2=new Person("Anna",1993,2,23,new Address("Gaia"));
        Person p3=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person p4=new Person("Frank",1996,12,5,new Address("Gaia"));
        Person p5=new Person("Jessica",2002,12,3,new Address("Gaia"));
        Person p6= new Person("Jack", 1990,1,3,new Address("Gaia"));
        HashSet<Person>totalSib=new HashSet<>(Arrays.asList(p2,p3,p4,p5,p6));
        HashSet<Person>removeSib= new HashSet<>(Arrays.asList(p3,p4));

        //Act
        p1.addMultipleSiblings(totalSib);
        p1.removeMultipleSibling(removeSib);

        //Assert
        assertTrue(p1.getSiblingList().size()==3);
    }

    @Test
    @DisplayName("Validate if multiple siblings were removed from a siblings list - not contains exact ones")
    void validateMultipleSiblingRemoval_not_contains_exact_ones (){
        //Arrange
        Person p1=new Person("John",1996,12,9,new Address("Gaia"));
        Person p2=new Person("Anna",1993,2,23,new Address("Gaia"));
        Person p3=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person p4=new Person("Frank",1996,12,5,new Address("Gaia"));
        Person p5=new Person("Jessica",2002,12,3,new Address("Gaia"));
        HashSet<Person>totalSib=new HashSet<>(Arrays.asList(p2,p3,p4,p5));
        HashSet<Person>removeSib= new HashSet<>(Arrays.asList(p3,p4));
        HashSet<Person>expectedSib=new HashSet<>(Arrays.asList(p2,p5));

        //Act
        p1.addMultipleSiblings(totalSib);
        p1.removeMultipleSibling(removeSib);

        //Assert
        assertTrue(p1.getSiblingList().containsAll(expectedSib));
    }



    /**
     *  Test if two people have the same mother
     */
    @Test
    @DisplayName("Validate if two people have the same mother - False")
    void checkSameMother_false(){
        Person motherP1 = new Person("Maria",1988,3,23,new Address("Gaia"));
        Person motherP2 = new Person("Josefa",1987,4,23,new Address("Gaia"));
        Person p1 = new Person("Ricardo",2005,4,20, new Address("Gaia");
        Person p2 = new Person("Pedro",2006,4,21,new Address("Gaia"));
        p1.setMother(motherP1);
        p2.setMother(motherP2);
        //Act
        boolean result = p1.checkSameMother(p2);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - True")
    void checkSameMother_true(){
        Person motherP1 = new Person("Maria",1988,3,23,new Address("Gaia"));
        Person p1 = new Person("Ricardo",2005,4,20,new Address("Gaia");
        Person p2 = new Person("Pedro",2006,4,21,new Address("Gaia"));
        p1.setMother(motherP1);
        p2.setMother(motherP1);
        //Act
        boolean result = p1.checkSameMother(p2);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - no mother")
    void checkSameMother_no_mother(){
        Person motherP1 = new Person("Teresa",1980,1,23,new Address("Gaia"));
        Person p1 = new Person("Ricardo",2005,4,20,new Address("Gaia"));
        Person p2 = new Person("Pedro",2006,4,21,new Address("Gaia"));
        p1.setMother(motherP1);
        //Act
        boolean result = p1.checkSameMother(p2);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if two people have the same mother - Null mother")
    void checkSameMother_null_mother(){
        Person motherP1 = new Person("Teresa",1980,1,23,new Address("Gaia"));
        Person motherP2 = null;
        Person p1 = new Person("Ricardo",2005,4,20,new Address("Gaia"));
        Person p2 = new Person("Pedro",2006,4,21,new Address("Gaia"));
        p1.setMother(motherP1);
        p2.setMother(motherP2);
        //Act
        boolean result = p1.checkSameMother(p2);
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
        Person p1=new Person("John",1996,12,9,new Address("Gaia"));
        Person p2=new Person("Anna",1993,2,23,new Address("Gaia"));
        //siblings:
        Person s1=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person s2=new Person("Frank",1996,12,5,new Address("Gaia"));
        Person s3=new Person("Jessica",2002,12,3,new Address("Gaia"));
        Person s4= new Person("Jack", 1990,1,3,new Address("Gaia"));
        //siblingList arrangement
        HashSet<Person> p1siblings = new HashSet<>(Arrays.asList(p2,s1,s2,s3,s4));
        HashSet<Person> p2siblings = new HashSet<>(Arrays.asList(p1,s1,s2,s3,s4));

        //Act
        p1.addMultipleSiblings(p1siblings);
        p2.addMultipleSiblings(p2siblings);

        //Assert
        assertTrue(p1.checkSameSiblings(p2));
    }

    @Test
    @DisplayName("Validate if two people have the same siblings - False")
    void compareSameSiblings2(){
        //Arrange
        Person p1=new Person("John",1996,12,9,new Address("Gaia"));
        Person p2=new Person("Anna",1993,2,23,new Address("Gaia"));
        //siblings:
        Person s1=new Person("Susan",1993,3,9,new Address("Gaia"));
        Person s2=new Person("Frank",1996,12,5,new Address("Gaia"));
        Person s3=new Person("Jessica",2002,12,3,new Address("Gaia"));
        Person s4= new Person("Jack", 1990,1,3,new Address("Gaia"));
        //siblingList arrangement
        HashSet<Person> p1siblings = new HashSet<>(Arrays.asList(s1,s2));
        HashSet<Person> p2siblings = new HashSet<>(Arrays.asList(s3,s4));

        //Act
        p1.addMultipleSiblings(p1siblings);
        p2.addMultipleSiblings(p2siblings);

        //Assert
        assertFalse(p1.checkSameSiblings(p2));
    }

    /**
     *  Test if person exists on the other siblings list
     */

    @Test
    @DisplayName("Test if person exists on the other siblings lis | True")
    public void personExistsOtherSiblingsList_1() {
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
        String brotherName = "Paulo";
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

        Person brother = new Person (brotherName,brotherYear,brotherMonth,brotherDay);
        Person sister = new Person(sisterName,sisterYear,sisterMonth,sisterDay);

        //Act
        onePerson.addSibling(brother);
        onePerson.addSibling(sister);
        onePerson.addSibling(otherPerson);

        otherPerson.addSibling(brother);
        otherPerson.addSibling(sister);
        otherPerson.addSibling(onePerson);

        brother.addSibling(onePerson);
        sister.addSibling(onePerson);

        boolean personExistsOtherSiblingsList= onePerson.personExistsOtherSiblingsList();

        //Assert
        assertTrue(personExistsOtherSiblingsList);
    }

    @Test
    @DisplayName("Test if person exists on the other siblings lis | True")
    public void personExistsOnOtherSiblingsList_2() {
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
        String brotherName = "Paulo";
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
        Person brother = new Person (brotherName,brotherYear,brotherMonth,brotherDay);
        Person sister = new Person(sisterName,sisterYear,sisterMonth,sisterDay);

        HashSet<Person> onePersonSiblings = new HashSet<>(Arrays.asList(brother,sister,otherPerson));
        HashSet<Person> otherPersonSiblings = new HashSet<>(Arrays.asList(onePerson,sister));
        HashSet<Person> brotherSiblings = new HashSet<>(Collections.singletonList(onePerson));
        HashSet<Person> sisterSiblings = new HashSet<>(Collections.singletonList(otherPerson));

        //Act
        onePerson.addMultipleSiblings(onePersonSiblings);
        otherPerson.addMultipleSiblings(otherPersonSiblings);
        brother.addMultipleSiblings(brotherSiblings);
        sister.addMultipleSiblings(sisterSiblings);

        boolean personExistsOtherSiblingsList = onePerson.personExistsOtherSiblingsList();

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
        String brotherName = "Paulo";
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
        Person brother = new Person (brotherName,brotherYear,brotherMonth,brotherDay);
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
    public void personDoNotExistsOnTheOtherPersonSiblingsList_01() {
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
        String brotherName = "Paulo";
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
        Person brother = new Person (brotherName,brotherYear,brotherMonth,brotherDay);
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


/**
 *Test if two people have the same father
 */
    @Test
    @DisplayName("Two Equals father_true")
    void checkSameFather_True() {
    //Arrange
    Person p1=new Person("Elsa",2000,02,24,new Address("Gaia"));
    Person p2=new Person("Filipa",1990,01,05,new Address("Gaia"));
    Person father1=new Person("Antonio",1970,02,15,new Address("Gaia"));

    //Act
    p1.setFather(father1);
    p2.setFather(father1);

    boolean result= p1.checkSameFather(p2);

    //Assert
    assertTrue(result);
    }
    @Test
    @DisplayName("Two Equals father_null")
    void checkSameFather() {
        //Arrange
        Person p1=new Person("Elsa",2000,02,24,new Address("Gaia");
        Person p2=new Person("Filipa",1990,01,05,new Address("Gaia"));
        Person father1=new Person(null,1990,1,12,new Address("Gaia"));
        Person father2=new Person("Afonso",1950,8,07,new Address("Gaia"));

        //Act
        p1.setFather(father1);
        p2.setFather(father2);

        boolean result= p1.checkSameFather(p2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Two Equals father_false")
    void checkSameFather_FalseFather() {
        //Arrange
        Person elsa=new Person("Elsa",2000,02,24, new Address("Miragaia"));
        Person filipa=new Person("Filipa",1990,01,05, new Address("Porto"));
        Person paiJose=new Person("jose",1980,05,04, new Address("Matosinhos"));
        Person paiPedro=new Person("Pedro",1970,05,04, new Address("Miragaia"));
        //Act
        elsa.setFather(paiJose);
        filipa.setFather(paiPedro);

        boolean result= elsa.checkSameFather(filipa);

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
        Person mae =new Person("Maria",1965,3,4, new Address("Miragaia"));
        Person antonio=new Person("António",1987,12,9, new Address("Porto"));
        Person manuel=new Person("Manuel",1986,9,12, new Address("Matosinhos"));

        //Act
        antonio.setMother(mae);
        manuel.setMother(mae);
        boolean resultado=antonio.isSibling(manuel);

        //Assert
        assertEquals(true,resultado);
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void isSiblingsSameFather() {
        //Arrange
        Person pai=new Person("José",1963,3,9, new Address("Miragaia"));
        Person antonio=new Person("António",1987,12,9, new Address("Matosinhos"));
        Person manuel=new Person("Manuel",1986,9,12, new Address("Porto"));

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
        Person antonio=new Person("António",1987,12,9, new Address("Miragaia"));
        Person manuel=new Person("Manuel",1986,9,12, new Address("Porto"));
        Person roberto=new Person("Roberto",1992,8,10, new Address("Matosinhos"));


        HashSet<Person> siblings=new HashSet<Person>(Arrays.asList(manuel,roberto));
        HashSet<Person>siblings2=new HashSet<Person>(Arrays.asList(antonio,roberto));

        //Act
        antonio.addMultipleSiblings(siblings);
        roberto.addMultipleSiblings(siblings2);

        boolean resultado=antonio.isSibling(manuel);

        //Assert
        assertEquals(true,resultado);
    }
}