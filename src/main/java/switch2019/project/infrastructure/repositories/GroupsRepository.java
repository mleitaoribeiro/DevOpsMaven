package switch2019.project.infrastructure.repositories;

import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.ledger.Periodicity;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class GroupsRepository implements Repository {
    // Private instance variables
    private Set<Group> groups;

    //String literals should not be duplicated
    private static final String NOT_A_MEMBER = "You are not a member of that group.";
    private static final String NO_GROUPS_FOUND = "There're no groups found with that description.";

    //Constructor
    public GroupsRepository() {
        groups = new HashSet<>();
    }

    /**
     * As a user I want to create a group becoming a group administrator(US02.1)
     *
     * @param groupDescription
     * @param groupCreator
     */
    public Group createGroup(Description groupDescription, Person groupCreator) {
        if(!isIDOnRepository(new GroupID(groupDescription))) {
            Group group1 = new Group(groupDescription, groupCreator);
            groups.add(group1);
                return group1;
        } else throw new IllegalArgumentException("This Group Description already exists.");
    }

    /**
     * method to add group to the Repository
     *
     * @param group
     */
    public boolean addGroupToRepository(Group group) {
        if (group != null) {
            return groups.add(group);
        } else return false;
    }

    /**
     * Method to check the number of Groups inside the Repository.
     *
     * @return size of the groupsList
     */
    public int repositorySize () {
        return groups.size();
    }

    /**
     * Method to return Only Families
     *
     * @return groups that are all family
     */
    public Set<Group> returnOnlyFamilies() {
        Set<Group> groupsFamily = new HashSet<>();
        for (Group group : groups) {
            if (group.isFamily()) {
                groupsFamily.add(group);
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
                                                    Account accountFrom, Account accountTo, Type type) {
        for (Group group : groups) {
            if (group.getGroupID().equalsIgnoreCase(groupDescription)) {
                if (group.isGroupMember(person))
                    return group.createGroupTransaction(amount, transactionDescription, localDate, category, accountFrom, accountTo, type);
                else throw new IllegalArgumentException(NOT_A_MEMBER);
            }
        }
        throw new IllegalArgumentException(NO_GROUPS_FOUND);
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
    public boolean createScheduleOnSpecificGroup(Person person, String groupDescription, Periodicity periodicity, MonetaryValue amount, String transactionDescription,
                                                 LocalDateTime localDate, Category category,
                                                 Account accountFrom, Account accountTo, Type type) {
        for (Group group : groups) {
            if (group.getGroupID().equalsIgnoreCase(groupDescription)) {
                if (group.isGroupMember(person))
                    return group.scheduleNewTransaction(periodicity, amount, transactionDescription, localDate, category, accountFrom, accountTo, type);
                else throw new IllegalArgumentException(NOT_A_MEMBER);
            }
        }
        throw new IllegalArgumentException(NO_GROUPS_FOUND);
    }

    /**
     * Method to return the transactions of all the groups a given person is a member on, in a selected date range
     *
     * @param person
     * @param initialDate
     * @param finalDate
     */
    public List<Transaction> returnTransactionsFromAllGroupsAPersonIsIn(Person person, LocalDateTime initialDate, LocalDateTime finalDate) {
        List<Transaction> groupTransactions = new ArrayList<>();
        Set<Group> groups = new HashSet<>();
        for (Group group : this.groups) {
            if (group.isGroupMember(person))
                groups.add(group);
        }
        for (Group group : groups) {
            groupTransactions.addAll(group.returnGroupLedgerInDateRange(initialDate, finalDate, person));
        }
        groupTransactions.sort((transaction1, transaction2) -> transaction2.getDate().compareTo(transaction1.getDate()));
        return groupTransactions;
    }

    /**
     * Method to check if a person is admin on a group
     *
     * @param groupID
     * @param person
     */
    public boolean checkIfAPersonIsAdminInAGivenGroup(GroupID groupID, Person person) {
        for (Group group : groups) {
            if (group.getGroupID().equalsIgnoreCase(groupID.toString()))
                return group.isGroupAdmin(person);
        }
        throw new IllegalArgumentException(NO_GROUPS_FOUND);
    }

    /**
     * Method to check a specific group ledger size
     *
     * @param groupDescription
     */
    public int checkAGroupsLedgerSize(String groupDescription) {
        for (Group group : groups) {
            if (group.getGroupID().equalsIgnoreCase(groupDescription))
                return group.ledgerSize();
        }
        throw new IllegalArgumentException(NO_GROUPS_FOUND);
    }

    /**
     * Method used to find a specific group by its Description
     */
    public Group findGroupByDescription(Description groupDescription) {
        for (Group group : groups) {
            if (group.getID().getDescription().equals(groupDescription.getDescription()))
                return group;
        }
        throw new IllegalArgumentException("No group was found with the given description");
    }

    /**
     * Method to return the group corespondent to the given GroupID
     *
     * @param groupID
     */
    public Group getByID (ID groupID) {
        for (Group group : groups) {
            if (group.getID().equals(groupID))
                return group;
        }
        throw new IllegalArgumentException("No group found with that ID.");
    }

    /**
     * method to validate if the group t is in the groups Repository
     *
     * @param groupID
     * @return boolean
     */

    public boolean isIDOnRepository(ID groupID) {
        for (Group groups : groups)
            if (groups.getID().equals(groupID))
                return true;
        return false;
    }


}
