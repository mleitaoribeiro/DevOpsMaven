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
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test set function for denomination")
    void setDenomination() {
        //Arrange
        Account bills = new Account("Bills", "pay every 8th");
        String expected = "Water bill";
        //Act
        bills.setDenomination("Water bill");
        //Assert
        assertEquals(expected, bills.getDenomination());
    }

    @Test
    @DisplayName("Test set function for denomination - null")
    void setDenomintationNull() {
        //Arrange
        Account bills = new Account("Bills", "pay every 8th");
        String expected = "Water bill";
        try {
            //Act
            bills.setDenomination("Water bill");
            fail();
        }
        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("The denomination in your Account is not valid or it's missing. Please try again.", denomination.getMessage());
        }
    }


}