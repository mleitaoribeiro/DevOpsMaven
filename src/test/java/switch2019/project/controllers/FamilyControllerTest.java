package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.Group;
import switch2019.project.model.GroupsList;
import switch2019.project.model.Person;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FamilyControllerTest {
    public static GroupsList groupsListSetUp() {

        // Global Groups List
        GroupsList globalGroupsList = new GroupsList();

        // 1 _________________________________________________________________________________________________________
        // First global group - All Family
        Person manuelaMOM = new Person("Manuela", 1990, 10, 10);
        Person carlosDAD = new Person("Carlos", 1990, 10, 10);
        Person oscar = new Person("Oscar", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), manuelaMOM, carlosDAD);
        Person marta = new Person("Marta", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), manuelaMOM, carlosDAD);
        Person joao = new Person("Joao", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), manuelaMOM, carlosDAD);

        // Group
        HashSet<Person> familyMembersToAdd = new HashSet<>(Arrays.asList(oscar, marta, joao, manuelaMOM, carlosDAD));
        Group family = new Group("Family", 2019, 1, 1);
        family.addMultipleMembers(familyMembersToAdd);
        globalGroupsList.addGroupToGroupList(family);

        // 2 _________________________________________________________________________________________________________
        // Second global group - All Family 2
        Person homer = new Person("Homer", 1990, 10, 10);
        Person marge = new Person("Marge", 1990, 10, 10);
        Person bart = new Person("Bart", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), marge, homer);
        Person lisa = new Person("Lisa", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), marge, homer);
        Person maggie = new Person("Maggie", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), marge, homer);

        // Group
        HashSet<Person> simpsonsMembersToAdd = new HashSet<>(Arrays.asList(homer, marge, bart, lisa, maggie));
        Group simpsons = new Group("Simpsons", 2019, 1, 1);
        simpsons.addMultipleMembers(simpsonsMembersToAdd);
        globalGroupsList.addGroupToGroupList(simpsons);

        // 3 _________________________________________________________________________________________________________
        // Third global group - No Mom
        Person joaoDAD = new Person("Joao", 1990, 10, 10);
        Person diana = new Person("Diana", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), null, joaoDAD);
        Person elsa = new Person("Elsa", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), null, joaoDAD);
        Person ines = new Person("Ines", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), null, joaoDAD);

        // Group
        HashSet<Person> noMomMembersToAdd = new HashSet<>(Arrays.asList(diana, elsa, ines, joao));
        Group family_with_no_mom = new Group("Family with no Mom", 2019, 1, 1);
        family_with_no_mom.addMultipleMembers(noMomMembersToAdd);
        globalGroupsList.addGroupToGroupList(family_with_no_mom);

        // 4 _________________________________________________________________________________________________________
        // Forth global group - Marta's group
        Person martaR = new Person("Marta Ribeiro", 1990, 10, 10);
        Person martaC = new Person("Marta Cardoso", 1990, 10, 10);
        Person martaP = new Person("Marta Pinheiro", 1990, 10, 10);

        // Group
        HashSet<Person> martasGroupMembersToAdd = new HashSet<>(Arrays.asList(martaR, martaC, martaP));
        Group marta_group = new Group("Marta's group", 2019, 1, 1);
        marta_group.addMultipleMembers(martasGroupMembersToAdd);
        globalGroupsList.addGroupToGroupList(marta_group);

        // 5 _________________________________________________________________________________________________________
        // Fifth global group - Bojack's Gang ( no relationships )
        Person bojack = new Person("Bojack", 1990, 10, 10);
        Person carolyn = new Person("Princess Carolyn", 1990, 10, 10);
        Person todd = new Person("Todd Chavez", 1990, 10, 10);
        Person diane = new Person("Diane Nguyen", 1990, 10, 10);

        // Group
        HashSet<Person> bojackGangMembersToAdd = new HashSet<>(Arrays.asList(bojack, carolyn, todd, diane));
        Group bojack_gang = new Group("Bojack's Gang", 2019, 1, 1);
        bojack_gang.addMultipleMembers(bojackGangMembersToAdd);
        globalGroupsList.addGroupToGroupList(bojack_gang);

        return globalGroupsList;
    }
    public static GroupsList groupsListFamiliesSetUp() {

        // Families Groups List => EXPECT RESULT
        GroupsList familiesGroupsList = new GroupsList();

        // 1 _________________________________________________________________________________________________________
        // First global group - All Family
        Person manuelaMOM = new Person("Manuela", 1990, 10, 10);
        Person carlosDAD = new Person("Carlos", 1990, 10, 10);
        Person oscar = new Person("Oscar", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), manuelaMOM, carlosDAD);
        Person marta = new Person("Marta", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), manuelaMOM, carlosDAD);
        Person joao = new Person("Joao", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), manuelaMOM, carlosDAD);

        // Group
        HashSet<Person> familyMembersToAdd = new HashSet<>(Arrays.asList(oscar, marta, joao, manuelaMOM, carlosDAD));
        Group family = new Group("Family", 2019, 1, 1);
        family.addMultipleMembers(familyMembersToAdd);
        familiesGroupsList.addGroupToGroupList(family);

        // 2 _________________________________________________________________________________________________________
        // Second global group - All Family 2
        Person homer = new Person("Homer", 1990, 10, 10);
        Person marge = new Person("Marge", 1990, 10, 10);
        Person bart = new Person("Bart", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), marge, homer);
        Person lisa = new Person("Lisa", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), marge, homer);
        Person maggie = new Person("Maggie", 1990, 10, 10,
                new Address("Matosinhos", "Porto", "000"), marge, homer);

        // Group
        HashSet<Person> simpsonsMembersToAdd = new HashSet<>(Arrays.asList(homer, marge, bart, lisa, maggie));
        Group simpsons = new Group("Simpsons", 2019, 1, 1);
        simpsons.addMultipleMembers(simpsonsMembersToAdd);
        familiesGroupsList.addGroupToGroupList(simpsons);

        return familiesGroupsList;
    }

    /**
     *  If the list of groups consists exclusively in families.
     */

    @Test
    @DisplayName("Validate if only groups of families are being returned")

    public void ifGroupListIsOnlyFamilies_SuccessCase() {
        //Arrange
        GroupsList newGroupList = groupsListSetUp();
        //Act
        HashSet<Group> realResult = newGroupList.returnOnlyFamilies();
        GroupsList expecteResult = groupsListFamiliesSetUp();
        //Assert
        assertEquals(expecteResult, realResult);
    }

}