package switch2019.project.services;


import switch2019.project.model.group.Group;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.GroupID;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

public class US007CreateGroupAccountService {

    public boolean createGroupAccount (PersonRepository personRepository, GroupsRepository groupsRepository,
                                       AccountRepository accountRepository, Email personEmail, String groupDescription,
                                       Denomination accountDenomination, Description accountDescription ) {

        Person onePerson = personRepository.findPersonByEmail(personEmail);

        Group oneGroup = groupsRepository.findGroupByDescription(groupDescription);
        GroupID oneGroupID = oneGroup.getID();

        boolean personIsGroupAdmin = oneGroup.isGroupAdmin(onePerson);

        if (personIsGroupAdmin) {
            return accountRepository.createAccount(accountDenomination,accountDescription);
        }
        return false;
    }

}