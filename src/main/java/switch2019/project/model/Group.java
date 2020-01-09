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
     * Add a new member to a group
     * @param member
     */
    public void addMember(Person member){
        if (member != null)
        members.add(member);
    }

    /**
     * Remove one member from a group
     * @param memberToRemove
     */
    public void removeMember(Person memberToRemove){
        if (memberToRemove != null)
        members.remove(memberToRemove);
    }

    /**
     * Getter function for the group members
     * @return membersClone;
     */
    public HashSet<Person> getMembers () {
        return new HashSet<>(this.members);
    }

    /**
     * Getter function for the group admins
     * @return adminsClone
     */
    public HashSet<Person> getAdmins() { return new HashSet<>(this.admins);}

    /**
     * Group Account List Getter
     */
    public AccountsList getGroupAccountsList() {
        AccountsList groupAccounts = this.groupAccountsList;
        return groupAccounts;
    }

    /**
     * Add multiple members to Group
     * @param: newMembers;
     */
    public void addMultipleMembers(HashSet<Person>newMembers) {
        for (Person member : newMembers){
            addMember(member);
        }
    }

    /**
     * Remove multiple Members
     * @param toRemove HashSet of members that are going to be removed.
     */
    public void removeMultipleMembers(HashSet<Person> toRemove) {
        for (Person member : toRemove) {
            removeMember(member);
        }
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
     *@param person
     */
    public void setAdmin (Person person) {
    }

    /**
     * Promote member to group admin.
     * @param member
     */
    public void promoteMemberToAdmin(Person member) {
        if (members.contains(member) && member != null) {
            admins.add(member);
        }
    }

    /**
     * Demote group admin to group member only.
     * @param member
     */
    public void demoteMemberFromAdmin(Person member) {
    }

    /**
     * Promote multiple members to group admins
     * @param multipleMembers
     */

    public void promoteMultipleMemberToAdmin (HashSet<Person> multipleMembers) {
    }

}


