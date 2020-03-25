package switch2019.project.domain.domainEntities.account;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    /**
     * Test if two accounts are the same
     */

    @Test
    @DisplayName("Test if two accounts are the same - true")
    public void testIfTwoAccountsAreTheSame() {

        //Arrange
        Account supermarket = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"), new PersonID(new Email("marco@gmail.com")));
        Account supermercado = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"), new PersonID(new Email("marco@gmail.com")));
        //Act
        boolean result = supermarket.equals(supermercado);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - different description")
    public void testIfTwoAccountsAreTheSameNoBeing() {

        //Arrange
        Account supermarket = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"), new PersonID(new Email("marco@gmail.com")));
        Account supermercado = new Account(new Denomination("Supermarket"),
                new Description("Monthly spends"), new PersonID(new Email("marco@gmail.com")));

        //Act
        boolean result = supermarket.equals(supermercado);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - Same Account")
    public void testIfSameObject() {

        //Arrange
        Account oneAccount = new Account(new Denomination("Supermarket"),
                new Description("Weekly spends"),new PersonID(new Email("marco@gmail.com")));

        //Act
        boolean result = oneAccount.equals(oneAccount);

        //Assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Test if two accounts are the same - one Account is Null")
    public void testIfTwoAccountsAreTheSameOneAccountIsNull() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"), new PersonID(new Email("marco@gmail.com")));
        Account otherAccount = null;

        //Act
        boolean result = oneAccount.equals(otherAccount);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two accounts are the same - different objects")
    public void testIfTwoAccountsAreTheSameDifferentObjects() {
        //Arrange

        Account oneAccount = new Account(new Denomination("xpto"),new Description("xpto account"), new PersonID(new Email("marco@gmail.com")));
        Person onePerson = new Person ("Alexandre", new DateAndTime(1994, 02, 10), new Address("Porto"),
                new Address("Rua de Requeixos", "Vizela", "4620-585"), new Email("1234@isep.pt"));

        //Act
        boolean result = oneAccount.equals(onePerson);

        //Assert
        assertFalse(result);
    }

    /**
     * Test if two lists have the same Hashcode
     */

    @Test
    @DisplayName("Test if two accounts have the same Hashcode - True")
    public void testIfTwoAccountsHaveTheSameHashCode() {

        //Arrange & Act
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"), new PersonID(new Email("marta@123.com")));
        Account otherAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"), new PersonID(new Email("marta@123.com")));

        //Assert
        assertEquals(oneAccount.hashCode(), otherAccount.hashCode());
    }

    @Test
    @DisplayName("Test if two accounts have the same Hashcode - Not The Same")
    public void testIfTwoAccountsDontHaveTheSameHashCode() {

        //Arrange & Act
        Person person = new Person("Alex", new DateAndTime(1995, 12, 04), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"),new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 04), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"),new Email("zzzzzz@isep.pt"));
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto account"), person.getID());
        Account otherAccount = new Account(new Denomination("xpto"),
                new Description("xyz account"), person2.getID());

        //Assert
        assertNotEquals(oneAccount.hashCode(), otherAccount.hashCode());
    }


    /**
     * Test Account Constructor
     */

    @Test
    @DisplayName("Test Constructor - Denomination Null")
    void denominationConstructorNull() {

        //Arrange & Act
        try {
            Account oneAccount = new Account(null, new Description("xpto Account"), new PersonID(new Email("marta@123.com")));
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test Constructor - Description Null")
    void descriptionConstructorNull() {

        //Arrange & Act
        try {
            Account oneAccount = new Account(new Denomination("xpto"), null, new PersonID(new Email("marta@123.com")));
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("Account Description can't be null.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test Constructor - Description")
    void descriptionConstructor() {

        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xyz"), new PersonID(new Email("marta@123.com")));
        String expected = "XYZ";

        //Act
        String real = oneAccount.getDescription();

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test toString")
    public void testToString() {

        //Arrange
        Account supermarket = new Account(new Denomination("Supermarket")
                ,new Description("Weekly spends"), new PersonID(new Email("marco@gmail.com")));
        String expected = "WEEKLY SPENDS, 0.0 EUR€, SUPERMARKET, marco@gmail.com";

        //Act
        String result = supermarket.toString();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test get Owner ID - Person ID")
    public void testGetOwnerID() {

        //Arrange
        Account supermarket = new Account(new Denomination("Supermarket")
                ,new Description("Weekly spends"), new PersonID(new Email("martacarda@hotmail.com")));

        PersonID personID = new PersonID(new Email("martacarda@hotmail.com"));
        AccountID accountID = supermarket.getID();

        //Act
        OwnerID accountOwnerID = accountID.getOwnerID();

        //Assert
        assertEquals( personID, accountOwnerID);
    }

    @Test
    @DisplayName("Test get Owner ID - Group ID")
    public void testGetOwnerIDGroup() {

        //Arrange
        Account supermarket = new Account(new Denomination("Supermarket")
                ,new Description("Weekly spends"), new GroupID(new Description("Friends")));

        GroupID groupID = new GroupID(new Description("Friends"));
        AccountID accountID = supermarket.getID();

        //Act
        OwnerID accountOwnerID = accountID.getOwnerID();

        //Assert
        assertEquals( groupID, accountOwnerID);
    }

    /**
     * Test Method get ownerID.
     */

    @Test
    @DisplayName("Test Get owner ID - Group Creator - Success")
    void testGetownerID_GroupCreator() {

        //Arrange:
        Denomination accountDenomination = new Denomination("Revo");
        Description accountDescription = new Description("xpto");
        GroupID groupID = new GroupID(new Description("Policias"));

        Account oneAccount = new Account(accountDenomination, accountDescription, groupID);

        OwnerID expected = groupID;


        //Act:
        OwnerID real = oneAccount.getOwnerID();

        //Assert:
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test Get owner ID - Person Creator - Success")
    void testGetOwnerID_PersonCreator() {

        //Arrange:
        Denomination accountDenomination = new Denomination("Revo");
        Description accountDescription = new Description("xpto");
        PersonID personID = new PersonID(new Email("qwerty.1@gmail.com"));

        Account oneAccount = new Account(accountDenomination, accountDescription, personID);

        OwnerID expected = personID;


        //Act:
        OwnerID real = oneAccount.getOwnerID();

        //Assert:
        assertEquals(expected, real);
    }


}