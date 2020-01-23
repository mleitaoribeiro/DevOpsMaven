package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {


    /**
     * Test to see if two transactions are the equals
     */

    @Test
    @DisplayName("Test if two transactions are the equals - true")

    public void testIfTwoTransactionsAreEqualsTrue() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13,13,02);

        Transaction transaction = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test if two transactions are the same - true")

    public void testIfTwoTransactionsAreTheSame() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13,13,02);

        Transaction transaction = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);



        //Act

        boolean result = transaction.equals(transaction);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - different account to")

    public void testIfTwoTransactionsAreEqualsDifferentAccountTo() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account3 = new Account("bowling", "bowling NorteShopping");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",null, category, account1, account3,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different account from")

    public void testIfTwoTransactionsAreEqualsFalseDifferentAccountFrom() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account3 = new Account("bowling", "bowling NorteShopping");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",null, category, account3, account2,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different category")

    public void testIfTwoTransactionsAreEqualsFalseDifferentCategory() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        Category category2 = new Category("transport");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",null, category2, account1, account2,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different monetary value")

    public void testIfTwoTransactionsAreEqualsFalseDifferentMonetaryValue() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(30, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue2, "payment",null, category, account1, account2,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different description")

    public void testIfTwoTransactionsAreEqualsFalseDifferentDescription() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "transportation",null, category, account1, account2,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }


    @Test
    @DisplayName("Test if two transactions are the equals - false - different date")
    public void testIfTwoTransactionsAreEqualsFalseDifferentDate() {

        //Arrange
        Account account1 = new Account("Health", "Health Maria");
        Account account2 = new Account("Transport", "Metro");
        Category category = new Category("House");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13,13,02);
        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);


        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test if two transactions are the equals  - different types")
    public void testIfTwoTransactionsAreEqualsDifferentTypes() {

        //Arrange
        Account account1 = new Account("Health", "Health Maria");
        Account account2 = new Account("Transport", "Metro");
        Category category = new Category("House");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13,13,02);
        Transaction transaction = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",date, category, account1, account2,true);


        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - True - system date")
    public void testIfTwoTransactionsAreEqualsTrueSystemDate() {
        //Arrange
        Account account1 = new Account("Health", "Health Maria");
        Account account2 = new Account("Transport", "Metro");
        Category category = new Category("House");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateNow.format(formatter), formatter);
        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - one is null")

    public void testIfTwoTransactionsAreEqualsOneIsNull() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");

        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = null;


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);
        }

    @Test
    @DisplayName("Test if two transactions are the equals - different objects")

    public void testIfTwoTransactionsAreEqualsDifferentObjects() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");

        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Person person1 = new Person("António",1987,6,22,new Address("Rua 2", "Porto", "4620-580"),new Address("Porto"));


        //Act

        boolean result = transaction.equals(person1);

        //Assert
        assertEquals(false, result);
    }

    /**
     * Test if two transactions have the same hashcode
     */

    @Test
    @DisplayName("Test if two transactions have the same hashcode - true")

    public void testIfTwoTransactionsHaveTheSameHashcode() {
        //Arrange & Act
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13,13,02);

        Transaction transaction = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);


        //Assert
        assertEquals(transaction.hashCode(),transaction2.hashCode());

    }

    @Test
    @DisplayName("Test if two transactions have the same hashcode - not the same")

    public void testIfTwoTransactionsHaveTheSameHashcodeNo() {
        //Arrange & Act
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account3 = new Account("bowling", "bowling NorteShopping");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13,13,02);

        Transaction transaction = new Transaction(monetaryValue, "payment",date, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",date, category, account1, account3,false);


        //Assert
        assertNotEquals(transaction.hashCode(),transaction2.hashCode());

    }



    /**
     * Tests to validate if a transaction was created
     */

    @Test
    @DisplayName("Test if two transactions are the equals - false")

    public void testIfTwoTransactionsAreEqualsFalse() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account3 = new Account("bowling", "bowling NorteShopping");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",null, category, account1, account3,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    /**
     * Tests to validate if a transaction was created
     */

    @Test
    @DisplayName("Test for validating transaction - sucess case")
    void isAValidTransactionTrue() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Category category = new Category("grocery");
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30);
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        boolean type1 = true;

        //Act
        Transaction transaction = new Transaction(monetaryValue, "payment", localDateTime, category, account1, account2, type1);

        //Assert
        assertTrue(transaction != null);
    }

    @Test
    @DisplayName("Test for validating transaction - with no automatic date - success case")
    void isAValidTransactionWithNoAutomaticDate() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Category category = new Category("grocery");
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        boolean type1 = true;

        //Act
        Transaction transaction = new Transaction(monetaryValue, "payment", localDateTime, category, account1, account2, type1);

        //Assert
        assertTrue(transaction != null);
    }

    @Test
    @DisplayName("Test for validating transaction - null monetary value")
    void isAValidTransactionFalseNullMonetaryValue() {
        //Arrange
        Category category = new Category("grocery");
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        boolean type = true;

        //Act
        try {
            new Transaction(null, "payment", localDateTime, category, account1, account2, type);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null category")
    void isAValidTransactionFalseNullCategory() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        boolean type = true;

        //Act
        try {
            new Transaction(monetaryValue, "payment", localDateTime, null, account1, account2, type);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null account")
    void isAValidTransactionFalseNullAccount() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Category category = new Category("grocery");
        Account account1 = new Account("mercearia", "mercearia Continente");
        boolean type = true;

        //Act
        try {
            new Transaction(monetaryValue, "payment", localDateTime, category, account1, null, type);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The accounts can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null type")
    void isAValidTransactionFalseNullType() {
        //Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        //Act
        try {
            new Transaction(monetaryValue, "payment", localDateTime, category, account1, account2,false);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The type can´t be null. Please try again.", description.getMessage());
        }
    }

}