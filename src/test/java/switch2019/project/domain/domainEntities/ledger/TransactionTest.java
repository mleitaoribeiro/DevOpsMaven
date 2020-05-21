package switch2019.project.domain.domainEntities.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.dataModel.entities.TransactionJpa;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;


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
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));

        TransactionJpa transactionJpa = new TransactionJpa(1, "Switch", 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(true, result);

    }
    @Test
    @DisplayName("Test if two transactions are the same - true")

    public void testIfDatesAreTheSameWithToString() {
        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13,13,02);

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"),date, category, account1, account2,new Type (false));

        String test = transaction.dateToString();

        //Act
        boolean result = test.equals("2020-01-13 13:02");

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Test if two transactions are the same - true")
    public void testIfTwoTransactionsAreTheSame() {
        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));

        //Act
        boolean result = transaction.equals(transaction);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - different account to")
    public void testIfTwoTransactionsAreEqualsDifferentAccountTo() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        AccountID account1 = new AccountID(new Denomination("mercearia"), person.getID());
        AccountID account2 = new AccountID(new Denomination("transporte"), person.getID());
        AccountID account3 = new AccountID(new Denomination("bowling"), person.getID());
        CategoryID category = new CategoryID(new Denomination("grocery"), person.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account3, new Type (false));

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different account from")
    public void testIfTwoTransactionsAreEqualsFalseDifferentAccountFrom() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        AccountID account1 = new AccountID(new Denomination("mercearia"), person.getID());
        AccountID account2 = new AccountID(new Denomination("transporte"), person.getID());
        AccountID account3 = new AccountID(new Denomination("bowling"), person.getID());

        CategoryID category = new CategoryID(new Denomination("grocery"), person.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), null, category, account3, account2, new Type (false));

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different category")
    public void testIfTwoTransactionsAreEqualsFalseDifferentCategory() {
        //Arrange
        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),person1.getID());
        CategoryID category2 = new CategoryID(new Denomination("transport"),person1.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), null, category2, account1, account2, new Type (false));

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different monetary value")
    public void testIfTwoTransactionsAreEqualsFalseDifferentMonetaryValue() {
        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(30, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue2, new Description("payment"), null, category, account1, account2, new Type (false));

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different description")
    public void testIfTwoTransactionsAreEqualsFalseDifferentDescription() {
        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("transportation"), null, category, account1, account2, new Type (false));

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different date")
    public void testIfTwoTransactionsAreEqualsFalseDifferentDate() {

        //Arrange
        AccountID account1 = new AccountID(new Denomination("Health"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("Transport"),new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);
        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));


        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test if two transactions are the equals  - different types")
    public void testIfTwoTransactionsAreEqualsDifferentTypes() {

        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID category = new CategoryID(new Denomination("House"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);
        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (true));


        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - True - system date")
    public void testIfTwoTransactionsAreEqualsTrueSystemDate() {
        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("House"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime dateNow = new DateAndTime();

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), dateNow, category, account1, account2, new Type (false));


        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - one is null")
    public void testIfTwoTransactionsAreEqualsOneIsNull() {
        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
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
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Person person1 = new Person("António", new DateAndTime(1995, 12, 4), new Address("Porto"),
                new Address("Rua 2", "Porto", "4620-580"), new Email("1234@isep.pt"));


        //Act

        boolean result = transaction.equals(person1);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test if two transactions are the equals - false")
    public void testIfTwoTransactionsAreEqualsFalse() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        AccountID account1 = new AccountID(new Denomination("mercearia"), person.getID());
        AccountID account2 = new AccountID(new Denomination("transporte"), person.getID());
        AccountID account3 = new AccountID(new Denomination("bowling"), person.getID());

        CategoryID category = new CategoryID(new Denomination("grocery"), person.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), null, category, account1, account3, new Type (false));

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    /**
     * Tests for the Transaction method toString
     */
    @Test
    @DisplayName("Test if method toString returns String Transaction")
    public void validateToString() {
        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));

        String expected = "2020-01-13 13:02 | 200.0 EUR DEBIT | MERCEARIA -> TRANSPORTE | Description: \"PAYMENT\"  | GROCERY";

        //Act
        String result = transaction.toString();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test if method toString returns String Transaction for Credit")
    public void validateToStringCredit() {
        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail2@email.pt")));

        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (true));

        String expected = "2020-01-13 13:02 | 200.0 EUR CREDIT | MERCEARIA -> TRANSPORTE | Description: \"PAYMENT\"  | GROCERY";

        //Act
        String result = transaction.toString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test if two transactions have the same hashcode
     */
    @Test
    @DisplayName("Test if two transactions have the same hashcode - true")
    public void testIfTwoTransactionsHaveTheSameHashcode() {
        //Arrange & Act
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));

        //Assert
        assertEquals(transaction.hashCode(), transaction2.hashCode());

    }

    @Test
    @DisplayName("Test if two transactions have the same hashcode - not the same")
    public void testIfTwoTransactionsHaveTheSameHashcodeNo() {
        //Arrange & Act
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        AccountID account1 = new AccountID(new Denomination("mercearia"),person.getID());
        AccountID account2 = new AccountID(new Denomination("transporte"), person.getID());
        AccountID account3 = new AccountID(new Denomination("bowling"), person.getID());
        CategoryID category = new CategoryID(new Denomination("grocery"), person.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, new Description("payment"), date, category, account1, account3, new Type (false));

        //Assert
        assertNotEquals(transaction.hashCode(), transaction2.hashCode());

    }


    /**
     * Tests to validate if a transaction was created
     */
    @Test
    @DisplayName("Test for validating transaction - success case")
    void isAValidTransactionTrue() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        DateAndTime localDateTime = new DateAndTime(2010, 07, 10, 20, 30);
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"),new PersonID(new Email("personEmail@email.pt")));

        //Act
        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), localDateTime, category, account1, account2, new Type (true));

        //Assert
        assertTrue(transaction != null);
    }

    @Test
    @DisplayName("Test for validating transaction - with no automatic date - success case")
    void isAValidTransactionWithNoAutomaticDate() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        DateAndTime localDateTime = new DateAndTime(2010, 07, 10, 20, 30);
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"),
                 new PersonID(new Email("personEmail@email.pt")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        //Act
        Transaction transaction = new Transaction(monetaryValue, new Description("payment"), localDateTime, category, account1, account2, new Type (false));

        //Assert
        assertTrue(transaction != null);
    }

    @Test
    @DisplayName("Test for validating transaction - null monetary value")
    void isAValidTransactionFalseNullMonetaryValue() {
        //Arrange
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        DateAndTime localDateTime = new DateAndTime(2010, 07, 10, 20, 30);
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"),new PersonID(new Email("personEmail@email.pt")));

        //Act
        try {
            new Transaction(null, new Description("payment"), localDateTime, category, account1, account2, new Type (true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value cannot be null.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - negative monetary value")
    void isAValidTransactionFalseNuegativeMonetaryValue() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(-200, Currency.getInstance("EUR"));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        DateAndTime localDateTime = new DateAndTime(2010, 07, 10, 20, 30);
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        try {
            new Transaction(monetaryValue, new Description("payment"), localDateTime, category, account1, account2, new Type (true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value cannot be negative.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null category")
    void isAValidTransactionFalseNullCategory() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime localDateTime = new DateAndTime(2010, 07, 10, 20, 30);
        AccountID account1 = new AccountID(new Denomination("mercearia"),new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        try {
            new Transaction(monetaryValue, new Description("payment"), localDateTime, null, account1, account2, new Type (true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category cannot be null.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null account1")
    void isAValidTransactionFalseNullAccount1() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime localDateTime = new DateAndTime(2010, 07, 10, 20, 30);
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        AccountID account2 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        try {
            new Transaction(monetaryValue, new Description("payment"), localDateTime, category, null, account2, new Type (true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The accounts cannot be null.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null account2")
    void isAValidTransactionFalseNullAccount2() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime localDateTime = new DateAndTime(2010, 07, 10, 20, 30);
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        try {
            new Transaction(monetaryValue, new Description("payment"), localDateTime, category, account1, null, new Type (true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The accounts cannot be null.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null type")
    void isAValidTransactionFalseNullType() {
        //Arrange
        DateAndTime localDateTime = new DateAndTime(2010, 07, 10, 20, 30);
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        //Act
        try {
            new Transaction(monetaryValue, new Description("payment"), localDateTime, category, account1, account2, new Type (false));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The type can´t be null. Please try again.", description.getMessage());
        }
    }

    /**
     * Validate method to get Description
     */
    @Test
    @DisplayName("Test for getDescription")
    void getDescription() {

        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);
        Description description = new Description("Payment");

        Transaction transaction = new Transaction(monetaryValue, description, date, category, account1, account2,
                new Type (false));

        //Act
        Description result = transaction.getDescription();

        //Assert
        assertEquals(description, result);
    }

    /**
     * Validate method categoryToString
     */
    @Test
    @DisplayName("Test for categoryToString")
    void categoryToString() {

        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);
        Description description = new Description("Payment");

        Transaction transaction = new Transaction(monetaryValue, description, date, category, account1, account2,
                new Type (false));
        String expected = "GROCERY, personemail@email.com";

        //Act
        String result = transaction.categoryToString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Validate method amountToString
     */
    @Test
    @DisplayName("Test for amountToString")
    void amountToString() {

        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2020, 1, 13, 13, 02);
        Description description = new Description("Payment");

        Transaction transaction = new Transaction(monetaryValue, description, date, category, account1, account2,
                new Type (false));
        String expected = "200.0 EUR";

        //Act
        String result = transaction.amountToString();

        //Assert
        assertEquals(expected, result);
    }
}