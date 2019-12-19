package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {



    /**
     * Test if member was added to Group
     */

    @Test
    @DisplayName("Validate if a member was added to a group")
    void addMember() {

        //Arrange
        Person marta = new Person("Marta", 2000, 10, 10);
        Group A = new Group("OsMaisFixes", 2019, 10, 10);

        //Act
        A.addMember(marta);

        //Assert
        assertTrue(A.members.contains(marta));
    }


    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMember_null() {

        //Arrange
        Person marta = null;
        Group A = new Group("OsMaisFixes", 2019, 10, 10);

        //Act
        A.addMember(marta);

        //Assert
        assertFalse(A.members.contains(marta));
    }

    /**
     * Test if multiple members were added to Group
     */

    @Test
    @DisplayName("Test if all members were added to Group => Sucess Case")
    void addMultipleMembers_Sucess() {
        //Arrange
        Group newGroup = new Group("M'Nation", 2019,12, 18 );

        Person person1 = new Person("Maria", 1994, 05, 01);
        Person person2 = new Person("Mariana",1986,12,01);
        Person person3 = new Person("Marisa",2000,8,27);

        HashSet<Person> finalGroup= new HashSet<>(Arrays.asList(person1,person3,person2));

        //Act
        newGroup.addMultipleMembers(finalGroup);

        //Assert
        assertTrue(newGroup.getMembers().containsAll(finalGroup));
    }

    @Test
    @DisplayName("Test if the same person is not added twice")
    void addMultipleMembers__ErrorCase() {
        //Arrange
        Group newGroup = new Group("Maria's Group", 2019,12, 18 );

        Person person1 = new Person("Maria", 1994, 05, 01);
        Person person2 = new Person("Maria", 1994, 05, 01);

        HashSet<Person> finalGroup= new HashSet<>(Arrays.asList(person1,person2));

        //Act
        newGroup.addMultipleMembers(finalGroup);

        //Assert
        assertFalse(newGroup.getMembers().size() ==2);
        assertTrue(newGroup.getMembers().size() ==1);
    }

    @Test
    @DisplayName("Test if a null case is added to group")
    void addMultipleMembers__ErrorCase2() {
        //Arrange
        Group newGroup = new Group("Grupo das M'Nation", 2019,12, 18 );

        Person person1 = new Person("Maria", 1994, 05, 01);
        Person person2 = null;

        HashSet<Person> finalGroup= new HashSet<>(Arrays.asList(person1,person2));

        //Act
        newGroup.addMultipleMembers(finalGroup);

        //Assert
        assertFalse(newGroup.getMembers().contains(person2));
    }

    /**
     * Test if member was removed from Group
     */

    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroup() {
        //Arrange

        //New Group
        String description = "123";
        //Starting date
        int year = 2005;
        int month = 4;
        int day = 15;

        //One Member
        String oneMemberName = "João";
        //One Member BirthDate
        int oneMemberYear = 1993;
        int oneMemberMonth = 9;
        int oneMemberDay = 1;

        //Other Member
        String otherMemberName = "Elsa";
        //Other Member BirthDate
        int otherMemberYear = 1992;
        int otherMemberMonth = 10;
        int otherMemberDay = 9;

        Group oneGroup = new Group(description, year, month, day);

        Person oneMember = new Person(oneMemberName, oneMemberYear, oneMemberMonth, oneMemberDay);
        Person otherMember = new Person(otherMemberName, otherMemberYear, otherMemberMonth, otherMemberDay);

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(oneMember, otherMember));

        //Act
        oneGroup.addMultipleMembers(putMembers);

        oneGroup.removeMember(oneMember);

        //Assert
        assertFalse(oneGroup.getMembers().contains(oneMember));
    }

    @Test
    @DisplayName("Test if a member was removed from a Group - Remove all members")
    void removeMemberFromGroup_1() {
        //Arrange

        //New Group
        String description = "123";
        //Starting date
        int year = 2005;
        int month = 4;
        int day = 15;

        //One Member
        String oneMemberName = "João";
        //One Member BirthDate
        int oneMemberYear = 1993;
        int oneMemberMonth = 9;
        int oneMemberDay = 1;

        //Other Member
        String otherMemberName = "Elsa";
        //Other Member BirthDate
        int otherMemberYear = 1992;
        int otherMemberMonth = 10;
        int otherMemberDay = 9;

        Group oneGroup = new Group(description, year, month, day);

        Person oneMember = new Person(oneMemberName, oneMemberYear, oneMemberMonth, oneMemberDay);
        Person otherMember = new Person(otherMemberName, otherMemberYear, otherMemberMonth, otherMemberDay);

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(oneMember, otherMember));

        //Act
        oneGroup.addMultipleMembers(putMembers);

        oneGroup.removeMember(oneMember);
        oneGroup.removeMember(otherMember);

        //Assert
        assertEquals(0, oneGroup.getMembers().size());
    }

    /**
     * Test if multiple members were removed from a Group
     */

    @Test
    @DisplayName("Test if multiple members were removed from a Group - remove all ")
    void removeMultipleMembersFromAGroup(){
        //Arrange
        Group g1=new Group("G1",2005,2,12);
        Person p1=new Person("Pedro",1999,12,9);
        Person p2=new Person("Gabriel",1996,3,6);
        HashSet<Person>putMembers=new HashSet<>(Arrays.asList(p1,p2));

        //Act
        g1.addMultipleMembers(putMembers);
        g1.removeMultipleMembers(putMembers);

        //Assert
        assertTrue(g1.getMembers().size()==0);
    }

    @Test
    @DisplayName("Test if multiple members were removed from a Group - only the members I choose ")
    void removeMultipleMembers() {
        //Arrange
        Group g1 = new Group("G1", 2005, 2, 12);
        Person p1 = new Person("Pedro", 1999, 12, 9);
        Person p2 = new Person("Gabriel", 1996, 3, 6);
        Person p3 = new Person("Laurinda", 1998, 3, 14);
        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(p1, p2, p3));
        HashSet<Person> removeSome = new HashSet<>(Arrays.asList(p2, p3));

        //Act
        g1.addMultipleMembers(putMembers);
        g1.removeMultipleMembers(removeSome);

        //Assert
        assertTrue(g1.getMembers().size() == 1);
    }

    /**
     * Check if member was promoted to Admin
     */


    /**
     * Check if member was demoted from Admin
     */


    /**
     * Test if a Group is a family
     */

    @Test
    @DisplayName("Validate if a group is a family - All Family")
    void ifGroupIsFamily_AllFamily() {

        //Arrange
        Person oscar = new Person("Oscar", 1990, 10, 10);
        Person marta = new Person("Marta", 1990, 10, 10);
        Person joao = new Person("Joao", 1990, 10, 10);
        Person manuela = new Person("Manuela", 1990, 10, 10);
        Person carlos = new Person("Carlos", 1990, 10, 10);
        HashSet<Person> famList = new HashSet<>(Arrays.asList(oscar, marta, joao, manuela, carlos));

        oscar.setMother(manuela);
        oscar.setFather(carlos);
        marta.setMother(manuela);
        marta.setFather(carlos);
        joao.setMother(manuela);
        joao.setFather(carlos);

        Group family = new Group("Family", 2019, 10, 10);

        // Act
        family.addMultipleMembers(famList);

        // Assert
        assertTrue(family.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - All except one")
    void ifGroupIsFamily_AllFamilyExceptOne() {

        //Arrange
        Person oscar = new Person("Oscar", 1990, 10, 10);
        Person marta = new Person("Marta", 1990, 10, 10);
        Person joao = new Person("Joao", 1990, 10, 10);
        Person manuela = new Person("Manuela", 1990, 10, 10);
        Person carlos = new Person("Carlos", 1990, 10, 10);
        Person random = new Person("Diana", 1990, 10, 10);
        HashSet<Person> famList = new HashSet<>(Arrays.asList(oscar, marta, joao, manuela, carlos, random));

        oscar.setMother(manuela);
        oscar.setFather(carlos);
        marta.setMother(manuela);
        marta.setFather(carlos);
        joao.setMother(manuela);
        joao.setFather(carlos);

        Group family = new Group("Family", 2019, 10, 10);

        // Act
        family.addMultipleMembers(famList);

        // Assert
        assertFalse(family.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No one is family")
    void ifGroupIsFamily_NoneFamily() {

        //Arrange
        Person oscar = new Person("Oscar", 1990, 10, 10);
        Person marta = new Person("Marta", 1990, 10, 10);
        Person joao = new Person("Joao", 1990, 10, 10);
        Person manuela = new Person("Manuela", 1990, 10, 10);
        Person carlos = new Person("Carlos", 1990, 10, 10);
        HashSet<Person> famList = new HashSet<>(Arrays.asList(oscar, marta, joao, manuela, carlos));

        Group family = new Group("Family", 2019, 10, 10);

        // Act
        family.addMultipleMembers(famList);

        // Assert
        assertFalse(family.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No Mother")
    void ifGroupIsFamily_FamilyButNoMother() {

        //Arrange
        Person oscar = new Person("Oscar", 1990, 10, 10);
        Person marta = new Person("Marta", 1990, 10, 10);
        Person joao = new Person("Joao", 1990, 10, 10);
        Person manuela = new Person("Manuela", 1990, 10, 10);
        Person carlos = new Person("Carlos", 1990, 10, 10);
        HashSet<Person> famList = new HashSet<>(Arrays.asList(oscar, marta, joao, manuela, carlos));

        oscar.setFather(carlos);
        marta.setFather(carlos);
        joao.setFather(carlos);

        Group family = new Group("Family", 2019, 10, 10);

        // Act
        family.addMultipleMembers(famList);

        // Assert
        assertFalse(family.isFamily());
    }

    /**
     * Test Equals method for the Group class
     */
    @Test
    @DisplayName( "Two group are the same")
    void equalsGroupClass_JustGrouptrue() {

        Group g1=new Group("Familia",1987,01,16);
        Group g2= new Group("Familia",1987,01,16);

        //Act
        boolean result= g1.equals(g2);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_addPersonTrue() {
        //Arrange
        Person p1=new Person("Elsa",2000,02,24);
        Person p2=new Person("Filipa",1990,01,05);
        Group g1=new Group("Familia",1987,01,16);
        Group g2= new Group("Familia",1987,01,16);
        HashSet<Person>members= new HashSet<>(Arrays.asList(p2,p1));

        //Act
        g1.addMultipleMembers(members);
        g2.addMultipleMembers(members);
        boolean result= g1.equals(g2);

        //Assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_GroupFalse() {
        //Arrange
        Person p1=new Person("Elsa",2000,02,24);
        Person p2=new Person("Filipa",1990,01,05);
        Group g1=new Group("Familia",1987,01,16);
        Group g2= new Group("Familia",1985,01,16);
        HashSet<Person>members= new HashSet<>(Arrays.asList(p2,p1));

        //Act
        g1.addMultipleMembers(members);
        g2.addMultipleMembers(members);
        boolean result= g1.equals(g2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_addPersonFalse() {
        //Arrange
        Person p1=new Person("Elsa",2000,02,24);
        Person p2=new Person("Filipa",1990,01,05);
        Person p3=new Person("Pedro",1990,01,05);
        Group g1=new Group("Familia",1987,01,16);
        Group g2= new Group("Familia",1985,01,16);
        HashSet<Person>members= new HashSet<>(Arrays.asList(p2,p1));
        HashSet<Person>members2= new HashSet<>(Arrays.asList(p2,p3));
        //Act
        g1.addMultipleMembers(members);
        g2.addMultipleMembers(members2);
        boolean result= g1.equals(g2);

        //Assert
        assertFalse(result);
    }
}

