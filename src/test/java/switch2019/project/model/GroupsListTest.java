package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupsListTest {




    /**
     * Test if a Group was added to the list
     */

    @Test
    @DisplayName("Test if the group added is in the list")
    public void testGroupIsInList(){
    //Arrange
    Group A = new Group("Switchieees",2019,12,19);
    HashSet<Group> expected = new HashSet<>(Collections.singleton(A));
    GroupsList groupList = new GroupsList();

    //Act
    groupList.addGroupToGroupList(A);

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
