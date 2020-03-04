package switch2019.project.model.group;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.group.Group;
import switch2019.project.model.ledger.Transaction;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;
import switch2019.project.model.valueObject.Address;
import switch2019.project.model.valueObject.MonetaryValue;
import switch2019.project.repository.GroupsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    /**
     *
     * Compare the same Group - Should be the Same
     */

    @Test
    @DisplayName("Compare the same Group - Should be the Same")
    public void compareTheSameObject() {

        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Washington D.C."),
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Washington D.C."),
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Mary", LocalDate.of(1995, 12, 13), new Address("Detroit"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Vasylia", LocalDate.of(1995, 12, 13), new Address("California"),
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Washington D.C."),
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Washington D.C."),
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
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Washington D.C."),
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
     * Check if a group and another object are diferent
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
     * US002 - check if group was added to groupList
     * Methods to check if the number of groups in the GroupList is increased
     */

    @Test
    @DisplayName("Check if One group was added")
    public void wasGroupAddedToList() {

        //Arrange
        Person person1 = new Person("John", LocalDate.of(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Frank", LocalDate.of(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Amigos");
        GroupsRepository groupList1 = new GroupsRepository();

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
        GroupsRepository groupList1 = new GroupsRepository();

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
        GroupsRepository groupList1 = new GroupsRepository();

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
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Mariana", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
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
        Person person1 = new Person("Juan", LocalDate.of(1995, 12, 13), new Address("Toledo"),
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
        Person person1 = new Person("Juan", LocalDate.of(1995, 12, 13), new Address("Toledo"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Pablo", LocalDate.of(1995, 12, 13), new Address("Madrid"),
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

        Person person1 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Mariana", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Marisa", LocalDate.of(1995, 12, 13), new Address("Leiria"),
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

        Person person1 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"),
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

        Person person1 = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person personAdmin = new Person("António", LocalDate.of(1995, 12, 13), new Address("Guimarães"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("João", LocalDate.of(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"),
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

        Person personAdmin = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("João", LocalDate.of(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"),
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

        Person person1 = new Person("João", LocalDate.of(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Diana", LocalDate.of(1995, 12, 13), new Address("Porto"),
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

        Person person1 = new Person("João", LocalDate.of(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Mariana", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Diana", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person personAdmin = new Person("Maria", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("João", LocalDate.of(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"),
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

        Person person1 = new Person("João", LocalDate.of(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Gabriel", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person personAdmin = new Person("Catarina", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("João", LocalDate.of(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Gabriel", LocalDate.of(1995, 12, 13), new Address("Porto"),
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

        Person person1 = new Person("João", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Gabriel", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Oscar", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Gabriel", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Oscar", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Gabriel", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Oscar", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Gabriel", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Laurinda", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Oscar", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Joao", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Manuela", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person5 = new Person("Carlos", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Oscar", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Joao", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Manuela", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person5 = new Person("Carlos", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person6 = new Person("Diana", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Oscar", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Joao", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Manuela", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person5 = new Person("Carlos", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Oscar", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Joao", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Manuela", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person5 = new Person("Carlos", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
    void isPersonAddedAsMemberAndAdmin() {

        //Arrange:
        Person person1 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person groupAdmin = new Person("João", LocalDate.of(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group");

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
        Person groupAdmin = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = null;
        Group group1 = new Group("Test Group");

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
        Person person1 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person groupAdmin = new Person("João", LocalDate.of(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test Group");

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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Albert", LocalDate.of(1995, 12, 13), new Address("Bristol"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
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
        Person personAdmin = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person personAdmin = new Person("Marta", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Pedro", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Elsa", LocalDate.of(1995, 12, 13), new Address("Porto"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Vladimir", LocalDate.of(1995, 12, 13), new Address("Moscow"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
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
        Person person1 = new Person("Francis", LocalDate.of(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("John", LocalDate.of(1995, 12, 13), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Susan", LocalDate.of(1995, 12, 13), new Address("Edinburgh"),
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
        Person person1 = new Person("Francis", LocalDate.of(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(2000, 12, 12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("John", LocalDate.of(2000, 12, 12), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Susan", LocalDate.of(2000, 12, 12), new Address("Edinburgh"),
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
        Person person1 = new Person("Francis", LocalDate.of(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(2000, 12, 12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("John", LocalDate.of(2000, 12, 12), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Susan", LocalDate.of(2000, 12, 12), new Address("Edinburgh"),
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
        Person person1 = new Person("Francis", LocalDate.of(2000, 12, 12), new Address("London"),
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
        Person person1 = new Person("Francis", LocalDate.of(2000, 12, 12), new Address("London"),
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
        Person personAdmin = new Person("Marta", LocalDate.of(2000, 12, 12), new Address("Guimarães"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Francis", LocalDate.of(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Jaques", LocalDate.of(2000, 12, 12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Pedro", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person4 = new Person("Elsa", LocalDate.of(2000, 12, 12), new Address("Porto"),
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
        Person person1 = new Person("Alexandre", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Maria", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
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
        Person person1 = new Person("Alexandre", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Maria", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
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
     * Test if a person is a Group Member
     */
    @DisplayName("Check if a person is in the Group Member List")
    @Test
    void isGroupMember() {
        //Arrange:
        Person person1 = new Person("Alexandre", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Maria", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Group group1 = new Group("Maria's Group");
        group1.addMember(person3);

        //Act
        boolean isMember = group1.isGroupMember(person3);

        //Assert
        assertTrue(isMember);
    }

    @DisplayName("Check if a person is not in the Group Member List")
    @Test
    void isGroupMemberFalse() {
        //Arrange:
        Person person1 = new Person("Alexandre", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Person person2 = new Person("Elsa", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Person person3 = new Person("Maria", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), person2, person3);
        Group group1 = new Group("Maria's Group");
        group1.addMember(person3);

        //Act
        boolean isMember = group1.isGroupMember(person4);

        //Assert
        assertFalse(isMember);
    }

    @DisplayName("Check if a person null can be a Group Member")
    @Test
    void isGroupMemberNull() {
        //Arrange:
        Person person1 = null;
        Group group1 = new Group("Maria's Group");
        group1.addMember(person1);

        //Act
        boolean isMember = group1.isGroupMember(person1);

        //Assert
        assertFalse(isMember);
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
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test group");
        group1.addMember(person1);

        //Act
        boolean result = false;
        if (group1.isGroupAdmin(person1)) {
            result = group1.createGroupAccount("Account1", "Test");
        }

        //Assert :
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a group can create multiple group accounts - TRUE")
    void createGroupAccountsTest() {

        //Arrange
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test group");
        group1.addMember(person1);

        //Act
        boolean addGroupAccount1 = false;
        boolean addGroupAccount2 = false;
        boolean addGroupAccount3 = false;
        if (group1.isGroupAdmin(person1)) {
            addGroupAccount1 = group1.createGroupAccount("Account1", "Test");
            addGroupAccount2 = group1.createGroupAccount("Account2", "Test");
            addGroupAccount3 = group1.createGroupAccount("Account3", "Test");
        }

        //Assert
        assertTrue(addGroupAccount1 && addGroupAccount2 && addGroupAccount3);
    }

    @Test
    @DisplayName("Test if a person that is not in a group can create a group account - False")
    void createGroupAccountFalse() {

        //Arrange :
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Group group1 = new Group("Test group");
        group1.addMember(person3);

        //Act :
        boolean addGroupAccount = false;
        if (group1.isGroupAdmin(person4)) {
            addGroupAccount = group1.createGroupAccount("Account1", "Test");
        }

        //Assert:
        assertFalse(addGroupAccount);
    }

    @Test
    @DisplayName("Test if a regular member can add a Group Account")
    void createGroupAccountRegularMemberFalse() {

        //Arrange:
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person1, person2);
        Person person4 = new Person("Alexandre", LocalDate.of(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"));
        Group group1 = new Group("Test group");
        group1.addMember(person3);
        group1.addMember(person4);

        //Act
        boolean canARegularMemberAddGroupAccount = false;
        if (group1.isGroupAdmin(person4)) {
            canARegularMemberAddGroupAccount = group1.createGroupAccount("Account1", "Test");
        }

        //Assert
        assertFalse(canARegularMemberAddGroupAccount);
    }

    @Test
    @DisplayName("Test if method works while the group description is null")
    void createGroupAccountOnNullGroup() {

        //Arrange:
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
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
        Person person1 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
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
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("test group");

        //Act:
        group1.addMember(person1);
        group1.createGroupAccount("Account1", "Test");
        boolean addGroupAccountRepeated = group1.createGroupAccount("Account1", "Test");

        //Assert
        //assertFalse(addGroupAccountRepeated);
    }

    @Test
    @DisplayName("Test if Method cant create two accounts with the same Account Denomination, but different letter casing.")
    void createGroupAccountSameDescriptionIgnoreCasing() {
        //Arrange:
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group group1 = new Group("Test group");

        //Act:
        group1.addMember(person1);
        group1.createGroupAccount("Account1", "Test");
        boolean addGroupAccountRepeated = group1.createGroupAccount("AcCouNT1", "Test");

        //Assert:
        //assertFalse(addGroupAccountRepeated);
    }

    @Test
    @DisplayName("Test if an admin of many groups can add an account to all of them")
    void createGroupAccountsOnMultipleGroups() {

        //Arrange:
        Person person1 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
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
     * Check if a transaction is inside a groups Ledger
     */
    @Test
    @DisplayName("isTransactionInsideGroupLedger - True (Happy Case")
    void isTransactionInsideGroupLedgerTrue() {

        //Arrange:
        Group group1 = new Group("Grupo de Jantares");

        // Arrange Transaction:
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 31, 13, 02);
        boolean oneType = true; //Credit
        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Category category1 = new Category("ASD");
        Transaction transaction1 = new Transaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, oneType);

        //Act:
        group1.createGroupTransaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, oneType);
        boolean result = group1.isTransactionInsideTheGroupLedger(transaction1);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("isTransactionInsideGroupLedger - False")
    void isTransactonInsideGroupLedgerFalse() {

        //Arrange:
        Group group1 = new Group("Grupo de Jantares");

        // Arrange Transaction:
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 31, 13, 02);
        boolean creditType = true; //Credit
        boolean debitType = false; //Debit
        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Category category1 = new Category("ASD");
        Transaction transaction1 = new Transaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, creditType);

        //Act:
        group1.createGroupTransaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, debitType);
        boolean result = group1.isTransactionInsideTheGroupLedger(transaction1);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("is Transaction Inside the Ledger - Two transactions (True)")
    void areTwoTransactionsInsideTheLedger() {

        //Arrange:
        Group group1 = new Group("Grupo de Jantares");

        // Arrange Transaction:
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 31, 13, 02);
        boolean creditType = true; //Credit
        boolean debitType = false; //Debit
        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Category category1 = new Category("ASD");
        Transaction transaction1 = new Transaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, debitType);
        Transaction transaction2 = new Transaction(monetaryValue, "Test Transaction2", date1, category1, oneAccount, otherAccount, creditType);

        //Act:
        group1.createGroupTransaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, debitType);
        group1.createGroupTransaction(monetaryValue, "Test Transaction2", date1, category1, oneAccount, otherAccount, creditType);

        boolean result = group1.isTransactionInsideTheGroupLedger(transaction1)
                && group1.isTransactionInsideTheGroupLedger(transaction2);

        //Assert:
        assertTrue(result);
    }

    /**
     * Are multiple transactions inside the GroupLedger
     */

    @Test
    @DisplayName("Verify if multiple transactions are inside the GroupLedger - Happy Case")
    void areMultipleTransactionsInsideTheGroupLedgerTrue() {
        //Arrange:
        Group testGroup = new Group("test group");

        //Transactions arrangement:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        //Transactions added to Group Ledger:
        testGroup.createGroupTransaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, true);
        testGroup.createGroupTransaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, false);
        testGroup.createGroupTransaction(monetaryValue3, "testTransaction3", null, category1, account1, account2, true);

        //Transactions to check:
        Transaction testTransaction1 = new Transaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, true);
        Transaction testTransaction2 = new Transaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, false);
        Transaction testTransaction3 = new Transaction(monetaryValue3, "testTransaction3", null, category1, account1, account2, true);

        Set transactionsToCheck = new HashSet<Transaction>();
        transactionsToCheck.add(testTransaction1);
        transactionsToCheck.add(testTransaction2);
        transactionsToCheck.add(testTransaction3);

        //Act:
        boolean result = testGroup.areMultipleTransactionsInsideTheGroupLedger(transactionsToCheck);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Verify if multiple transactions are inside the GroupLedger - null transaction")
    void areMultipleTransactionsInsideTheGroupLedgerTestNull() {
        //Arrange:
        Group testGroup = new Group("test group");

        //Transactions arrangement:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        //Transactions added to Group Ledger:
        testGroup.createGroupTransaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, true);
        testGroup.createGroupTransaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, false);
        testGroup.createGroupTransaction(monetaryValue3, "testTransaction3", null, category1, account1, account2, true);

        //Transactions to check:
        Transaction testTransaction1 = new Transaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, true);
        Transaction testTransaction2 = new Transaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, false);
        Transaction testTransaction3 = null;

        Set transactionsToCheck = new HashSet<Transaction>();
        transactionsToCheck.add(testTransaction1);
        transactionsToCheck.add(testTransaction2);
        transactionsToCheck.add(testTransaction3);

        //Act:
        try {
            testGroup.areMultipleTransactionsInsideTheGroupLedger(transactionsToCheck);
        }
        //Assert:
        catch (IllegalArgumentException nullTransaction) {
            assertEquals("One (or more) of the transactions is null.",nullTransaction.getMessage());
        }
    }

    @Test
    @DisplayName("Verify if multiple transactions are inside the GroupLedger")
    void areMultipleTransactionsInsideTheGroupLedgerTest1() {
        //Arrange:
        Group testGroup = new Group("test group");

        //Transactions arrangement:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        //Transactions added to Group Ledger:
        testGroup.createGroupTransaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, true);
        testGroup.createGroupTransaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, false);

        //Transactions to check:
        Transaction testTransaction1 = new Transaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, true);
        Transaction testTransaction2 = new Transaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, false);
        Transaction testTransaction3 = new Transaction(monetaryValue3, "testTransaction3", null, category1, account1, account2, true);

        Set transactionsToCheck = new HashSet<Transaction>();
        transactionsToCheck.add(testTransaction1);
        transactionsToCheck.add(testTransaction2);
        transactionsToCheck.add(testTransaction3);

        //Act:
        boolean result = testGroup.areMultipleTransactionsInsideTheGroupLedger(transactionsToCheck);

        //Assert:
        assertFalse(result);
    }

    /**
     * Check if a Admin createad and added Category to the groups Category list (US05.1)
     */

    @Test
    @DisplayName("Check if a category was added to Category List by admin- Main Scenario")
    void addCategoryToListMainScenario() {
        //Arrange
        Group group1 = new Group("Grupo dos amigos");
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        group1.addMember(person1);


        //Act
        boolean realResult = group1.createAndAddCategoryToCategoryList("School expenses", person1);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Check if Admin created and added category  - False Scenario")
    void addCategoryToListFalseScenario() {
        //Arrange

        Group group1 = new Group("Grupo dos amigos");
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Filipa", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));

        group1.addMember(person1);

        //Act
        boolean realResult = group1.createAndAddCategoryToCategoryList("School expenses", person2);

        //Assert
        assertFalse(realResult);
    }

    @Test
    @DisplayName("Check if null category is not added by the admin")
    void addCategoryToListWithANullCase() {
        //Arrange
        //Initialize Group
        Group group1 = new Group("Grupo dos amigos");
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        group1.addMember(person1);

        //Act
        boolean realResult = group1.createAndAddCategoryToCategoryList(null, person1);

        //Assert
        assertFalse(realResult);

    }

    @Test
    @DisplayName("Check if the same Category is not added simultaneously by the admin")
    void addTwoCategoriesToListWithTwoCategoriesThatAreTheSame() {
        //Arrange

        Group group1 = new Group("Groupo dos amigos");
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        group1.addMember(person1);
        //Categories to be included in Category List

        //Act
        boolean categoryAdded = group1.createAndAddCategoryToCategoryList("Cinema", person1);
        boolean sameCategoryAdded = group1.createAndAddCategoryToCategoryList("Cinema", person1);
        boolean isSameCategoryAdded = categoryAdded && sameCategoryAdded;

        //Assert
        assertFalse(isSameCategoryAdded);

    }

    @Test
    @DisplayName("Check if the same Category is not added simultaneously - Ignore letter capitalization and special characters ")
    void addTwoCategoriesToListWithTwoCategoriesCaseInsensitive() {
        //Arrange
        Group group1 = new Group("Groupo dos amigos");
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        group1.addMember(person1);
        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("SCHOóL expenses");

        //Act
        boolean realResult = group1.createAndAddCategoryToCategoryList("School expenses", person1) && !group1.createAndAddCategoryToCategoryList("SCHOóL expenses", person1);

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
    @DisplayName("Remove category from GroupList - Happy Case(True)")
    void isCategoryRemovedFromCategoryList() {

        //Arrange:
        Person groupAdmin = new Person("Francisco", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group testGroup = new Group("Test group");
        testGroup.addMember(groupAdmin);
        testGroup.createAndAddCategoryToCategoryList("groceries", groupAdmin);

        //Act:
        boolean result = testGroup.removeCategoryFromList("groceries", groupAdmin);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Remove category from GroupList - False - person trying to remove is not an admin)")
    void isCategoryRemovedFromCategoryListNotAdmin() {

        //Arrange:
        Person groupAdmin = new Person("Francisco", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person groupMember = new Person("João", LocalDate.of(1991, 12, 11), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group testGroup = new Group("Test group");
        testGroup.addMember(groupAdmin);
        testGroup.addMember(groupMember);
        testGroup.createAndAddCategoryToCategoryList("groceries", groupAdmin);

        //Act:
        boolean result = testGroup.removeCategoryFromList("groceries", groupMember);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Remove category from GroupList - False - person trying to remove is null)")
    void isCategoryRemovedFromCategoryListNullAdmin() {

        //Arrange:
        Person groupAdmin = null;
        Group testGroup = new Group("Test group");
        testGroup.addMember(groupAdmin);
        testGroup.createAndAddCategoryToCategoryList("groceries", groupAdmin);

        //Act:
        boolean result = testGroup.removeCategoryFromList("groceries", groupAdmin);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Remove category from GroupList - False - category to remove is null)")
    void isCategoryRemovedFromCategoryListNullCategory() {

        //Arrange:
        Person groupAdmin = new Person("Francisco", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Group testGroup = new Group("Test group");
        testGroup.addMember(groupAdmin);
        testGroup.createAndAddCategoryToCategoryList(null, groupAdmin);

        //Act:
        boolean result = testGroup.removeCategoryFromList(null, groupAdmin);

        //Assert:
        assertFalse(result);
    }


    /**
     * US010 Como membro de grupo, quero obter os movimentos de determinada conta num dado período.
     */

    @Test
    @DisplayName("Obtain transactions from an account - case of success")
    void obtainTransactionsFromAnAccount() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);


        //Assert
        assertEquals(expectedTransactions,listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - dates change")
    void obtainTransactionsFromAnAccountDateChange() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);


        //Assert
        assertEquals(expectedTransactions,listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - same day")
    void obtainTransactionsFromAnAccountSameDay() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 14, 00, 00);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 14, 23, 59);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);


        //Assert
        assertEquals(expectedTransactions,listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - check before the creation of the ledger")
    void obtainTransactionsFromAnAccountBeforeLedger() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2017, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2018, 1, 31, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList());


        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);


        //Assert
        assertEquals(expectedTransactions,listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - first date null")
    void obtainTransactionsFromAnAccountFirstDateNull() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = null;
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));


        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);
        }

        //Assert
        catch (IllegalArgumentException initialDate) {
            assertEquals("The dates can´t be null", initialDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - second date null")
    void obtainTransactionsFromAnAccountSecondDateNull() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = null;


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);
        }

        //Assert
        catch (IllegalArgumentException finalDate) {
            assertEquals("The dates can´t be null", finalDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - first date is after now")
    void obtainTransactionsFromAnAccountFirstDateNotValide() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 2, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);
        }

        //Assert
        catch (IllegalArgumentException initialDate) {
            assertEquals("One of the submitted dates is not valid.", initialDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - final date is after now")
    void obtainTransactionsFromAnAccountFinalDateNotValide() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 2, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);
        }

        //Assert
        catch (IllegalArgumentException finalDate) {
            assertEquals("One of the submitted dates is not valid.", finalDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - empty ledger exception")
    void obtainTransactionsFromAnAccountEmptyLedger() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);

        Account account5 = new Account("comida de gato", "comida para a gatinha");


        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        group1.addMember(person1);


        //Act
        try {
            group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);
        }

        //Assert
        catch (IllegalArgumentException ledger) {
            assertEquals("The ledger is empty. ", ledger.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - member is not a member")
    void obtainTransactionsFromAnAccountNotMember() {
        //Arrange
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act:
        try {
            group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);
        }
        //Assert:
        catch (IllegalArgumentException result) {
            assertEquals("You don't have access to that account.", result.getMessage());
        }
    }

    /**
     * Test if a group transaction was created
     */
    @Test
    @DisplayName("Test if a group transaction was created - success case")
    void createGroupTransactionSuccessCase() {
        //Arrange
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Group group1 = new Group("Test group");
        group1.addMember(person3);
        group1.addMember(person4);

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");

        if (group1.isGroupAdmin(person3)) {
            group1.createGroupAccount("Wallet", "General expenses");
            group1.createGroupAccount("TransportAccount", "Transport expenses");
        }

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));

        String description = "payment";

        Category category = new Category("General");
        person4.createCategoryAndAddToCategoryList("General");

        boolean type = false; //debit

        //Act
        boolean transactionCreated = false;
        if (group1.isGroupMember(person4)) {
            transactionCreated = group1.createGroupTransaction(amount, description, null, category, from, to, type);
        }

        //Assert
        assertTrue(transactionCreated);
    }

    @Test
    @DisplayName("Test if a group transaction was created - monetary value is negative")
    void createGroupTransactionAccountNegativeMonetaryValue() {
        //Arrange
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Group group1 = new Group("Test group");
        group1.addMember(person3);
        group1.addMember(person4);

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");

        if (group1.isGroupAdmin(person3)) {
            group1.createGroupAccount("Wallet", "General expenses");
            group1.createGroupAccount("TransportAccount", "Transport expenses");
        }

        MonetaryValue amountNegative = new MonetaryValue(-50, Currency.getInstance("EUR"));
        String description1 = "payment";

        Category category = new Category("General");
        person4.createCategoryAndAddToCategoryList("General");

        boolean type = false; //debit

        //Act
        try {
            if (group1.isGroupMember(person4)) {
                group1.createGroupTransaction(amountNegative, description1, null, category, from, to, type);
            }
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value can´t be null or negative. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if a transaction was created - person is not a member of the group")
    void createGroupTransactionNotMember() {
        //Arrange
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Group group1 = new Group("Test group");
        group1.addMember(person3);

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");

        if (group1.isGroupAdmin(person3)) {
            group1.createGroupAccount("Wallet", "General expenses");
            group1.createGroupAccount("TransportAccount", "Transport expenses");
        }

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));

        String description = "payment";

        Category category = new Category("General");
        person4.createCategoryAndAddToCategoryList("General");

        boolean type = false; //debit

        //Act
        boolean transactionCreated = false;
        if (group1.isGroupMember(person4)) {
            transactionCreated = group1.createGroupTransaction(amount, description, null, category, from, to, type);
        }

        //Assert
        assertFalse(transactionCreated);
    }

    /**
     * US012 - Como utilizador membro de grupo, quero obter os movimentos do grupo num dado período.
     */

    @Test
    @DisplayName("Get Group Ledger Transactions in a given period - Success Case")
    void getLedgerTransactionsInPeriod() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));


        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Group Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);

        List<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction2, expectedTransaction1));
        //Act
        List<Transaction> real = oneGroup.returnGroupLedgerInDateRange(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2019, 2, 3, 10, 40), onePerson);
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - EmptyLedger")
    void getLedgerTransactionsInPeriodEmptyLedger() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        ArrayList<Transaction> expected = new ArrayList<>();

        //Act
        List<Transaction> real = oneGroup.returnGroupLedgerInDateRange(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2017, 12, 3, 10, 40), onePerson);
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Same date")
    void getLedgerTransactionsInPeriodSameDay() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);

        List<Transaction> expected = new ArrayList<>(Collections.singletonList(expectedTransaction1));
        //Act
        List<Transaction> real = oneGroup.returnGroupLedgerInDateRange(LocalDateTime.of(2018, 10, 2, 9, 10),
                LocalDateTime.of(2018, 10, 2, 9, 10), onePerson);
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - No transactions on the given date")
    void getLedgerTransactionsInPeriodNoTransactions() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account anotherAccount = new Account("abc", "abc Account");


        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Expected Transactions
        List<Transaction> expected = new ArrayList<>();
        //Act
        List<Transaction> real = oneGroup.returnGroupLedgerInDateRange(LocalDateTime.of(2012, 10, 2, 9, 10),
                LocalDateTime.of(2013, 10, 2, 9, 10), onePerson);
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initital Date > Final Date")
    void getLedgerTransactionsInPeriodFalse() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);

        List<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction2, expectedTransaction1));
        //Act
        List<Transaction> real = oneGroup.returnGroupLedgerInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40),
                LocalDateTime.of(2017, 10, 2, 9, 20), onePerson);

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initital Date > Actual Date")
    void getLedgerTransactionsInPeriodInitialDateSuperiorActualDate() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            oneGroup.returnGroupLedgerInDateRange(LocalDateTime.of(2025, 2, 3, 10, 40),
                    LocalDateTime.of(2017, 10, 2, 9, 20), onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("One of the submitted dates is not valid.", getTransactionsFromPeriod.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Final  Date > Actual Date")
    void getLedgerTransactionsInPeriodFinalDateSuperiorActualDate() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            oneGroup.returnGroupLedgerInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40),
                    LocalDateTime.of(2030, 10, 2, 9, 20), onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("One of the submitted dates is not valid.", getTransactionsFromPeriod.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initial Date null")
    void getLedgerTransactionsInPeriodInitialDateNull() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            oneGroup.returnGroupLedgerInDateRange(null, LocalDateTime.of(2030, 10, 2, 9, 20), onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("The dates can´t be null", getTransactionsFromPeriod.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Final Date null")
    void getLedgerTransactionsInPeriodFinalDateNull() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            oneGroup.returnGroupLedgerInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40), null, onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("The dates can´t be null", getTransactionsFromPeriod.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Person is not admin")
    void getLedgerTransactionsInPeriodPersonNotAdmin() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            oneGroup.returnGroupLedgerInDateRange(null, LocalDateTime.of(2030, 10, 2, 9, 20), onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("Person is not a member of the group.", getTransactionsFromPeriod.getMessage());
        }
    }

    /**
     * getGroupBalanceInDateRange Tests - Check Group balance between two dates
     */

    @Test
    @DisplayName("Check Group balance between two dates - Happy Case (True)")
    void getGroupBalanceInDateRangeTrue() {

        // Arrange:
        //Transaction attributes:
        MonetaryValue monetaryValueOne = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueTwo = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueThree = new MonetaryValue(125, Currency.getInstance("EUR"));

        LocalDateTime localDateOne = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime localDateTwo = LocalDateTime.of(2018, 11, 23, 9, 10);
        LocalDateTime localDateThree = LocalDateTime.of(2019, 10, 2, 9, 10);

        Category categoryOne = new Category("food");
        Category categoryTwo = new Category("movie");
        Category categoryThree = new Category("groceries");

        Account accountOne = new Account("myxpto", "xpto Account");
        Account accountTwo = new Account("xyz", "xyz Account");

        boolean typeOne = true;  // Credit
        boolean typeTwo = false; // Debit

        //Group instanced:
        Group group1 = new Group("Test Group");

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, typeTwo);

        //Act:
        double result = group1.getGroupBalanceInDateRange(LocalDateTime.of(2018, 01, 01, 0, 0),
                LocalDateTime.of(2019, 01, 01, 0, 0));

        double expected = 450;

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check Group balance between two dates - Happy Case (True) - Reorder Dates")
    void getGroupBalanceInDateRangeTrueReorderDates() {
        // Arrange:
        //Transaction attributes:
        MonetaryValue monetaryValueOne = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueTwo = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueThree = new MonetaryValue(125, Currency.getInstance("EUR"));

        LocalDateTime localDateOne = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime localDateTwo = LocalDateTime.of(2018, 11, 23, 9, 10);
        LocalDateTime localDateThree = LocalDateTime.of(2019, 10, 2, 9, 10);

        Category categoryOne = new Category("food");
        Category categoryTwo = new Category("movie");
        Category categoryThree = new Category("groceries");

        Account accountOne = new Account("myxpto", "xpto Account");
        Account accountTwo = new Account("xyz", "xyz Account");

        boolean typeOne = true;  // Credit
        boolean typeTwo = false; // Debit

        //Group instanced:
        Group group1 = new Group("Test Group");

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, typeTwo);

        //Act:
        double result = group1.getGroupBalanceInDateRange(LocalDateTime.of(2019, 01, 01, 0, 0),
                LocalDateTime.of(2018, 01, 01, 0, 0));

        double expected = 450;

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check Group balance between two dates - Happy Case (True) - One hour interval")
    void getGroupBalanceInDateRangeTrueOneHour() {

        // Arrange:
        //Transaction attributes:
        MonetaryValue monetaryValueOne = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueTwo = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueThree = new MonetaryValue(125, Currency.getInstance("EUR"));

        LocalDateTime localDateOne = LocalDateTime.of(2018, 10, 23, 9, 10);
        LocalDateTime localDateTwo = LocalDateTime.of(2018, 10, 23, 9, 20);
        LocalDateTime localDateThree = LocalDateTime.of(2018, 10, 23, 9, 40);

        Category categoryOne = new Category("food");
        Category categoryTwo = new Category("movie");
        Category categoryThree = new Category("groceries");

        Account accountOne = new Account("myxpto", "xpto Account");
        Account accountTwo = new Account("xyz", "xyz Account");

        boolean typeOne = true;  // Credit
        boolean typeTwo = false; // Debit

        //Group instanced:
        Group group1 = new Group("Test Group");

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, typeTwo);

        //Act:
        double result = group1.getGroupBalanceInDateRange(LocalDateTime.of(2018, 10, 23, 9, 0),
                LocalDateTime.of(2019, 10, 23, 10, 0));

        double expected = 325;

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check Group balance between two dates - Invalid Date (after current date")
    void getGroupBalanceInDateRangeExceptionInvalidDate() {
        // Arrange:
        //Transaction attributes:
        MonetaryValue monetaryValueOne = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueTwo = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueThree = new MonetaryValue(125, Currency.getInstance("EUR"));

        LocalDateTime localDateOne = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime localDateTwo = LocalDateTime.of(2018, 11, 23, 9, 10);
        LocalDateTime localDateThree = LocalDateTime.of(2019, 10, 2, 9, 10);

        Category categoryOne = new Category("food");
        Category categoryTwo = new Category("movie");
        Category categoryThree = new Category("groceries");

        Account accountOne = new Account("myxpto", "xpto Account");
        Account accountTwo = new Account("xyz", "xyz Account");

        boolean typeOne = true;  // Credit
        boolean typeTwo = false; // Debit

        //Group instanced:
        Group group1 = new Group("Test Group");

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, typeTwo);

        String expected = "One of the submitted dates is not valid.";

        //Act:
        try {
            double result = group1.getGroupBalanceInDateRange(LocalDateTime.of(2021, 03, 01, 0, 0),
                    LocalDateTime.of(2022, 01, 17, 0, 0));
        }
        //Assert:
        catch (IllegalArgumentException result) {
            assertEquals(expected, result.getMessage());
        }
    }

    @Test
    @DisplayName("Check Group balance between two dates - Happy Case (True) - No results for the date")
    void getGroupBalanceInDateRangeTrueNotFoundAny() {

        // Arrange:
        //Transaction attributes:
        MonetaryValue monetaryValueOne = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueTwo = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValueThree = new MonetaryValue(125, Currency.getInstance("EUR"));

        LocalDateTime localDateOne = LocalDateTime.of(2018, 10, 23, 9, 10);
        LocalDateTime localDateTwo = LocalDateTime.of(2018, 10, 23, 9, 20);
        LocalDateTime localDateThree = LocalDateTime.of(2018, 10, 23, 9, 40);

        Category categoryOne = new Category("food");
        Category categoryTwo = new Category("movie");
        Category categoryThree = new Category("groceries");

        Account accountOne = new Account("myxpto", "xpto Account");
        Account accountTwo = new Account("xyz", "xyz Account");

        boolean typeOne = true;  // Credit
        boolean typeTwo = false; // Debit

        //Group instanced:
        Group group1 = new Group("Test Group");

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, typeOne);
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, typeTwo);

        //Act:
        double result = group1.getGroupBalanceInDateRange(LocalDateTime.of(2019, 10, 23, 9, 0),
                LocalDateTime.of(2019, 10, 23, 10, 0));

        double expected = 0;

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check Group balance between two dates - empty ledger")
    void getGroupBalanceInDateRangeExceptionNoResults() {
        // Arrange:
        //Group instanced:
        Group group1 = new Group("Test Group");

        //Act:
        String expected = "The ledger is Empty.";
        try {
            double result = group1.getGroupBalanceInDateRange(LocalDateTime.of(2018, 03, 01, 0, 0),
                    LocalDateTime.of(2016, 01, 17, 0, 0));
        }
        //Assert:
        catch (IllegalArgumentException result) {
            assertEquals(expected, result.getMessage());
        }
    }

    @Test
    void scheduleNewTransactionDaily() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        Group group = new Group("tarzan");

        //Act
        boolean result = group.scheduleNewTransaction("daily", amount, description, null, category, from, to, type);

        Thread.sleep(2400); // 250 x 10 = 2500

        //Assert
        assertTrue(result && group.ledgerSize() == 10);
    }


    @Test
    void scheduleNewTransactionWorkingDays() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        Group group = new Group("tarzan");

        //Act
        boolean result = group.scheduleNewTransaction("working days", amount, description, null, category, from, to, type);

        Thread.sleep(1900); // 500 x 4 = 2000

        //Assert
        assertTrue(result && group.ledgerSize() == 4);
    }

    @Test
    void scheduleNewTransactionWeekly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        Group group = new Group("tarzan");

        //Act
        boolean result = group.scheduleNewTransaction("weekly", amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 750 x 4 = 3000

        //Assert
        assertTrue(result && group.ledgerSize() == 4);
    }


    @Test
    void scheduleNewTransactionMonthly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        Group group = new Group("tarzan");

        //Act
        boolean result = group.scheduleNewTransaction("monthly", amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 1000 x 3 = 3000

        //Assert
        assertTrue(result && group.ledgerSize() == 3);
    }

    @Test
    void scheduleNewTransactionNoMatch() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        Group group = new Group("tarzan");

        try {
            //Act
            group.scheduleNewTransaction("tomorrow", amount, description, null, category, from, to, type);
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }
    }
}






