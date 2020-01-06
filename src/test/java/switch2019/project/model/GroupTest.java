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
        Group grupoMaisFixe = new Group("OsMaisFixes");

        //Act
        grupoMaisFixe.addMember(marta);

        //Assert
        assertTrue(grupoMaisFixe.getMembers().contains(marta));
    }


    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMember_null() {

        //Arrange
        Person Marta = null;
        Group osMaisFixes = new Group("OsMaisFixes");

        //Act
        osMaisFixes.addMember(Marta);

        //Assert
        assertFalse(osMaisFixes.getMembers().contains(Marta));
    }

    /**
     * Test if multiple members were added to Group
     */

    @Test
    @DisplayName("Test if all members were added to Group => Success Case")
    void addMultipleMembers_Success() {
        //Arrange
        Group Mnation = new Group("MNation");

        Person Maria = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person Mariana = new Person("Mariana",1986,12,01, new Address("Lisboa"));
        Person Marisa = new Person("Marisa",2000,8,27, new Address("Leiria"));

        HashSet<Person> grupoParaComparar = new HashSet<>(Arrays.asList(Maria,Mariana,Marisa));

        //Act
        Mnation.addMultipleMembers(grupoParaComparar);

        //Assert
        assertTrue(Mnation.getMembers().containsAll(grupoParaComparar));
    }

    @Test
    @DisplayName("Test if the same person is not added twice")
    void addMultipleMembers__ErrorCase() {
        //Arrange
        Group grupoDaMaria = new Group("Maria's Group");

        Person Maria = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person Maria1 = new Person("Maria", 1994, 05, 01, new Address("Porto"));

        HashSet<Person> grupoParaAdicionar= new HashSet<>(Arrays.asList(Maria,Maria1));

        //Act
        grupoDaMaria.addMultipleMembers(grupoParaAdicionar);

        //Assert
        assertTrue(grupoDaMaria.getMembers().size() ==1);
    }

    @Test
    @DisplayName("Test if a null case is added to group")
    void addMultipleMembers__ErrorCase2() {
        //Arrange
        Group mNation = new Group("Grupo das M'Nation");

        Person Maria = new Person("Maria", 1994, 05, 01, new Address("Porto"));
        Person Mariana = null;

        HashSet<Person> grupoTeste= new HashSet<>(Arrays.asList(Maria,Mariana));

        //Act
        mNation.addMultipleMembers(grupoTeste);

        //Assert
        assertFalse(mNation.getMembers().contains(Mariana));
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

        Group grupoDoJoaoEdaElsa = new Group(description);

        Person Joao = new Person(oneMemberName, oneMemberYear, oneMemberMonth, oneMemberDay, new Address("Paranhos"));
        Person Elsa = new Person(otherMemberName, otherMemberYear, otherMemberMonth, otherMemberDay, new Address("Porto"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(Joao, Elsa));

        //Act
        grupoDoJoaoEdaElsa.addMultipleMembers(putMembers);

        grupoDoJoaoEdaElsa.removeMember(Joao);

        //Assert
        assertFalse(grupoDoJoaoEdaElsa.getMembers().contains(Joao));
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

        Group grupoDoJoaoeElsa = new Group(description);

        Person Joao = new Person(oneMemberName, oneMemberYear, oneMemberMonth, oneMemberDay, new Address("Lisboa"));
        Person Elsa = new Person(otherMemberName, otherMemberYear, otherMemberMonth, otherMemberDay, new Address("Porto"));

        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(Joao, Elsa));

        //Act
        grupoDoJoaoeElsa.addMultipleMembers(putMembers);

        grupoDoJoaoeElsa.removeMember(Joao);
        grupoDoJoaoeElsa.removeMember(Elsa);

        //Assert
        assertEquals(0, grupoDoJoaoeElsa.getMembers().size());
    }

    /**
     * Test if multiple members were removed from a Group
     */

    @Test
    @DisplayName("Test if multiple members were removed from a Group - remove all ")
    void removeMultipleMembersFromAGroup(){
        //Arrange
        Group grupoDoPedrodoGabriel=new Group("grupo dos amiguinhos");
        Person Pedro =new Person("Pedro",1999,12,9, new Address("Porto"));
        Person Gabriel=new Person("Gabriel",1996,3,6, new Address("Porto"));
        HashSet<Person>putMembers=new HashSet<>(Arrays.asList(Pedro, Gabriel));

        //Act
        grupoDoPedrodoGabriel.addMultipleMembers(putMembers);
        grupoDoPedrodoGabriel.removeMultipleMembers(putMembers);

        //Assert
        assertTrue(grupoDoPedrodoGabriel.getMembers().size()==0);
    }

    @Test
    @DisplayName("Test if multiple members were removed from a Group - only the members I choose ")
    void removeMultipleMembers() {
        //Arrange
        Group grupoPedroGabrielLaurinda = new Group("Grupo ainda mais fixe que o outro");
        Person Pedro = new Person("Pedro", 1999, 12, 9, new Address("Porto"));
        Person Gabriel = new Person("Gabriel", 1996, 3, 6, new Address("Porto"));
        Person Laurinda = new Person("Laurinda", 1998, 3, 14, new Address("Porto"));
        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(Pedro, Gabriel, Laurinda));
        HashSet<Person> removeSome = new HashSet<>(Arrays.asList(Gabriel, Laurinda));

        //Act
        grupoPedroGabrielLaurinda.addMultipleMembers(putMembers);
        grupoPedroGabrielLaurinda.removeMultipleMembers(removeSome);

        //Assert
        assertTrue(grupoPedroGabrielLaurinda.getMembers().size() == 1);
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
        Person oscar = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person marta = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person joao = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person manuela = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person carlos = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(oscar, marta, joao, manuela, carlos));

        oscar.setMother(manuela);
        oscar.setFather(carlos);
        marta.setMother(manuela);
        marta.setFather(carlos);
        joao.setMother(manuela);
        joao.setFather(carlos);

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
        Person oscar = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person marta = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person joao = new Person("Joao", 1990, 10, 10,   new Address("Porto"));
        Person manuela = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person carlos = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        Person random = new Person("Diana", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(oscar, marta, joao, manuela, carlos, random));

        oscar.setMother(manuela);
        oscar.setFather(carlos);
        marta.setMother(manuela);
        marta.setFather(carlos);
        joao.setMother(manuela);
        joao.setFather(carlos);

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
        Person oscar = new Person("Oscar", 1990, 10, 10, new Address("Porto"));
        Person marta = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person joao = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person manuela = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person carlos = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> famList = new HashSet<>(Arrays.asList(oscar, marta, joao, manuela, carlos));

        Group family = new Group("Family");

        // Act
        family.addMultipleMembers(famList);

        // Assert
        assertFalse(family.isFamily());
    }

    @Test
    @DisplayName("Validate if a group is a family - No Mother")
    void ifGroupIsFamily_FamilyButNoMother() {

        //Arrange
        Person oscar = new Person("Oscar", 1990, 11, 10, new Address("Porto"));
        Person marta = new Person("Marta", 1990, 10, 10, new Address("Porto"));
        Person joao = new Person("Joao", 1990, 10, 10, new Address("Porto"));
        Person manuela = new Person("Manuela", 1990, 10, 10, new Address("Porto"));
        Person carlos = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        HashSet<Person> familyList = new HashSet<>(Arrays.asList(oscar, marta, joao, manuela, carlos));

        oscar.setFather(carlos);
        marta.setFather(carlos);
        joao.setFather(carlos);

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

        Group grupoFamilia=new Group("Familia");
        Group grupoOutraFamilia= new Group("Familia");

        //Act
        boolean result= grupoFamilia.equals(grupoOutraFamilia);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_addPersonTrue() {
        //Arrange
        Person Elsa=new Person("Elsa",2000,02,24, new Address("Porto"));
        Person Filipa=new Person("Filipa",1990,01,05, new Address("Porto"));
        Group Familia=new Group("Familia");
        Group outraFamilia= new Group("Familia");
        HashSet<Person>members= new HashSet<>(Arrays.asList(Elsa,Filipa));

        //Act
        Familia.addMultipleMembers(members);
        outraFamilia.addMultipleMembers(members);
        boolean result= Familia.equals(outraFamilia);

        //Assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_GroupFalse() {
        //Arrange
        Person Elsa=new Person("Elsa",2000,02,24, new Address("Porto"));
        Person Filipa=new Person("Filipa",1990,01,05, new Address("Porto"));
        Group Familia=new Group("Familia");
        Group outraFamilia= new Group("Familia");
        HashSet<Person>members= new HashSet<>(Arrays.asList(Elsa,Filipa));

        //Act
        Familia.addMultipleMembers(members);
        outraFamilia.addMultipleMembers(members);
        boolean result= Familia.equals(outraFamilia);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Two Equals group add person")
    void equalsGroupClass_addPersonFalse() {
        //Arrange
        Person Elsa=new Person("Elsa",2000,02,24, new Address("Porto"));
        Person Filipa=new Person("Filipa",1990,01,05, new Address("Porto"));
        Person Pedro=new Person("Pedro",1990,01,05, new Address("Porto"));
        Group Familia=new Group("Familia fixe");
        Group outraFamilia= new Group("Familia mais fixe");
        HashSet<Person>members= new HashSet<>(Arrays.asList(Filipa,Elsa));
        HashSet<Person>members2= new HashSet<>(Arrays.asList(Filipa,Pedro));
        //Act
        Familia.addMultipleMembers(members);
        outraFamilia.addMultipleMembers(members2);
        boolean result= Familia.equals(outraFamilia);

        //Assert
        assertFalse(result);
    }
}

