package switch2019.project.domain.domainEntities.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;

import static org.junit.jupiter.api.Assertions.*;

class LedgerIDTest {

    @Test
    @DisplayName("Test if two ledger ID are equals-True case")
    void testEqualsLedgerIDTrue() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        LedgerID ledgerID = new LedgerID(person.getID());

        //Act
        boolean result = ledgerID.equals(ledgerID);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Test if two ledger ID are equals-False case")
    void testEqualsLedgerIDFalse() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        Person person2 = new Person("Ana", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        LedgerID ledgerID = new LedgerID(person.getID());
        LedgerID ledgerID2 = new LedgerID(person2.getID());

        //Act
        boolean result = ledgerID.equals(ledgerID2);

        //Assert
        assertTrue(result);

    }

    @Test
    void testEqualsNull() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        LedgerID ledgerID = new LedgerID(person.getID());
        LedgerID ledgerID2 = null;

        //Act
        boolean result = ledgerID.equals(ledgerID2);

        //Assert
        assertFalse(result);

    }

    @Test
    void testEqualsDifferentClass() {
        //Arrange:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        LedgerID ledgerID = new LedgerID(person.getID());
        PersonID personID = new PersonID(new Email("1234@isep.pt"));

        //Act
        boolean result = ledgerID.equals(personID);

        //Assert
        assertFalse(result);

    }

    @Test
    void testHashCodeTrue() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        LedgerID ledgerID = new LedgerID(person.getID());
        LedgerID ledgerID2 = new LedgerID(person.getID());


        //Act e Assert
        assertEquals(ledgerID.hashCode(), ledgerID2.hashCode());


    }

    @Test
    void testHashCodeFalse() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        Person person2 = new Person("Ana", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        LedgerID ledgerID = new LedgerID(person.getID());
        LedgerID ledgerID2 = new LedgerID(person2.getID());

        //Act e Assert
        assertEquals(ledgerID.hashCode(), ledgerID2.hashCode());
    }

    @Test
    @DisplayName("Get Owner ID from ledgerID - PersonID")
    void getOwnerID() {

        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365")
                , new Email("1234@isep.pt"));
        LedgerID ledgerID = new LedgerID(person.getID());


        //Act
        OwnerID realOwnerID = ledgerID.getOwnerID();
        PersonID expectedOwnerID = person.getID();

        //Assert
        assertEquals(expectedOwnerID, realOwnerID);

    }
}