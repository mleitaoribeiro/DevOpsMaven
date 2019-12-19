package switch2019.project.controllers;

import org.junit.jupiter.api.Test;
import switch2019.project.model.Group;
import switch2019.project.model.GroupsList;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupControllerTest {

    /**
     * Method to check if a Group exists
     */

    @Test
    public void isGroupInList(){
        // Arrange Groups
        Group g1 = new Group(null,2019,12,19);

        //Arrange Group List
        GroupsList gl = new GroupsList();

        //Act
        gl.addGroupToGroupList(g1);
        HashSet<Group> expected = new HashSet<>(Arrays.asList(g1));

        //Assert
        assertEquals(gl.getGroups(), expected);
    }

    @Test
    public void areGroupsInList(){
        // Arrange Groups
        Group g1 = new Group(null,2019,12,19);
        Group g2 = new Group(null, 2019,11,19);

        //Arrange Group List
        GroupsList gl = new GroupsList();

        //Act
        gl.addGroupToGroupList(g1);
        gl.addGroupToGroupList(g2);
        HashSet<Group> expected = new HashSet<>(Arrays.asList(g1,g2));

        //Assert
        assertEquals(gl.getGroups(), expected);
    }




}