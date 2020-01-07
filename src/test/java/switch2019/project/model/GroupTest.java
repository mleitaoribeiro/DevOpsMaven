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
        Person marta = new Person("Marta", 2000, 10, 10, new Address("Porto"));
        Group groupOsMaisFixes = new Group("OsMaisFixes");

        //Act
        groupOsMaisFixes.addMember(marta);

        //Assert
        assertTrue(groupOsMaisFixes.getMembers().contains(marta));
    }


    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMember_null() {

        //Arrange
        Person nullPerson = null;
        Group groupOsMaisFixes = new Group("OsMaisFixes");

        //Act
        groupOsMaisFixes.addMember(nullPerson);

        //Assert
        assertFalse(groupOsMaisFixes.getMembers().contains(nullPerson));
    }

    /**
     * Test if multiple members were added to Group
     */

    @Test
    @DisplayName("Test if all members were added to Group => Success Case")
    void addMultipleMembers_Success() {
        //Arrange
        Group mNationGroup = new Group("MNation");

        Person personMaria = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person personMariana = new Person("Mariana",1986,12,01, new Address("Lisboa"));
        Person personMarisa = new Person("Marisa",2000,8,27, new Address("Leiria"));

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(personMaria,personMariana,personMarisa));

        //Act
        mNationGroup.addMultipleMembers(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(mNationGroup.getMembers().containsAll(setOfPeopleToAddToGroup));
    }

    @Test
    @DisplayName("Test if the same person is not added twice")
    void addMultipleMembers__ErrorCase() {
        //Arrange
        Group groupMarias = new Group("Maria's Group");

        Person personMaria = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person personSameMaria = new Person("Maria", 1994, 05, 01, new Address("Porto"));

        HashSet<Person> setOfPeopleToAddToGroup= new HashSet<>(Arrays.asList(personMaria,personSameMaria));

        //Act
        groupMarias.addMultipleMembers(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(groupMarias.getMembers().size() ==1);
    }

    @Test
    @DisplayName("Test if a null case is added to group")
    void addMultipleMembers__ErrorCase2() {
        //Arrange
        Group mNationGroup = new Group("Grupo das M'Nation");

        Person personMaria = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person personMariana = null;

        HashSet<Person> setOfPeopleToAddToGroup= new HashSet<>(Arrays.asList(personMaria,personMariana));

        //Act
        mNationGroup.addMultipleMembers(setOfPeopleToAddToGroup);

        //Assert
        assertFalse(mNationGroup.getMembers().contains(personMariana));
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

        Group groupOfJoaoAndElsa = new Group(description);

        Person personJoao = new Person(oneMemberName, oneMemberYear, oneMemberMonth, oneMemberDay, new Address("Paranhos"));
        Person personElsa = new Person(otherMemberName, otherMemberYear, otherMemberMonth, otherMemberDay, new Address("Porto"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(personJoao, personElsa));

        //Act
        groupOfJoaoAndElsa.addMultipleMembers(putMembers);

        groupOfJoaoAndElsa.removeMember(personJoao);

        //Assert
        assertFalse(groupOfJoaoAndElsa.getMembers().contains(personJoao));
    }

    @Test
    @DisplayName("Test if a member was removed from a Group - Remove all members")
    void removeMemberFromGroup_1() {
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

        Group groupOfJoaoAndElsa = new Group(description);

        Person personJoao = new Person(oneMemberName, oneMemberYear, oneMemberMonth, oneMemberDay, new Address("Lisboa"));
        Person personElsa = new Person(otherMemberName, otherMemberYear, otherMemberMonth, otherMemberDay, new Address("Porto"));

        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(personJoao, personElsa));

        //Act
        groupOfJoaoAndElsa.addMultipleMembers(setOfPeopleToAddToGroup);

        groupOfJoaoAndElsa.removeMember(personJoao);
        groupOfJoaoAndElsa.removeMember(personElsa);

        //Assert
        assertEquals(0, groupOfJoaoAndElsa.getMembers().size());
    }

    /**
     * Test if multiple members were removed from a Group
     */

    @Test
    @DisplayName("Test if multiple members were removed from a Group - remove all ")
    void removeMultipleMembersFromAGroup(){
        //Arrange
        Group groupOfPedroAndGabriel=new Group("grupo dos amiguinhos");
        Person personPedro =new Person("Pedro",1999,12,9, new Address("Porto"));
        Person personGabriel=new Person("Gabriel",1996,3,6, new Address("Porto"));
        HashSet<Person>setOfPeopleToAddToGroup=new HashSet<>(Arrays.asList(personPedro, personGabriel));

        //Act
        groupOfPedroAndGabriel.addMultipleMembers(setOfPeopleToAddToGroup);
        groupOfPedroAndGabriel.removeMultipleMembers(setOfPeopleToAddToGroup);

        //Assert
        assertTrue(groupOfPedroAndGabriel.getMembers().size()==0);
    }

    @Test
    @DisplayName("Test if multiple members were removed from a Group - only the members I choose ")
    void removeMultipleMembers() {
        //Arrange
        Group groupOfPedroGabrielLaurinda = new Group("Grupo ainda mais fixe que o outro");
        Person personPedro = new Person("Pedro", 1999, 12, 9, new Address("Porto"));
        Person personGabriel = new Person("Gabriel", 1996, 3, 6, new Address("Porto"));
        Person personLaurinda = new Person("Laurinda", 1998, 3, 14, new Address("Porto"));
        HashSet<Person> setOfPeopleToAddToGroup = new HashSet<>(Arrays.asList(personPedro, personGabriel, personLaurinda));
        HashSet<Person> setOfPeopleToRemoveFromGroup = new HashSet<>(Arrays.asList(personGabriel, personLaurinda));

        //Act
        groupOfPedroGabrielLaurinda.addMultipleMembers(setOfPeopleToAddToGroup);
        groupOfPedroGabrielLaurinda.removeMultipleMembers(setOfPeopleToRemoveFromGroup);

        //Assert
        assertTrue(groupOfPedroGabrielLaurinda.getMembers().size() == 1);
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
        Person personOscar = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person personMarta = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person personJoao = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person personManuela = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person personCarlos = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(personOscar, personMarta, personJoao, personManuela, personCarlos));

        personOscar.setMother(personManuela);
        personOscar.setFather(personCarlos);
        personMarta.setMother(personManuela);
        personMarta.setFather(personCarlos);
        personJoao.setMother(personManuela);
        personJoao.setFather(personCarlos);

        Group family = new Group("Family");

        // Act
        family.addMultipleMembers(familyList);

        // Assert
        assertTrue(family.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - All except one")
    void ifGroupIsFamily_AllFamilyExceptOne() {

        //Arrange
        Person personOscar = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person personMarta = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person personJoao = new Person("Joao", 1990, 10, 10,   new Address("Porto"));
        Person personManuela = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person personCarlos = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        Person personRandom = new Person("Diana", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(personOscar, personMarta, personJoao, personManuela, personCarlos, personRandom));

        personOscar.setMother(personManuela);
        personOscar.setFather(personCarlos);
        personMarta.setMother(personManuela);
        personMarta.setFather(personCarlos);
        personJoao.setMother(personManuela);
        personJoao.setFather(personCarlos);

        Group family = new Group("Family");

        // Act
        family.addMultipleMembers(familyList);

        // Assert
        assertFalse(family.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No one is family")
    void ifGroupIsFamily_NoneFamily() {

        //Arrange
        Person personOscar = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person personMarta = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person personJoao = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person personManuela = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person personCarlos = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(personOscar, personMarta, personJoao, personManuela, personCarlos));

        Group family = new Group("Family");

        // Act
        family.addMultipleMembers(familyList);

        // Assert
        assertFalse(family.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No Mother")
    void ifGroupIsFamily_FamilyButNoMother() {

        //Arrange
        Person personOscar = new Person("Oscar", 1990, 11, 10, new Address("Porto"));
        Person personMarta = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person personJoao = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person personManuela = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person personCarlos = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(personOscar, personMarta, personJoao, personManuela, personCarlos));

        personOscar.setFather(personCarlos);
        personMarta.setFather(personCarlos);
        personJoao.setFather(personCarlos);

        Group family = new Group("Family");

        // Act
        family.addMultipleMembers(familyList);

        // Assert
        assertFalse(family.isFamily());
    }

    /**
     * Test Equals method for the Group class
     */
    @Test
    @DisplayName( "Two group are the same")
    void equalsGroupClass_JustGrouptrue() {

        Group familyGroup =new Group("Familia");
        Group otherFamilyGroup= new Group("Familia");

        //Act
        boolean result= familyGroup .equals(otherFamilyGroup);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_addPersonTrue() {
        //Arrange
        Person personElsa = new Person("Elsa",2000,02,24, new Address("Porto"));
        Person personFilipa = new Person("Filipa",1990,01,05, new Address("Porto"));
        Group family = new Group("Familia");
        Group otherFamily= new Group("Familia");
        HashSet<Person>members= new HashSet<>(Arrays.asList(personElsa,personFilipa));

        //Act
        family.addMultipleMembers(members);
        otherFamily.addMultipleMembers(members);
        boolean result= family.equals(otherFamily);

        //Assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_GroupFalse() {
        //Arrange
        Person personElsa = new Person("Elsa",2000,02,24, new Address("Porto"));
        Person personFilipa = new Person("Filipa",1990,01,05, new Address("Porto"));
        Group family = new Group("Familia");
        Group otherFamily = new Group("Familia");
        HashSet<Person> members= new HashSet<>(Arrays.asList(personElsa,personFilipa));

        //Act
        family.addMultipleMembers(members);
        otherFamily.addMultipleMembers(members);
        boolean result = family.equals(otherFamily);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_addPersonFalse() {
        //Arrange
        Person personElsa = new Person("Elsa",2000,02,24, new Address("Porto"));
        Person personFilipa = new Person("Filipa",1990,01,05, new Address("Porto"));
        Person personPedro = new Person("Pedro",1990,01,05, new Address("Porto"));
        Group family = new Group("Familia fixe");
        Group otherFamily= new Group("Familia mais fixe");
        HashSet<Person> membersFamily = new HashSet<>(Arrays.asList(personFilipa,personElsa));
        HashSet<Person> membersOtherFamily = new HashSet<>(Arrays.asList(personFilipa,personPedro));
        //Act
        family.addMultipleMembers(membersFamily);
        otherFamily.addMultipleMembers(membersOtherFamily);
        boolean result= family.equals(otherFamily);

        //Assert
        assertFalse(result);
    }
}

