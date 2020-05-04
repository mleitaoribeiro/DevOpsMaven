package switch2019.project.domain.domainEntities.group;

import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.ledger.Periodicity;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.domainEntities.frameworks.Owner;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Person;

import java.time.LocalDateTime;
import java.util.*;

public class Group implements Owner {

    private GroupID groupID;
    private final DateAndTime startingDate;
    private Set<Person> members;
    private Set<Person> admins;
    private Ledger ledger;


    public Group(Description description, Person groupCreator) {
        setGroupID(description);
        startingDate = new DateAndTime();
        members = new HashSet<>();
        admins = new HashSet<>();
        ledger = new Ledger();
        this.addMember(groupCreator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupID, group.groupID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupID, startingDate, members);
    }

    @Override
    public String toString() {
        return groupID.toString();
    }

    /**
     * Method to Set GroupID
     *
     * @param groupID
     */
    public void setGroupID(Description groupID) {
        if (groupID != null) this.groupID = new GroupID(groupID);
        else throw new IllegalArgumentException("GroupID can't be null");
    }

    /**
     * Method to get Group ID
     *
     * @return groupID
     */
    public GroupID getID() {
        return groupID;
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
     * Add a new person to a group
     *
     * @param person
     * @return true if member was added, false if it wasn't
     */
    public boolean addMember(Person person) {
        if (person != null && this.members.isEmpty()) {
            members.add(person);
            return setAdmin(person);
        } else if (person != null)
            return members.add(person);
        else return false;
    }

    /**
     * Set a person directly as group administrator
     *
     * @param person
     * @return true if person was promoted, false if it wasn't
     */
    public boolean setAdmin(Person person) {
        if (person != null && isGroupMember(person.getID()))
            return this.admins.add(person);
        else return false;
    }

    /**
     * Validate if a person is a Group Admin
     *
     * @param isAdmin
     * @return true if is group admin, false if isn't
     */
    public boolean isGroupAdmin(Person isAdmin) {
        if (isAdmin != null)
            return this.admins.contains(isAdmin);
        else return false;
    }

    /**
     * Validate if a personID is a Group Admin
     *
     * @param personID
     * @return true if is group admin, false if isn't
     */
    public boolean isGroupAdmin(PersonID personID) {
        for (Person person : admins)
            if (person.getID().equals(personID))
                return true;
        return false;
    }

    /**
     * Validate if a person is a Group member
     *
     * @param isMember
     * @return boolean
     */
    public boolean isGroupMember(Person isMember) {
        if (isMember != null)
            return this.members.contains(isMember);
        else return false;
    }

    /**
     * Validate if a person is a Group member
     *
     * @param personID
     * @return boolean
     */
    public boolean isGroupMember(PersonID personID) {
        for (Person person : members)
            if (person.getID().equals(personID))
                return true;
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
            if (admins.contains(memberToRemove) && admins.size() > 1)
                return admins.remove(memberToRemove) && members.remove(memberToRemove);
            else if (!admins.contains(memberToRemove) && members.contains(memberToRemove))
                return members.remove(memberToRemove);
            else return false;
        } else return false;
    }

    /**
     * Add multiple members to Group
     *
     * @param newMembers
     * @return true if multiple members were added, false if they weren't
     */
    public boolean addMultipleMembers(Set<Person> newMembers) {
        if (!members.isEmpty())
            for (Person member : newMembers)
                members.add(member);
        return members.containsAll(newMembers);
    }

    /**
     * Get the admins of one group
     * @return
     */
    public Set<PersonID> getAdmins() {
       Set<PersonID> auxAdmins =  new HashSet<>();
        for(Person p: this.admins)
            auxAdmins.add(p.getID());
        return auxAdmins;
    }

    /**
     * Get members of one group
     * @return
     */
    public Set<PersonID> getMembers() {
        Set<PersonID> auxMembers =  new HashSet<>();
        for(Person p: this.members)
            auxMembers.add(p.getID());
        return auxMembers;
    }

    /**
     * Validate if a group is a family
     *
     * @return boolean
     */
    public boolean isFamily() {
        PersonID dadPerson = null;
        PersonID momPerson = null;
        for (Person person : members) {
            for (Person person2 : members) {
                if (!person.equals(person2)) {
                    if (person.isFather(person2.getID()))
                        dadPerson = person2.getID();
                    else if (person.isMother(person2.getID()))
                        momPerson = person2.getID();
                }
            }
        } if (dadPerson == null || momPerson == null) return false;

        for (Person person : members) {
            if (!person.getID().equals(dadPerson) && !person.getID().equals(momPerson) &&
                    (!person.isMother(momPerson) || !person.isFather(dadPerson)))
                return false;
        } return true;
    }

    /**
     * Demote group admin to group member only.
     *
     * @param member
     * @return true if group admin was demoted, false if it wasn't
     */
    public boolean demoteMemberFromAdmin(Person member) {
        if (this.admins.contains(member) && this.admins.size() >= 2)
            return this.admins.remove(member);
        return false;
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
     *
     * @param transactionsToVerify
     * @return
     */
    public boolean areMultipleTransactionsInsideTheGroupLedger(Set<Transaction> transactionsToVerify) {
        for (Transaction transaction : transactionsToVerify) {
            if (transaction == null) {
                throw new IllegalArgumentException("One (or more) of the transactions is null.");
            } else if (!this.ledger.isTransactionInLedger(transaction)) {
                return false;
            }
        }
        return true;
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
     *
     * @return ledger size
     */
    public int ledgerSize() {
        return ledger.getLedgerSize();
    }





}


