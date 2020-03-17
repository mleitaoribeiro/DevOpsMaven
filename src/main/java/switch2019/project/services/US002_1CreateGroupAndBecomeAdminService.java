package switch2019.project.services;

import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

public class US002_1CreateGroupAndBecomeAdminService {

    private GroupsRepository groupsRepository;
    private PersonRepository personRepository;

    public US002_1CreateGroupAndBecomeAdminService(GroupsRepository groupsRepository, PersonRepository personRepository) {
        this.groupsRepository = groupsRepository;
        this.personRepository = personRepository;
    }

    public boolean createGroupAndBecomeAdmin(Description groupDescription, PersonID personID) {

        if (groupDescription != null && personID != null) {
            Person person = personRepository.findPersonByID(personID);
            return groupsRepository.createGroup(groupDescription.toString(), person);
        } else return false;
    }
}
