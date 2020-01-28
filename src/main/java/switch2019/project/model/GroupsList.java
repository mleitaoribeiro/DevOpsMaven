package switch2019.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GroupsList {
    // Private instance variables
    private Set<Group> groupsList;

    /**
     * Default Constructor for Group List
     */
    public GroupsList() {
        groupsList = new HashSet<Group>();
    }

    /**
     *Develop @override of equals for Group and @override of hashcode
     */

    /**
     * Method used to create a Group
     *
     * @param groupDescription
     * @param groupCreator
     */
    public boolean createGroup(String groupDescription, Person groupCreator) {
        if (groupDescription != null) {
            Group group1 = new Group(groupDescription);
            return (group1.addMember(groupCreator) && this.groupsList.add(group1));
        } return false;
    }

    /**
     * method to add group to the list
     */
    public boolean addGroupToGroupList(Group group1) {
        if (group1 != null) {
            return groupsList.add(group1);
        } else return false;
    }

    /**
     * Method to check the number of Groups inside the list.
     */
    public int howManyGroups() {
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
        } return groupsFamily;
    }

    public boolean createTransactionOnSpecificGroup(String groupDescription, MonetaryValue amount, String transactionDescription,
                                                    LocalDateTime localDate, Category category,
                                                    Account accountFrom, Account accountTo, boolean type){
        Group groupFound = new Group(null);
        for (Group group : groupsList) {
            if (group.getDescription().equalsIgnoreCase(groupDescription))
                groupFound = group;
        } return groupFound.createGroupTransaction(amount, transactionDescription, localDate, category, accountFrom, accountTo, type);
    }

    public ArrayList<Transaction> returnTransactionsFromAllGroupsAPersonIsIn(Person person, LocalDateTime initialDate, LocalDateTime finalDate){
        ArrayList<Transaction> groupTransactions = new ArrayList<>();
        HashSet<Group> groups = new HashSet<>();
        for (Group g : groupsList) {
            if (g.isGroupMember(person))
                groups.add(g);
        } for (Group g : groups) {
            groupTransactions.addAll(g.returnGroupLedgerInDateRange(initialDate, finalDate));
        } return groupTransactions;
    }
}
