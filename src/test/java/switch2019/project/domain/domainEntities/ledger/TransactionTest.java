package switch2019.project.domain.domainEntities.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;

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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(true, result);

    }
    @Test
    @DisplayName("Test if two transactions are the same - true")

    public void testIfDatesAreTheSameWithToString() {
        //Arrange
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13,13,02);

        Transaction transaction = new Transaction(monetaryValue, "payment",date, category, account1, account2,new Type (false));


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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));


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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), person.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), person.getID());
        Account account3 = new Account(new Denomination("bowling"),
                new Description("bowling NorteShopping"), person.getID());
        Category category = new Category(new Denomination("grocery"), person.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", null, category, account1, account3, new Type (false));

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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), person.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), person.getID());
        Account account3 = new Account(new Denomination("bowling"),
                new Description("bowling NorteShopping"), person.getID());

        Category category = new Category(new Denomination("grocery"), person.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", null, category, account3, account2, new Type (false));


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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),person1.getID());
        Category category2 = new Category(new Denomination("transport"),person1.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", null, category2, account1, account2, new Type (false));


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different monetary value")
    public void testIfTwoTransactionsAreEqualsFalseDifferentMonetaryValue() {
        //Arrange
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(30, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue2, "payment", null, category, account1, account2, new Type (false));


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different description")
    public void testIfTwoTransactionsAreEqualsFalseDifferentDescription() {
        //Arrange
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "transportation", null, category, account1, account2, new Type (false));


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false - different date")
    public void testIfTwoTransactionsAreEqualsFalseDifferentDate() {

        //Arrange
        Account account1 = new Account(new Denomination("Health"),
                new Description("Health Maria"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("Transport"),
                new Description("Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);
        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));


        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test if two transactions are the equals  - different types")
    public void testIfTwoTransactionsAreEqualsDifferentTypes() {

        //Arrange
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));

        Category category = new Category(new Denomination("House"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);
        Transaction transaction = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (true));


        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test if two transactions are the equals - True - system date")
    public void testIfTwoTransactionsAreEqualsTrueSystemDate() {
        //Arrange
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("House"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateNow.format(formatter), formatter);
        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));

        //Act
        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - one is null")
    public void testIfTwoTransactionsAreEqualsOneIsNull() {
        //Arrange
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));

        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));

        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), person.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), person.getID());
        Account account3 = new Account(new Denomination("bowling"),
                new Description("bowling NorteShopping"), person.getID());

        Category category = new Category(new Denomination("grocery"), person.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", null, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", null, category, account1, account3, new Type (false));

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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));

        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));

        String expected = "2020-01-13 13:02 | 200.0 EUR DEBIT | MERCEARIA -> TRANSPORTE | Description: \"payment\"  | GROCERY";

        //Act
        String result = transaction.toString();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test if method toString returns String Transaction for Credit")
    public void validateToStringCredit() {
        //Arrange
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail2@email.pt")));

        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (true));

        String expected = "2020-01-13 13:02 | 200.0 EUR CREDIT | MERCEARIA -> TRANSPORTE | Description: \"payment\"  | GROCERY";

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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));

        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));


        //Assert
        assertEquals(transaction.hashCode(), transaction2.hashCode());

    }

    @Test
    @DisplayName("Test if two transactions have the same hashcode - not the same")
    public void testIfTwoTransactionsHaveTheSameHashcodeNo() {
        //Arrange & Act
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), person.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), person.getID());
        Account account3 = new Account(new Denomination("bowling"),
                new Description("bowling NorteShopping"), person.getID());
        Category category = new Category(new Denomination("grocery"), person.getID());
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);

        Transaction transaction = new Transaction(monetaryValue, "payment", date, category, account1, account2, new Type (false));
        Transaction transaction2 = new Transaction(monetaryValue, "payment", date, category, account1, account3, new Type (false));


        //Assert
        assertNotEquals(transaction.hashCode(), transaction2.hashCode());

    }


    /**
     * Tests to validate if a transaction was created
     */
    @Test
    @DisplayName("Test for validating transaction - sucess case")
    void isAValidTransactionTrue() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30);
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        Transaction transaction = new Transaction(monetaryValue, "payment", localDateTime, category, account1, account2, new Type (true));

        //Assert
        assertTrue(transaction != null);
    }

    @Test
    @DisplayName("Test for validating transaction - with no automatic date - success case")
    void isAValidTransactionWithNoAutomaticDate() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



        //Act
        Transaction transaction = new Transaction(monetaryValue, "payment", localDateTime, category, account1, account2, new Type (false));

        //Assert
        assertTrue(transaction != null);
    }

    @Test
    @DisplayName("Test for validating transaction - null monetary value")
    void isAValidTransactionFalseNullMonetaryValue() {
        //Arrange
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        try {
            new Transaction(null, "payment", localDateTime, category, account1, account2, new Type (true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value can´t be null or negative. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null category")
    void isAValidTransactionFalseNullCategory() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        try {
            new Transaction(monetaryValue, "payment", localDateTime, null, account1, account2, new Type (true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null account1")
    void isAValidTransactionFalseNullAccount1() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        Account account2 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        try {
            new Transaction(monetaryValue, "payment", localDateTime, category, null, account2, new Type (true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The accounts can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null account2")
    void isAValidTransactionFalseNullAccount2() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime localDateTime = LocalDateTime.of(2010, Month.JULY, 10, 20, 30, 40);
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        try {
            new Transaction(monetaryValue, "payment", localDateTime, category, account1, null, new Type (true));
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
        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), new PersonID(new Email("personEmail@email.pt")));
        Category category = new Category(new Denomination("grocery"),new PersonID(new Email("personEmail@email.com")));
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        //Act
        try {
            new Transaction(monetaryValue, "payment", localDateTime, category, account1, account2, new Type (false));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The type can´t be null. Please try again.", description.getMessage());
        }
    }

}