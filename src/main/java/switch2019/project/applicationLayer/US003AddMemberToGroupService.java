package switch2019.project.applicationLayer;

import switch2019.project.DTO.AddMemberDTO;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;


public class US003AddMemberToGroupService {

    private PersonRepository personRepository;
    private GroupsRepository groupsRepository;

    public US003AddMemberToGroupService(PersonRepository personRepository, GroupsRepository groupsRepository) {
        this.personRepository = personRepository;
        this.groupsRepository = groupsRepository;
    }

    /**
     * US003
     * Add Member To Group
     *
     * @param addMemberDTO
     * @return boolean
     */
    public boolean addMemberToGroup(AddMemberDTO addMemberDTO) {
        Person person = personRepository.findPersonByEmail(new Email(addMemberDTO.getPersonEmail()));
        Group group = groupsRepository.findGroupByDescription(new Description(addMemberDTO.getGroupDescription()));
        return group.addMember(person);
    }
}
