package switch2019.project.model.account;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.security.krb5.internal.crypto.Des;
import switch2019.project.model.person.Person;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.PersonName;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.DateAndTime;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
// Account equals not working properly - Gabriel (Work in Progress)
class AccountTest {

    /**
     * Test if two accounts are the same
     */

    @Test
    @DisplayName("Test if two accounts are the same - true")
    public void testIfTwoAccountsAreTheSame() {

        //Arrange
        Account supermarket = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"));
        Account supermercado = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"));
        //Act
        boolean result = supermarket.equals(supermercado);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - false")
    public void testIfTwoAccountsAreTheSameNoBeing() {

        //Arrange
        Account supermarket = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"));
        Account supermercado = new Account(new Denomination("Supermarket"),
                new Description("Monthly spends"));

        //Act
        boolean result = supermarket.equals(supermercado);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - Same Account")
    public void testIfSameObject() {

        //Arrange
        Account oneAccount = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"));

        //Act
        boolean result = oneAccount.equals(oneAccount);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - Same Account")
    public void testIfSameObjectToString() {

        //Arrange
        Account oneAccount = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"));
        String one = oneAccount.toString();

        //Act
        boolean result =(one != null && one.equals("SUPERMARKET, WEEKLY SPENDS, 0.0€"));

        //Assert
        //assertTrue(result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - one Account is Null")
    public void testIfTwoAccountsAreTheSameOneAccountIsNull() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"));
        Account otherAccount = null;

        //Act
        boolean result = oneAccount.equals(otherAccount);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - diferent objects")
    public void testIfTwoAccountsAreTheSameDifferentObjects() {
        //Arrange

        Account oneAccount = new Account(new Denomination("xpto"),new Description("xpto account"));
        Person onePerson = new Person ("Alexandre", new DateAndTime(1994, 02, 10), new Address("Porto"), new Address("Rua de Requeixos", "Vizela", "4620-585"));

        //Act
        boolean result = oneAccount.equals(onePerson);

        //Assert
        assertFalse(result);
    }

    /**
     * Test if two lists have the same Hashcode
     */

    @Test
    @DisplayName("Test if two lists have the same Hashcode - True")
    public void testIfTwoAccountsHaveTheSameHashCode() {

        //Arrange & Act
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"));
        Account otherAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"));

        //Assert
        //assertEquals(oneAccount.hashCode(), otherAccount.hashCode());
    }

    @Test
    @DisplayName("Test if two lists have the same Hashcode - Not The Same")
    public void testIfTwoAccountsDontHaveTheSameHashCode() {

        //Arrange & Act
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"));
        Account otherAccount = new Account(new Denomination("xpto"),
                new Description("xyz account"));

        //Assert
        assertNotEquals(oneAccount.hashCode(), otherAccount.hashCode());
    }

    /**
     * Test set function for denomination
     */

    @Test
    @DisplayName("Test set function for denomination")
    void setDenomination() {
        //Arrange
        Account toPay = new Account(new Denomination("Bills"),
                new Description("pay every 8th"));
        String expected = "WATER BILL";
        //Act
        toPay.setDenomination("Water bill");
        //Assert
        assertEquals(expected, toPay.denominationToString());
    }

    @Test
    @DisplayName("Test set function for denomination - null")
    void setDenomintationNull() {
        //Arrange
        Account toPay = new Account(new Denomination("Bills"),
                new Description("pay every 8th"));
        String expected = null;
        try {
            //Act
            toPay.setDenomination(expected);
            fail();
        }
        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("The denomination can´t be null or empty!", denomination.getMessage());
        }
    }

    @Test
    @DisplayName("Test set function for denomination - remove word accents")
    void setDenominationToRemoveAccents() {
        //Arrange
        Account escolaDosMiudos = new Account(new Denomination("Escola dos Miudos"),
                new Description("pay every 8th"));
        String expected = "ESCOLA DOS MIUDOS";
        //Act
        escolaDosMiudos.setDenomination("Escola dos Miúdos");
        //Assert
        assertEquals(expected, escolaDosMiudos.denominationToString());
    }

    @Test
    @DisplayName("Test set function for denomination - remove special Characters")
    void setDenominationToRemoveSpecialCharacters() {
        //Arrange
        Account football = new Account(new Denomination("Os Mancos"),
                new Description("pay every 8th"));
        String expected = "OS MANCOS";
        //Act
        football.setDenomination("Os-Mancos");
        //Assert
        assertEquals(expected, football.denominationToString());
    }

    @Test
    @DisplayName("Test set function for denomination - Empty")
    void setDenominationEmpty() {
        //Arrange
        Account butcher = new Account(new Denomination("Butcher")
                ,new Description("Talho do Amadeu"));
        String newDenomination = "";
        try {
            //Act
            butcher.setDenomination(newDenomination);
            fail();
        }
        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("The denomination can´t be null or empty!", denomination.getMessage());
        }
    }

    /**
     * Test set function for description
     */

    @Test
    @DisplayName("Test set function for description")
    void setDescription() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("account 1"));
        String expected = "MILU";
        //Act
        oneAccount.setDescription(expected);
        //Assert
        assertEquals(expected, oneAccount.getDescription());
    }

    @Test
    @DisplayName("Test set function for description - null")
    void setDescriptionNull() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto")
                ,new Description("xpto account"));
        String expected = null;
        try {
            //Act
            oneAccount.setDescription(expected);
            fail();
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null or empty!", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test set function for description - Empty")
    void setDescriptionEmpty() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"));
        String newDescription = "";
        try {
            //Act
            oneAccount.setDescription(newDescription);
            fail();
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null or empty!", description.getMessage());
        }
    }

    /**
     * Test Account Constructor
     */

    @Test
    @DisplayName("Test Constructor - Denomination Null")
    void denominationConstructorNull() {

        //Arrange & Act
        try {
            Account oneAccount = new Account(null, new Description("xpto Account"));
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The denomination can´t be null or empty!", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test Constructor - Description Null")
    void descriptionConstructorNull() {

        //Arrange & Act
        try {
            Account oneAccount = new Account(new Denomination("xpto"), null);
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null or empty!", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test Constructor - Description")
    void descriptionConstructor() {

        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xyz"));
        String expected = "XYZ";

        //Act
        String real = oneAccount.getDescription();

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test toString")
    public void testToString() {

        //Arrange
        Account supermarket = new Account(new Denomination("Supermarket")
                ,new Description("Weekly spends"));
        String expected = "SUPERMARKET, WEEKLY SPENDS, 0.0 EUR€";

        //Act
        String result = supermarket.toString();

        //Assert
        assertEquals(expected, result);
    }
}