package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDTOTest {




    @Test
    @DisplayName("Test if two transactions are the equals - True - Exactly same object")
    void testEqualsExactlySameObject() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        //Act
        boolean isEquals = transactionDTO.equals(transactionDTO);

        //Assert
        assertTrue(isEquals);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - True - Different equals objects")
    void testEquals() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertTrue(isEquals);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - False - Different Amount")
    void testEqualsDifferentAmount() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("5 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False - Different Description")
    void testEqualsDifferentDescription() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAY", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - False- Different Date")
    void testEqualsDifferentDate() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-1-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False- Different Category")
    void testEqualsDifferentCategory() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False- Different AccountFrom")
    void testEqualsDifferentAccountFrom() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "MONEY", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False- Different AccountTo")
    void testEqualsDifferentAccountTo() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "EBAY", "CREDIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False- Different Type")
    void testEqualsDifferentType() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }


    @Test
    void testHashCode() {
    }

    @Test
    void getAmount() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void getDate() {
    }

    @Test
    void getCategory() {
    }

    @Test
    void getAccountFrom() {
    }

    @Test
    void getAccountTo() {
    }

    @Test
    void getType() {
    }
}