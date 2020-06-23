package switch2019.project.domain.domainEntities.group;

import switch2019.project.domain.domainEntities.frameworks.Owner;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Group implements Owner {

    private GroupID groupID;
    private final DateAndTime startingDate;
    private final Set<PersonID> members;
    private Set<PersonID> admins;
    private final PersonID groupCreator;

    public Group(Description description, PersonID groupCreator) {
        setGroupID(description);
        startingDate = new DateAndTime();
        members = new HashSet<>();
        admins = new HashSet<>();
        this.addMember(groupCreator);
        this.groupCreator=groupCreator;
    }

    public Group(Description description, PersonID groupCreator, DateAndTime dateAndTime,
                 Set<PersonID> members, Set<PersonID> admins) {
        setGroupID(description);
        startingDate = dateAndTime;
        this.members = new LinkedHashSet<>(members);
        this.admins = new LinkedHashSet<>(admins);
        this.addMember(groupCreator);
        this.groupCreator=groupCreator;
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

    public void setGroupID(Description groupID) {
        if (groupID != null) this.groupID = new GroupID(groupID);
        else throw new IllegalArgumentException("GroupID can't be null");
    }

    public GroupID getID() {
        return groupID;
    }

    public boolean addMember(PersonID person) {
        if (person != null && this.members.isEmpty()) {
            members.add(person);
            return setAdmin(person);
        } else if (person != null)
            return members.add(person);
        else return false;
    }

    public boolean setAdmin(PersonID person) {
        if (person != null && isGroupMember(person))
            return this.admins.add(person);
        else return false;
    }

    public boolean isGroupAdmin(PersonID personID) {
        for (PersonID person : admins)
            if (person.equals(personID))
                return true;
        return false;
    }

    public boolean isGroupMember(PersonID personID) {
        for (PersonID person : members)
            if (person.equals(personID))
                return true;
        return false;
    }

    public boolean removeMember(PersonID memberToRemove) {
        if (memberToRemove != null) {
            if (admins.contains(memberToRemove) && admins.size() > 1)
                return admins.remove(memberToRemove) && members.remove(memberToRemove);
            else if (!admins.contains(memberToRemove) && members.contains(memberToRemove))
                return members.remove(memberToRemove);
            else return false;
        } else return false;
    }

    public boolean addMultipleMembers(Set<PersonID> newMembers) {
        if (!members.isEmpty())
            members.addAll(newMembers);
        return members.containsAll(newMembers);
    }

    public Set<PersonID> getAdmins() {
       Set<PersonID> auxAdmins =  new HashSet<>();
        auxAdmins.addAll(this.admins);
        return auxAdmins;
    }

    public Set<PersonID> getMembers() {
        Set<PersonID> auxMembers =  new HashSet<>();
        auxMembers.addAll(this.members);
        return auxMembers;
    }

    public boolean demoteMemberFromAdmin(PersonID member) {
        if (this.admins.contains(member) && this.admins.size() >= 2)
            return this.admins.remove(member);
        return false;
    }

    public String getStartingDate(){
        return this.startingDate.yearMonthDayToString();
    }

    public PersonID getGroupCreator() {
        return groupCreator;
    }

}



