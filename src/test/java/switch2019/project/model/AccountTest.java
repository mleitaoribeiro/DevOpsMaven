package switch2019.project.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Test set function for denomination")
    void setDenomination() {
        //Arrange
        Account toPay = new Account("Bills", "pay every 8th");
        String expected = "WATER BILL";
        //Act
        toPay.setDenomination("Water bill");
        //Assert
        assertEquals(expected, toPay.getDenomination());
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
            assertEquals("The denomination can´t be null. Please try again.", denomination.getMessage());
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
        assertEquals(expected, escolaDosMiudos.getDenomination());
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
        assertEquals(expected, football.getDenomination());
    }


    @Test
    @DisplayName("Test set function for description")
    void setDescription() {
        //Arrange
        Account oneAccount = new Account ("xpto","account 1");
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
        Account oneAccount = new Account ("xpto","xpto account");
        String expected = null;
        try {
            //Act
            oneAccount.setDescription(expected);
            fail();
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null. Please try again.", description.getMessage());
        }
    }


}