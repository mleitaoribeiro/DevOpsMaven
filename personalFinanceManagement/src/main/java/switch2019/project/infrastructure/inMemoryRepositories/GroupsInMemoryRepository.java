package switch2019.project.infrastructure.inMemoryRepositories;


import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupsInMemoryRepository implements GroupRepository {

    // Private instance variables
    private List <Group> groups;

    //String literals should not be duplicated
    private static final String NOT_A_MEMBER = "This person is not a member of this group.";
    private static final String NO_GROUPS_FOUND = "No group found with that description.";

    //Constructor
    public GroupsInMemoryRepository() {
        groups = new ArrayList<>();
    }

    public Group createGroup(Description groupDescription, PersonID groupCreator) {
        if(!isIDOnRepository(new GroupID(groupDescription))) {
            Group group1 = new Group(groupDescription, groupCreator);
            groups.add(group1);
            return group1;
        } else throw new ResourceAlreadyExistsException("This group description already exists.");
    }

    public Group findGroupByDescription(Description groupDescription) {
        for (Group group : groups) {
            if (group.getID().getDescription().equals(groupDescription.getDescription()))
                return group; }
        throw new ArgumentNotFoundException(NO_GROUPS_FOUND);
    }

    public Group getByID (ID groupID) {
        for (Group group : groups) {
            if (group.getID().equals(groupID))
                return group; }
        throw new ArgumentNotFoundException("No group found with that ID.");
    }

    public boolean isIDOnRepository(ID groupID) {
        for (Group groupSet : groups)
            if (groupSet.getID().equals(groupID))
                return true;
        return false;
    }

    public List <Group> getAllGroups() {
        return new ArrayList<>(groups);
    }

    public long repositorySize () {
        return groups.size();
    }

    public boolean addMember(Group group, String personID) {
        return group.addMember(new PersonID(new Email(personID)));
    }

    public boolean setAdmin(Group group, String personID) {
        return group.setAdmin(new PersonID(new Email(personID)));
    }
}
