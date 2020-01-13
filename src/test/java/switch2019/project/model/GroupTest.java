package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    /**
     * Test if a user was added as first member and group admin to a Group and the second as member
     */
    @Test
    @DisplayName("Validate if a member was added to a group")
    void addMember() {

        //Arrange
        Person person1 = new Person("Marta", 2000, 10, 10, new Address("Porto"));
        Person person2 = new Person("Mariana", 1986, 12, 01, new Address("Lisboa"));

        HashSet<Person> setOfMembers = new HashSet<>(Arrays.asList(person1, person2));

        Group group1 = new Group("OsMaisFixes");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);

        //Assert
        assertTrue(group1.getMembers().containsAll(setOfMembers) && group1.getAdmins().contains(person1));
    }

    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMemberNull() {

        //Arrange
        Person person1 = null;
        Group group1 = new Group("OsMaisFixes");

        //Act
        group1.addMember(person1);

        //Assert
        assertFalse(group1.getMembers().contains(person1));
    }

    /**
     * Test if a member added to the group is automatically promoted to admin if the group is empty
     */
    @Test
    @DisplayName("True - member added to an empty group")
    void promoteAddedMemberIfEmptyTrue() {
        //Arrange
        Person person1 = new Person("Juan", 1970, 1, 2, new Address("Toledo"));
        Group group1 = new Group("Group with no members");

        //Act
        group1.addMember(person1);

        //Assert
        assertTrue(group1.getMembers().contains(person1) && group1.getAdmins().contains(person1));
    }

    @Test
    @DisplayName("False - member added to a non empty group")
    void promoteAddedMemberIfEmptyTestFalse() {
        //Arrange
        Person person1 = new Person("Juan", 1970, 1, 2, new Address("Toledo"));
        Person person2 = new Person("Pablo", 1978, 5, 16, new Address("Madrid"));
        Group group1 = new Group("Group with no members");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);

        //Assert
        assertTrue(group1.getMembers().contains(person2) && !group1.getAdmins().contains(person2));
    }

    /**
     * Test if multiple members were added to Group
     */
    @Test
    @DisplayName("Test if all members were added to Group => Success Case")
    void addMultipleMembersSuccess() {
        //Arrange
        Group group1 = new Group("MNation");

        Person person1 = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person person2 = new Person("Mariana", 1986, 12, 01, new Address("Lisboa"));
        Person person3 = new Person("Marisa", 2000, 8, 27, new Address("Leiria"));

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person2, person3));

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        group1.addMember(person3);


        //Assert
        assertTrue(group1.getMembers().containsAll(setOfPeopleToAddToGroup) && group1.getAdmins().size() == 1);
    }

    @Test
    @DisplayName("Test if the same person is not added twice")
    void addMultipleMembersSamePersonNotTwice() {
        //Arrange
        Group group1 = new Group("Maria's Group");

        Person person2 = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person person3 = new Person("Maria", 1994, 05, 01, new Address("Porto"));

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person2, person3));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(group1.getMembers().size() == 1);
    }

    @Test
    @DisplayName("Test if a null case is added to group")
    void addMultipleMembersNullCase() {
        //Arrange
        Group group1 = new Group("Grupo das M'Nation");

        Person person1 = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person person2 = null;

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person1, person2));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Assert
        assertFalse(group1.getMembers().contains(person2));
    }

    /**
     * Test if member was removed from Group
     */
    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroup() {
        //Arrange

        //New Group
        String description = "Grupo a ser submetido aos testes";

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

        Group group1 = new Group(description);

        Person person1 = new Person(oneMemberName, oneMemberYear, oneMemberMonth, oneMemberDay, new Address("Paranhos"));
        Person person2 = new Person(otherMemberName, otherMemberYear, otherMemberMonth, otherMemberDay, new Address("Porto"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));

        //Act
        group1.addMultipleMembers(putMembers);

        group1.removeMember(person2);

        //Assert
        assertFalse(group1.getMembers().contains(person2));
    }

    @Test
    @DisplayName("Test if a member was removed from a Group - try to remove all members")
    void removeMemberFromGroupAllMembers() {
        //Arrange

        //New Group
        String description = "123 são os primeiros três números inteiros";

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

        Group group1 = new Group(description);

        Person person1 = new Person(oneMemberName, oneMemberYear, oneMemberMonth, oneMemberDay, new Address("Lisboa"));
        Person person2 = new Person(otherMemberName, otherMemberYear, otherMemberMonth, otherMemberDay, new Address("Porto"));

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person1, person2));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);

        group1.removeMember(person1);
        group1.removeMember(person2);

        //Assert
        assertEquals(1, group1.getMembers().size());
    }

    /**
     * Test if multiple members were removed from a Group and there is at least one group admin in the group
     */
    @Test
    @DisplayName("Test if multiple members were removed from a Group - not removed 1 group admin and 1 member ")
    void removeMultipleMembersFromAGroupNotRemovingOneGroupAdmin() {
        //Arrange
        Group group1 = new Group("grupo dos amiguinhos");
        Person person1 = new Person("Pedro", 1999, 12, 9, new Address("Porto"));
        Person person2 = new Person("Gabriel", 1996, 3, 6, new Address("Porto"));
        Person person3 = new Person("Laurinda", 1998, 3, 14, new Address("Porto"));
        Person person4 = new Person("Oscar", 1990, 10, 10, new Address("Porto"));

        //Act
        group1.addMember(person1);
        group1.setAdmin(person2);

        HashSet<Person> setOfMembers = new HashSet<>(Arrays.asList(person3, person4));
        HashSet<Person> setOfMembersToRemove = new HashSet<>(Arrays.asList(person1, person4));

        group1.addMultipleMembers(setOfMembers);
        group1.removeMultipleMembers(setOfMembersToRemove);

        //Assert
        assertTrue(group1.getMembers().size() == 2);
        assertFalse(group1.getAdmins().contains(person1));
    }

    // Perguntar ao professor depois o que deve remover, todos menos 1 ou não deve remover nenhum
    @Test
    @DisplayName("Test if multiple members were removed from a Group - tried to remove all the group admins")
    void removeMultipleMembersFromAGroupAllAdmins() {
        //Arrange
        Group group1 = new Group("grupo dos amiguinhos");
        Person person1 = new Person("Pedro", 1999, 12, 9, new Address("Porto"));
        Person person2 = new Person("Gabriel", 1996, 3, 6, new Address("Porto"));
        Person person3 = new Person("Laurinda", 1998, 3, 14, new Address("Porto"));
        Person person4 = new Person("Oscar", 1990, 10, 10, new Address("Porto"));

        //Act
        group1.addMember(person1);
        group1.setAdmin(person2);

        HashSet<Person> setOfMembers = new HashSet<>(Arrays.asList(person3, person4));
        HashSet<Person> setOfMembersToRemove = new HashSet<>(Arrays.asList(person1, person2, person4));

        group1.addMultipleMembers(setOfMembers);
        group1.removeMultipleMembers(setOfMembersToRemove);

        //Assert
        assertTrue(group1.getMembers().size() == 2);
        assertTrue(group1.getAdmins().size() == 1);
    }

    @Test
    @DisplayName("Test if multiple members were removed from a Group - only the members I choose ")
    void removeMultipleMembers() {
        //Arrange
        Group group1 = new Group("Grupo ainda mais fixe que o outro");
        Person person1 = new Person("Pedro", 1999, 12, 9, new Address("Porto"));
        Person person2 = new Person("Gabriel", 1996, 3, 6, new Address("Porto"));
        Person person3 = new Person("Laurinda", 1998, 3, 14, new Address("Porto"));
        Person person4 = new Person("Oscar", 1990, 10, 10, new Address("Porto"));

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person2, person3, person4));
        HashSet<Person> setOfPeopleToRemoveFromGroup = new HashSet<>(Arrays.asList(person2, person3));

        //Act
        group1.addMember(person1);
        group1.addMultipleMembers(setOfPeopleToAddToGroup);
        group1.removeMultipleMembers(setOfPeopleToRemoveFromGroup);

        //Assert
        assertTrue(group1.getMembers().size() == 2);
    }

    /**
     * Test if a removed member is also removed from admin
     */
    @Test
    @DisplayName("multiple members")
    void isRemovedMemberAlsoRemovedFromAdmin() {
        //Arrange:
        Group group1 = new Group("Grupo ainda mais fixe que o outro");
        Person person1 = new Person("Pedro", 1999, 12, 9, new Address("Porto"));
        Person person2 = new Person("Gabriel", 1996, 3, 6, new Address("Porto"));
        Person person3 = new Person("Laurinda", 1998, 3, 14, new Address("Porto"));

        //Act:
        group1.addMember(person1);
        group1.addMember(person2);
        group1.promoteMemberToAdmin(person2);
        group1.addMember(person3);
        group1.removeMember(person1);

        //Assert:
        assertTrue(!group1.getAdmins().contains(person1) && group1.getAdmins().size() == 1);
    }


    /**
     * Test if a Group is a family
     */
    @Test
    @DisplayName("Validate if a group is a family - All Family")
    void ifGroupIsFamilyAllFamily() {

        //Arrange
        Person person1 = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person person2 = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person person3 = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person person4 = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person person5 = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5));

        person1.setMother(person4);
        person1.setFather(person5);
        person2.setMother(person4);
        person2.setFather(person5);
        person3.setMother(person4);
        person3.setFather(person5);

        Group group1 = new Group("Family");

        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertTrue(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - All except one")
    void ifGroupIsFamilyAllFamilyExceptOne() {

        //Arrange
        Person person1 = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person person2 = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person person3 = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person person4 = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person person5 = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        Person person6 = new Person("Diana", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5, person6));

        person1.setMother(person4);
        person1.setFather(person5);
        person2.setMother(person4);
        person2.setFather(person5);
        person3.setMother(person4);
        person3.setFather(person5);

        Group group1 = new Group("Family");

        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertFalse(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No one is family")
    void ifGroupIsFamilyNoneFamily() {

        //Arrange
        Person person1 = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person person2 = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person person3 = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person person4 = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person person5 = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5));

        Group group1 = new Group("Family");

        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertFalse(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No Mother")
    void ifGroupIsFamilyFamilyButNoMother() {

        //Arrange
        Person person1 = new Person("Oscar", 1990, 11, 10, new Address("Porto"));
        Person person2 = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person person3 = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person person4 = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person person5 = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5));

        person1.setFather(person5);
        person2.setFather(person5);
        person3.setFather(person5);

        Group group1 = new Group("Family");

        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertFalse(group1.isFamily());
    }

    /**
     * Test Equals method for the Group class
     */
    @Test
    @DisplayName("Two group are the same")
    void equalsGroupClassJustGroupTrue() {

        Group group1 = new Group("Familia");
        Group group2 = new Group("Familia");

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClassAddPersonTrue() {
        //Arrange
        Person person1 = new Person("Elsa", 2000, 02, 24, new Address("Porto"));
        Person person2 = new Person("Filipa", 1990, 01, 05, new Address("Porto"));
        Group group1 = new Group("Familia");
        Group group2 = new Group("Familia");
        HashSet<Person> members = new HashSet<>(Arrays.asList(person1, person2));

        //Act
        group1.addMultipleMembers(members);
        group2.addMultipleMembers(members);
        boolean result = group1.equals(group2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClassGroupFalse() {
        //Arrange
        Person person1 = new Person("Elsa", 2000, 02, 24, new Address("Porto"));
        Person person2 = new Person("Filipa", 1990, 01, 05, new Address("Porto"));
        Group group1 = new Group("Familia");
        Group group2 = new Group("Familia");
        HashSet<Person> members = new HashSet<>(Arrays.asList(person1, person2));

        //Act
        group1.addMultipleMembers(members);
        group2.addMultipleMembers(members);
        boolean result = group1.equals(group2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClassAddPersonFalse() {
        //Arrange
        Person person1 = new Person("Elsa", 2000, 02, 24, new Address("Porto"));
        Person person2 = new Person("Filipa", 1990, 01, 05, new Address("Porto"));
        Person person3 = new Person("Pedro", 1990, 01, 05, new Address("Porto"));
        Group group1 = new Group("Familia fixe");
        Group group2 = new Group("Familia mais fixe");
        HashSet<Person> membersFamily = new HashSet<>(Arrays.asList(person2, person1));
        HashSet<Person> membersOtherFamily = new HashSet<>(Arrays.asList(person2, person3));
        //Act
        group1.addMultipleMembers(membersFamily);
        group2.addMultipleMembers(membersOtherFamily);
        boolean result = group1.equals(group2);

        //Assert
        assertFalse(result);
    }


    /**
     * Check if member was promoted to group admin
     */
    @Test
    @DisplayName("Promote one member to group admin")
    void promoteMemberTest1Person() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.promoteMemberToAdmin(person1);
        HashSet<Person> adminList = group1.getAdmins();
        HashSet<Person> memberList = group1.getMembers();
        boolean wasPromoted = adminList.contains(person1) && memberList.contains(person1);

        //Assert
        assertTrue(wasPromoted);
    }

    @Test
    @DisplayName("Promote one member to group admin while there are more than one")
    void promoteMemberTest2Person() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        group1.promoteMemberToAdmin(person1);
        HashSet<Person> adminList = group1.getAdmins();
        HashSet<Person> memberList = group1.getMembers();
        boolean wasPromoted = adminList.contains(person1) && memberList.contains(person1) && memberList.contains(person2);

        //Assert
        assertTrue(wasPromoted);
    }

    /**
     * Check if member was demoted from group admin
     */
    /*@Test
    @DisplayName("Demote one group admin to member")
    void demoteMemberTest() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.promoteMemberToAdmin(person1);
        HashSet<Person> adminListWithAdmin = group1.getAdmins();
        group1.demoteMemberFromAdmin(person1);
        HashSet<Person> adminListWithoutAdmin = group1.getAdmins();
        boolean wasDemoted = adminListWithAdmin.contains(person1) && adminListWithoutAdmin.isEmpty();

        //Assert
        assertTrue(wasDemoted);
    }

    @Test
    @DisplayName("Promote one member to Admin while there are more than one member")
    void demoteMemberTest2() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        group1.promoteMemberToAdmin(person1);
        group1.promoteMemberToAdmin(person2);
        HashSet<Person> adminListWithAdmin = group1.getAdmins();
        boolean before = adminListWithAdmin.contains(person1) && adminListWithAdmin.contains(person2);
        group1.demoteMemberFromAdmin(person1);
        HashSet<Person> adminListWithoutAdmin = group1.getAdmins();
        boolean after = adminListWithoutAdmin.contains(person2) && !adminListWithoutAdmin.contains(person1);

        //Assert
        assertTrue(before && after);
    }*/

    /**
     * Check if multiple members were promoted to Admin
     */
    @Test
    @DisplayName("Promote multiple members to Admin")
    void promoteMultipleMembersToAdmin() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Person person3 = new Person("Pedro", 1990, 01, 05, new Address("Porto"));

        Group group1 = new Group("Francis Group");

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person1, person2, person3));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);
        group1.promoteMultipleMemberToAdmin(setOfPeopleToAddToGroup);

        HashSet<Person> adminList = group1.getAdmins();
        HashSet<Person> memberList = group1.getMembers();
        boolean membersWerePromoted = adminList.containsAll(setOfPeopleToAddToGroup) && memberList.containsAll(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(membersWerePromoted);
    }

    @Test
    @DisplayName("Promote multiple members to admin while there are more than members that are not admins")
    void promoteMultipleMembersToAdminWhileThereAreOtherGroupMembers() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Person person3 = new Person("Pedro", 1990, 01, 05, new Address("Porto"));
        Person person4 = new Person("Elsa", 2000, 02, 24, new Address("Porto"));

        Group group1 = new Group("Francis Group");

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person1, person2, person3, person4));
        HashSet<Person> setOfPeopleToBeAdmin = new HashSet<>(Arrays.asList(person1, person2));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);
        group1.promoteMultipleMemberToAdmin(setOfPeopleToBeAdmin);

        HashSet<Person> adminList = group1.getAdmins();
        HashSet<Person> memberList = group1.getMembers();
        boolean werePromoted = adminList.containsAll(setOfPeopleToBeAdmin) && memberList.containsAll(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(werePromoted);
    }

    //Fazer teste para pessoas nulas

    /**
     * Check if a person was promoted to member and group administrator simultaneously
     */
    @Test
    @DisplayName("Promote person to member and group admin simultaneously")
    void memberAndGroupAdminSimultaneously() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.setAdmin(person1);

        HashSet<Person> adminList = group1.getAdmins();
        HashSet<Person> memberList = group1.getMembers();
        boolean wasPromoted = adminList.contains(person1) && memberList.contains(person1);

        //Assert
        assertTrue(wasPromoted);
    }

    @Test
    @DisplayName("Promote person to member and group admin simultaneously while there are more than members that are not admins")
    void memberAndGroupAdminSimultaneouslyWhileThereAreOtherGroupMembers() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Person person3 = new Person("Pedro", 1990, 01, 05, new Address("Porto"));
        Person person4 = new Person("Elsa", 2000, 02, 24, new Address("Porto"));

        Group group1 = new Group("Francis Group");

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person2, person3, person4));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);
        group1.setAdmin(person1);

        HashSet<Person> adminList = group1.getAdmins();
        HashSet<Person> memberList = group1.getMembers();
        boolean wasPromoted = adminList.contains(person1) && memberList.containsAll(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(wasPromoted);
    }

    /**
     * Check if Account was added to the groups account list
     * Testing getGroupAccountList() to see if account was added to the group´s list
     */
    @DisplayName("Using .contains() method from the HashSet class")
    @Test
    void addAccountToGroupListTestContains() {
        //Arrange:
        Group group1 = new Group("Test Group");
        Account groupAccount = new Account("Group Account Test", "group account");

        //Act
        boolean result =group1.addAccountToGroupAccountList(groupAccount);

        //Assert
        assertTrue(result);
    }

    @DisplayName("Using .size() method from the HashSet class")
    @Test
    void addAccountToGroupListTestSize() {
        //Arrange
        Group group1 = new Group("Test Group");
        Account groupAccount = new Account("Group Account Test", "group account");
        Account groupAccount2 = new Account("Group Account Test 2", "group account");

        //Act
        group1.addAccountToGroupAccountList(groupAccount);
        group1.addAccountToGroupAccountList(groupAccount2);
        int result = group1.getGroupAccountsList().size();

        //Assert
        assertEquals(2, result);
    }

    /**
     * Test if a person can create a group account (must be a group admin).
     * User Stories Method : createGroupAccount
     */

    @Test
    @DisplayName("Test if a group admin can create a group account - TRUE")
    void createGroupAccountTest(){
        //Arrange
        Group group1 = new Group ("test group");

        //Act
        boolean result = group1.createGroupAccount("Conta de Grupo", "Test");

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a group can create multiple group accounts - TRUE")
    void createGroupAccountsTest(){
        //Arrange
        Group group1 = new Group("test group");

        //Act
        boolean addGroupAccount1 = group1.createGroupAccount("Conta de Grupo 1","Test");
        boolean addGroupAccount2 = group1.createGroupAccount("Conta de Grupo 2", "Test");

        //Assert
        assertTrue(addGroupAccount1 && addGroupAccount2);
    }
}



