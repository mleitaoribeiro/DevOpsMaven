package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

class GroupJpaTest {

    @Test
    void testEquals() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        GroupJpa groupJpa2 = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        //Act
        boolean result = groupJpa.equals(groupJpa2);
        boolean result1 = groupJpa.equals(groupJpa);

        //Assert
        assertTrue(result && result1);
    }

    @Test
    void testEqualsNull() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        GroupJpa groupJpa2 = null;

        //Act
        boolean result = groupJpa.equals(groupJpa2);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObjects() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        Account oneAccount = new Account(new Denomination("xpto"),new Description("xpto account"), new PersonID(new Email("marco@gmail.com")));

                //Act
        boolean result = groupJpa.equals(oneAccount);

        //Assert
        assertFalse(result);
    }

    @Test
    void testHashCode() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        GroupJpa groupJpa1 = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        GroupJpa groupJpa2 = new GroupJpa("!Switch", "mail@msil.com", "2002-09-10");
        //Assert
        assertEquals(groupJpa.hashCode(), groupJpa1.hashCode());
        assertNotEquals(groupJpa.hashCode(), groupJpa2.hashCode());
    }
}