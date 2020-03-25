package switch2019.project.applicationLayer;

import switch2019.project.DTO.AdminCreateGroupDTO;
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
     * @param dto
     * @return true if the group was created and person is now Admin, false if any of those are false
     */

    public Optional<Group> createGroupAndBecomeAdmin(AdminCreateGroupDTO dto) {
        Description description = new Description(dto.getGroupDescription());
        Person person = personRepository.findPersonByEmail(new Email(dto.getPersonEmail()));

        return Optional.of(groupsRepository.createGroup(description, person));
    }

}
