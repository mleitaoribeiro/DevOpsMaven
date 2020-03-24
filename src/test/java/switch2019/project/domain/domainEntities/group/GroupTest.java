package switch2019.project.domain.domainEntities.group;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.ledger.Periodicity;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.infrastructure.repositories.GroupsRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    /**
     * Compare the same Group - Should be the Same
     */

    @Test
    @DisplayName("Compare the same Group - Should be the Same")
    public void compareTheSameObject() {

        //Arrange
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Amigos"));
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
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Amigos"));
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
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person3 = new Person("Mary", new DateAndTime(1995, 12, 13), new Address("Detroit"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));
        Person person4 = new Person("Vasylia", new DateAndTime(1995, 12, 13), new Address("California"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("0@isep.pt"));
        Group group1 = new Group(new Description("Amigos"));
        group1.addMember(person1);
        group1.addMember(person2);
        Group group2 = new Group(new Description("Amigos"));
        group2.addMember(person3);
        group2.addMember(person4);

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertTrue(result);
    }

    /**
     * Compare two groups with members but different description
     */
    @Test
    @DisplayName("Compare two groups with members but different description")
    public void compareGroupsWithSameMembersButDifferentDescription() {
        //Arrange
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Mary's Gift"));
        group1.addMember(person1);
        group1.addMember(person2);
        Group group2 = new Group(new Description("School Trip"));
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
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Mary's Gift"));
        group1.addMember(person1);
        group1.addMember(person2);
        Group group2 = new Group(new Description("Mary's Gift"));
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
        Group group1 = new Group(new Description("Mary's Gift"));
        Account group2 = new Account(new Denomination("Mary"),
                new Description("Mary Gift"));

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
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Group group1 = new Group(new Description("Amigos"));
        GroupsRepository groupList1 = new GroupsRepository();

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        boolean addedGroup = groupList1.addGroupToRepository(group1);


        //Assert
        assertTrue(addedGroup);
    }

    /**
     * US003 (add a member to a group)
     * Test if a user was added as first member and group admin to a Group and the second as member
     */
    @Test
    @DisplayName("Validate if a member was added to a group")
    void addMember() {

        //Arrange
        Person person1 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Mariana", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));

        HashSet<Person> setOfMembers = new HashSet<>(Arrays.asList(person1, person2));

        Group group1 = new Group(new Description("OsMaisFixes"));

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
        Group group1 = new Group(new Description("OsMaisFixes"));

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
        Person person1 = new Person("Juan", new DateAndTime(1995, 12, 13), new Address("Toledo"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Group with no members"));

        //Act
        boolean isMemberAddedToEmpyGroup = group1.addMember(person1);

        //Assert
        assertTrue(isMemberAddedToEmpyGroup);
    }

    @Test
    @DisplayName("False - member added to a non empty group")
    void promoteAddedMemberIfEmptyTestFalse() {

        //Arrange
        Person person1 = new Person("Juan", new DateAndTime(1995, 12, 13), new Address("Toledo"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Pablo", new DateAndTime(1995, 12, 13), new Address("Madrid"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Group with no members"));

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
        Group group1 = new Group(new Description("MNation"));

        Person person1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Mariana", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Marisa", new DateAndTime(1995, 12, 13), new Address("Leiria"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

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
        Group group1 = new Group(new Description("Maria's Group"));

        Person person1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));

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
        Group group1 = new Group(new Description("Grupo das M'Nation"));

        Person person1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
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
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"));
        Person personAdmin = new Person("António", new DateAndTime(1995, 12, 13), new Address("Guimarães"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));
        group1.addMember(personAdmin);
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
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"));

        Person personAdmin = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("13@isep.pt"));
        Person person3 = null;
        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));
        group1.addMember(personAdmin);
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
        Group group1 = new Group(new Description("OS FIXES"));

        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person3 = new Person("Diana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));

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
        Group group1 = new Group(new Description("OS FIXES"));

        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Mariana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Diana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

        //Act
        group1.addMember(person1); //Admin
        group1.addMember(person2);
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
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"));
        Person personAdmin = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        group1.addMember(personAdmin);
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
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"));

        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Gabriel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("14@isep.pt"));

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
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"));
        Person personAdmin = new Person("Catarina", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Gabriel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(person1, person2));
        group1.addMember(personAdmin);
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
        Group group1 = new Group(new Description("123 são os primeiros três números inteiros"));

        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Laurinda", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

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
        Group group1 = new Group(new Description("grupo dos amiguinhos"));
        Person person1 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Gabriel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Laurinda", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person4 = new Person("Oscar", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));

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
        Group group1 = new Group(new Description("grupo dos amiguinhos"));
        Person person1 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Gabriel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Laurinda", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person4 = new Person("Oscar", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));

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
        Group group1 = new Group(new Description("Grupo ainda mais fixe que o outro"));
        Person person1 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Gabriel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Laurinda", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person4 = new Person("Oscar", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));

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
        Group group1 = new Group(new Description("Grupo ainda mais fixe que o outro"));
        Person person1 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Gabriel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Laurinda", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

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
        Person person1 = new Person("Oscar", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Joao", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person4 = new Person("Manuela", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));
        Person person5 = new Person("Carlos", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12345@isep.pt"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5));

        person1.setMother(person4);
        person1.setFather(person5);
        person2.setMother(person4);
        person2.setFather(person5);
        person3.setMother(person4);
        person3.setFather(person5);

        Group group1 = new Group(new Description("Family"));
        group1.addMember(person4);
        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertTrue(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - All except one")
    void ifGroupIsFamilyAllFamilyExceptOne() {

        //Arrange
        Person person1 = new Person("Oscar", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Person person3 = new Person("Joao", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person4 = new Person("Manuela", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Person person5 = new Person("Carlos", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("14@isep.pt"));
        Person person6 = new Person("Diana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("34@isep.pt"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5, person6));

        person1.setMother(person4);
        person1.setFather(person5);
        person2.setMother(person4);
        person2.setFather(person5);
        person3.setMother(person4);
        person3.setFather(person5);

        Group group1 = new Group(new Description("Family"));
        group1.addMember(person1);
        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertFalse(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No one is family")
    void ifGroupIsFamilyNoneFamily() {

        //Arrange
        Person person1 = new Person("Oscar", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Person person3 = new Person("Joao", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("14@isep.pt"));
        Person person4 = new Person("Manuela", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Person person5 = new Person("Carlos", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person2, person3, person4, person5));

        Group group1 = new Group(new Description("Family"));
        group1.addMember(person1);

        // Act
        group1.addMultipleMembers(familyList);

        // Assert
        assertFalse(group1.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No Mother")
    void ifGroupIsFamilyFamilyButNoMother() {

        //Arrange
        Person person1 = new Person("Oscar", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Joao", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person4 = new Person("Manuela", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("34@isep.pt"));
        Person person5 = new Person("Carlos", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(person2, person3, person4, person5));

        person1.setFather(person5);
        person2.setFather(person5);
        person3.setFather(person5);

        Group group1 = new Group(new Description("Family"));
        group1.addMember(person1);

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

        Group group1 = new Group(new Description("Familia"));
        Group group2 = new Group(new Description("Familia"));

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
        Person person1 = new Person("Francisco", new DateAndTime(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person groupAdmin = new Person("João", new DateAndTime(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Test Group"));

        //Act:
        group1.addMember(groupAdmin);
        boolean result = group1.setAdmin(person1);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("trying to user method on a null person returns - FALSE")
    void isNullPersonAddedToGroupAsAdminAndMember() {

        //Arrange:
        Person groupAdmin = new Person("Francisco", new DateAndTime(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person1 = null;
        Group group1 = new Group(new Description("Test Group"));

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
        Person person1 = new Person("Francisco", new DateAndTime(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person groupAdmin = new Person("João", new DateAndTime(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Test Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Albert", new DateAndTime(1995, 12, 13), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

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
        Person personAdmin = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));

        Group group1 = new Group(new Description("Francis Group"));
        group1.addMember(personAdmin);

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
        Person personAdmin = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("14@isep.pt"));
        Person person3 = new Person("Pedro", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person4 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));

        Group group1 = new Group(new Description("Francis Group"));
        group1.addMember(personAdmin);

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Vladimir", new DateAndTime(1995, 12, 13), new Address("Moscow"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Test Group 1"));
        Group group2 = new Group(new Description("Test Group 2"));
        Group group3 = new Group(new Description("Test Group 3"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Test Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("John", new DateAndTime(1995, 12, 13), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person4 = new Person("Susan", new DateAndTime(1995, 12, 13), new Address("Edinburgh"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));
        Group group1 = new Group(new Description("Test Group"));

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
        Person person1 = new Person("Francis", new DateAndTime(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(2000, 12, 12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("John", new DateAndTime(2000, 12, 12), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Person person4 = new Person("Susan", new DateAndTime(2000, 12, 12), new Address("Edinburgh"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Group group1 = new Group(new Description("Test Group"));

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
    @DisplayName("Check if multiple admins can´t be demoted - FALSE - removeMembers same number of admins")
    void multipleAdminsDemotionTestFalseSecondCondition() {

        //Arrange:
        Person person1 = new Person("Francis", new DateAndTime(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(2000, 12, 12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person3 = new Person("John", new DateAndTime(2000, 12, 12), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person4 = new Person("Susan", new DateAndTime(2000, 12, 12), new Address("Edinburgh"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person5 = new Person("Michael", new DateAndTime(2002, 11, 20), new Address("Edinburgh"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12345@isep.pt"));
        Group group1 = new Group(new Description("Test Group"));

        //Act:
        group1.addMember(person1); // automatically promoted to admin
        group1.addMember(person2);
        group1.addMember(person3);
        group1.addMember(person4);
        HashSet<Person> membersToPromote = new HashSet<>(Arrays.asList(person2, person3, person4));
        group1.promoteMultipleMemberToAdmin(membersToPromote);
        HashSet<Person> membersToDemote = new HashSet<>(Arrays.asList(person1, person2, person3, person4, person5));
        // Last person will not be removed since if it is, there will be no admins left on the group;
        boolean isLastAdminDemoted = group1.demoteMultipleMembersFromAdmin(membersToDemote);

        //Assert:
        assertFalse(isLastAdminDemoted);
    }

    @Test
    @DisplayName("Check if multiple admins can´t be demoted - FALSE - tring to remove member that is not part of the group")
    void multipleAdminsDemotionTestFalseNotInGroup() {

        //Arrange:
        Person person1 = new Person("Francis", new DateAndTime(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(2000, 12, 12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("14@isep.pt"));
        Person person3 = new Person("John", new DateAndTime(2000, 12, 12), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Person person4 = new Person("Susan", new DateAndTime(2000, 12, 12), new Address("Edinburgh"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Group group1 = new Group(new Description("Test Group"));

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
    @DisplayName("Promote person to group admin - False - person not member")
    void promoteNotMemberToAdmin() {
        //Arrange
        Person person1 = new Person("Francis", new DateAndTime(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

        //Act
        boolean isMemberAddedAsAdmin = group1.setAdmin(person1);

        //Assert
        assertFalse(isMemberAddedAsAdmin);
    }

    @Test
    @DisplayName("Promote person to member and group admin simultaneously - false because member is already group admin")
    void memberAndGroupAdminSimultaneouslyFalse() {
        //Arrange
        Person person1 = new Person("Francis", new DateAndTime(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"));

        //Act
        group1.addMember(person1);
        boolean isMemberAddedAsAdmin = group1.setAdmin(person1);

        //Assert
        assertFalse(isMemberAddedAsAdmin);
    }

    @Test
    @DisplayName("Promote person to member and group admin simultaneously while there are more than members that are not admins")
    void promoteNotMembertoAdminWhileThereAreOtherGroupMembers_False() {
        //Arrange
        Person personAdmin = new Person("Marta", new DateAndTime(2000, 12, 12), new Address("Guimarães"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(2000, 12, 12), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Person person3 = new Person("Pedro", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1@isep.pt"));
        Person person4 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12345@isep.pt"));

        Group group1 = new Group(new Description("Francis Group"));
        group1.addMember(personAdmin);

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person2, person3, person4));
        group1.addMultipleMembers(setOfPeopleToAddToGroup);

        //Act
        boolean isAdminPromoted = group1.setAdmin(person1);

        boolean wasPromoted = isAdminPromoted;

        //Assert
        assertFalse(wasPromoted);
    }

    /**
     * Test if a person is a Group Admin
     */
    @DisplayName("Check if a person is in the Group Admin List")
    @Test
    void isGroupAdmin() {
        //Arrange:
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("asd@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("qwerty@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("321@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"));
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
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("12@isep.pt"));
        Person person4 = new Person("João", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), person2, person3, new Email("1@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"));
        group1.addMember(person3);
        group1.addMember(person4);

        //Act
        boolean isAdmin = group1.isGroupAdmin(person4);

        //Assert
        assertFalse(isAdmin);
    }


    /**
     * Test if a person is a Group Admin - check PersonID
     */
    @Test
    @DisplayName("Check if a person is in the Group Admin List - True ")
    void isGroupAdmin_True() {

        //Arrange
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("234@isep.pt"));
        Group oneGroup = new Group(new Description("XPTO"));

        oneGroup.addMember(person2);
        oneGroup.addMember(person3);

        //Act
        boolean isgroupAdmin = oneGroup.isGroupAdmin(person2.getID());

        //Assert
        assertTrue(isgroupAdmin);

    }

    @Test
    @DisplayName("Check if a person is in the Group Admin List - False - Person member but not admin")
    void isGroupAdmin_False() {

        //Arrange
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("ada@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("dasdas@isep.pt"));

        Group oneGroup = new Group(new Description("XPTO"));

        oneGroup.addMember(person2);
        oneGroup.addMember(person3);

        //Act
        boolean isgroupAdmin = oneGroup.isGroupAdmin(person3.getID());

        //Assert
        assertFalse(isgroupAdmin);

    }

    @Test
    @DisplayName("Check if a person is in the Group Admin List - False - Person not member")
    void isGroupAdmin_False_PersonNotMember() {

        //Arrange
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("alexandre@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("elsa@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("sd@isep.pt"));

        Group oneGroup = new Group(new Description("XPTO"));
        oneGroup.addMember(person2);
        oneGroup.addMember(person3);

        //Act
        boolean isgroupAdmin = oneGroup.isGroupAdmin(person1.getID());

        //Assert
        assertFalse(isgroupAdmin);

    }


    @DisplayName("Check if a person null can be a Group Admin")
    @Test
    void isGroupAdminNull() {
        //Arrange:
        Person person1 = null;
        Group group1 = new Group(new Description("Maria's Group"));
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
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("134@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("34@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"));
        group1.addMember(person3);
        group1.addMember(person1);

        //Act
        boolean isMember = group1.isGroupMember(person1.getID());

        //Assert
        assertTrue(isMember);
    }

    @DisplayName("Check if a person is not in the Group Member List")
    @Test
    void isGroupMemberFalse() {
        //Arrange:
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("12@isep.pt"));
        Person person4 = new Person("João", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), person2, person3, new Email("1@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"));
        group1.addMember(person1);

        //Act
        boolean isMember = group1.isGroupMember(person3.getID());

        //Assert
        assertFalse(isMember);
    }

    @DisplayName("Person it´s not member")
    @Test
    void isGroupMemberNull() {
        //Arrange:
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"));
        group1.addMember(person1);
        //Act
        boolean isMember = group1.isGroupMember(person2.getID());

        //Assert
        assertFalse(isMember);
    }


    /**
     * Check if a transaction is inside a groups Ledger
     */
    @Test
    @DisplayName("isTransactionInsideGroupLedger - True (Happy Case")
    void isTransactionInsideGroupLedgerTrue() {

        //Arrange:
        Group group1 = new Group(new Description("Grupo de Jantares"));

        // Arrange Transaction:
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 31, 13, 02);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Category category1 = new Category("ASD");
        Transaction transaction1 = new Transaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, new Type(true));

        //Act:
        group1.createGroupTransaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, new Type(true));
        boolean result = group1.isTransactionInsideTheGroupLedger(transaction1);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("isTransactionInsideGroupLedger - False")
    void isTransactonInsideGroupLedgerFalse() {

        //Arrange:
        Group group1 = new Group(new Description("Grupo de Jantares"));

        // Arrange Transaction:
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 31, 13, 02);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Category category1 = new Category("ASD");
        Transaction transaction1 = new Transaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, new Type(true));

        //Act:
        group1.createGroupTransaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, new Type(false));
        boolean result = group1.isTransactionInsideTheGroupLedger(transaction1);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("is Transaction Inside the Ledger - Two transactions (True)")
    void areTwoTransactionsInsideTheLedger() {

        //Arrange:
        Group group1 = new Group(new Description("Grupo de Jantares"));

        // Arrange Transaction:
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 31, 13, 2);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Category category1 = new Category("ASD");
        Transaction transaction1 = new Transaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, new Type(false));
        Transaction transaction2 = new Transaction(monetaryValue, "Test Transaction2", date1, category1, oneAccount, otherAccount, new Type(true));

        //Act:
        group1.createGroupTransaction(monetaryValue, "Test Transaction", date1, category1, oneAccount, otherAccount, new Type(false));
        group1.createGroupTransaction(monetaryValue, "Test Transaction2", date1, category1, oneAccount, otherAccount, new Type(true));

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
        Group testGroup = new Group(new Description("test group"));

        //Transactions arrangement:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));

        Category category1 = new Category("grocery");

        Account account1 = new Account(new Denomination("groceries"),
                new Description("mercearia Continente"));
        Account account2 = new Account(new Denomination("groceries"),
                new Description("mercearia Pingo Doce"));

        //Transactions added to Group Ledger:
        testGroup.createGroupTransaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, new Type(true));
        testGroup.createGroupTransaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, new Type(false));
        testGroup.createGroupTransaction(monetaryValue3, "testTransaction3", null, category1, account1, account2, new Type(true));

        //Transactions to check:
        Transaction testTransaction1 = new Transaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, new Type(true));
        Transaction testTransaction2 = new Transaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, new Type(false));
        Transaction testTransaction3 = new Transaction(monetaryValue3, "testTransaction3", null, category1, account1, account2, new Type(true));

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
        Group testGroup = new Group(new Description("test group"));

        //Transactions arrangement:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));

        Category category1 = new Category("grocery");

        Account account1 = new Account(new Denomination("groceries"),
                new Description("mercearia Continente"));
        Account account2 = new Account(new Denomination("groceries"),
                new Description("mercearia Pingo Doce"));

        //Transactions added to Group Ledger:
        testGroup.createGroupTransaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, new Type(true));
        testGroup.createGroupTransaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, new Type(false));
        testGroup.createGroupTransaction(monetaryValue3, "testTransaction3", null, category1, account1, account2, new Type(true));

        //Transactions to check:
        Transaction testTransaction1 = new Transaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, new Type(true));
        Transaction testTransaction2 = new Transaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, new Type(false));
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
            assertEquals("One (or more) of the transactions is null.", nullTransaction.getMessage());
        }
    }

    @Test
    @DisplayName("Verify if multiple transactions are inside the GroupLedger")
    void areMultipleTransactionsInsideTheGroupLedgerTest1() {
        //Arrange:
        Group testGroup = new Group(new Description("test group"));

        //Transactions arrangement:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));

        Category category1 = new Category("grocery");

        Account account1 = new Account(new Denomination("groceries"),
                new Description("mercearia Continente"));
        Account account2 = new Account(new Denomination("groceries"),
                new Description("mercearia Pingo Doce"));

        //Transactions added to Group Ledger:
        testGroup.createGroupTransaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, new Type(true));
        testGroup.createGroupTransaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, new Type(false));

        //Transactions to check:
        Transaction testTransaction1 = new Transaction(monetaryValue1, "testTransaction1", null, category1, account1, account2, new Type(true));
        Transaction testTransaction2 = new Transaction(monetaryValue2, "testTransaction2", null, category1, account2, account1, new Type(false));
        Transaction testTransaction3 = new Transaction(monetaryValue3, "testTransaction3", null, category1, account1, account2, new Type(true));

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
     * Test if two Groups are the same
     */

    @Test
    @DisplayName("test if two Groups are the same")
    public void testIfTwoGroupsAreTheSameHashcode() {
        //Arrange

        Group group1 = new Group(new Description("Talho do Amadeu"));
        Group group2 = new Group(new Description("Talho do Amadeu"));

        //Act
        int g1 = group1.hashCode();
        int g2 = group2.hashCode();
        boolean x = group1.equals(group2);


        //Assert
        assertEquals(g1, g2);
    }

    @Test
    @DisplayName("test if two Groups are the same")
    public void testIfTwoGroupsAreTheSameHashcodeFalse() {
        //Arrange

        Group group1 = new Group(new Description("Talho do Amadeu"));
        Group group2 = new Group(new Description("Talho do João"));

        //Act
        int g1 = group1.hashCode();
        int g2 = group2.hashCode();


        //Assert
        assertNotEquals(g1, g2);
    }

    /**
     * US010 Como membro de grupo, quero obter os movimentos de determinada conta num dado período.
     */

    @Test
    @DisplayName("Obtain transactions from an account - case of success")
    void obtainTransactionsFromAnAccount() {
        //Arrange
        Group group = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), group.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), group.getID());
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"), group.getID());

        Category category1 = new Category(new Denomination("grocery"), group.getID());
        Category category2 = new Category(new Denomination("friends"), group.getID());

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        group.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        group.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        //Act
        List<Transaction> listOfTransactions = group.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - dates change")
    void obtainTransactionsFromAnAccountDateChange() {
        //Arrange
        Group group = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), group.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), group.getID());
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"), group.getID());

        Category category1 = new Category(new Denomination("grocery"), group.getID());
        Category category2 = new Category(new Denomination("friends"), group.getID());

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        group.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        group.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        //Act
        List<Transaction> listOfTransactions = group.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - same day")
    void obtainTransactionsFromAnAccountSameDay() {
        //Arrange
        Group group = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 14, 00, 00);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 14, 23, 59);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), group.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), group.getID());
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"), group.getID());

        Category category1 = new Category(new Denomination("grocery"), group.getID());
        Category category2 = new Category(new Denomination("friends"), group.getID());

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));


        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        group.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1));

        group.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        //Act
        List<Transaction> listOfTransactions = group.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - check before the creation of the ledger")
    void obtainTransactionsFromAnAccountBeforeLedger() {
        //Arrange
        Group group1 = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2017, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2018, 1, 31, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"));
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"));

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));


        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Collections.emptyList());


        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        //Act
        List<Transaction> listOfTransactions = group1.getOneAccountTransactionsFromGroup(account5, date1, date2, person1);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - first date null")
    void obtainTransactionsFromAnAccountFirstDateNull() {
        //Arrange
        Group group1 = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = null;
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"));
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"));

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));


        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));


        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

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
        Group group1 = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 2);
        LocalDateTime date2 = null;


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"));
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"));

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));


        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

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
        Group group1 = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2020, 2, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"), group1.getID());
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"), group1.getID());
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"), group1.getID());

        Category category1 = new Category(new Denomination("grocery"), group1.getID());
        Category category2 = new Category(new Denomination("friends"), group1.getID());

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));

        group1.addMember(person1);

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

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
        Group group1 = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2020, 2, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"));
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"));

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));


        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        group1.addMember(person1);

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

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
        Group group1 = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);

        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"));


        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));
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
        Group group1 = new Group(new Description("Caloteiros"));
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 13, 2);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 13, 2);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account(new Denomination("mercearia"),
                new Description("mercearia Continente"));
        Account account2 = new Account(new Denomination("transporte"),
                new Description("transporte Metro"));
        Account account5 = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"));

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", new DateAndTime(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"), new Email("1234@isep.pt"));


        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, new Type(true));
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, new Type(true));
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, new Type(false));

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
        Person person1 = new Person("João", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Francisca", new DateAndTime(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Person person3 = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("14@isep.pt"));
        Person person4 = new Person("Francisco", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Test group"));
        group1.addMember(person3);
        group1.addMember(person4);

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));

        String description = "payment";

        Category category = new Category("General");

        //Act
        boolean transactionCreated = false;
        if (group1.isGroupMember(person4)) {
            transactionCreated = group1.createGroupTransaction(amount, description, null, category, from, to, new Type(false));
        }

        //Assert
        assertTrue(transactionCreated);
    }

    @Test
    @DisplayName("Test if a group transaction was created - monetary value is negative")
    void createGroupTransactionAccountNegativeMonetaryValue() {
        //Arrange
        Person person1 = new Person("João", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Person person2 = new Person("Francisca", new DateAndTime(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person3 = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("1234@isep.pt"));
        Person person4 = new Person("Francisco", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Test group"));
        group1.addMember(person3);
        group1.addMember(person4);

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));


        MonetaryValue amountNegative = new MonetaryValue(-50, Currency.getInstance("EUR"));
        String description1 = "payment";

        Category category = new Category("General");

        //Act
        try {
            if (group1.isGroupMember(person4)) {
                group1.createGroupTransaction(amountNegative, description1, null, category, from, to, new Type(false));
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
        Person person1 = new Person("João", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Francisca", new DateAndTime(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Jose", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("12@isep.pt"));
        Person person4 = new Person("Francisco", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1, new Email("1@isep.pt"));
        Group group1 = new Group(new Description("Test group"));
        group1.addMember(person3);

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));


        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));

        String description = "payment";

        Category category = new Category("General");

        //Act
        boolean transactionCreated = false;
        if (group1.isGroupMember(person4)) {
            transactionCreated = group1.createGroupTransaction(amount, description, null, category, from, to, new Type(false));
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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 4),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));


        oneGroup.addMember(onePerson);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Group Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        oneGroup.addMember(onePerson);

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        oneGroup.addMember(onePerson);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        oneGroup.addMember(onePerson);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));


        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        oneGroup.addMember(onePerson);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        oneGroup.addMember(onePerson);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        oneGroup.addMember(onePerson);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        oneGroup.addMember(onePerson);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        oneGroup.addMember(onePerson);

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

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
        Group oneGroup = new Group(new Description("XPTO"));

        Person onePerson = new Person("Alex", new DateAndTime(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Account oneAccount = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        Account anotherAccount = new Account(new Denomination("abc"),
                new Description("abc Account"));

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");


        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

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

        Account accountOne = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account accountTwo = new Account(new Denomination("xyz"),
                new Description("xyz Account"));

        //Group instanced:
        Group group1 = new Group(new Description("Test Group"));

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, new Type(false));

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

        Account accountOne = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account accountTwo = new Account(new Denomination("xyz"),
                new Description("xyz Account"));

        //Group instanced:
        Group group1 = new Group(new Description("Test Group"));

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, new Type(false));

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

        Account accountOne = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account accountTwo = new Account(new Denomination("xyz"),
                new Description("xyz Account"));


        //Group instanced:
        Group group1 = new Group(new Description("Test Group"));

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, new Type(false));

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

        Account accountOne = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account accountTwo = new Account(new Denomination("xyz"),
                new Description("xyz Account"));


        //Group instanced:
        Group group1 = new Group(new Description("Test Group"));

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, new Type(false));

        String expected = "One of the submitted dates is not valid.";

        //Act:
        try {
            group1.getGroupBalanceInDateRange(LocalDateTime.of(2021, 3, 1, 0, 0),
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

        Account accountOne = new Account(new Denomination("myxpto"),
                new Description("xpto Account"));
        Account accountTwo = new Account(new Denomination("xyz"),
                new Description("xyz Account"));

        //Group instanced:
        Group group1 = new Group(new Description("Test Group"));

        //Add the Transactions to Group:
        group1.createGroupTransaction(monetaryValueOne, "test transaction 1", localDateOne, categoryOne,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueTwo, "test transaction 2", localDateTwo, categoryTwo,
                accountOne, accountTwo, new Type(true));
        group1.createGroupTransaction(monetaryValueThree, "test transaction 3", localDateThree, categoryThree,
                accountTwo, accountOne, new Type(false));

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
        Group group1 = new Group(new Description("Test Group"));

        //Act:
        String expected = "The ledger is Empty.";
        try {
            group1.getGroupBalanceInDateRange(LocalDateTime.of(2018, 3, 1, 0, 0),
                    LocalDateTime.of(2016, 1, 17, 0, 0));
        }
        //Assert:
        catch (IllegalArgumentException result) {
            assertEquals(expected, result.getMessage());
        }
    }

    @Test
    void scheduleNewTransactionDaily() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));


        Group group = new Group(new Description("tarzan"));

        //Act
        boolean result = group.scheduleNewTransaction(new Periodicity("daily"), amount, description, null, category, from, to, new Type(false));

        Thread.sleep(2400); // 250 x 10 = 2500

        //Assert
        assertTrue(result && group.ledgerSize() == 10);
    }


    @Test
    void scheduleNewTransactionWorkingDays() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));


        Group group = new Group(new Description("tarzan"));

        //Act
        boolean result = group.scheduleNewTransaction(new Periodicity("working days"), amount, description, null, category, from, to, new Type(false));

        Thread.sleep(1900); // 500 x 4 = 2000

        //Assert
        assertTrue(result && group.ledgerSize() == 4);
    }

    @Test
    void scheduleNewTransactionWeekly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));


        Group group = new Group(new Description("tarzan"));

        //Act
        boolean result = group.scheduleNewTransaction(new Periodicity("weekly"), amount, description, null, category, from, to, new Type(false));

        Thread.sleep(2900); // 750 x 4 = 3000

        //Assert
        assertTrue(result && group.ledgerSize() == 4);
    }


    @Test
    void scheduleNewTransactionMonthly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));

        Group group = new Group(new Description("tarzan"));

        //Act
        boolean result = group.scheduleNewTransaction(new Periodicity("monthly"), amount, description, null, category, from, to, new Type(false));

        Thread.sleep(2900); // 1000 x 3 = 3000

        //Assert
        assertTrue(result && group.ledgerSize() == 3);
    }

    @Test
    void scheduleNewTransactionNoMatch() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));

        Group group = new Group(new Description("tarzan"));

        try {
            //Act
            group.scheduleNewTransaction(new Periodicity("tomorrow"), amount, description, null, category, from, to, new Type(false));
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }
    }

    @Test
    void getGroupDescription() {

        Group group = new Group(new Description("tarzan"));
        String expected = "TARZAN";

        //Act
        String result = group.getGroupID();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test method getID from Group ")
    void getID() {
        Group group1 = new Group(new Description("Gym Buddies"));
        GroupID expected = new GroupID(new Description("Gym Buddies"));

        //Act
        GroupID result = group1.getID();

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Test to check if converts a group into a String.
     */

    @Test
    @DisplayName("gruoupToString tested - Success")
    void testToString() {

        //Arrange:
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Group group = new Group(new Description("policias"), person);


        //Act:
        String groupInString = group.toString();
        String expected = "POLICIAS";

        //Assert:
        assertEquals(expected, groupInString);
    }

    @Test
    @DisplayName("test the exception of setGroupID method")
    void setGroupIdExceptionTest() {

        //Arrange:
        Group group = new Group(new Description("grupo de rafting"));

        //Act:
        try {group.setGroupID(null);}

        //Assert:
        catch (IllegalArgumentException nullDescription) {
            assertEquals("GroupID can't be null", nullDescription.getMessage());
        }
    }
}