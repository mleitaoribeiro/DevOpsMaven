package switch2019.project.domain.domainEntities.group;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        Group group1 = new Group(new Description("Amigos"), person1.getID());
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());

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
        Group group1 = new Group(new Description("Amigos"), person1.getID());
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
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
        Group group1 = new Group(new Description("Amigos"), person1.getID());
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
        Group group2 = new Group(new Description("Amigos"), person3.getID());
        group2.addMember(person3.getID());
        group2.addMember(person4.getID());

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertTrue(result);
    }

    /**
     * Compare two groups with same members but different description
     */
    @Test
    @DisplayName("Compare two groups with members but different description")
    public void compareGroupsWithSameMembersButDifferentDescription() {
        //Arrange
        Person person1 = new Person("John", new DateAndTime(1995, 12, 13), new Address("New York"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Frank", new DateAndTime(1995, 12, 13), new Address("Washington D.C."),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Mary's Gift"), person1.getID());
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
        Group group2 = new Group(new Description("School Trip"), person2.getID());
        group2.addMember(person1.getID());
        group2.addMember(person2.getID());

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
        Group group1 = new Group(new Description("Mary's Gift"), person1.getID());
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
        Group group2 = new Group(new Description("Mary's Gift"), person2.getID());
        group2.addMember(person1.getID());
        group2.addMember(person2.getID());

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertTrue(result);
    }

    /**
     * Check if Group and another object are different
     */
    @Test
    @DisplayName("Compare different objects")
    public void compareGroupToAnotherObject() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Mary's Gift"), person.getID());
        Account group2 = new Account(new Denomination("Mary"),
                new Description("Mary Gift"), new GroupID(new Description("Gift")));

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertFalse(result);
    }


    /**
     * US003 (add a member to a group)
     * Test if a user was added as first member and group admin to a Group and the second as member
     */

    @Test
    @DisplayName("Validate if members were added to a group")
    void addMembers() {

        //Arrange
        Person person1 = new Person("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Mariana", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));

        HashSet<PersonID> setOfMembers = new HashSet<>(Arrays.asList(person1.getID(), person2.getID()));

        Group group1 = new Group(new Description("OsMaisFixes"), person1.getID());

        group1.addMultipleMembers(setOfMembers);

        //Act
        boolean areMembersAddedToGroup = (group1.isGroupMember(person1.getID()) && group1.addMember(person2.getID()));

        //Assert
        //assertTrue(areMembersAddedToGroup);
    }

    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMemberNull() {

        //Arrange
        PersonID person1 = null;
        Group group1 = new Group(new Description("OsMaisFixes"), person1);

        //Act
        boolean isMemberAddedToGroup = group1.addMember(person1);

        //Assert
        assertFalse(isMemberAddedToGroup);
    }

    /**
     * Test if a member added to the group is automatically promoted to admin if the group is empty
     */
    @Test
    @DisplayName("Member added to an empty group and Set as Admin")
    void promoteToAdminMemberAddedToAnEmptyTrue() {

        //Arrange
        Person person1 = new Person("Juan", new DateAndTime(1995, 12, 13), new Address("Toledo"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Group with no members"), person1.getID());

        //Act
        boolean isMemberAddedToEmpyGroup = group1.addMember(person1.getID());

        //Assert
        assertFalse(isMemberAddedToEmpyGroup);
        assertTrue(group1.isGroupAdmin(person1.getID()));
    }

    @Test
    @DisplayName("Member added to a non empty group - Not Admin")
    void addMemberToNonEmptyGroup() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Group with no members"), person.getID());

        Person person1 = new Person("Juan", new DateAndTime(1995, 12, 13), new Address("Toledo"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));

        group1.addMember(person1.getID());

        Person person2 = new Person("Pablo", new DateAndTime(1995, 12, 13), new Address("Madrid"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));

        //Act
        boolean isPerson2AddedButNotToSettledAsAdmin = group1.addMember(person2.getID()) && !group1.isGroupAdmin(person2.getID());

        //Assert
        assertTrue(isPerson2AddedButNotToSettledAsAdmin);
    }


    @Test
    @DisplayName("Test if the same person is not added twice")
    void addSamePersonTwice() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"), person.getID());

        Person person1 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));

        group1.addMember(person1.getID());

        Person person2 = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));

        //Act

        boolean person2NotAdded = group1.addMember(person2.getID());

        //Assert
        assertFalse(person2NotAdded);
    }

    @Test
    @DisplayName("Test if multiple members are not added to an empty group")
    void addMultipleMembersEmptyGroup() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"), person.getID());
        Person admin = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        group1.addMember(admin.getID());
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

        HashSet<PersonID> putMembers = new HashSet<>(Arrays.asList(person1.getID(), person2.getID()));
        //Act
        boolean result = group1.addMultipleMembers(putMembers);

        //Assert
        assertTrue(result);
    }

    /**
     * Test if member was removed from Group
     */

    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroup() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"), person.getID());
        Person personAdmin = new Person("António", new DateAndTime(1995, 12, 13), new Address("Guimarães"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

        HashSet<PersonID> putMembers = new HashSet<>(Arrays.asList(person1.getID(), person2.getID()));
        group1.addMember(personAdmin.getID());
        //Act
        group1.addMultipleMembers(putMembers);

        boolean removeSingleMember = group1.removeMember(person2.getID());

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


        Person personAdmin = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("13@isep.pt"));
        PersonID person3 = null;
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"), personAdmin.getID());
        HashSet<PersonID> putMembers = new HashSet<>(Arrays.asList(person1.getID(), person2.getID()));
        group1.addMember(personAdmin.getID());
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
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("OS FIXES"), person.getID());

        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person3 = new Person("Diana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));

        //Act
        group1.addMember(person1.getID()); //Admin
        group1.addMember(person3.getID());

        boolean removeAdmin = group1.removeMember(person1.getID());

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
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("OS FIXES"), person.getID());

        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Mariana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Diana", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

        //Act
        group1.addMember(person1.getID()); //Admin
        group1.addMember(person2.getID());
        group1.setAdmin(person2.getID()); //Admin
        group1.addMember(person3.getID());

        boolean removeAdmin = group1.removeMember(person1.getID());

        //Assert
        assertTrue(removeAdmin);
    }

    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroupNullPerson() {

        //Arrange

        Person personAdmin = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"), personAdmin.getID());
        HashSet<PersonID> putMembers = new HashSet<>(Arrays.asList(person1.getID(), person2.getID()));

        //Act
        group1.addMultipleMembers(putMembers);

        boolean removeSingleMember = group1.removeMember(null);

        //Assert
        assertFalse(removeSingleMember);
    }


    @Test
    @DisplayName("Test if a member was removed from a Group")
    void removeMemberFromGroupPersonNotInGroup() {

        //Arrange

        Person personAdmin = new Person("Catarina", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Paranhos"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Gabriel", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));

        HashSet<PersonID> putMembers = new HashSet<>(Arrays.asList(person1.getID(), person2.getID()));
        Group group1 = new Group(new Description("Grupo a ser submetido aos testes"), personAdmin.getID());
        group1.addMultipleMembers(putMembers);

        //Act

        boolean removeSingleMember = group1.removeMember(person3.getID());

        //Assert
        assertFalse(removeSingleMember);
    }

    @Test
    @DisplayName("Test if a member was removed from a Group - try to remove all members")
    void removeMemberFromGroupAllMembers() {

        //Arrange


        Person person1 = new Person("João", new DateAndTime(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Laurinda", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));

        Group group1 = new Group(new Description("123 são os primeiros três números inteiros"), person1.getID());
        HashSet<PersonID> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(person3.getID(), person2.getID()));

        //Act
        group1.addMultipleMembers(setOfPeopleToAddToGroup);
        boolean areBothMembersRemoved = (group1.removeMember(person3.getID()) && group1.removeMember(person2.getID()));

        //Assert
        assertTrue(areBothMembersRemoved);
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
        Group group1 = new Group(new Description("Test Group"), person1.getID());

        //Act:
        boolean result = group1.setAdmin(person1.getID());

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("trying to user method on a null person returns - FALSE")
    void isNullPersonAddedToGroupAsAdminAndMember() {

        //Arrange:
        Person groupAdmin = new Person("Francisco", new DateAndTime(1995, 12, 13), new Address("Braga"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        PersonID person1 = null;
        Group group1 = new Group(new Description("Test Group"), groupAdmin.getID());

        //Act:
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
        Group group1 = new Group(new Description("Test Group"), groupAdmin.getID());

        //Act:

        group1.setAdmin(person1.getID());
        boolean result = group1.setAdmin(person1.getID());

        //Assert:
        assertFalse(result);
    }


    @Test
    @DisplayName("Promote one member to Admin while there are more than one member")
    void promoteMemberTest2() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Albert", new DateAndTime(1995, 12, 13), new Address("Bristol"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"), person.getID());

        //Act
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
        group1.addMember(person3.getID());
        boolean wereMembersPromoted = group1.setAdmin(person2.getID()) && group1.setAdmin(person3.getID());

        //Assert
        assertTrue(wereMembersPromoted);
    }

    @Test
    @DisplayName("Promote one member to group admin - false because member is already group admin")
    void promoteMemberFalseAlreadyAdmin() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("124@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"), person.getID());

        //Act
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
        boolean isFirstMemberPromotedAgain = group1.setAdmin(person1.getID());

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
        Group group1 = new Group(new Description("Francis Group"), person1.getID());

        //Act

        boolean wasPromoted = group1.setAdmin(person1.getID());

        //Assert
        assertFalse(wasPromoted);
    }

    /**
     * Test Equals method for the Group class
     */
    @Test
    @DisplayName("Two group are the same")
    void equalsGroupClassJustGroupTrue() {

        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Familia"),person.getID());
        Group group2 = new Group(new Description("Familia"),person.getID());

        //Act
        boolean result = group1.equals(group2);

        //Assert
        assertTrue(result);

    }
    /**
     * Check if member was demoted from group admin
     */
    @Test
    @DisplayName("Demote one group admin to member")
    void demoteMemberTest() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"), person.getID());

        //Act
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
        group1.setAdmin(person2.getID());
        boolean wasDemoted = group1.demoteMemberFromAdmin(person2.getID());

        //Assert
        assertTrue(wasDemoted);
    }

    @Test
    @DisplayName("Demote one group admin to member")
    void demoteMemberNotAdmin() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"), person.getID());

        //Act
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
        boolean wasDemoted = group1.demoteMemberFromAdmin(person2.getID());

        //Assert
        assertFalse(wasDemoted);
    }

    @Test
    @DisplayName("Demote a member who is not an admin - FALSE")
    void demoteMemberTestFalse() {

        //Arrange:
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"), person.getID());

        //Act:
        group1.addMember(person1.getID());
        group1.addMember(person2.getID());
        boolean wasDemoted = group1.addMember(person2.getID());

        //Assert:
        assertFalse(wasDemoted);
    }

    @Test
    @DisplayName("Demote multiple group admins to members")
    void demoteMultipleMembersTest() {

        //Arrange:
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Person person3 = new Person("Vladimir", new DateAndTime(1995, 12, 13), new Address("Moscow"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("12@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"), person.getID());

        //Act:
        group1.addMember(person1.getID()); // Torna-se admin automaticamente
        group1.addMember(person2.getID());
        group1.addMember(person3.getID());
        group1.setAdmin(person2.getID());
        group1.setAdmin(person3.getID());
        boolean isFirstAdminRemoved = group1.demoteMemberFromAdmin(person2.getID());
        boolean isSecondAdminRemoved = group1.demoteMemberFromAdmin(person3.getID());

        //Assert:
        assertTrue(isFirstAdminRemoved && isSecondAdminRemoved);
    }

    @Test
    @DisplayName("Check if the same admin can be demoted from multiple groups")
    void demoteMemberFromMultipleGroups() {

        //Arrange:
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Test Group 1"), person.getID());
        Group group2 = new Group(new Description("Test Group 2"), person.getID());
        Group group3 = new Group(new Description("Test Group 3"), person.getID());

        //Act:
        group1.addMember(person1.getID()); // admin automatically
        group1.addMember(person2.getID());
        group2.addMember(person1.getID()); // admin automatically
        group2.addMember(person2.getID());
        group3.addMember(person1.getID()); // admin automatically
        group3.addMember(person2.getID());
        group1.setAdmin(person2.getID());
        group2.setAdmin(person2.getID());
        group3.setAdmin(person2.getID());
        boolean isRemovedFromGroup1 = group1.demoteMemberFromAdmin(person1.getID());
        boolean isRemovedFromGroup2 = group2.demoteMemberFromAdmin(person1.getID());
        boolean isRemovedFromGroup3 = group3.demoteMemberFromAdmin(person1.getID());

        // Assert
        assertTrue(isRemovedFromGroup1 && isRemovedFromGroup2 && isRemovedFromGroup3);
    }

    @Test
    @DisplayName("Check if the last admin can be demoted - FALSE expected because group must contain at least 1 admin")
    void canLastAdminBeDemoted() {

        //Arrange:
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(1995, 12, 13), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Person person2 = new Person("Jaques", new DateAndTime(1995, 12, 13), new Address("Paris"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("123@isep.pt"));
        Group group1 = new Group(new Description("Test Group"), person.getID());

        //Act:
        group1.addMember(person1.getID()); //Automatically promoted to admin
        group1.addMember(person2.getID());
        boolean isRemovedFromAdminPerson2 = group1.demoteMemberFromAdmin(person2.getID());
        boolean isRemovedFromAdminPerson1 = group1.demoteMemberFromAdmin(person1.getID());

        //Assert:
        assertFalse(isRemovedFromAdminPerson2 && isRemovedFromAdminPerson1);
    }


    /**
     * Check if a person was promoted to member and group administrator simultaneously
     */
    @Test
    @DisplayName("Promote person to group admin - False - person not member")
    void promoteNotMemberToAdmin() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"), person.getID());

        //Act
        boolean isMemberAddedAsAdmin = group1.setAdmin(person1.getID());

        //Assert
        assertFalse(isMemberAddedAsAdmin);
    }

    @Test
    @DisplayName("Promote person to member and group admin simultaneously - false because member is already group admin")
    void memberAndGroupAdminSimultaneouslyFalse() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Francis", new DateAndTime(2000, 12, 12), new Address("London"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Francis Group"), person.getID());

        //Act
        group1.addMember(person1.getID());
        boolean isMemberAddedAsAdmin = group1.setAdmin(person1.getID());

        //Assert
        assertFalse(isMemberAddedAsAdmin);
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
                new Address("Rua X", "Porto", "4520-266"), person2.getID(), person1.getID(), new Email("321@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"), person3.getID());


        //Act
        boolean isAdmin = group1.isGroupAdmin(person3.getID());

        //Assert
        assertTrue(isAdmin);
    }

    @DisplayName("Check if a person is not in the Group Admin List")
    @Test
    void isGroupAdminFalse() {
        //Arrange:
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2.getID(), person1.getID(), new Email("12@isep.pt"));
        Person person4 = new Person("João", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), person2.getID(), person3.getID(), new Email("1@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"), person.getID());
        group1.addMember(person3.getID());
        group1.addMember(person4.getID());

        //Act
        boolean isAdmin = group1.isGroupAdmin(person4.getID());

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
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2.getID(), person1.getID(), new Email("234@isep.pt"));
        Group oneGroup = new Group(new Description("XPTO"), person.getID());

        oneGroup.addMember(person2.getID());
        oneGroup.addMember(person3.getID());

        //Act
        boolean isgroupAdmin = oneGroup.isGroupAdmin(person2.getID());

        //Assert
        assertTrue(isgroupAdmin);

    }

    @Test
    @DisplayName("Check if a person is in the Group Admin List - False - Person member but not admin")
    void isGroupAdmin_False() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("ada@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2.getID(), person1.getID(), new Email("dasdas@isep.pt"));

        Group oneGroup = new Group(new Description("XPTO"), person.getID());

        oneGroup.addMember(person2.getID());
        oneGroup.addMember(person3.getID());

        //Act
        boolean isgroupAdmin = oneGroup.isGroupAdmin(person3.getID());

        //Assert
        assertFalse(isgroupAdmin);

    }

    @Test
    @DisplayName("Check if a person is in the Group Admin List - False - Person not member")
    void isGroupAdmin_False_PersonNotMember() {

        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("alexandre@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("elsa@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2.getID(), person1.getID(), new Email("sd@isep.pt"));

        Group oneGroup = new Group(new Description("XPTO"), person.getID());
        oneGroup.addMember(person2.getID());
        oneGroup.addMember(person3.getID());

        //Act
        boolean isgroupAdmin = oneGroup.isGroupAdmin(person1.getID());

        //Assert
        assertFalse(isgroupAdmin);

    }

    @DisplayName("Check if a person null can be a Group Admin")
    @Test
    void isGroupAdminNull() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));

        PersonID personID = null;

        Group group1 = new Group(new Description("Group"), person.getID());


        //Act
        boolean isAdmin = group1.isGroupAdmin(personID);

        //Assert
        assertFalse(isAdmin);
    }

    /**
     * Test if a person is a Group Member with ID
     */
    @DisplayName("Check if a person is in the Group Member List")
    @Test
    void isGroupMemberID() {
        //Arrange:
        Person father = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Person mother = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("134@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), person2.getID(), mother.getID(), new Email("34@isep.pt"));
        Group group1 = new Group(new Description("Group"), father.getID());

        group1.addMember(person3.getID());
        group1.addMember(person2.getID());

        //Act
        boolean isMember = group1.isGroupMember(person2.getID());

        //Assert
        assertTrue(isMember);
    }

    @DisplayName("Check if a person is not in the Group Member List")
    @Test
    void isGroupMemberIDFalse() {
        //Arrange:
        Person father = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person mother = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Maria", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), mother.getID(), father.getID(), new Email("12@isep.pt"));

        Person person4 = new Person("João", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), mother.getID(), person3.getID(), new Email("1@isep.pt"));

        Group group = new Group(new Description("Group"), father.getID());
        group.addMember(person4.getID());
        group.addMember(mother.getID());

        //Act
        boolean isMember = group.isGroupMember(person3.getID());

        //Assert
        assertFalse(isMember);
    }

    @DisplayName("Check if a person is not in the Group Member List")
    @Test
    void isGroupMemberIDNull() {
        //Arrange:
        Person admin = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        PersonID personID = null;

        Group group1 = new Group(new Description("Group"), admin.getID());

        //Act
        boolean isMember = group1.isGroupMember(personID);

        //Assert
        assertFalse(isMember);
    }


    /**
     * Get admins and members
     */

    @DisplayName("Get amdins of Group - happy case")
    @Test
    public void getAdmins() {
        //Arrange
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Joao", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person4 = new Person("Manuela", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"), person1.getID());

        group1.addMember(person2.getID());
        group1.setAdmin(person2.getID());
        group1.addMember(person3.getID());
        group1.addMember(person4.getID());

        Set<PersonID> expectedListOfAdmins = new HashSet<>(Arrays.asList(person1.getID(), person2.getID()));
        //Act
        Set<PersonID> resultListOfAdmins = group1.getAdmins();

        //Assert
        assertEquals(expectedListOfAdmins, resultListOfAdmins);
    }

    @DisplayName("Get members of Group - happy case")
    @Test
    public void getMembers() {
        //Arrange
        Person person1 = new Person("Alexandre", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person person2 = new Person("Elsa", new DateAndTime(2000, 12, 12), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123@isep.pt"));
        Person person3 = new Person("Joao", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("134@isep.pt"));
        Person person4 = new Person("Manuela", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("234@isep.pt"));
        Group group1 = new Group(new Description("Maria's Group"), person1.getID());

        group1.addMember(person2.getID());
        group1.setAdmin(person2.getID());
        group1.addMember(person3.getID());
        group1.addMember(person4.getID());

        Set<PersonID> expectedListOfAdmins = new HashSet<>(Arrays.asList(person1.getID(), person2.getID(), person3.getID(), person4.getID()));
        //Act
        Set<PersonID> resultListOfAdmins = group1.getMembers();

        //Assert
        assertEquals(expectedListOfAdmins, resultListOfAdmins);
    }

    /**
     * Test if two Groups are the same
     */

    @Test
    @DisplayName("test if two Groups are the same")
    public void testIfTwoGroupsAreTheSameHashcode() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Talho do Amadeu"), person.getID());
        Group group2 = new Group(new Description("Talho do Amadeu"), person.getID());

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
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Talho do Amadeu"), person.getID());
        Group group2 = new Group(new Description("Talho do João"), person.getID());

        //Act
        int g1 = group1.hashCode();
        int g2 = group2.hashCode();


        //Assert
        assertNotEquals(g1, g2);
    }


    @Test
    void getGroupDescription() {
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group = new Group(new Description("tarzan"), person.getID());
        String expected = "TARZAN";

        //Act
        String result = group.getID().toString();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test method getID from Group ")
    void getID() {
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Gym Buddies"), person.getID());
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
        Group group = new Group(new Description("policias"), person.getID());


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
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group = new Group(new Description("grupo de rafting"), person.getID());

        //Act:
        try {
            group.setGroupID(null);
        }

        //Assert:
        catch (IllegalArgumentException nullDescription) {
            assertEquals("GroupID can't be null", nullDescription.getMessage());
        }
    }
}
