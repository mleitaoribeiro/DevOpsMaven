package switch2019.project.applicationLayer;

import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

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
