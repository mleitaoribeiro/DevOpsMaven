package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.Test;

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
    void testEMpty() {
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member2 = new MembersJpa();

        boolean result = member1.equals(member2);

        assertFalse(result);
    }
    @Test
    void testDifferentGroup() {
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        GroupJpa groupJpa2 = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2003-09-10");

        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member2 = new MembersJpa(groupJpa2, "1191755@isep.ipp.pt");


        boolean result = member1.equals(member2);

        assertFalse(result);
    }

    @Test
    void testSameAsItSelf() {
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");


        boolean result = member1.equals(member1);

        assertTrue(result);
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
    void testHashCodeNotEquals() {
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        GroupJpa groupJpa1 = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2003-09-10");
        MembersJpa member1 = new MembersJpa(groupJpa, "1191755@isep.ipp.pt");

        MembersJpa member2 = new MembersJpa(groupJpa1, "1191755@isep.ipp.pt");

        assertNotEquals(member1.hashCode(), member2.hashCode());
    }

    @Test
    void toToString(){
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        MembersJpa.MembersIdJpa member1 = new MembersJpa.MembersIdJpa(groupJpa, "Maria");
        MembersJpa.MembersIdJpa member2 = new MembersJpa.MembersIdJpa(groupJpa, member1.getPersonID());

        //Act
        String result = member1.toString();
        String result2= member2.toString();

        //Assert
        assertEquals(result,result2);
    }

    @Test
    void testToStringDifferent() {

        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");
        MembersJpa member1 = new MembersJpa(groupJpa, "Maria");
        MembersJpa member2 = new MembersJpa(groupJpa, "Joana");

        //Act
        String result = member1.toString();
        String result2= member2.toString();

        //Assert
        assertNotEquals(result,result2);
    }

    @Test
    void testGetID() {
        GroupJpa groupJpa = new GroupJpa("","", "");

        MembersJpa.MembersIdJpa member1 = new MembersJpa.MembersIdJpa(groupJpa, "Maria");
        MembersJpa.MembersIdJpa member2 = new MembersJpa.MembersIdJpa(member1.getGroupID(),member1.getPersonID());


        String expected = member1.toString();
        String result = member2.toString();

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

    @Test
    void testGetIDDifferent() {
        GroupJpa groupJpa = new GroupJpa("","", "");

        MembersJpa.MembersIdJpa member1 = new MembersJpa.MembersIdJpa(groupJpa, "Maria");
        MembersJpa.MembersIdJpa member2 = new MembersJpa.MembersIdJpa(member1.getGroupID(),
                "Joana");


        String result = member1.toString();
        String result2 = member2.toString();

        assertNotEquals(result, result2);
    }

    @Test
    void testMembersJpaId() {
        GroupJpa groupJpa = new GroupJpa("","", "");

        MembersJpa.MembersIdJpa member1 = new MembersJpa.MembersIdJpa(groupJpa, "Maria");


        assertEquals(member1,member1);
    }

    @Test
    void testMembersJpaIdCompare() {
        GroupJpa groupJpa = new GroupJpa("","", "");

        MembersJpa.MembersIdJpa member1 = new MembersJpa.MembersIdJpa(groupJpa, "Maria");


        assertNotEquals(member1,groupJpa);
    }


}
