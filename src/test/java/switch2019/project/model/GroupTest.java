package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.controllers.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    /**
     * USER STORY 2 - check if user was added
     * Methods to check if the number of groups in the GroupList is increased
     */

    @Test
    @DisplayName("Check if One group was added")
    public void wasGroupAddedToList() {
        //Arrange
        Person person1 = new Person("John", 1998, 10, 15, new Address("New York"));
        Person person2 = new Person("Frank", 1994, 10, 12, new Address("Washington D.C."));
        Group group1 = new Group("Amigos");
        GroupsList groupList1 = new GroupsList();

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        boolean addedGroup = groupList1.addGroupToGroupList(group1);


        //Assert
        assertTrue(addedGroup);
    }

    /**
     * Method to check if a Group was created inside a GroupList
     */
    @Test
    public void isGroupInListCompare() {
        // Arrange Groups
        Group group1 = new Group("Amigos");

        // Arrange Group List
        GroupsList groupList1 = new GroupsList();

        // Act
        boolean groupAdded = groupList1.addGroupToGroupList(group1);

        //Assert
        assertTrue(groupAdded);
    }

    /**
     * Method to check if Muliple Groups were created inside a GroupList
     */

    @Test
    public void areGroupsInListCompare() {
        // Arrange Groups
        Group group1 = new Group("Programadores");
        Group group2 = new Group("Amigos");

        // Arrange Group List
        GroupsList groupList1 = new GroupsList();

        // Act
        boolean group1Added = groupList1.addGroupToGroupList(group1);
        boolean group2Added = groupList1.addGroupToGroupList(group2);

        // Assert
        assertTrue(group1Added && group2Added);
    }

    /**
     * User Story 3 (add a member to a group)
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
        boolean areMembersAddedToGroup = (
            group1.addMember(person1) &&
            group1.addMember(person2));

        //Assert
        assertTrue(areMembersAddedToGroup);
    }

    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMemberNull() {

        //Arrange
        Person person1 = null;
        Group group1 = new Group("OsMaisFixes");

        //Act
        boolean isMemberAddedToGroup = group1.addMember(person1);

        //Assert
        assertFalse(isMemberAddedToGroup);
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
        boolean isMemberAddedToEmpyGroup = group1.addMember(person1);

        //Assert
        assertTrue(isMemberAddedToEmpyGroup);
    }

    @Test
    @DisplayName("False - member added to a non empty group")
    void promoteAddedMemberIfEmptyTestFalse() {
        //Arrange
        Person person1 = new Person("Juan", 1970, 1, 2, new Address("Toledo"));
        Person person2 = new Person("Pablo", 1978, 5, 16, new Address("Madrid"));
        Group group1 = new Group("Group with no members");

        //Act
        boolean areMembersAddedToANonEmptyGroup = (
            group1.addMember(person1) &&
            group1.addMember(person2));

        //Assert
        assertTrue(areMembersAddedToANonEmptyGroup);
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
        boolean areThreePeopleAdded = (
            group1.addMember(person1) &&
            group1.addMember(person2) &&
            group1.addMember(person3));


        //Assert
        assertTrue(areThreePeopleAdded);
    }

    @Test
    @DisplayName("Test if the same person is not added twice")
    void addMultipleMembersSamePersonNotTwice() {
        //Arrange
        Group group1 = new Group("Maria's Group");

        Person person1 = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person person2 = new Person("Maria", 1994, 05, 01, new Address("Porto"));

        //Act
        group1.addMember(person1);
        boolean memberNotAdded = group1.addMember(person2);

        //Assert
        assertFalse(memberNotAdded);
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
        boolean isPerson2NotAdded = group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Assert
        assertFalse(isPerson2NotAdded);
    }

    /**
     * Test if member was removed from Group
     */
    /*@Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroup() {
        //Arrange
        Group group1 = new Group("Grupo a ser submetido aos testes");

        Person person1 = new Person("João", 1993, 9, 1, new Address("Paranhos"));
        Person person2 = new Person("Elsa", 1992, 10, 9, new Address("Porto"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));

        //Act
        group1.addMultipleMembers(putMembers);

        boolean removeSingleMember = group1.removeMember(person2);

        //Assert
        assertTrue(removeSingleMember);
    }*/

    /*@Test
    @DisplayName("Test if a member was removed from a Group - try to remove all members")
    void removeMemberFromGroupAllMembers() {
        //Arrange
        Group group1 = new Group("123 são os primeiros três números inteiros");

        Person person1 = new Person("João", 1993, 9, 1, new Address("Lisboa"));
        Person person2 = new Person("Elsa", 1992, 10, 9, new Address("Porto"));
        Person person3 = new Person("Laurinda", 1998, 3, 14, new Address("Porto"));

        group1.addMember(person1);
        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person3, person2));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);
        boolean areBothMembersRemoved = (group1.removeMember(person3) && group1.removeMember(person2));

        //Assert
        assertTrue (areBothMembersRemoved);
    }*/

    /**
     * Test if multiple members were removed from a Group and there is at least one group admin in the group
     */
    /*@Test
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

        boolean removeOnlyNonAdminMembers = (
            group1.addMultipleMembers(setOfMembers) &&
            group1.removeMultipleMembers(setOfMembersToRemove));

        //Assert
        assertTrue(removeOnlyNonAdminMembers);
    }*/


    /*@Test
    @DisplayName("Test if multiple members were removed from a Group - tried to remove all the group admins")
    void removeMultipleMembersFromAGroupAllAdmins() { // Perguntar ao professor depois o que deve remover, todos menos 1 ou não deve remover nenhum
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

        boolean removeMultipleMembersAndAdmins = (
            group1.addMultipleMembers(setOfMembers) &&
            group1.removeMultipleMembers(setOfMembersToRemove));

        //Assert
        assertTrue(removeMultipleMembersAndAdmins);
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

        group1.addMember(person1);
        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person2, person3, person4));
        HashSet<Person> setOfPeopleToRemoveFromGroup = new HashSet<>(Arrays.asList(person2, person3));

        group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Act
        boolean areMultipleMembersRemoved = (group1.removeMultipleMembers(setOfPeopleToRemoveFromGroup));

        //Assert
        assertTrue(areMultipleMembersRemoved);
    }*/

    /**
     * Test if a removed member is also removed from admin
     */
   /* @Test
    @DisplayName("multiple members")
    void isRemovedMemberAlsoRemovedFromAdmin() {
        //Arrange:
        Group group1 = new Group("Grupo ainda mais fixe que o outro");
        Person person1 = new Person("Pedro", 1999, 12, 9, new Address("Porto"));
        Person person2 = new Person("Gabriel", 1996, 3, 6, new Address("Porto"));
        Person person3 = new Person("Laurinda", 1998, 3, 14, new Address("Porto"));

        //Act:
        boolean areMembersBeingAddedAndRemoved = (
            group1.addMember(person1) &&
            group1.addMember(person2) &&
            group1.promoteMemberToAdmin(person2) &&
            group1.addMember(person3) &&
            group1.removeMember(person1)
        );

        //Assert:
        assertTrue(areMembersBeingAddedAndRemoved);
    }*/


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
    void promoteMember() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Group group1 = new Group("Francis Group");

        //Act
        boolean isFirstMemberAdded = group1.addMember(person1);
        boolean isSecondMemberAdded = group1.addMember(person2);
        boolean isSecondMemberPromoted = group1.promoteMemberToAdmin(person2);

        boolean wasPromoted = isFirstMemberAdded && isSecondMemberAdded && isSecondMemberPromoted;

        //Assert
        assertTrue(wasPromoted);
    }

    @Test
    @DisplayName("Promote one member to Admin while there are more than one member")
    void promoteMemberTest2() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Person person3 = new Person("Albert",1987,10,10,new Address("Bristol"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        group1.addMember(person3);
        boolean wereMembersPromoted = group1.promoteMemberToAdmin(person2) && group1.promoteMemberToAdmin(person3);

        //Assert
        assertTrue(wereMembersPromoted);
    }

    @Test
    @DisplayName("Promote one member to group admin - false because member is already group admin")
    void promoteMemberFalseAlreadyAdmin() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        boolean isFirstMemberPromotedAgain = group1.promoteMemberToAdmin(person1);

        boolean wasPromoted = isFirstMemberPromotedAgain;

        //Assert
        assertFalse(wasPromoted);
    }

    @Test
    @DisplayName("Promote one member to group admin - false because member is already group admin")
    void promoteMemberFalseNotMember() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Group group1 = new Group("Francis Group");

        //Act
        boolean isFirstMemberPromoted = group1.promoteMemberToAdmin(person1);

        boolean wasPromoted = isFirstMemberPromoted;

        //Assert
        assertFalse(wasPromoted);
    }

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
        boolean addedMultipleMembers = group1.addMultipleMembers(setOfPeopleToAddToGroup);
        boolean membersWerePromoted = group1.promoteMultipleMemberToAdmin(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(addedMultipleMembers && membersWerePromoted);
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
        boolean areMultipleMembersAdded = group1.addMultipleMembers(setOfPeopleToAddToGroup);
        boolean areMultipleMembersPromoted = group1.promoteMultipleMemberToAdmin(setOfPeopleToBeAdmin);

        boolean werePromoted = areMultipleMembersPromoted && areMultipleMembersAdded;

        //Assert
        assertTrue(werePromoted);
    }

    /**
     * Check if member was demoted from group admin
     */
    /*@Test
    @DisplayName("Demote one group admin to member")
    void demoteMemberTest() {

        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        group1.promoteMemberToAdmin(person2);
        boolean wasDemoted = group1.demoteMemberFromAdmin(person2);

        //Assert
        assertTrue(wasDemoted);
    }

    @Test
    @DisplayName("Demote multiple group admins to members")
    void demoteMultipleMembersTest() {

        //Arrange:
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Person person3 = new Person("Vladimir",1970,4,12, new Address("Moscow"));
        Group group1 = new Group("Francis Group");

        //Act:
        group1.addMember(person1); // Torna-se admin automáticamente
        group1.addMember(person2);
        group1.addMember(person3);
        group1.promoteMemberToAdmin(person2);
        group1.promoteMemberToAdmin(person3);
        boolean isFirstAdminRemoved = group1.demoteMemberFromAdmin(person2);
        boolean isSecondAdminRemoved = group1.demoteMemberFromAdmin(person3);

        //Assert:
        assertTrue (isFirstAdminRemoved && isSecondAdminRemoved);
    }

    @Test
    @DisplayName("Check if the same admin can be demoted from multiple groups")
    void demoteMemberFromMultipleGroups() {

        //Arrange:
        Person person1 = new Person ("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Group group1 = new Group("Test Group 1");
        Group group2 = new Group("Test Group 2");
        Group group3 = new Group ("Test Group 3");

        //Act:
        group1.addMember(person1); // admin automatically
        group1.addMember(person2);
        group2.addMember(person1); // admin automatically
        group2.addMember(person2);
        group3.addMember(person1); // admin automatically
        group3.addMember(person2);
        group1.promoteMemberToAdmin(person2);
        group2.promoteMemberToAdmin(person2);
        group3.promoteMemberToAdmin(person2);
        boolean isRemovedFromGroup1 = group1.demoteMemberFromAdmin(person1);
        boolean isRemovedFromGroup2 = group2.demoteMemberFromAdmin(person1);
        boolean isRemovedFromGroup3 = group3.demoteMemberFromAdmin(person1);

        // Assert
        assertTrue(isRemovedFromGroup1 && isRemovedFromGroup2 && isRemovedFromGroup3);
    }

    @Test
    @DisplayName("Check if the last admin can be demoted - FALSE expected because group must contain at least 1 admin")
    void canLastAdminBeDemoted() {

        //Arrange:
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Group group1 = new Group ("Test Group");

        //Act:
        group1.addMember(person1); //Automatically promoted to admin
        group1.addMember(person2);
        boolean isRemovedFromAdminPerson2 = group1.demoteMemberFromAdmin(person2);
        boolean isRemovedFromAdminPerson1 = group1.demoteMemberFromAdmin(person1);

        //Assert:
        assertFalse (isRemovedFromAdminPerson2 && isRemovedFromAdminPerson1);
    }

    /**
     * Test used to check if an HashSet of group admins can be demoted to member
     */
    /*
    @Test
    @DisplayName("Check if multiple admins are demoted")
    void multipleAdminDemotionTest() {
        // Arrange:
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Person person2 = new Person("Jaques", 2000, 12, 1, new Address("Paris"));
        Person person3 = new Person("John",1990,06,22,new Address("Bristol"));
        Person person4 = new Person("Susan",1997,12,11,new Address("Edinburgh"));
        Group group1 = new Group("Test Group");

        // Act:
        group1.addMember(person1); //automatically promoted to admin
        group1.addMember(person2);
        group1.addMember(person3);
        group1.addMember(person4);
        HashSet<Person> membersToPromote = new HashSet<>(Arrays.asList(person2, person3));
        group1.promoteMultipleMemberToAdmin(membersToPromote);
        HashSet<Person> membersToDemote = new HashSet<>(Arrays.asList(person2, person3));
        boolean areAllDemoted = group1.demoteMultipleMembersFromAdmin(membersToDemote);

        // Assert:
        assertTrue(areAllDemoted);
    }//Fazer teste para pessoas nulas
    */


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
        boolean isMemberAddedAsAdmin = group1.setAdmin(person1);

        //Assert
        assertTrue(isMemberAddedAsAdmin);
    }

    @Test
    @DisplayName("Promote person to member and group admin simultaneously - false because member is already group admin")
    void memberAndGroupAdminSimultaneouslyFalse() {
        //Arrange
        Person person1 = new Person("Francis", 1994, 05, 23, new Address("London"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        boolean isMemberAddedAsAdmin = group1.setAdmin(person1);

        //Assert
        assertFalse(isMemberAddedAsAdmin);
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
        boolean areMultipleMembersAdded = group1.addMultipleMembers(setOfPeopleToAddToGroup);
        boolean isAdminPromoted = group1.setAdmin(person1);

        boolean wasPromoted = areMultipleMembersAdded && isAdminPromoted;

        //Assert
        assertTrue(wasPromoted);
    }

    /**
     * Check if Account was added to the groups account list
     * Testing getGroupAccountList() to see if account was added to the group´s list
     */
    @DisplayName("Added 1 Account")
    @Test
    void addAccountToGroupListTestContains() {
        //Arrange:
        Group group1 = new Group("Test Group");

        //Act
        boolean result =group1.addAccountToGroupAccountsList("Group Account Test", "group account");

        //Assert
        assertTrue(result);
    }


    @DisplayName("Added 2 Accounts")
    @Test
    void addAccountToGroupListTestSize() {
        //Arrange
        Group group1 = new Group("Test Group");

        //Act
        boolean addAccountToGroupList1 = group1.addAccountToGroupAccountsList("Group Account Test", "group account");
        boolean addAccountToGroupList2 = group1.addAccountToGroupAccountsList("Group Account Test 2", "group account");

        //Assert
        assertTrue(addAccountToGroupList1 && addAccountToGroupList2);
    }

    /**
     * Test if a person can create a group account (must be a group admin).
     * User Stories 7: createGroupAccount
     */

    @Test
    @DisplayName("Test if a group admin can create a group account - TRUE")
    void createGroupAccountTest(){

        //Arrange :
        Person person1 = new Person ("João",1994,11,13,new Address("Porto"));
        Group group1 = new Group ("Test group");

        //Act :
        group1.addMember(person1);
        boolean result = group1.createGroupAccount("Account1", "Test", person1);

        //Assert :
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a group can create multiple group accounts - TRUE")
    void createGroupAccountsTest(){

        //Arrange
        Person person1 = new Person ("João",1994,11,13,new Address("Porto"));
        Group group1 = new Group("Test group");

        //Act
        group1.addMember(person1);
        boolean addGroupAccount1 = group1.createGroupAccount("Account1","Test", person1);
        boolean addGroupAccount2 = group1.createGroupAccount("Account2", "Test", person1);
        boolean addGroupAccount3 = group1.createGroupAccount("Account3","Test",person1);

        //Assert
        assertTrue(addGroupAccount1 && addGroupAccount2 && addGroupAccount3);
    }

    @Test
    @DisplayName("Test if a person that is not a group admin can create a group account - False")
    void createGroupAccountFalse(){

        //Arrange :
        Person person1 = new Person ("João",1994,11,13,new Address("Porto"));
        Person person2 = new Person ("Francisca", 12,3,15,new Address("Lisboa"));
        Group group1 = new Group("Test group");

        //Act :
        group1.addMember(person1);
        group1.addMember(person2);
        boolean addGroupAccount = group1.createGroupAccount("Account1","Test", person2);

        //Assert:
        assertFalse(addGroupAccount);
    }

    @Test
    @DisplayName("Test if an Account with the same Account Denomination is added to the list")
    void createGroupAccountSameDescriptionFalse() {

        //Arrange :
        Person person1 = new Person("João", 1994, 11, 13, new Address("Porto"));
        Group group1 = new Group("test group");

        //Act:
        group1.addMember(person1);
        group1.createGroupAccount("Account1","Test", person1);
        boolean addGroupAccountRepeated = group1.createGroupAccount("Account1","Test", person1);

        //Assert
        assertFalse(addGroupAccountRepeated);
    }

    @Test
    @DisplayName("Test if Method cant create two accounts with the same Account Denomination, but different letter casing.")
    void createGroupAccountSameDescriptionIgnoreCasing() {
        //Arrange:
        Person person1 = new Person("João", 1994, 11, 13, new Address("Porto"));
        Group group1 = new Group("Test group");

        //Act:
        group1.addMember(person1);
        group1.createGroupAccount("Account1", "Test", person1);
        boolean addGroupAccountRepeated = group1.createGroupAccount("AcCouNT1", "Test", person1);

        //Assert:
        assertFalse(addGroupAccountRepeated);
    }

    @Test
    @DisplayName("Test if a regular member can add a Group Account")
    void createGroupAccountRegularMemberFalse(){

        //Arrange:
        Person person1 = new Person ("João",1994,11,13,new Address("Porto"));
        Person person2 = new Person ("Francisca", 12,3,15,new Address("Lisboa"));
        Group group1 = new Group("Test group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        boolean canARegularMemberAddGroupAccount = group1.createGroupAccount("Account1","Test", person2);


        //Assert
        assertFalse (canARegularMemberAddGroupAccount);
    }

    @Test
    @DisplayName("Test if method works while the group description is null")
    void createGroupAccountOnNullGroup(){

        //Arrange:
        Person person1 = new Person ("João",1994,11,13,new Address("Porto"));
        Group group1 = new Group(null);

        //Act:
        boolean canAnAccountBeAddedToNullGroup = group1.addMember(person1)
                && group1.createGroupAccount("Account1","Test",person1);

        //Assert:
        assertFalse(canAnAccountBeAddedToNullGroup);
    }

    @Test
    @DisplayName("Test if an admin of many groups can add an account to all of them")
    void createGroupAccountsOnMultipleGroups(){

        //Arrange:
        Person person1 = new Person ("Francisca", 12,3,15,new Address("Lisboa"));
        Group group1 = new Group("Test Group");
        Group group2 = new Group("Test Group 2");
        Group group3 = new Group("Test Group 3");

        //Act:
        group1.addMember(person1);
        group2.addMember(person1);
        group3.addMember(person1);
        boolean isGroup1AccountCreated = group1.createGroupAccount("Account1","User Story 7", person1);
        boolean isGroup2AccountCreated = group2.createGroupAccount("Account2","User Story 7", person1);
        boolean isGroup3AccountCreated = group3.createGroupAccount("Account3", "User Story 7", person1);

        //Assert
        assertTrue(isGroup1AccountCreated && isGroup2AccountCreated && isGroup3AccountCreated);
    }

    @Test
    @DisplayName("Create Account with null Denomination")
    void canAccountWithNullDescriptionBeCreated() {

        //Arrange:
        Person person1 = new Person ("Francisca", 12,3,15,new Address("Lisboa"));
        Group group1 = new Group("Test Group");

        //Act:
        group1.addMember(person1);
        boolean canNullAccountBeAdded = group1.createGroupAccount(null, "User Story 7",person1);

        //Assert:
        assertFalse(canNullAccountBeAdded);
    }

    /**
     * Check if a Category was added to the groups Category list
     */
    /*
    @Test
    @DisplayName("Check if a category was added to Category List - Main Scenario")
    void addCategoryToListMainScenario() {
        //Arrange
        //Initialize Group

        Group group1 = new Group("Grupo dos amigos");

        //Category to be included in Category List
        Category category1 = new Category("School expenses");

        //Act
        boolean realResult = group1.addCategoryToCategoryList(category1);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Check if null category is not added")
    void addCategoryToListWithANullCase() {
        //Arrange

        //Initialize Group
        Group group1 = new Group("Grupo dos amigos");

        //Category to be included in Category List
        Category category1 = null;

        //Act
        boolean realResult = group1.addCategoryToCategoryList(category1);

        //Assert
        assertFalse(realResult);

    }

    @Test
    @DisplayName("Check if the same Category is not added simultaneously")
    void addTwoCategoriesToListWithTwoCategoriesThatAreTheSame() {
        //Arrange

        //Initialize Group
        Group group1 = new Group("Groupo dos amigos");

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("School expenses");

        //Act
        boolean realResult =  group1.addCategoryToCategoryList(category1) && !group1.addCategoryToCategoryList(category2);

        //Assert
        assertTrue(realResult);

    }

    @Test
    @DisplayName("Check if the same Category is not added simultaneously - Ignore letter capitalization and special characters ")
    void addTwoCategoriesToListWithTwoCategoriesCaseInsensitive() {
        //Arrange

        //Initialize Group
        Group group1 = new Group("Groupo dos amigos");

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("SCHOóL expenses");

        //Act
        boolean realResult = group1.addCategoryToCategoryList(category1) && !group1.addCategoryToCategoryList(category2);

        //Assert
        assertTrue(realResult);

    }

    /**
     * Check if a Category was removed from the groups Category list
     */

    /*
    @Test
    @DisplayName("Remove categories from User Category List - Main Scenario")
    void removeCategoryFromListMainScenario() {
        //Arrange

        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("Health expenses");

        group1.addCategoryToCategoryList(category1);
        group1.addCategoryToCategoryList(category2);

        //Act
        boolean realResult = group1.removeCategoryFromList(category1);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("To Try to remove a set of Categories that does not exist or null")
    void removeCategoriesToListWithANullCase() {
        //Arrange

        //Initialize Group
        Group group1 = new Group("Groupo dos amigos");

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = null;

        group1.addCategoryToCategoryList(category1);
        group1.addCategoryToCategoryList(category2);

        //Act
        boolean realResult = group1.removeCategoryFromList(category1);

        //Assert
        assertTrue(realResult);

    }

    @Test
    @DisplayName("Remove a Category from user's Category List - Ignore letter capitalization and special characters")
    void removeCategoryFromListIgnoreLettersFormatAndSpecialCase() {
        //Arrange
        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        // Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("ScHOÓL eXpenSÉs");
        group1.addCategoryToCategoryList(category1);

        //Act
        boolean realResult = group1.removeCategoryFromList(category2);

        //Assert
        assertTrue(realResult);
    }

    /**
     * Check if multiple Categories were added to the groups Category list
     */

    /*
    @Test
    @DisplayName("Add a Set of Categories to user Category List - Main Scenario")
    void addMultipleCategoriesToListMainScenario() {
        // Arrange

        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryUniversity = new Category("University");

        //Act

        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryUniversity));
        //Category - add several categories to the user Category List with method
        boolean realResult = group1.addMultipleCategoriesToList(setOfCategories);


        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if null category is not added")
    void addMultipleCategoriesToListWithANullCase() {
        // Arrange

        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        // Categories to be included in Category List
        Category categoryBets = new Category("Bets and Games");
        Category categoryNull = null;
        Category categoryBeauty = new Category("Beauty");

        //Act

        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryBets, categoryNull, categoryBeauty));
        //Category - add several categories to the user Category List with method
        group1.addMultipleCategoriesToList(setOfCategories);

        boolean realResult = !group1.addCategoryToCategoryList(categoryNull);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously")
    void addMultipleCategoriesToListWithTwoCategoriesThatAreTheSame() {
        // Arrange
        //Initialize group
        Group group1 = new Group("Groupo dos amigos");
        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryHealthDuplicated = new Category("Health");
        Category categoryBeauty = new Category("Beauty");

        //Act
        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //The user adds several categories to his Category List with method

        boolean realResult = group1.addMultipleCategoriesToList(setOfCategories);
        //Assert
        assertTrue(realResult);

    }

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters ")
    void addMultipleCategoriesToListWithTwoCategoriesCaseInsensitive() {
        // Arrange

        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryHealthDuplicated = new Category("heálth");
        Category categoryBeauty = new Category("Beauty");

        //Act

        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));

        //The user adds several categories to his Category List with method
        boolean realResult =  group1.addMultipleCategoriesToList(setOfCategories);

        //Assert
        assertTrue(realResult);
    }

    /**
     * Check if multiple Categories were removed from the groups Category list
     */
/*
    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Main Scenario")
    void removeMultipleCategoriesToListMainScenario() {
        // Arrange

        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        //Act

        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        group1.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryBeauty, categoryGym));
        boolean realResult = group1.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - try to remove a set of Categories that does not " +
            "or null")
    void removeMultipleCategoriesToListExceptionCase() {
        // Arrange

        ///Initialize group
        Group group1 = new Group("Groupo dos amigos");

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        Category categoryCar = new Category("Car");
        Category categoryNull = null;
        Category categoryUniversity = new Category("University");

        //Act

        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        group1.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryCar, categoryNull, categoryUniversity));


        boolean realResult = group1.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Ignore letter capitalization and special characters")
    void removeMultipleCategoriesToListIgnoreLettersFormatAndSpecialCase() {
        // Arrange

        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        Category categoryHealthLowerCase = new Category("health");
        Category categoryGymSpecialCharacter = new Category("Gým");
        Category categoryBeautyUpperCase = new Category("BEAUTY");

        //Act

        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        group1.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryHealthLowerCase, categoryGymSpecialCharacter, categoryBeautyUpperCase));


        boolean realResult = group1.removeMultipleCategoriesToList(setOfCategoriesToRemove);
        //Assert
        assertTrue(realResult);
    }

*/
}



