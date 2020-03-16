package switch2019.project.model.group;

import switch2019.project.model.frameworks.Owner;
import switch2019.project.model.shared.*;
import switch2019.project.model.ledger.Type;
import switch2019.project.model.ledger.*;
import switch2019.project.repository.CategoryRepository;
import switch2019.project.repository.AccountRepository;
import switch2019.project.model.account.Account;
import switch2019.project.model.category.Category;
import switch2019.project.model.person.Person;

import java.time.LocalDateTime;
import java.util.*;

public class Group implements Owner {

    private GroupID groupID;
    private final DateAndTime startingDate;
    private Set<Person> members;
    private Set<Person> admins;
    private CategoryRepository categoryList;
    private AccountRepository groupAccountsList;
    private Ledger ledger;

    /**
     * Default Group constructor
     *
     * @param description
     */

    public Group(String description) {
        setGroupID(description);
        startingDate = new DateAndTime();
        members = new HashSet<>();
        admins = new HashSet<>();
        groupAccountsList = new AccountRepository();
        categoryList = new CategoryRepository();
        ledger = new Ledger();
    }


    /**
     * Method to Set GroupID
     * @param groupID
     */
    public void setGroupID(String groupID) {
            this.groupID = new GroupID(new Description(groupID));
        }


    /**
     * Override of equals for Group
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupID, group.groupID) &&
                Objects.equals(startingDate, group.startingDate) &&
                Objects.equals(members, group.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupID, startingDate, members);
    }

    /**
     * Method to get Group ID
     * @return groupID
     */

    public GroupID getID() {
        return groupID;
    }

    /**
     * Add a new person to a group
     *
     * @param person
     * @return true if member was added, false if it wasn't
     */
    public boolean addMember(Person person) {
        if (!this.members.isEmpty() && person != null) {
            return members.add(person);
        } else if (person != null) {
            return setAdmin(person);
        } else
            return false;
    }

    /**
     * Remove one member from a group
     *
     * @param memberToRemove
     * @return true if member was removed, false if it wasn't
     */
    public boolean removeMember(Person memberToRemove) {
        if (memberToRemove != null) {
            if (admins.contains(memberToRemove) && members.contains(memberToRemove) && admins.size() > 1) {
                return admins.remove(memberToRemove) && members.remove(memberToRemove);
            } else if (!admins.contains(memberToRemove) && members.contains(memberToRemove)) {
                return members.remove(memberToRemove);
            } else
                return false;
        } else return false;
    }

    /**
     * Add multiple members to Group
     *
     * @param newMembers
     * @return true if multiple members were added, false if they weren't
     */
    public boolean addMultipleMembers(Set<Person> newMembers) {
        if (!members.isEmpty()) {
            for (Person member : newMembers) {
                addMember(member);
            }
            return this.members.containsAll(newMembers);
        } else
            throw new IllegalArgumentException("You cannot add an empty list of members or a non existing person. Please try again.");
    }

    /**
     * Remove multiple Members
     *
     * @param toRemove HashSet of members that are going to be removed
     * @return true if multiple members were removed, false if they weren't
     */
    public boolean removeMultipleMembers(Set<Person> toRemove) {
        for (Person member : toRemove) {
            removeMember(member);
        }
        return !this.members.containsAll(toRemove);

    }

    /**
     * Validate if a group is a family
     *
     * @return boolean
     */
    public boolean isFamily() {
        Person dadPerson = null;
        Person momPerson = null;
        for (Person person : members) {
            for (Person person2 : members) {
                if (!person.equals(person2)) {
                    if (person.isFather(person2))
                        dadPerson = person2;
                    else if (person.isMother(person2))
                        momPerson = person2;
                }
            }
        }
        if (dadPerson == null || momPerson == null) return false;

        for (Person person : members) {
            if (!person.equals(dadPerson) && !person.equals(momPerson) &&
                    (!person.isMother(momPerson) || !person.isFather(dadPerson))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Setter function to promote a person directly to group administrator
     *
     * @param person
     * @return true if person was promoted, false if it wasn't
     */
    public boolean setAdmin(Person person) {
        if (person != null && !this.admins.contains(person)) {
            this.members.add(person);
            this.admins.add(person);
            if (this.admins.contains(person) && this.members.contains(person)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Promote multiple members to group admins
     *
     * @param multipleMembers
     * @return true if person was promoted, false if it wasn't
     */
    public boolean promoteMultipleMemberToAdmin(Set<Person> multipleMembers) {
        for (Person member : multipleMembers) {
            setAdmin(member);
        }
        return admins.containsAll(multipleMembers);
    }

    /**
     * Demote group admin to group member only.
     *
     * @param member
     * @return true if group admin was demoted, false if it wasn't
     */
    public boolean demoteMemberFromAdmin(Person member) {
        if (this.members.contains(member) && this.admins.contains(member) && this.admins.size() >= 2) {
            return this.admins.remove(member);
        }
        return false;
    }

    /**
     * Demote multiple group admins to member only
     *
     * @param multipleMembers
     * @return true if all
     */

    public boolean demoteMultipleMembersFromAdmin(Set<Person> multipleMembers) {
        if (multipleMembers.size() >= this.admins.size()) {
            return false;
        }
        for (Person member : multipleMembers) {
            demoteMemberFromAdmin(member);
        }
        return members.containsAll(multipleMembers) && !admins.containsAll(multipleMembers);
    }

    /**
     * Validate if a person is a Group Admin
     *
     * @param isAdmin
     * @return true if is group admin, false if isn't
     */
    public boolean isGroupAdmin(Person isAdmin) {
        return this.admins.contains(isAdmin) && isAdmin != null;
    }

    /**
     * Validate if a person is a Group Admin
     *
     * @param isMember
     * @return
     */
    public boolean isGroupMember(Person isMember) {
        return this.members.contains(isMember) && isMember != null;
    }

    /**
     * Add account to Group´s Account List
     *
     * @param accountDenomination
     * @param accountDescription
     * @return true if account was added to GroupAccountsList, false if it wasn't
     */
    public boolean addAccountToGroupAccountsList(Denomination accountDenomination, Description accountDescription) {
        return this.groupAccountsList.createAccount(accountDenomination, accountDescription);
    }


    /**
     * Develop method to create a new Account to the group: US7 - As a groupAdmin, I want to create a group account
     *
     * @param accountDenomination
     * @param accountDescription
     * @return true if group account was created, false if it wasn't
     */

    //alterar para depois por o ID
    public boolean createAccount(String accountDenomination, String accountDescription) {
        if (accountDenomination != null && accountDescription != null && this.groupID != null) {
            return this.addAccountToGroupAccountsList(new Denomination(accountDenomination),
                    new Description(accountDescription));
        }
        return false;
    }

    /**
     * As a admin i want to develop method add one category to group's Category List(US005.1)
     *
     * @param nameOfCategory
     * @param categoryCreator
     * @return true if category was added to group's Category List, false if it wasn't
     */

    //alterar para depois por o ID
    public boolean createCategory(String nameOfCategory, Person categoryCreator) {
        if (isGroupAdmin(categoryCreator) && nameOfCategory != null) {
            return categoryList.createCategory(nameOfCategory);
        } else return false;
    }


    /**
     * Remove a category from CategoryList
     *
     * @param nameOfcategory
     */

    public boolean removeCategoryFromList(String nameOfcategory, Person groupAdmin) {
        if (nameOfcategory == null || !this.isGroupAdmin(groupAdmin)) {
            return false;
        }
        return this.categoryList.removeCategoryFromList(nameOfcategory);
    }

    /**
     * Develop method to create a new group transaction (US008.1)
     *
     * @param amount
     * @param description
     * @param localDate
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     * @return true if transaction was created and added to Ledger
     */

    public boolean createGroupTransaction(MonetaryValue amount, String description, LocalDateTime localDate, Category category, Account accountFrom, Account accountTo, Type type) {
        Transaction newGroupTransaction = new Transaction(amount, description, localDate, category, accountFrom, accountTo, type);
        this.ledger.addTransactionToLedger(amount, description, localDate, category, accountFrom, accountTo, type);

        return this.isTransactionInsideTheGroupLedger(newGroupTransaction);
    }


    /**
     * Get the group's ledger transactions in a given period from specific account (US010)
     *
     * @param account1
     * @param initialDate
     * @param finalDate
     * @param person1
     */
    public List<Transaction> getOneAccountTransactionsFromGroup(Account account1, LocalDateTime initialDate, LocalDateTime finalDate, Person person1) {
        if (this.isGroupMember(person1)) {
            List<Transaction> listOfTransactionsFromPeriod = this.ledger.getTransactionsInDateRange(initialDate, finalDate);
            return this.ledger.getTransactionsFromOneAccount(account1, listOfTransactionsFromPeriod);
        } else
            throw new IllegalArgumentException("You don't have access to that account.");
    }


    /**
     * Get the group's ledger transactions in a given period (US012)
     *
     * @param initialDate
     * @param finalDate
     * @param person
     */

    public List<Transaction> returnGroupLedgerInDateRange(LocalDateTime initialDate, LocalDateTime finalDate, Person person) {
        if (isGroupMember(person)) {
            return this.ledger.getTransactionsInDateRange(initialDate, finalDate);
        }

        throw new IllegalArgumentException("Person is not a member of the group.");
    }

    /**
     * Get the balance of the group transactions given a specific date range(US018)
     *
     * @param initialDate
     * @param finalDate
     * @return group balance in a specific date range
     */

    public double getGroupBalanceInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) {
        return ledger.getBalanceInDateRange(initialDate, finalDate);
    }


    /**
     * Method used to check if a transaction is inside a groupLedger
     *
     * @param transaction1
     * @return true if transaction is inside the group ledger
     */
    public boolean isTransactionInsideTheGroupLedger(Transaction transaction1) {
        return this.ledger.isTransactionInLedger(transaction1);
    }

    /**
     * Method used to check if multiple transactions are inside a groupLedger
     * @param transactionsToVerify
     * @return
     */
    public boolean areMultipleTransactionsInsideTheGroupLedger (Set<Transaction> transactionsToVerify) {
        for (Transaction transaction : transactionsToVerify){
            if (transaction == null){
                throw new IllegalArgumentException("One (or more) of the transactions is null.");
            }
            else if (!this.ledger.isTransactionInLedger(transaction)){
                return false;
            }
        } return true;
    }

    /**
     * Method used to get group description
     *
     * @return description
     */
    public String getGroupID() {
        return groupID.getDescription();
    }

    /**
     * Develop method to create a new schedule (USER STORY)
     *
     * @param periodicity
     * @param amount
     * @param description
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public boolean scheduleNewTransaction(Periodicity periodicity, MonetaryValue amount, String description, LocalDateTime date,
                                          Category category, Account accountFrom, Account accountTo, Type type) {
        return ledger.scheduleNewTransaction(periodicity, amount, description, date, category, accountFrom, accountTo, type);
    }

    /**
     * Develop method to return the ledger size
     * @return ledger size
     */
    public int ledgerSize() {
        return ledger.getLedgerSize();
    }


    public String getGroupDescription() {
        return groupID.getDescription();
    }
}


