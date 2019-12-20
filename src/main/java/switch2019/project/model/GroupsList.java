package switch2019.project.model;

import java.util.HashSet;

public class GroupsList {
    // Private instance variables
    private HashSet<Group> groupsList;

    /**
     * Default Constructor for Group List
     */

    public GroupsList(){
        groupsList = new HashSet<Group>();
    }

    /**
     *Develop @override of equals for Group and @override of hashcode
     */


    /**
     * method to add group to the list
     */
    public void addGroupToGroupList (Group group1) {
        if ( group1 != null) {
            if (!groupsList.contains(group1)) {
                groupsList.add(group1);
            }
        }
    }

    public void newGroup(String description, int startingDateYear, int startingDateMonth, int startingDateDay) {
        Group newGroup = new Group(description, startingDateYear, startingDateMonth, startingDateDay);
        addGroupToGroupList(newGroup);
    }

    public HashSet<Group> getGroups(){
        return new HashSet<>(this.groupsList);
    }
    /**
    * Method to return Only Families
    */
    public HashSet<Group> returnOnlyFamilies() {
        HashSet<Group> groupsFamily = new HashSet<>();
        for (Group a : groupsList) {
            if (a.isFamily()) {
                groupsFamily.add(a);
            }
        }
        return groupsFamily;
    }

    /**
     * Method to check if a Group is inside a GroupList:
     */
    public boolean groupListContains (Group g1) {
        if (!this.groupsList.contains(g1)) {
            return false;
        }
        return true;
    }
}
