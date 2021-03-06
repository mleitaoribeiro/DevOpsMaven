package switch2019.project.domain.domainEntities.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;

import static org.junit.jupiter.api.Assertions.*;

class AccountIDTest {

    @Test
    void getDenomination() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());
        String expected = "SWITCH";

        //Act:
        String result = accountID.getDenominationToString();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    void testEqualsSameObject() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());

        //Act:
        boolean result = accountID.equals(accountID);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentObjectTrue() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());
        AccountID accountID2 = new AccountID(new Denomination("switch"), person.getID());

        //Act:
        boolean result = accountID.equals(accountID2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentObjectFalse() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());
        AccountID accountID2 = new AccountID(new Denomination("games"), person.getID());

        //Act:
        boolean result = accountID.equals(accountID2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsNull() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());
        AccountID accountID2 = null;

        //Act:
        boolean result = accountID.equals(accountID2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentClass() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());

        //Act:
        boolean result = accountID.equals(personID);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testHashCodeTrue() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());
        AccountID accountID2 = new AccountID(new Denomination("switch"), person.getID());

        //Act & Assert:
        assertEquals(accountID.hashCode(), accountID2.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());
        AccountID accountID2 = new AccountID(new Denomination("games"), person.getID());

        //Act & Assert:
        assertNotEquals(accountID.hashCode(), accountID2.hashCode());
    }

    @Test
    @DisplayName("Get Owner ID from accountID - PersonID")
    void testIfGetOwnerID() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));
        AccountID accountID = new AccountID(new Denomination("switch"), person.getID());

        OwnerID realOwnerID = accountID.getOwnerID();
        PersonID expectedOwnerID = person.getID();

        //Assert
        assertEquals(expectedOwnerID, realOwnerID);
    }

    @Test
    @DisplayName("Get Owner ID from accountID - GroupID")
    void testIfGetOwnerIDGroup() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group = new Group(new Description("Friends with Benefits"),person.getID());
        AccountID accountID = new AccountID(new Denomination("switch"), group.getID());

        OwnerID realOwnerID = accountID.getOwnerID();
        GroupID expectedOwnerID = group.getID();

        //Assert
        assertEquals(expectedOwnerID, realOwnerID);
    }

    /**
     * Tests to constructor method
     */

    @Test
    @DisplayName("Test to Constructor - Null Denomination")
    void testAccountIDNullDenomination() {
        //Arrange:
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group = new Group(new Description("Running Team"),person.getID());
        //Act:
        try {
            AccountID accountID = new AccountID(null, group.getID());
        }
        //Assert:
        catch (IllegalArgumentException accountID) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", accountID.getMessage());
        }
    }

    @Test
    @DisplayName("Test to Constructor - Null Owner")
    void testAccountIDNullOwner() {
        //Act:
        try {
            AccountID accountID = new AccountID(new Denomination("Trail&Run"), null);
        }
        //Assert:
        catch (IllegalArgumentException accountID) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", accountID.getMessage());
        }
    }

    @Test
    @DisplayName("Test to Constructor - Null Owner & Denomination")
    void testAccountIDNullOwnerAndDenomination() {
        //Act:
        try {
            AccountID accountID = new AccountID(null, null);
        }
        //Assert:
        catch (IllegalArgumentException accountID) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", accountID.getMessage());
        }
    }

    @Test
    @DisplayName("Test getDenomination")
    void testGetDenomination() {
        //Arrange
        PersonID id = new PersonID(new Email("marta@gmail.com"));
        AccountID accountID = new AccountID(new Denomination("switch"), id);
        Denomination expected = new Denomination("switch");

        //Act
        Denomination result = accountID.getDenomination();

        //Assert
        assertEquals(result, expected);
    }
}