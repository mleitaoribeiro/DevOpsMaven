package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.GroupDomainDataAssembler;
import switch2019.project.dataModel.entities.AdminsJpa;
import switch2019.project.dataModel.entities.GroupJpa;
import switch2019.project.dataModel.entities.MembersJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.infrastructure.jpa.AdminsJpaRepository;
import switch2019.project.infrastructure.jpa.GroupJpaRepository;
import switch2019.project.infrastructure.jpa.MembersJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("GroupDbRepository")
public class GroupDbRepository implements GroupRepository {

    @Autowired
    GroupJpaRepository groupJpaRepository;

    @Autowired
    MembersJpaRepository membersJpaRepository;

    @Autowired
    AdminsJpaRepository adminsJpaRepository;

    //String literals - Exceptions
    private static final String NO_GROUPS_FOUND = "No group found with that description.";
    private static final String NO_GROUPS_FOUND_ID = "No group found with that ID.";
    private static final String GROUP_ALREADY_EXISTS= "This group description already exists.";
    private static final String NOT_A_MEMBER = "This person is not a member of this group.";
    private static final String NOT_AN_ADMIN = "This person is not a admin of this group.";


    //Constructor
    public GroupDbRepository() {
    }

    /**
     * As a user I want to create a group becoming a group administrator(US02.1)
     *
     * @param groupDescription
     * @param groupCreator
     */

    public Group createGroup(Description groupDescription, PersonID groupCreator) {
        Group group = new Group(groupDescription, groupCreator);
        GroupJpa newGroup = groupJpaRepository.save(GroupDomainDataAssembler.toData(group));

        MembersJpa memberJpa = new MembersJpa(newGroup, groupCreator.toString());
        AdminsJpa adminsJpa = new AdminsJpa(newGroup, groupCreator.toString());

        membersJpaRepository.save(memberJpa);
        adminsJpaRepository.save(adminsJpa);

        return GroupDomainDataAssembler.toDomain(newGroup);
    }

    /**
     * Method used to find a specific group by its Description
     *
     * @param groupDescription
     * @return group
     */

    public Group findGroupByDescription(Description groupDescription) {
        Optional<GroupJpa> groupJpa = groupJpaRepository.findById(groupDescription.getDescription());
        if (groupJpa.isPresent()) {
            return new Group(new Description(groupJpa.get().getId()), new PersonID(new Email(groupJpa.get().getGroupCreator())));
        }
        throw new ArgumentNotFoundException(NO_GROUPS_FOUND);
    }

    /**
     * Method to return the group corespondent to the given GroupID
     *
     * @param groupID
     * @return group
     */
    public Group getByID(ID groupID) {
        Optional<GroupJpa> groupJpa = groupJpaRepository.findById(groupID.toString());
        if (groupJpa.isPresent()) {
            return new Group(new Description(groupJpa.get().getId()), new PersonID(new Email(groupJpa.get().getGroupCreator())));
        }
        throw new ArgumentNotFoundException(NO_GROUPS_FOUND);
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

    /**
     * method get All the groups
     *
     * @return List<Group>
     */

    public List<Group> getAllGroups() {
        List<GroupJpa> groupJpa = groupJpaRepository.findAll();
        List<Group> groups = new ArrayList<>();
        for (GroupJpa groupJpa1 : groupJpa) {
            groups.add(getByID(new GroupID(new Description(groupJpa1.getId()))));
        }
        return groups;
    }

    /**
     * Method to check the number of Groups inside the Repository.
     *
     * @return size of the groupsList
     */

    public long repositorySize() {
        return getAllGroups().size();
    }

    /**
     * Method to add a member to a Group
     *
     * @param group
     * @param personID
     * @return
     */
    public boolean addMember(Group group, String personID) {

        GroupJpa groupJpa = GroupDomainDataAssembler.toData(group);

        MembersJpa memberJpa = new MembersJpa(groupJpa, personID);

        membersJpaRepository.save(memberJpa);

        return true;
    }


    /**
     * Method to find all the members of a Group
     *
     * @param id
     * @return
     */
    public List<MembersJpa> findMembersByGroupId(String id) {
        return membersJpaRepository.findAllById_GroupID_Id (id);
    }

    /**
     * Method to add a member to a Group
     *
     * @param group
     * @param personID
     * @return
     */
    public boolean setAdmin(Group group, String personID) {

        GroupJpa groupJpa = GroupDomainDataAssembler.toData(group);

        AdminsJpa adminsJpa = new AdminsJpa(groupJpa, personID);

        adminsJpaRepository.save(adminsJpa);

        return true;
    }

    /**
     * Method to find all the admins of a Group
     *
     * @param id
     * @return
     */
    public List<AdminsJpa> findAdminsByGroupId(String id) {
        return adminsJpaRepository.findAllById_GroupID_Id (id);
    }

}
