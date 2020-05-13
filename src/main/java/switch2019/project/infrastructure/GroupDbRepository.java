package switch2019.project.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.entities.GroupJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.infrastructure.jpa.GroupJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("GroupDbRepository")
public class GroupDbRepository implements GroupRepository {

    @Autowired
    GroupJpaRepository groupJpaRepository;


    //String literals should not be duplicated
    private static final String NO_GROUPS_FOUND = "No group found with that description.";

    //Constructor
    public GroupDbRepository(){}

    /**
     * As a user I want to create a group becoming a group administrator(US02.1)
     *
     * @param groupDescription
     * @param groupCreator
     */
    public Group createGroup(Description groupDescription, PersonID groupCreator) {
        if(!isIDOnRepository(new GroupID(groupDescription))) {

            Group group1 = getByID(new GroupID(groupDescription));

            GroupJpa groupJpa = new GroupJpa(groupDescription.getDescription(), groupCreator.getEmail(),
                    group1.getStartingDate());

            groupJpaRepository.save(groupJpa);

            return group1;
        } else throw new ResourceAlreadyExistsException("This group description already exists.");
    }




    /**
     * Method used to find a specific group by its Description
     * @param groupDescription
     * @return group
     */

    public Group findGroupByDescription(Description groupDescription) {
        Optional<GroupJpa> groupJpa = groupJpaRepository.findById(groupDescription.getDescription());
        if (groupJpa.isPresent()){
            return new Group(new Description(groupJpa.get().getId()), new PersonID(new Email(groupJpa.get().getGroupCreator())));
        } throw new ArgumentNotFoundException("No group found with that ID.");
    }

    /**
     * Method to return the group corespondent to the given GroupID
     * @param groupID
     * @return group
     */
    public Group getByID (ID groupID) {
        Optional<GroupJpa> groupJpa = groupJpaRepository.findById(groupID.toString());
        if (groupJpa.isPresent()){
            return new Group(new Description(groupJpa.get().getId()), new PersonID(new Email(groupJpa.get().getGroupCreator())));
        } throw new ArgumentNotFoundException("No group found with that ID.");
    }

    /**
     * method to validate if the group t is in the groups Repository
     *
     * @param groupID
     * @return boolean
     */

    public boolean isIDOnRepository(ID groupID) {
        Optional<GroupJpa> groupJpa = groupJpaRepository.findById(groupID.toString());
        return groupJpa.isPresent();
    }

    public List<Group> getAllGroups() {
        List <GroupJpa> groupJpa = groupJpaRepository.findAll();
        List <Group> groups = new ArrayList<>();
        for (GroupJpa groupJpa1 : groupJpa){
            groups.add(getByID(new GroupID(new Description(groupJpa1.getId()))));
        }
        return groups;
    }

    /**
     * Method to check the number of Groups inside the Repository.
     *
     * @return size of the groupsList
     */

    public long repositorySize () {
        return getAllGroups().size();
    }

}
