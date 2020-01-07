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
    public void testIfTwoAccountsAreTheSame(){
        //Arrange
        Account supermarket =new Account("Supermarket","Weekly spends");
        Account supermercado =new Account("Supermarket","Weekly spends");

        //Act
        boolean result= supermarket.equals(supermercado);

        //Assert
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - false")
    public void testIfTwoAccountsAreTheSameNoBeing(){
        //Arrange
        Account supermarket =new Account("Supermarket","Weekly spends");
        Account supermercado =new Account("Supermarket","Monthly spends");

        //Act
        boolean result= supermarket.equals(supermercado);

        //Assert
        assertEquals(false,result);
    }





}