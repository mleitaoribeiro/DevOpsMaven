package switch2019.project.services;

import switch2019.project.model.person.Email;
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

    /**
     * US002.1
     * I want to create a group and become an Admin
     *
     * @param groupDescriptionString
     * @param personEmail
     * @return true if the group was created and person is now Admin, false if don't
     */

    public boolean createGroupAndBecomeAdmin(String groupDescriptionString, String personEmail) {

        Description groupDescription = new Description(groupDescriptionString);

        Person person = personRepository.findPersonByEmail(new Email (personEmail));

        return groupsRepository.createGroup(groupDescription, person);

    }
    
}
