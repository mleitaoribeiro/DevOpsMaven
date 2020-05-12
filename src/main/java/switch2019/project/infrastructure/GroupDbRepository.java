package switch2019.project.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.entities.GroupJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.infrastructure.jpa.GroupJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.LinkedHashSet;
import java.util.Set;

@Component("GroupDbRepository")
public class GroupDbRepository implements GroupRepository {

    @Autowired
    GroupJpaRepository groupJpaRepository;

    // Private instance variables
    private Set<Group> groups;

    //String literals should not be duplicated
    private static final String NOT_A_MEMBER = "This person is not a member of this group.";
    private static final String NO_GROUPS_FOUND = "No group found with that description.";

    //Constructor
    public GroupDbRepository() {
        groups = new LinkedHashSet<>();
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

            GroupJpa groupJpa = new GroupJpa(groupDescription.getDescription(), groupCreator.getEmail(),
                    group1.getStartingDate());

            groupJpaRepository.save(groupJpa);

            return group1;
        } else throw new ResourceAlreadyExistsException("This group description already exists.");
    }

    /**
     * method to add group to the Repository
     * @param group
     * @return boolean
     */
    public boolean addGroupToRepository(Group group) {
        if (group != null) {
            return groups.add(group);
        } else return false;
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

    public Set<Group> getAllGroups() {
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

}
