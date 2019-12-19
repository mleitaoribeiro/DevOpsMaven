package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupsListTest {

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

        /**
        * Test if a Group was added to the list
        */

    @Test
    @DisplayName("Test if the group added is in the list")
    void testGroupIsInList(){

        //Arrange
        Group A = new Group("Switchieees",2019,12,19);
        GroupsList groupsList = groupsListSetUp();

        //Act
            groupsList.addGroupToGroupList(A);

        //Assert
        assertEquals(1, 1);
    }

    @Test
    @DisplayName("Test if the group added is in the list")
    public void testGroupIsInList_Not(){
        //Arrange
        Group A = new Group("Switchieees",2019,12,19);
        Group B = new Group(null, 1, 1, 1);
        HashSet<Group> expected = new HashSet<>(Collections.singleton(B));
        GroupsList groupList = new GroupsList();

        //Act
        groupList.addGroupToGroupList(A);

        //Assert
        assertNotEquals(expected , groupList.getGroups());
    }

    @Test
    @DisplayName("Test if the group added is in the list")
    public void testGroupIsInList_MoreThanOne(){
        //Arrange
        Group A = new Group("Switchieees",2019,12,19);
        Group B = new Group(null, 1, 1, 1);
        Group C = new Group(null, 2, 1, 1);
        HashSet<Group> expected = new HashSet<>(Arrays.asList(A, B, C));
        GroupsList groupList = new GroupsList();

        //Act
        groupList.addGroupToGroupList(A);
        groupList.addGroupToGroupList(B);
        groupList.addGroupToGroupList(C);

        //Assert
        assertEquals(expected , groupList.getGroups());
    }


    /**
     * Test if a Group was removed from the list
     */

    /**
     * Test if all groups that are family are retrieved from the list
     */

    /**
     * Test equals method of the GroupList class
     */

}
