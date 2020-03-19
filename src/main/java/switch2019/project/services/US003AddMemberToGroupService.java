package switch2019.project.services;

import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
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
     * @param personID
     * @param groupID
     * @return
     */
    public boolean addMemberToGroup(PersonID personID, GroupID groupID) {

        Person person = personRepository.findPersonByID(personID);
        Group group = groupsRepository.findGroupByID(groupID);
        return group.addMember(person);
    }
}
