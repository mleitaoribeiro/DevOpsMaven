package switch2019.project.controllers;

import org.junit.jupiter.api.Test;
import switch2019.project.model.Group;
import switch2019.project.model.GroupsList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupControllerTest {
    /**
     * Method to check if a Group was created (with HashSet.contains function) - TRUE
     */
    @Test
    public void isGroupInList() {
        // Arrange Groups
        Group group1 = new Group(null, 2019, 12, 19);
        // Arrange GroupList
        GroupsList gl = new GroupsList();

        // Act
        gl.addGroupToGroupList(group1);

        // Assert
        assertTrue(gl.groupListContains(group1));
    }

    /**
     * Method to check if a Group was created inside a GroupList (Compare with Group HashSet)
     */
    @Test
    public void isGroupInListCompare(){
        // Arrange Groups
        Group group1 = new Group(null,2019,12,19);

        // Arrange Group List
        GroupsList gl = new GroupsList();

        // Act
        gl.addGroupToGroupList(group1);
        HashSet<Group> expected = new HashSet<>(Collections.singleton(group1));

        //Assert
        assertEquals(gl.getGroups(), expected);
    }

    /**
     * Method to check if Groups were created inside a GroupList (Compare with Group HashSet)
     */
    @Test
    public void areGroupsInListCompare(){
        // Arrange Groups
        Group group1 = new Group(null,2019,12,19);
        Group group2 = new Group(null, 2019,11,19);

        // Arrange Group List
        GroupsList gl = new GroupsList();

        // Act
        gl.addGroupToGroupList(group1);
        gl.addGroupToGroupList(group2);
        HashSet<Group> expected = new HashSet<>(Arrays.asList(group1,group2));

        // Assert
        assertEquals(gl.getGroups(), expected);
    }
}