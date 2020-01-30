package switch2019.project.model;

import java.time.LocalDateTime;
import java.util.*;

public class GroupsList {
    // Private instance variables
    private Set<Group> groupsList;

    String action = "You are not a member of that group.";
    String action2 = "There're no groups found with that description.";

    /**
     * Default Constructor for Group List
     */
    public GroupsList() {
        groupsList = new HashSet<>();
    }

    /**
     * As a user I want to create a group becoming a group administrator(US02.1)
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
     * @param group
     */
    public boolean addGroupToGroupList(Group group) {
        if (group != null) {
            return groupsList.add(group);
        } else return false;
    }

    /**
     * Method to check the number of Groups inside the list.
     *
     * @return size of the groupsList
     */
    public int howManyGroups() {
        return groupsList.size();
    }

    /**
     * Method to return Only Families
     *
     * @return groups that are all family
     */
    public Set<Group> returnOnlyFamilies() {
        Set<Group> groupsFamily = new HashSet<>();
        for (Group g : groupsList) {
            if (g.isFamily()) {
                groupsFamily.add(g);
            }
        }
        return groupsFamily;
    }

    /**
     * Method to create a transaction on a specific group
     *
     * @param groupDescription
     * @param amount
     * @param transactionDescription
     * @param localDate
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */
    public boolean createTransactionOnSpecificGroup(Person person, String groupDescription,
                                                    MonetaryValue amount, String transactionDescription,
                                                    LocalDateTime localDate, Category category,
                                                    Account accountFrom, Account accountTo, boolean type) {
        for (Group group : groupsList) {
            if (group.getDescription().equalsIgnoreCase(groupDescription)) {
                if (group.isGroupMember(person))
                    return group.createGroupTransaction(amount, transactionDescription, localDate, category, accountFrom, accountTo, type);
                else throw new IllegalArgumentException(action);
            }
        }
        throw new IllegalArgumentException(action2);
    }

    /**
     * Method to create a transaction on a specific group
     *
     * @param groupDescription
     * @param amount
     * @param transactionDescription
     * @param localDate
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */
    public boolean createScheduleOnSpecificGroup(Person person, String groupDescription, String periodicity, MonetaryValue amount, String transactionDescription,
                                                    LocalDateTime localDate, Category category,
                                                    Account accountFrom, Account accountTo, boolean type){
        for (Group group : groupsList) {
            if (group.getDescription().equalsIgnoreCase(groupDescription)){
                if(group.isGroupMember(person))
                    return group.scheduleNewTransaction(periodicity, amount, transactionDescription, localDate, category, accountFrom, accountTo, type);
                else throw new IllegalArgumentException(action);
            }
        } throw new IllegalArgumentException(action2);
    }

    /**
     * Method to return the transactions of all the groups a given person is a member on, in a selected date range
     *
     * @param person
     * @param initialDate
     * @param finalDate
     */
    public ArrayList<Transaction> returnTransactionsFromAllGroupsAPersonIsIn(Person person, LocalDateTime initialDate, LocalDateTime finalDate){
        ArrayList<Transaction> groupTransactions = new ArrayList<>();
        Set<Group> groups = new HashSet<>();
        for (Group group : groupsList) {
            if (group.isGroupMember(person))
                groups.add(group);
        } for (Group group : groups) {
            groupTransactions.addAll(group.returnGroupLedgerInDateRange(initialDate, finalDate, person));
        }
        return groupTransactions;
    }

    /**
     * Method to check if a person is admin on a group
     *
     * @param groupDescription
     * @param person
     */
    public boolean checkIfAPersonIsAdminInAGivenGroup(String groupDescription, Person person) {
        for (Group group : groupsList) {
            if (group.getDescription().equalsIgnoreCase(groupDescription))
                return group.isGroupAdmin(person);
        }
        throw new IllegalArgumentException(action2);
    }

    /**
     * Method to check a specific group ledger size
     *
     * @param groupDescription
     */
    public int checkIfAGroupsLedgerSize(String groupDescription) {
        for (Group group : groupsList) {
            if (group.getDescription().equalsIgnoreCase(groupDescription))
                return group.ledgerSize();
        }
        throw new IllegalArgumentException(action2);
    }
}
