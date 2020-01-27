package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    /**
     * Compare the same Group - Should be the Same
     */

    @Test
    @DisplayName("Compare the same Group - Should be the Same")
    public void compareTheSameObject() {

        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995,12,13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank",LocalDate.of(1995,12,13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Amigos");
        group1.addMember(person1);
        group1.addMember(person2);

        //Act
        boolean result = group1.equals(group1);

        //Assert
        assertTrue(result);
    }

    /**
     * Compare a Group with a null Group
     */

    @Test
    @DisplayName("Compare one Group with a null Group")
    public void equalsWithNullGroup() {
        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995,12,13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995,12,13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Amigos");
        group1.addMember(person1);
        group1.addMember(person2);
        Group group2 = null;

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertFalse(result);
    }

    /**
     * Compare two groups with same description but different members
     */
    @Test
    @DisplayName("Compare two groups with same members and different description")
    public void compareGroupsWithSameDescriptionAndDifferentMembers() {
        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995,12,13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995,12,13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Mary", LocalDate.of(1995,12,13), new Address("Detroit"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Vasylia", LocalDate.of(1995,12,13), new Address("California"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Amigos");
        group1.addMember(person1);
        group1.addMember(person2);
        Group group2 = new Group("Amigos");
        group2.addMember(person3);
        group2.addMember(person4);

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertFalse(result);
    }

    /**
     * Compare two groups with members but different description
     */
    @Test
    @DisplayName("Compare two groups with members but different description")
    public void compareGroupsWithSameMembersButDifferentDescription() {
        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995,12,13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995,12,13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Mary's Gift");
        group1.addMember(person1);
        group1.addMember(person2);
        Group group2 = new Group("School Trip");
        group2.addMember(person1);
        group2.addMember(person2);

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertFalse(result);
    }

    /**
     * Compare two groups - same members but one with null description
     */

    @Test
    @DisplayName("Compare two groups - same members but one with null description")
    public void compareGroupsSameMembersButOneWithNullDescription() {
        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995,12,13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995,12,13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Mary's Gift");
        group1.addMember(person1);
        group1.addMember(person2);
        Group group2 = new Group(null);
        group2.addMember(person1);
        group2.addMember(person2);

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertFalse(result);
    }

    /**
     * Compare two groups with same members and same description
     */

    @Test
    @DisplayName("Compare two groups with same members and same description")
    public void compareGroupsWithSameMembersAndSameDescription() {
        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995,12,13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank",LocalDate.of(1995,12,13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Mary's Gift");
        group1.addMember(person1);
        group1.addMember(person2);
        Group group2 = new Group("Mary's Gift");
        group2.addMember(person1);
        group2.addMember(person2);

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertTrue(result);
    }

    /**
     *
     */
    @Test
    @DisplayName("Compare different objects")
    public void compareGroupToAnotherObject() {
        //Arrange
        Group group1 = new Group("Mary's Gift");
        Account group2 = new Account("Mary", "Mary Gift");

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertFalse(result);
    }


    /**
     * US002 - check if group was added to group
     * Methods to check if the number of groups in the GroupList is increased
     */

    @Test
    @DisplayName("Check if One group was added")
    public void wasGroupAddedToList() {

        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995,12,13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995,12,13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
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
     * US003 (add a member to a group)
     * Test if a user was added as first member and group admin to a Group and the second as member
     */
    @Test
    @DisplayName("Validate if a member was added to a group")
    void addMember() {

        //Arrange
        Person person1 = new Person("Marta", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Mariana", LocalDate.of(1995,12,13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

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
        Person person1 = new Person("Juan", LocalDate.of(1995,12,13), new Address("Toledo"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
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
        Person person1 = new Person("Juan", LocalDate.of(1995,12,13), new Address("Toledo"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Pablo", LocalDate.of(1995,12,13), new Address("Madrid"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
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

        Person person1 = new Person("Maria", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Mariana", LocalDate.of(1995,12,13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Marisa", LocalDate.of(1995,12,13), new Address("Leiria"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

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

        Person person1 = new Person("Maria", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Maria", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        //Act
        group1.addMember(person1);
        boolean memberNotAdded = group1.addMember(person2);

        //Assert
        assertFalse(memberNotAdded);
    }


    @Test
    @DisplayName("Test if a null members is added to group trough a collection of members")
    void addMultipleMembersWithANullCase() {

        //Arrange
        Group group1 = new Group("Grupo das M'Nation");

        Person person1 = new Person("Maria", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = null;

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person1, person2));
        try {
            //Act
            boolean isPerson2NotAdded = group1.addMultipleMembers(setOfPeopleToAddToGroup);
            fail();
        } catch (IllegalArgumentException message) {
            assertEquals("You cannot add an empty list of members or a non existing person. Please try again.", message.getMessage());
        }
    }

    /**
     * Test if member was removed from Group
     */

    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroup() {

        //Arrange
        Group group1 = new Group("Grupo a ser submetido aos testes");
        Person personAdmin = new Person("António", LocalDate.of(1995,12,13), new Address("Guimarães"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("João", LocalDate.of(1995,12,13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));
        group1.setAdmin(personAdmin);
        //Act
        group1.addMultipleMembers(putMembers);

        boolean removeSingleMember = group1.removeMember(person2);

        //Assert
        assertTrue(removeSingleMember);
    }

    /**
     * Test if member was removed from Group - null member
     */
    @Test
    @DisplayName("Test if a null member was removed from a Group")
    void removeNullMemberFromGroup() {
        //Arrange
        Group group1 = new Group("Grupo a ser submetido aos testes");

        Person personAdmin = new Person("Maria", LocalDate.of(1995,12,13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("João", LocalDate.of(1995,12,13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = null;
        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));
        group1.setAdmin(personAdmin);
        group1.addMultipleMembers(putMembers);

        //Act
        boolean removeSingleMember = group1.removeMember(person3);

        //Assert

        assertFalse(removeSingleMember);

    }

    /**
     * Test if an Administrator was removed from the Group in case he's the only Admin - Shouldn't work
     */

    @Test
    @DisplayName("Test if a member, not null, that is the only administrator is removed - false")
    void removeTheOnlyAdministratorFromGroup() {

        //Arrange
        Group group1 = new Group("OS FIXES");

        Person person1 = new Person("João", LocalDate.of(1995,12,13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Diana", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        //Act
        group1.addMember(person1); //Admin
        group1.addMember(person3);

        boolean removeAdmin = group1.removeMember(person1);

        //Assert
        assertFalse(removeAdmin);
    }

    /**
     * Test if an Administrator was removed from the Group in case he's the only Admin - Shouldn't work
     */

    @Test
    @DisplayName("Test if one of the administrators is removed - true em case more then one")
    void removeTheOneOfTheAdministratorFromGroup() {

        //Arrange
        Group group1 = new Group("OS FIXES");

        Person person1 = new Person("João", LocalDate.of(1995,12,13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Mariana", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Diana", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        //Act
        group1.addMember(person1); //Admin
        group1.setAdmin(person2); //Admin
        group1.addMember(person3);

        boolean removeAdmin = group1.removeMember(person1);

        //Assert
        assertTrue(removeAdmin);
    }

    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroupNullPerson() {

        //Arrange
        Group group1 = new Group("Grupo a ser submetido aos testes");
        Person personAdmin = new Person("Maria", LocalDate.of(1995,12,13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("João", LocalDate.of(1995,12,13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        group1.setAdmin(personAdmin);
        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));

        //Act
        group1.addMultipleMembers(putMembers);

        boolean removeSingleMember = group1.removeMember(null);

        //Assert
        assertFalse(removeSingleMember);
    }

    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroupPersonGroupWithTwoAdmins() {

        //Arrange
        Group group1 = new Group("Grupo a ser submetido aos testes");

        Person person1 = new Person("João", LocalDate.of(1995,12,13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Gabriel", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person2, person1));

        //Act
        group1.addMember(person2);
        group1.addMember(person1);
        group1.setAdmin(person2);

        boolean removeSingleMember = group1.removeMember(person2);

        //Assert
        assertFalse(removeSingleMember);
    }

    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroupPersonNotInGroup() {

        //Arrange
        Group group1 = new Group("Grupo a ser submetido aos testes");
        Person personAdmin = new Person("Catarina", LocalDate.of(1995,12,13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("João", LocalDate.of(1995,12,13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Gabriel", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));
        group1.setAdmin(personAdmin);
        group1.addMultipleMembers(putMembers);

        //Act

        boolean removeSingleMember = group1.removeMember(person3);

        //Assert
        assertFalse(removeSingleMember);
    }

    @Test
    @DisplayName("Test if a member was removed from a Group - try to remove all members")
    void removeMemberFromGroupAllMembers() {

        //Arrange
        Group group1 = new Group("123 são os primeiros três números inteiros");

        Person person1 = new Person("João", LocalDate.of(1995,12,13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda",LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        group1.addMember(person1);
        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person3, person2));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);
        boolean areBothMembersRemoved = (group1.removeMember(person3) && group1.removeMember(person2));

        //Assert
        assertTrue(areBothMembersRemoved);
    }

    /**
     * Test if multiple members were removed from a Group and there is at least one group admin in the group
     */
    @Test
    @DisplayName("Test if multiple members were removed from a Group - not removed 1 group admin and 1 member ")
    void removeMultipleMembersFromAGroupNotRemovingOneGroupAdmin() {

        //Arrange
        Group group1 = new Group("grupo dos amiguinhos");
        Person person1 = new Person("Pedro", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Gabriel", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Oscar", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

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
    }

    @Test
    @DisplayName("Test if multiple members were removed from a Group - tried to remove all the group admins")
    void removeMultipleMembersFromAGroupAllAdmins() {

        //Arrange
        Group group1 = new Group("grupo dos amiguinhos");
        Person person1 = new Person("Pedro", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Gabriel", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Oscar", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

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
        Person person1 = new Person("Pedro", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Gabriel", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Oscar", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        group1.addMember(person1);
        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person2, person3, person4));
        HashSet<Person> setOfPeopleToRemoveFromGroup = new HashSet<>(Arrays.asList(person2, person3));

        group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Act
        boolean areMultipleMembersRemoved = (group1.removeMultipleMembers(setOfPeopleToRemoveFromGroup));

        //Assert
        assertTrue(areMultipleMembersRemoved);
    }

    /**
     * Test if a removed member is also removed from admin
     */
    @Test
    @DisplayName("multiple members")
    void isRemovedMemberAlsoRemovedFromAdmin() {

        //Arrange:
        Group group1 = new Group("Grupo ainda mais fixe que o outro");
        Person person1 = new Person("Pedro", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Gabriel", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        //Act:
        boolean areMembersBeingAddedAndRemoved = (
                group1.addMember(person1) &&
                        group1.addMember(person2) &&
                        group1.setAdmin(person2) &&
                        group1.addMember(person3) &&
                        group1.removeMember(person1)
        );

        //Assert:
        assertTrue(areMembersBeingAddedAndRemoved);
    }


    /**
     * Test if a Group is a family
     */

    @Test
    @DisplayName("Validate if a group is a family - All Family")
    void ifGroupIsFamilyAllFamily() {

        //Arrange
        Person person1 = new Person("Oscar", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Marta", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Joao", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Manuela", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person5 = new Person("Carlos", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5));

        person1.setMother(person4);
        person1.setFather(person5);
        person2.setMother(person4);
        person2.setFather(person5);
        person3.setMother(person4);
        person3.setFather(person5);

        Group group1 = new Group("Family");
        group1.setAdmin(person4);
        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertTrue(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - All except one")
    void ifGroupIsFamilyAllFamilyExceptOne() {

        //Arrange
        Person person1 = new Person("Oscar", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Marta", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Joao", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Manuela", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person5 = new Person("Carlos", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person6 = new Person("Diana", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5, person6));

        person1.setMother(person4);
        person1.setFather(person5);
        person2.setMother(person4);
        person2.setFather(person5);
        person3.setMother(person4);
        person3.setFather(person5);

        Group group1 = new Group("Family");
        group1.setAdmin(person1);
        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertFalse(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No one is family")
    void ifGroupIsFamilyNoneFamily() {

        //Arrange
        Person person1 = new Person("Oscar", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Marta", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Joao", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Manuela", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person5 = new Person("Carlos",LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person2, person3, person4, person5));

        Group group1 = new Group("Family");
        group1.setAdmin(person1);

        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertFalse(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No Mother")
    void ifGroupIsFamilyFamilyButNoMother() {

        //Arrange
        Person person1 = new Person("Oscar", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Marta", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Joao", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Manuela", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person5 = new Person("Carlos", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person2, person3, person4, person5));

        person1.setFather(person5);
        person2.setFather(person5);
        person3.setFather(person5);

        Group group1 = new Group("Family");
        group1.setAdmin(person1);

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

    /**
     * Check if a person is directly added to a group as both member and admin
     * setAdmin method.
     */
    @Test
    @DisplayName("add 1 person to both group member and group admin")
    void isPersonAddedAsMemberAndAdmin(){

        //Arrange:
        Person person1 = new Person ("Francisco", LocalDate.of(1995,12,13),new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person groupAdmin = new Person ("João",LocalDate.of(1995,12,13),new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group ("Test Group");

        //Act:
        group1.addMember(groupAdmin);
        boolean result = group1.setAdmin(person1);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("trying to user method on a null person returns - FALSE")
    void isNullPersonAddedToGroupAsAdminAndMember() {

        //Arrange:
        Person groupAdmin = new Person ("Francisco", LocalDate.of(1995,12,13),new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = null;
        Group group1 = new Group ("Test Group");

        //Act:
        group1.addMember(groupAdmin);
        boolean result = group1.setAdmin(person1);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("setting a person who is already member and admin - FALSE")
    void setAdminOnAAdmin() {

        //Arrange:
        Person person1 = new Person ("Francisco", LocalDate.of(1995,12,13),new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person groupAdmin = new Person ("João", LocalDate.of(1995,12,13),new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group ("Test Group");

        //Act:
        group1.addMember(groupAdmin);
        group1.setAdmin(person1);
        boolean result = group1.setAdmin(person1);

        //Assert:
        assertFalse(result);
    }

    /**
     * Check if member was promoted to group admin
     */
    @Test
    @DisplayName("Promote one member to group admin")
    void promoteMember() {

        //Arrange
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Francis Group");

        //Act
        boolean isFirstMemberAdded = group1.addMember(person1);
        boolean isSecondMemberAdded = group1.addMember(person2);
        boolean isSecondMemberPromoted = group1.setAdmin(person2);

        boolean wasPromoted = isFirstMemberAdded && isSecondMemberAdded && isSecondMemberPromoted;

        //Assert
        assertTrue(wasPromoted);
    }

    @Test
    @DisplayName("Promote one member to Admin while there are more than one member")
    void promoteMemberTest2() {

        //Arrange
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Albert",LocalDate.of(1995,12,13), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        group1.addMember(person3);
        boolean wereMembersPromoted = group1.setAdmin(person2) && group1.setAdmin(person3);

        //Assert
        assertTrue(wereMembersPromoted);
    }

    @Test
    @DisplayName("Promote one member to group admin - false because member is already group admin")
    void promoteMemberFalseAlreadyAdmin() {

        //Arrange
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        boolean isFirstMemberPromotedAgain = group1.setAdmin(person1);

        boolean wasPromoted = isFirstMemberPromotedAgain;

        //Assert
        assertFalse(wasPromoted);
    }

    @Test
    @DisplayName("Promote one member to group admin - false because member is already group admin")
    void promoteMemberFalseNotMember() {

        //Arrange
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.setAdmin(person1);

        boolean wasPromoted = group1.setAdmin(person1);

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
        Person personAdmin = new Person("Marta", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques",LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Pedro", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        Group group1 = new Group("Francis Group");
        group1.setAdmin(personAdmin);

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person1, person2, person3));
        boolean addedMultipleMembers = group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Act
        boolean membersWerePromoted = group1.promoteMultipleMemberToAdmin(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(addedMultipleMembers && membersWerePromoted);
    }

    @Test
    @DisplayName("Promote multiple members to admin while there are more than members that are not admins")
    void promoteMultipleMembersToAdminWhileThereAreOtherGroupMembers() {

        //Arrange
        Person personAdmin = new Person("Marta", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Pedro", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Elsa", LocalDate.of(1995,12,13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        Group group1 = new Group("Francis Group");
        group1.setAdmin(personAdmin);

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person1, person2, person3, person4));
        HashSet<Person> setOfPeopleToBeAdmin = new HashSet<>(Arrays.asList(person1, person2));

        boolean areMultipleMembersAdded = group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Act
        boolean areMultipleMembersPromoted = group1.promoteMultipleMemberToAdmin(setOfPeopleToBeAdmin);

        boolean werePromoted = areMultipleMembersPromoted && areMultipleMembersAdded;

        //Assert
        assertTrue(werePromoted);
    }

    /**
     * Check if member was demoted from group admin
     */
    @Test
    @DisplayName("Demote one group admin to member")
    void demoteMemberTest() {

        //Arrange
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        group1.setAdmin(person2);
        boolean wasDemoted = group1.demoteMemberFromAdmin(person2);

        //Assert
        assertTrue(wasDemoted);
    }

    @Test
    @DisplayName("Demote one group admin to member")
    void demoteMemberNotAdmin() {

        //Arrange
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Francis Group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        boolean wasDemoted = group1.demoteMemberFromAdmin(person2);

        //Assert
        assertFalse(wasDemoted);
    }

    @Test
    @DisplayName("Demote a member who is not an admin - FALSE")
    void demoteMemberTestFalse() {

        //Arrange:
        Person person1 = new Person("Francis",LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Francis Group");

        //Act:
        group1.addMember(person1);
        group1.addMember(person2);
        boolean wasDemoted = group1.addMember(person2);

        //Assert:
        assertFalse(wasDemoted);
    }

    @Test
    @DisplayName("Demote multiple group admins to members")
    void demoteMultipleMembersTest() {

        //Arrange:
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Vladimir", LocalDate.of(1995,12,13), new Address("Moscow"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Francis Group");

        //Act:
        group1.addMember(person1); // Torna-se admin automaticamente
        group1.addMember(person2);
        group1.addMember(person3);
        group1.setAdmin(person2);
        group1.setAdmin(person3);
        boolean isFirstAdminRemoved = group1.demoteMemberFromAdmin(person2);
        boolean isSecondAdminRemoved = group1.demoteMemberFromAdmin(person3);

        //Assert:
        assertTrue(isFirstAdminRemoved && isSecondAdminRemoved);
    }

    @Test
    @DisplayName("Check if the same admin can be demoted from multiple groups")
    void demoteMemberFromMultipleGroups() {

        //Arrange:
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group 1");
        Group group2 = new Group("Test Group 2");
        Group group3 = new Group("Test Group 3");

        //Act:
        group1.addMember(person1); // admin automatically
        group1.addMember(person2);
        group2.addMember(person1); // admin automatically
        group2.addMember(person2);
        group3.addMember(person1); // admin automatically
        group3.addMember(person2);
        group1.setAdmin(person2);
        group2.setAdmin(person2);
        group3.setAdmin(person2);
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
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group");

        //Act:
        group1.addMember(person1); //Automatically promoted to admin
        group1.addMember(person2);
        boolean isRemovedFromAdminPerson2 = group1.demoteMemberFromAdmin(person2);
        boolean isRemovedFromAdminPerson1 = group1.demoteMemberFromAdmin(person1);

        //Assert:
        assertFalse(isRemovedFromAdminPerson2 && isRemovedFromAdminPerson1);
    }

    /**
     * Test used to check if an HashSet of group admins can be demoted to member
     */

    @Test
    @DisplayName("Check if multiple admins are demoted - True")
    void multipleAdminDemotionTest() {

        // Arrange:
        Person person1 = new Person("Francis", LocalDate.of(1995,12,13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995,12,13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("John", LocalDate.of(1995,12,13), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Susan", LocalDate.of(1995,12,13), new Address("Edinburgh"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group");

        // Act:
        group1.addMember(person1); //automatically promoted to admin
        group1.addMember(person2);
        group1.addMember(person3);
        group1.addMember(person4);
        HashSet<Person> membersToPromote = new HashSet<>(Arrays.asList(person2, person3, person4));
        group1.promoteMultipleMemberToAdmin(membersToPromote);
        HashSet<Person> membersToDemote = new HashSet<>(Arrays.asList(person2, person3, person4));
        boolean areAllDemoted = group1.demoteMultipleMembersFromAdmin(membersToDemote);

        // Assert:
        assertTrue(areAllDemoted);
    }

    @Test
    @DisplayName("Check if multiple admins can´t be demoted - FALSE - tring to remove last admin")
    void multipleAdminsDemotionTestFalse() {

        //Arrange:
        Person person1 = new Person("Francis", LocalDate.of(2000,12,12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(2000,12,12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("John", LocalDate.of(2000,12,12), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Susan", LocalDate.of(2000,12,12), new Address("Edinburgh"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group");

        //Act:
        group1.addMember(person1); // automatically promoted to admin
        group1.addMember(person2);
        group1.addMember(person3);
        group1.addMember(person4);
        HashSet<Person> membersToPromote = new HashSet<>(Arrays.asList(person2, person3, person4));
        group1.promoteMultipleMemberToAdmin(membersToPromote);
        HashSet<Person> membersToDemote = new HashSet<>(Arrays.asList(person1, person2, person3, person4));
        // Last person will not be removed since if it is, there will be no admins left on the group;
        boolean isLastAdminDemoted = group1.demoteMultipleMembersFromAdmin(membersToDemote);

        //Assert:
        assertFalse(isLastAdminDemoted);
    }

    @Test
    @DisplayName("Check if multiple admins can´t be demoted - FALSE - tring to remove member that is not part of the group")
    void multipleAdminsDemotionTestFalseNotInGroup() {

        //Arrange:
        Person person1 = new Person("Francis", LocalDate.of(2000,12,12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(2000,12,12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("John", LocalDate.of(2000,12,12), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Susan", LocalDate.of(2000,12,12), new Address("Edinburgh"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group");

        //Act:
        group1.addMember(person1); // automatically promoted to admin
        group1.addMember(person2);
        group1.addMember(person3);
        HashSet<Person> membersToPromote = new HashSet<>(Arrays.asList(person2, person3));
        group1.promoteMultipleMemberToAdmin(membersToPromote);
        HashSet<Person> membersToDemote = new HashSet<>(Arrays.asList(person1, person2, person3, person4));
        // person4 is not part of the group
        boolean isNonMemberDemoted = group1.demoteMultipleMembersFromAdmin(membersToDemote);

        //Assert:
        assertFalse(isNonMemberDemoted);
    }

    /**
     * Check if a person was promoted to member and group administrator simultaneously
     */
    @Test
    @DisplayName("Promote person to member and group admin simultaneously")
    void memberAndGroupAdminSimultaneously() {
        //Arrange
        Person person1 = new Person("Francis", LocalDate.of(2000,12,12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
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
        Person person1 = new Person("Francis", LocalDate.of(2000,12,12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
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
        Person personAdmin = new Person("Marta", LocalDate.of(2000,12,12), new Address("Guimarães"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Francis", LocalDate.of(2000,12,12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(2000,12,12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Pedro", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Elsa", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        Group group1 = new Group("Francis Group");
        group1.setAdmin(personAdmin);

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person2, person3, person4));
        boolean areMultipleMembersAdded = group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Act
        boolean isAdminPromoted = group1.setAdmin(person1);

        boolean wasPromoted = areMultipleMembersAdded && isAdminPromoted;

        //Assert
        assertTrue(wasPromoted);
    }

    /**
     * Test if a person is a Group Admin
     */
    @DisplayName("Check if a person is in the Group Admin List")
    @Test
    void isGroupAdmin() {
        //Arrange:
        Person person1 = new Person("Alexandre", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address ("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address ("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Maria", LocalDate.of(2000,12,12),new Address("Porto"),
                new Address ("Rua X", "Porto", "4520-266"), person2, person1);
        Group group1 = new Group("Maria's Group");
        group1.addMember(person3);

        //Act
        boolean isAdmin = group1.isGroupAdmin(person3);

        //Assert
        assertTrue(isAdmin);
    }

    @DisplayName("Check if a person is not in the Group Admin List")
    @Test
    void isGroupAdminFalse() {
        //Arrange:
        Person person1 = new Person("Alexandre", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address ("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address ("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Maria", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address ("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("João", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), person2, person3);
        Group group1 = new Group("Maria's Group");
        group1.addMember(person3);
        group1.addMember(person4);

        //Act
        boolean isAdmin = group1.isGroupAdmin(person4);

        //Assert
        assertFalse(isAdmin);
    }

    @DisplayName("Check if a person null can be a Group Admin")
    @Test
    void isGroupAdminNull() {
        //Arrange:
        Person person1 = null;
        Group group1 = new Group("Maria's Group");
        group1.addMember(person1);

        //Act
        boolean isAdmin = group1.isGroupAdmin(person1);

        //Assert
        assertFalse(isAdmin);
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
        boolean result = group1.addAccountToGroupAccountsList("Group Account Test", "group account");

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
    void createGroupAccountTest() {

        //Arrange :
        Person person1 = new Person("João", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test group");

        //Act :
        group1.addMember(person1);
        boolean result = group1.createGroupAccount("Account1", "Test");

        //Assert :
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a group can create multiple group accounts - TRUE")
    void createGroupAccountsTest() {

        //Arrange
        Person person1 = new Person("João", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test group");

        //Act
        group1.addMember(person1);
        boolean addGroupAccount1 = group1.createGroupAccount("Account1", "Test");
        boolean addGroupAccount2 = group1.createGroupAccount("Account2", "Test");
        boolean addGroupAccount3 = group1.createGroupAccount("Account3", "Test");

        //Assert
        assertTrue(addGroupAccount1 && addGroupAccount2 && addGroupAccount3);
    }

    /*@Test
    @DisplayName("Test if a person that is not a group admin can create a group account - False")
    void createGroupAccountFalse() {

        //Arrange :
        Person person1 = new Person("João", 1994, 11, 13, new Address("Porto"));
        Person person2 = new Person("Francisca", 12, 3, 15, new Address("Lisboa"));
        Group group1 = new Group("Test group");

        //Act :
        group1.addMember(person1);
        group1.addMember(person2);
        boolean addGroupAccount = group1.createGroupAccount("Account1", "Test");

        //Assert:
        assertFalse(addGroupAccount);
    }*/

    /*@Test
    @DisplayName("Test if a regular member can add a Group Account")
    void createGroupAccountRegularMemberFalse() {

        //Arrange:
        Person person1 = new Person("João", 1994, 11, 13, new Address("Porto"));
        Person person2 = new Person("Francisca", 12, 3, 15, new Address("Lisboa"));
        Group group1 = new Group("Test group");

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        boolean canARegularMemberAddGroupAccount = group1.createGroupAccount("Account1", "Test");


        //Assert
        assertFalse(canARegularMemberAddGroupAccount);
    }*/

    @Test
    @DisplayName("Test if method works while the group description is null")
    void createGroupAccountOnNullGroup() {

        //Arrange:
        Person person1 = new Person("João", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group(null);

        //Act:
        boolean canAnAccountBeAddedToNullGroup = group1.addMember(person1)
                && group1.createGroupAccount("Account1", "Test");

        //Assert:
        assertFalse(canAnAccountBeAddedToNullGroup);
    }

    @Test
    @DisplayName("Create Account with null Denomination")
    void canAccountWithNullDescriptionBeCreated() {

        //Arrange:
        Person person1 = new Person("Francisca", LocalDate.of(2000,12,12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group");

        //Act:
        group1.addMember(person1);
        boolean canNullAccountBeAdded = group1.createGroupAccount(null, "User Story 7");

        //Assert:
        assertFalse(canNullAccountBeAdded);
    }

    @Test
    @DisplayName("Test if an Account with the same Account Denomination is added to the list")
    void createGroupAccountSameDescriptionFalse() {

        //Arrange :
        Person person1 = new Person("João", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("test group");

        //Act:
        group1.addMember(person1);
        group1.createGroupAccount("Account1", "Test");
        boolean addGroupAccountRepeated = group1.createGroupAccount("Account1", "Test");

        //Assert
        assertFalse(addGroupAccountRepeated);
    }

    @Test
    @DisplayName("Test if Method cant create two accounts with the same Account Denomination, but different letter casing.")
    void createGroupAccountSameDescriptionIgnoreCasing() {
        //Arrange:
        Person person1 = new Person("João", LocalDate.of(2000,12,12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test group");

        //Act:
        group1.addMember(person1);
        group1.createGroupAccount("Account1", "Test");
        boolean addGroupAccountRepeated = group1.createGroupAccount("AcCouNT1", "Test");

        //Assert:
        assertFalse(addGroupAccountRepeated);
    }

    @Test
    @DisplayName("Test if an admin of many groups can add an account to all of them")
    void createGroupAccountsOnMultipleGroups() {

        //Arrange:
        Person person1 = new Person("Francisca", LocalDate.of(2000,12,12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group");
        Group group2 = new Group("Test Group 2");
        Group group3 = new Group("Test Group 3");

        //Act:
        group1.addMember(person1);
        group2.addMember(person1);
        group3.addMember(person1);
        boolean isGroup1AccountCreated = group1.createGroupAccount("Account1", "User Story 7");
        boolean isGroup2AccountCreated = group2.createGroupAccount("Account2", "User Story 7");
        boolean isGroup3AccountCreated = group3.createGroupAccount("Account3", "User Story 7");

        //Assert
        assertTrue(isGroup1AccountCreated && isGroup2AccountCreated && isGroup3AccountCreated);
    }

    /**
     * Check if a Category was added to the groups Category list
     */

    @Test
    @DisplayName("Check if a category was added to Category List - Main Scenario")
    void addCategoryToListMainScenario() {
        //Arrange
        //Initialize Group

        Group group1 = new Group("Grupo dos amigos");

        //Act
        boolean realResult = group1.createAndAddCategoryToCategoryList("School expenses");

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Check if null category is not added")
    void addCategoryToListWithANullCase() {
        //Arrange

        //Initialize Group
        Group group1 = new Group("Grupo dos amigos");

        //Act
        boolean realResult = group1.createAndAddCategoryToCategoryList(null);

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

        //Act
        boolean categoryAdded = group1.createAndAddCategoryToCategoryList("Cinema");
        boolean sameCategoryAdded = group1.createAndAddCategoryToCategoryList("Cinema");
        boolean isSameCategoryAdded = categoryAdded && sameCategoryAdded;

        //Assert
        assertFalse(isSameCategoryAdded);

    }


    /**
     * Check if a Group is the same as another with Hashcode
     */

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
        boolean realResult = group1.createAndAddCategoryToCategoryList("School expenses") && !group1.createAndAddCategoryToCategoryList("SCHOóL expenses");

        //Assert
        assertTrue(realResult);

    }

    @Test
    @DisplayName("test if two Groups are the same")
    public void testIfTwoGroupsAreTheSameHashcode() {
        //Arrange

        Group group1 = new Group("Talho do Amadeu");
        Group group2 = new Group("Talho do Amadeu");

        //Act
        int g1 = group1.hashCode();
        int g2 = group2.hashCode();


        //Assert
        assertEquals(g1, g2);
    }

    @Test
    @DisplayName("test if two Groups are the same")
    public void testIfTwoGroupsAreTheSameHashcodeFalse() {
        //Arrange

        Group group1 = new Group("Talho do Amadeu");
        Group group2 = new Group("Talho do João");

        //Act
        int g1 = group1.hashCode();
        int g2 = group2.hashCode();


        //Assert
        assertNotEquals(g1, g2);
    }

    /**
     * Check if a Category was removed from the groups Category list
     */

    @Test
    @DisplayName("Remove categories from User Category List - Main Scenario")
    void removeCategoryFromListMainScenario() {
        //Arrange

        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        //Act
        group1.createAndAddCategoryToCategoryList("Jantares de Grupo");
        group1.createAndAddCategoryToCategoryList("filmes");
        boolean realResult = group1.removeCategoryFromList("filmes");

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("To Try to remove a set of Categories that does not exist or null")
    void removeCategoriesToListWithANullCase() {
        //Arrange

        //Initialize Group
        Group group1 = new Group("Groupo dos amigos");

        group1.createAndAddCategoryToCategoryList("Jantares de Grupo");
        group1.createAndAddCategoryToCategoryList(null);

        //Act
        boolean realResult = group1.removeCategoryFromList(null);

        //Assert
        assertFalse(realResult);
    }

    @Test
    @DisplayName("Remove a Category from user's Category List - Ignore letter capitalization and special characters")
    void removeCategoryFromListIgnoreLettersFormatAndSpecialCase() {
        //Arrange
        //Initialize group
        Group group1 = new Group("Groupo dos amigos");

        //Act
        boolean result = group1.createAndAddCategoryToCategoryList("SCHOóL expenses");

        //Assert
        assertTrue(result);
    }

}





