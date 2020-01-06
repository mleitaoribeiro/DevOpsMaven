package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupsListTest {
    @Test
    @DisplayName("Test if the number of groups on the list was increased")
    public void howManyGroupsTest(){
        //Arrange
        Group group1 = new Group ("Amigos");
        Group group2 = new Group ("Pokemons")
        GroupsList groupList = new GroupsList();

        //Act
        groupList.addGroupToGroupList(group1);
        int result = groupList.howManyGroups();

        //Assert
        assertEquals(2,result);
    }


    @Test
    @DisplayName("Test if the group added is not in the list")
    public void testGroupIsInList_Not() {
        //Arrange
        Group switchieees = new Group("Switchieees");
        Group costura = new Group("Clube da Costura");
        HashSet<Group> expected = new HashSet<>(Collections.singleton(costura));
        GroupsList groupList = new GroupsList();

        //Act
        groupList.addGroupToGroupList(switchieees);

        //Assert
        assertNotEquals(expected, groupList.getGroups());
    }

    @Test
    @DisplayName("Test if more than one group was added is in the list")
    public void testGroupIsInList_MoreThanOne() {
        //Arrange
        Group switchieees = new Group("Switchieees");
        Group costura = new Group("Clube da Costura");
        Group livros = new Group("Clube dos Livros");
        HashSet<Group> expected = new HashSet<>(Arrays.asList( switchieees, costura, livros));
        GroupsList groupList = new GroupsList();

        //Act
        groupList.addGroupToGroupList(switchieees);
        groupList.addGroupToGroupList(costura);
        groupList.addGroupToGroupList(livros);

        //Assert
        assertEquals(expected, groupList.getGroups());
    }
}
