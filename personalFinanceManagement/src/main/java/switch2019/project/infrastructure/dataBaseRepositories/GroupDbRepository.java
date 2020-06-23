package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.GroupDomainDataAssembler;
import switch2019.project.dataModel.entities.AdminsJpa;
import switch2019.project.dataModel.entities.GroupJpa;
import switch2019.project.dataModel.entities.MembersJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.infrastructure.jpa.AdminsJpaRepository;
import switch2019.project.infrastructure.jpa.GroupJpaRepository;
import switch2019.project.infrastructure.jpa.MembersJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class GroupDbRepository implements GroupRepository {

    @Autowired
    GroupJpaRepository groupJpaRepository;

    @Autowired
    MembersJpaRepository membersJpaRepository;

    @Autowired
    AdminsJpaRepository adminsJpaRepository;

    //String literals - Exceptions
    private static final String NO_GROUPS_FOUND = "No group found with that description.";
    private static final String GROUP_ALREADY_EXISTS = "This group description already exists.";



    //Constructor
    public GroupDbRepository() {
    }

    public Group createGroup(Description groupDescription, PersonID groupCreator) {
        Group group = new Group(groupDescription, groupCreator);
        GroupJpa newGroup = groupJpaRepository.save(GroupDomainDataAssembler.toData(group));

        MembersJpa memberJpa = new MembersJpa(newGroup, groupCreator.toString());
        AdminsJpa adminsJpa = new AdminsJpa(newGroup, groupCreator.toString());

        membersJpaRepository.save(memberJpa);
        adminsJpaRepository.save(adminsJpa);

        return GroupDomainDataAssembler.toDomain(newGroup,
                membersJpaRepository.findAllById_GroupID_Id(newGroup.getId()),
                adminsJpaRepository.findAllById_GroupID_Id(newGroup.getId()));
    }

    public Group findGroupByDescription(Description groupDescription) {
        Optional<GroupJpa> groupJpa = groupJpaRepository.findById(groupDescription.getDescription());
        if (groupJpa.isPresent()) {
            return GroupDomainDataAssembler.toDomain(groupJpa.get(),
                    membersJpaRepository.findAllById_GroupID_Id(groupJpa.get().getId()),
                    adminsJpaRepository.findAllById_GroupID_Id(groupJpa.get().getId()));
        }
        throw new ArgumentNotFoundException(NO_GROUPS_FOUND);
    }

    public Group getByID(ID groupID) {
        Optional<GroupJpa> groupJpa = groupJpaRepository.findById(groupID.toString());
        if (groupJpa.isPresent()) {
            return GroupDomainDataAssembler.toDomain(groupJpa.get(),
                    membersJpaRepository.findAllById_GroupID_Id(groupJpa.get().getId()),
                    adminsJpaRepository.findAllById_GroupID_Id(groupJpa.get().getId()));
        }
        throw new ArgumentNotFoundException(NO_GROUPS_FOUND);
    }

    public boolean isIDOnRepository(ID groupID) {
        Optional<GroupJpa> groupJpa = groupJpaRepository.findById(groupID.toString());
        return groupJpa.isPresent();
    }

    public List<Group> getAllGroups() {
        List<GroupJpa> groupsJpa = groupJpaRepository.findAll();
        List<Group> groups = new ArrayList<>();
        for (GroupJpa groupJpa : groupsJpa) {
            groups.add(GroupDomainDataAssembler.toDomain(groupJpa,
                    membersJpaRepository.findAllById_GroupID_Id(groupJpa.getId()),
                    adminsJpaRepository.findAllById_GroupID_Id(groupJpa.getId())));
        }
        return groups;
    }

    public long repositorySize() {
        return getAllGroups().size();
    }

    public boolean addMember(Group group, String personID) {
        List<MembersJpa> membersJpasList = membersJpaRepository.findAllById_GroupID_Id(group.toString());

        GroupJpa groupJpa = GroupDomainDataAssembler.toData(group);
        MembersJpa memberJpa = new MembersJpa(groupJpa, personID);

        if (personID != null && !membersJpasList.contains(memberJpa)) {
            membersJpaRepository.save(memberJpa);
            groupJpa.addMember(memberJpa.getPersonID());
            return true;
        } return false;
    }

    public boolean setAdmin(Group group, String personID) {
        List<MembersJpa> membersJpasList = membersJpaRepository.findAllById_GroupID_Id(group.toString());
        List<AdminsJpa> adminsJpasList = adminsJpaRepository.findAllById_GroupID_Id(group.toString());

        GroupJpa groupJpa = GroupDomainDataAssembler.toData(group);
        MembersJpa memberJpa = new MembersJpa(groupJpa, personID);
        AdminsJpa adminsJpa = new AdminsJpa(groupJpa, personID);

        if(membersJpasList.contains(memberJpa) && !adminsJpasList.contains(adminsJpa)) {
            adminsJpaRepository.save(adminsJpa);
            groupJpa.addAdmin(personID);
            return true;
        }
        return false;
    }

}
