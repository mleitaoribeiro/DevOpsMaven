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
        if ( group1 != null) {
            if (!groupsList.contains(group1)) {
                groupsList.add(group1);
            }
        }
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
}
