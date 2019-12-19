package switch2019.project.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class Group {
    String description;
    LocalDate startingDate;
    HashSet<Person> members;

    /**
     * Default Group constructor
     * @param description
     * @param startingDateYear
     * @param startingDateMonth
     * @param startingDateDay
     */

    public Group(String description, int startingDateYear, int startingDateMonth, int startingDateDay){
        this.description=description.toUpperCase();
        startingDate = startingDate.of(startingDateYear, startingDateMonth,startingDateDay);
        setStartingDate(startingDateYear,startingDateMonth, startingDateDay);

        members = new HashSet<>();
    }

    /**
     * Input Validation for Starting Date of Group
     * @param startingDateYear
     * @param startingDateMonth
     * @param startingDateDay
     */

    public void setStartingDate(int startingDateYear, int startingDateMonth, int startingDateDay) {
        if(startingDate.of(startingDateYear, startingDateMonth, startingDateDay).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Start Date Not Supported.");
        }
        startingDate = startingDate.of(startingDateYear, startingDateMonth,startingDateDay);
    }
    /**
     * setter Description
     * @param description
     */

    public void setDescription(String description){
        this.description = description.toUpperCase(); 
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
        HashSet <Person> membersClone = new HashSet<>(this.members);
        return membersClone;
    }

    /**
     * @param: newMembers;
     * Add multiple members to Group
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
}


