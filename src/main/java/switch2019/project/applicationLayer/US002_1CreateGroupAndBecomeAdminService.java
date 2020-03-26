package switch2019.project.applicationLayer;

import switch2019.project.DTO.AdminCreateGroupDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

import java.util.Optional;

public class US002_1CreateGroupAndBecomeAdminService {

    private GroupsRepository groupsRepository;
    private PersonRepository personRepository;

    public US002_1CreateGroupAndBecomeAdminService(GroupsRepository groupsRepository, PersonRepository personRepository) {
        this.groupsRepository = groupsRepository;
        this.personRepository = personRepository;
    }

    /**
     * US002.1
     * I want to create a group and become an Admin
     *
     * @param adminCreateGroupDTO
     * @return groupDTO
     */
    public GroupDTO createGroupAndBecomeAdmin(AdminCreateGroupDTO adminCreateGroupDTO) {

        Person admin = personRepository.findPersonByEmail(new Email(adminCreateGroupDTO.getPersonEmail()));
        Description groupDescription = new Description(adminCreateGroupDTO.getGroupDescription());

        Group groupCreated = groupsRepository.createGroup(groupDescription, admin);

        return GroupDTOAssembler.createGroupDTO(groupCreated.getID());
    }

}
