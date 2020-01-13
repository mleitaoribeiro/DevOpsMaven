package switch2019.project.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class Group {

    private String description;
    private LocalDate startingDate;
    private HashSet<Person> members;
    private HashSet<Person> admins;
    private AccountsList groupAccountsList;

    /**
     * Default Group constructor
     * @param description
     */

    public Group (String description){
        setDescription(description);
        startingDate = LocalDate.now();
        members = new HashSet<>();
        admins = new HashSet<>();
        groupAccountsList = new AccountsList();
    }

    /**
     * setter Description
     * @param description
     */
    public void setDescription(String description){
        if (description != null) {
            this.description = description.toUpperCase();
        }
    }

    /**
     * Override of equals for Group
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(description, group.description) &&
                Objects.equals(startingDate, group.startingDate) &&
                Objects.equals(members, group.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, startingDate, members);
    }

    /**
     * Add a new person to a group
     * @param person
     * @return true if member was added, false if it wasn't
     */
    public boolean addMember(Person person){
        if ( this.members.size() != 0 && person != null) {
            return members.add(person);
        }
        else if (person != null) {
            setAdmin(person);
            return true;
        }
        else
            return false;
    }

    /**
     * Remove one member from a group
     * @param memberToRemove
     * @return true if member was removed, false if it wasn't
     */
    public boolean removeMember(Person memberToRemove){
        if (memberToRemove != null) {
            if (admins.contains(memberToRemove) && admins.size() > 1) {
                return admins.remove(memberToRemove) && members.remove(memberToRemove);
            }
            else if (!admins.contains(memberToRemove)) {
               return members.remove(memberToRemove);
            }
            else
                return false;
        }
        else return false;
    }

    /**
     * Add account to Group´s Account List
     * @param account1
     * @return true if account was added to GroupAccountsList, false if it wasn't
     */
    public boolean addAccountToGroupAccountList(Account account1){
        return this.groupAccountsList.addAccountToAccountsList(account1);
    }

    /**
     * Add multiple members to Group
     * @param newMembers
     * @return true if multiple members were added, false if they weren't
     */
    public boolean addMultipleMembers(HashSet<Person>newMembers) {
        for (Person member : newMembers){
            return addMember(member);
        }
        return false;
    }

    /**
     * Remove multiple Members
     * @param toRemove HashSet of members that are going to be removed
     * @return true if multiple members were removed, false if they weren't
     */
    public boolean removeMultipleMembers(HashSet<Person> toRemove) {
        for (Person member : toRemove) {
            return removeMember(member);
        }
        return false;
    }

    /**
     * Validate if a group is a family
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
        } if (dadPerson == null || momPerson == null) return false;

        for (Person person : members) {
            if (!person.equals(dadPerson) && !person.equals(momPerson))
                if(!person.isMother(momPerson) || !person.isFather(dadPerson)) return false;
        } return true;
    }

    /**
     * Setter function to promote a person directly to group administrator
     * @param person
     * @return true if person was promoted, false if it wasn't
     */
    public boolean setAdmin (Person person) {
        if (!members.contains(person) && (!admins.contains(person) && person != null)){
            return members.add(person) && admins.add(person);
        }
        else
            return false;
    }

    /**
     * Promote member to group admin.
     * @param member
     * @return true if person was promoted, false if it wasn't
     */
    public boolean promoteMemberToAdmin(Person member) {
        if (members.contains(member) && member != null) {
            return admins.add(member);
        }
        else return false;
    }

    /**
     * Promote multiple members to group admins
     * @param multipleMembers
     * @return true if person was promoted, false if it wasn't
     */
    public void promoteMultipleMemberToAdmin (HashSet<Person> multipleMembers) {
        for (Person member:multipleMembers){
            promoteMemberToAdmin(member);
        }
    }

    /**
     * Demote group admin to group member only.
     * @param member
     */
    public void demoteMemberFromAdmin(Person member) {
        if (!this.members.contains(member)){
            this.members.remove(member);
        }

    }


    /**
     * Develop method to create a new Account to the group: US7 - As a groupAdmin, I want to create a group account
     * @param accountDenomination
     * @param accountDescription
     */
    public void createGroupAccount (String accountDenomination, String accountDescription) {
        this.addAccountToGroupAccountList(new Account(accountDenomination, accountDescription));
    }
}


