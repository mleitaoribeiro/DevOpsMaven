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
            groupsList.add(group1);
        }
    }

    /**
     * method to get Groups inside a GroupList
     * @return groupsList
     */
    public HashSet<Group> getGroupsList(){
        return new HashSet<>(this.groupsList);
    }

    /**
     * Method to check the number of Groups inside the list.
     */
    public int howManyGroups(){
        return this.groupsList.size();
    }

    /**
    * Method to return Only Families
    */
    public HashSet<Group> returnOnlyFamilies() {
        HashSet<Group> groupsFamily = new HashSet<>();
        for (Group g : groupsList) {
            if (g.isFamily()) {
                groupsFamily.add(g);
            }
        }
        return groupsFamily;
    }

    /**
     * Method to check if a Group is inside a GroupList:
     */
    public boolean groupListContains (Group oneGroup) {
        if (!this.groupsList.contains(oneGroup)) {
            return false;
        }
        return true;
    }
}
