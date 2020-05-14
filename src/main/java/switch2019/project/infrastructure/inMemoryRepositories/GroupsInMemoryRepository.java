package switch2019.project.infrastructure.inMemoryRepositories;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.GroupRepository;
import java.util.*;

@Primary
@Component("GroupInMemoryRepository")
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

    /**
     * As a user I want to create a group becoming a group administrator(US02.1)
     *
     * @param groupDescription
     * @param groupCreator
     */
    public Group createGroup(Description groupDescription, PersonID groupCreator) {
        if(!isIDOnRepository(new GroupID(groupDescription))) {
            Group group1 = new Group(groupDescription, groupCreator);
            groups.add(group1);
            return group1;
        } else throw new ResourceAlreadyExistsException("This group description already exists.");
    }


    /**
     * Method used to find a specific group by its Description
     * @param groupDescription
     * @return group
     */

    public Group findGroupByDescription(Description groupDescription) {
        for (Group group : groups) {
            if (group.getID().getDescription().equals(groupDescription.getDescription()))
                return group;
        }
        throw new ArgumentNotFoundException(NO_GROUPS_FOUND);
    }

    /**
     * Method to return the group corespondent to the given GroupID
     * @param groupID
     * @return group
     */
    public Group getByID (ID groupID) {
        for (Group group : groups) {
            if (group.getID().equals(groupID))
                return group;
        }
        throw new ArgumentNotFoundException("No group found with that ID.");
    }

    /**
     * method to validate if the group t is in the groups Repository
     *
     * @param groupID
     * @return boolean
     */

    public boolean isIDOnRepository(ID groupID) {
        for (Group groupSet : groups)
            if (groupSet.getID().equals(groupID))
                return true;
        return false;
    }

    public List <Group> getAllGroups() {
        return groups;
    }

    /**
     * Method to check the number of Groups inside the Repository.
     *
     * @return size of the groupsList
     */

    public long repositorySize () {
        return groups.size();
    }

    /**
     * Method to add a member to a Group
     *
     * @param group
     * @param personID
     * @return
     */
    public boolean addMember(Group group, String personID) {
        return true;
    }

    /**
     * Method to add an admin to a Group
     *
     * @param group
     * @param personID
     * @return
     */
    public boolean setAdmin(Group group, String personID) {
        return true;
    }

}
