package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Currency;

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
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        //Act
        boolean isEquals = transactionDTO.equals(transactionDTO);

        //Assert
        assertTrue(isEquals);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - True - Different equals objects")
    void testEqualsSameObject() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

       //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertTrue(isEquals);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - False - Different Amount")
    void testEqualsDifferentAmount() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0 ,Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(5.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False - Different Description")
    void testEqualsDifferentDescription() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAY", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - False- Different Date")
    void testEqualsDifferentDate() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-1-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False- Different Category")
    void testEqualsDifferentCategory() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0,Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False- Different AccountFrom")
    void testEqualsDifferentAccountFrom() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "MONEY", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0,Currency.getInstance("EUR"),  "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False- Different AccountTo")
    void testEqualsDifferentAccountTo() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "EBAY", "DEBIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - False- Different Type")
    void testEqualsDifferentType() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");


        //Act
        boolean isEquals = transactionDTO.equals(otherTransactionDTO);

        //Assert
        assertFalse(isEquals);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - False- Different Objects")
    void testEqualsDifferentObjects() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

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
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

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
    @DisplayName("Test to HashCode - True")
    void testSameHashCode() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        //Act
        int transactionDTOHashcode = transactionDTO.hashCode();
        int otherTransactionDTOHashcode = otherTransactionDTO.hashCode();

        //Assert
        assertEquals(transactionDTOHashcode, otherTransactionDTOHashcode);
    }

    @Test
    @DisplayName("Test to HashCode - False")
    void testDifferentHashCode() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        TransactionDTO otherTransactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        //Act
        int transactionDTOHashcode = transactionDTO.hashCode();
        int otherTransactionDTOHashcode = otherTransactionDTO.hashCode();

        //Assert
        assertNotEquals(transactionDTOHashcode, otherTransactionDTOHashcode);

    }


    /**
     *
     * Tests to Getters
     *
     */

    @Test
    @DisplayName("Test to getAmount")
    void getAmount() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        Double expectedAmount = 2.0;

        //Act
        Double realAmount = transactionDTO.getAmount();

        //Assert
        assertEquals(expectedAmount,realAmount);

    }

    @Test
    @DisplayName("Test to getDescription")
    void getDescription() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        String expectedDescription = "PAYMENT";

        //Act
        String realDescription = transactionDTO.getDescription();

        //Assert
        assertEquals(expectedDescription,realDescription);
    }

    @Test
    @DisplayName("Test to getDate")
    void getDate() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        String expectedDate = "20-3-2020";

        //Act
        String realDate = transactionDTO.getDate();

        //Assert
        assertEquals(expectedDate,realDate);

    }

    @Test
    void getCategory() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0,Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        String expectedCategory = "ONLINE SHOPPING";

        //Act
        String realCategory = transactionDTO.getCategory();

        //Assert
        assertEquals(expectedCategory,realCategory);

    }

    @Test
    void getAccountFrom() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        String expectedAccountFrom = "REVOLUT";

        //Act
        String realAccountFrom = transactionDTO.getAccountFrom();

        //Assert
        assertEquals(expectedAccountFrom,realAccountFrom);

    }

    @Test
    void getAccountTo() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "DEBIT");

        String expectedAccountTo = "ALIEXPRESS";

        //Act
        String realAccountTo = transactionDTO.getAccountTo();

        //Assert
        assertEquals(expectedAccountTo,realAccountTo);

    }

    @Test
    void getType() {

        //Arrange
        TransactionDTO transactionDTO = new TransactionDTO(2.0, Currency.getInstance("EUR"), "PAYMENT", "20-3-2020",
                "ONLINE SHOPPING", "REVOLUT", "ALIEXPRESS", "CREDIT");

        String expectedType = "CREDIT";

        //Act
        String realType = transactionDTO.getType();

        //Assert
        assertEquals(expectedType,realType);
    }
}