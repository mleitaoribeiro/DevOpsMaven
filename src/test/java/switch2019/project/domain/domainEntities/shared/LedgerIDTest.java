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