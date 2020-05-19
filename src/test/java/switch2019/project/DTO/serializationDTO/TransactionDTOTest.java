package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.category.Category;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDTOTest {


    /**
     *
     * Tests to Equals
     *
     */

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
    void testEqualsSameObject() {

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
    @DisplayName("Test if two transactions are the equals - False- Different Objects")
    void testEqualsDifferentObjects() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

       CategoryDTO otherTransactionDTO = new CategoryDTO("REVOLUT", "1110120@isep.ipp.pt");

        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - False - Null")
    void testEqualsNull() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = null;


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }


    /**
     *
     * Tests to HashCode
     *
     */

    @Test
    @DisplayName("Tests to HashCode - True")
    void testSameHashCode() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        //Act
        int transactionDTOHashcode = transactionDTO.hashCode();
        int otherTransactionDTOHashcode = otherTransactionDTO.hashCode();

        //Assert
        assertEquals(transactionDTOHashcode, otherTransactionDTOHashcode);
    }

    @Test
    @DisplayName("Tests to HashCode - True")
    void testDifferentHashCode() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO("2 EUR", "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        //Act
        int transactionDTOHashcode = transactionDTO.hashCode();
        int otherTransactionDTOHashcode = otherTransactionDTO.hashCode();

        //Assert
        assertNotEquals(transactionDTOHashcode, otherTransactionDTOHashcode);

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