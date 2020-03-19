package switch2019.project.services;

import switch2019.project.model.group.Group;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Description;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;


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
     * @param personEmail
     * @param groupDescription
     * @return
     */
    public boolean addMemberToGroup(String personEmail, String groupDescription) {

        Person person = personRepository.findPersonByEmail(new Email(personEmail));
        Group group = groupsRepository.findGroupByDescription(new Description(groupDescription));
        return group.addMember(person);
    }
}
