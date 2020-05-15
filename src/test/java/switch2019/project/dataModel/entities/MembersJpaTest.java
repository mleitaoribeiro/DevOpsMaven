package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.AccountID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

class MembersJpaTest {

    @Test
    void testEquals() {
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member2 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        boolean result = member1.equals(member2);

        assertTrue(result);
    }

    @Test
    void testNull() {
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member2 = null;

        boolean result = member1.equals(member2);

        assertFalse(result);
    }

    @Test
    void testDifferentObjet() {
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");


        boolean result = member1.equals(groupJpa);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentGroup() {

        GroupJpa groupJpa = new GroupJpa("", "", "");

        GroupJpa groupJpa1 = new GroupJpa(" ", "s", "s");

        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member3 = new MembersJpa(groupJpa1, "1191755@isep.ipp.pt");

        assertNotEquals(member1, member3);

    }

    @Test
    void testEqualsSame() {

        GroupJpa groupJpa = new GroupJpa("", "", "");

        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member3 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        assertEquals(member1, member3);

    }

    @Test
    void testEqualsMembers() {


        MembersJpa member1 = new MembersJpa();

        assertEquals(member1, member1);

    }

    @Test
    void testEqualsById() {

        GroupJpa groupJpa = new GroupJpa("", "", "");

        MembersJpa.MembersIdJpa memmberid = new MembersJpa.MembersIdJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member3 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");


        assertEquals(member3.getId(), memmberid);

    }


    @Test
    void testHashCode() {
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member2 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        assertEquals(member1.hashCode(), member2.hashCode());
    }

    @Test
    void testHasCodeNotEqualsGroup() {

        GroupJpa groupJpa = new GroupJpa("", "", "");

        GroupJpa groupJpa1 = new GroupJpa(" ", "s", "s");

        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member2 = new MembersJpa(groupJpa1, "1191755@isep.ipp.pt");


        assertNotEquals(member1.hashCode(), member2.hashCode());
    }

    @Test
    void testToString() {

        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");
        String expected = "MembersJpa{MembersIdJpa{groupID=switch2019.project.dataModel.entities.GroupJpa@4886206c," +
                " person_ID='1191755@isep.ipp.pt'}}";

        //Act
        String result = member1.toString();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void testToStringNotEquals() {

        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member2 = new MembersJpa(groupJpa, "119175@isep.ipp.pt");

        //Act
        String resultOne = member1.toString();

        String resultTwo = member2.toString();

        //Assert
        assertNotEquals(resultOne, resultTwo);
    }
}
