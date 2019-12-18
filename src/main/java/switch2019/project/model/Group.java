package switch2019.project.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class Group {
    String description;
    LocalDate startingDate;
    HashSet<Person> members; 


    /**
     * Default Person constructor
     * @param description
     * @param startingDateYear
     * @param startingDateMonth
     * @param startingDateDay
     */

    public Group(String description, int startingDateYear, int startingDateMonth, int startingDateDay){
        this.description=description;
        startingDate = startingDate.of(startingDateYear, startingDateMonth,startingDateDay);
        setStartingDate(startingDateYear,startingDateMonth, startingDateDay);

        members = new HashSet<>();
    }
    
    public void setStartingDate(int startingDateYear, int startingDateMonth, int startingDateDay) {
        if(startingDate.of(startingDateYear, startingDateMonth, startingDateDay).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Start Date Not Supported.");
        }
        startingDate = startingDate.of(startingDateYear, startingDateMonth,startingDateDay);
    }

    /**
     * Override of equals for Group
     *
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
     */
    public void addMember(Person member){
        if (member != null)
        members.add(member);
    }
    /**
     * Remove one member from a group
     */
    public void removeMember(Person member){

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
     * Remove multiple Siblings
     * @param toRemove HashSet of members that are going to be removed.
     */
    public void removeMultipleMembers(HashSet<Person> toRemove) {
        for (Person member : toRemove) {
            removeMember(member);
        }
    }
}


