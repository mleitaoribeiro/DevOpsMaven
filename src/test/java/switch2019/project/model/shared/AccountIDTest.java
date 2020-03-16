package switch2019.project.model.shared;

import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;

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
        String result = accountID.getDenomination();

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
}