package switch2019.project.model.account;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.person.Person;
import switch2019.project.model.valueObject.Address;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    /**
     * Test if two accounts are the same
     */

    @Test
    @DisplayName("Test if two accounts are the same - true")
    public void testIfTwoAccountsAreTheSame() {

        //Arrange
        Account supermarket = new Account("Supermarket", "Weekly spends");
        Account supermercado = new Account("Supermarket", "Weekly spends");

        //Act
        boolean result = supermarket.equals(supermercado);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - false")
    public void testIfTwoAccountsAreTheSameNoBeing() {

        //Arrange
        Account supermarket = new Account("Supermarket", "Weekly spends");
        Account supermercado = new Account("Supermarket", "Monthly spends");

        //Act
        boolean result = supermarket.equals(supermercado);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - Same Account")
    public void testIfSameObject() {

        //Arrange
        Account oneAccount = new Account("Supermarket", "Weekly spends");

        //Act
        boolean result = oneAccount.equals(oneAccount);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - Same Account")
    public void testIfSameObjectToString() {

        //Arrange
        Account oneAccount = new Account("Supermarket", "Weekly spends");
        String one = oneAccount.toString();

        //Act
        boolean result = one != null && one.equals("SUPERMARKET, Weekly spends, 0.0€");

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - one Account is Null")
    public void testIfTwoAccountsAreTheSameOneAccountIsNull() {
        //Arrange
        Account oneAccount = new Account("xpto", "xpto account");
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
        Account oneAccount = new Account("xpto", "xpto account");
        Person onePerson = new Person("Alexandre", LocalDate.of(1994, 02, 10), new Address("Porto"), new Address("Rua de Requeixos", "Vizela", "4620-585"));

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
        Account oneAccount = new Account("xpto", "xpto account");
        Account otherAccount = new Account("xpto", "xpto account");

        //Assert
        assertEquals(oneAccount.hashCode(), otherAccount.hashCode());
    }

    @Test
    @DisplayName("Test if two lists have the same Hashcode - Not The Same")
    public void testIfTwoAccountsDontHaveTheSameHashCode() {

        //Arrange & Act
        Account oneAccount = new Account("xpto", "xpto account");
        Account otherAccount = new Account("xyz", "xyz account");

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
        Account toPay = new Account("Bills", "pay every 8th");
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
        Account toPay = new Account("Bills", "pay every 8th");
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
        Account escolaDosMiudos = new Account("Escola dos Miudos", "pay every 8th");
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
        Account football = new Account("Os Mancos", "pay every 8th");
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
        Account butcher = new Account("Butcher", "Talho do Amadeu");
        String newDenomination = "";
        try {
            //Act
            butcher.setDenomination(newDenomination);
            fail();
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The denomination can´t be null or empty!", description.getMessage());
        }
    }

    /**
     * Test set function for description
     */

    @Test
    @DisplayName("Test set function for description")
    void setDescription() {
        //Arrange
        Account oneAccount = new Account("xpto", "account 1");
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
        Account oneAccount = new Account("xpto", "xpto account");
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
        Account oneAccount = new Account("xpto", "xpto account");
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
            Account oneAccount = new Account(null, "xpto Account");
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
            Account oneAccount = new Account("xpto", null);
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
        Account oneAccount = new Account("xpto", "xyz");
        String expected = "xyz";

        //Act
        String real = oneAccount.getDescription();

        //Assert
        assertEquals(expected, real);
    }

}