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
    public GroupsList(){
        groupsList = new HashSet<Group>();
    }

    /**
     * method to add group to the list
     */
    public void addGroupToGroupList (Group group1) {
        if (! groupsList.contains(group1)) {
            groupsList.add(group1);
        }
    }

    public HashSet<Group> getGroups(){
        HashSet <Group> groupsClone = new HashSet<>(this.groupsList);
        return groupsClone;
    }
    /**
    * Method to return Only Families
    */
    public HashSet<Group> returnOnlyFamilies() {
        return null;
    }
}
