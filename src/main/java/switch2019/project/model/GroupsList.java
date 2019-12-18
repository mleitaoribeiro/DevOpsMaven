package switch2019.project.model;

import java.util.HashSet;

public class GroupsList {
    // Private instance variables
    private HashSet<Group> groupsList;

    /**
     * Default Constructor for Group List
     */


    /**
     *Develop @override of equals for Group and @override of hashcode
     */


    /**
     * method to add group to the list
     */
    public void addGroupToGroupList (Group group1) {
        if (! groupsList.contains(group1)) {
            groupsList.add(group1);
        }
    }



}
